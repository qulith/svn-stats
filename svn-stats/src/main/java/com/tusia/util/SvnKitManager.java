package com.tusia.util;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SvnKitManager {
	
	Logger log = Logger.getLogger(SvnKitManager.class);
	
	SVNClientManager svnClientManager;
	
	SVNURL repositoryURL;
	
	SVNRepository repository;
	
	public SvnKitManager()
	{
		DefaultSVNOptions options = new DefaultSVNOptions();
		
		svnClientManager = SVNClientManager.newInstance(options, "ttusia", "pearljam");
		
	    try {
	           repositoryURL = SVNURL.parseURIDecoded("http://aimsweb.jira.com/svn/AIMSWEB/development/trunk" );
	           svnClientManager.createRepository(repositoryURL, true);
	    } catch ( SVNException e ) {
	    	log.error("FAILED TO INIT SVN URL");
	        e.printStackTrace();
	    }
	    
	    
	    
//	    try { 
//	        repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
//	        ISVNAuthenticationManager authManager = 
//	                     SVNWCUtil.createDefaultAuthenticationManager("ttusia", "pearljam");
//	        repository.setAuthenticationManager(authManager);
//	    } catch (SVNException e){
//	    	log.error("FAILED TO INIT SVN REPO: ");
//	        e.printStackTrace();
//	    }
	    
	    
	}
	
	public SVNClientManager getSvnClientManager()
	{
		return svnClientManager;
	}
	
	public SVNURL getSvnUrl()
	{
		return repositoryURL;
	}
	
	public SVNRepository getRepo()
	{
		return repository;
	}
}
