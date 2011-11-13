package com.tusia.service;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.ISVNDirEntryHandler;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.auth.SVNAuthentication;
import org.tmatesoft.svn.core.auth.SVNPasswordAuthentication;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNConnection;
import org.tmatesoft.svn.core.internal.util.SVNURLUtil;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.wc.xml.SVNXMLDirEntryHandler;
import org.tmatesoft.svn.core.wc.xml.SVNXMLLogHandler;
import org.xml.sax.helpers.DefaultHandler;

import com.tusia.dto.PieChartCommits;
import com.tusia.dto.UserSvnStat;
import com.tusia.util.SvnDirHandler;
import com.tusia.util.SvnKitManager;
import com.tusia.util.SvnLogHandler;

@Service("testService")
public class TestService {
	
	Logger log = Logger.getLogger(TestService.class);
	
	@Autowired
	SvnKitManager svnKitManager;
	
	@SuppressWarnings(value = { "unchecked" })
	public Collection<UserSvnStat> getUserStats() throws SVNException
	{
		
		DAVRepositoryFactory.setup( );
		String url = "http://aimsweb.jira.com/svn/AIMSWEB/development/trunk";
		String name = "ttusia";
		String password = "pearljam";
		long startRevision = 0;
		long endRevision = -1; //HEAD (the latest) revision
		
		SVNRepository repository = null;
		
		repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( url ) );
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( name, password );
		repository.setAuthenticationManager( authManager );
		Collection<SVNLogEntry> logEntries = null;
		
		logEntries = repository.log( new String[] { "" } , null , startRevision , endRevision , true , true );
		
		
		Map<String,UserSvnStat> stats = new LinkedHashMap<String, UserSvnStat>();
		
		for(SVNLogEntry logEntry : logEntries)
		{
			UserSvnStat userSvnStat = stats.get(logEntry.getAuthor());
			if(userSvnStat == null)
			{
				userSvnStat = new UserSvnStat();
				stats.put(logEntry.getAuthor(), userSvnStat);
				userSvnStat.setUserName(logEntry.getAuthor());
			}
			userSvnStat.setNumberCommits(userSvnStat.getNumberCommits()+1);
			
			
			if(logEntry.getMessage() != null && !logEntry.getMessage().equals(""))
				userSvnStat.setNumberComments(userSvnStat.getNumberComments() +1);
			
			if(userSvnStat.getLastCommitDate() == null)
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(logEntry.getDate());
				userSvnStat.setLastCommitDate(cal);
			}
			else if(userSvnStat.getLastCommitDate().before(logEntry.getDate()))
			{
				userSvnStat.getLastCommitDate().setTime(logEntry.getDate());
			}
			
			if(userSvnStat.getFirstCommitDate() == null)
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(logEntry.getDate());
				userSvnStat.setFirstCommitDate(cal);
			}
			else if(userSvnStat.getFirstCommitDate().after(logEntry.getDate()))
			{
				userSvnStat.getFirstCommitDate().setTime(logEntry.getDate());
			}
				
		}
		
		
		return stats.values(); 
		
	}
	
	
	public JSONArray getPieChartCommits() throws SVNException
	{
	
		JSONArray retVal = new JSONArray();
		DAVRepositoryFactory.setup( );
		String url = "http://aimsweb.jira.com/svn/AIMSWEB/development/trunk";
		String name = "ttusia";
		String password = "pearljam";
		long startRevision = 0;
		long endRevision = -1; //HEAD (the latest) revision
		
		SVNRepository repository = null;
		
		repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( url ) );
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( name, password );
		repository.setAuthenticationManager( authManager );
		Collection<SVNLogEntry> logEntries = null;
		
		logEntries = repository.log( new String[] { "" } , null , startRevision , endRevision , true , true );
		
		
		Map<String,PieChartCommits> stats = new LinkedHashMap<String, PieChartCommits>();
		
		int totalCommits = 0;
		
		for(SVNLogEntry logEntry : logEntries)
		{
			totalCommits++;
			PieChartCommits pieSlice = stats.get(logEntry.getAuthor());
			if(pieSlice == null)
			{
				pieSlice = new PieChartCommits();
				stats.put(logEntry.getAuthor(), pieSlice);
				pieSlice.setName(logEntry.getAuthor());
			}
			pieSlice.setY(pieSlice.getY()+1);
			
				
		}
		
		for (PieChartCommits pcc : stats.values())
		{
			retVal.add(pcc.getJson());
		}
		
		return retVal;
	}
}
