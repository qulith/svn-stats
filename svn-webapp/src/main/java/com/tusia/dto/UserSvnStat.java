package com.tusia.dto;

import java.util.Calendar;

public class UserSvnStat {
	String userName;
	int numberCommits = 0;
	int numberComments = 0;
	Calendar firstCommitDate = null;
	Calendar lastCommitDate = null;
	
	public String getName()
	{
		return userName;
	}
	
	public int getY()
	{
		return numberCommits;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSvnStat other = (UserSvnStat) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getNumberCommits() {
		return numberCommits;
	}
	public void setNumberCommits(int numberCommits) {
		this.numberCommits = numberCommits;
	}
	public int getNumberComments() {
		return numberComments;
	}
	public void setNumberComments(int numberComments) {
		this.numberComments = numberComments;
	}
	public Calendar getFirstCommitDate() {
		return firstCommitDate;
	}
	public void setFirstCommitDate(Calendar firstCommitDate) {
		this.firstCommitDate = firstCommitDate;
	}
	public Calendar getLastCommitDate() {
		return lastCommitDate;
	}
	public void setLastCommitDate(Calendar lastCommitDate) {
		this.lastCommitDate = lastCommitDate;
	}
	
	
}
