package com.tusia.util;

import org.tmatesoft.svn.core.ISVNDirEntryHandler;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;

public class SvnDirHandler implements ISVNDirEntryHandler {
	
	Integer countNonComment = 0;
	Integer countComment = 0;

	public void handleDirEntry(SVNDirEntry dirEntry) throws SVNException {
		if(dirEntry.getCommitMessage() == null)
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
