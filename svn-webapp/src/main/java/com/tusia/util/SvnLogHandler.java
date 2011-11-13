package com.tusia.util;

import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;

public class SvnLogHandler implements ISVNLogEntryHandler {

	Integer countNonComment = 0;
	Integer countComment = 0;

	public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {
		if(logEntry.getMessage() == null)
			countNonComment++;
		else
			countComment++;

	}
	
	public Integer getCountNonComment()
	{
		return countNonComment;
	}
	
	public Integer getCountComment()
	{
		return countComment;
	}

}
