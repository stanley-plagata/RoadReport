package model;

import java.sql.Date;

public class Report {
	
	private int reportID;
	
	private int severity;
	
	private int voteCount;
	
	private Date reportDate;
	
	private String resolutionStatus;

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getResolutionStatus() {
		return resolutionStatus;
	}

	public void setResolutionStatus(String resolutionStatus) {
		this.resolutionStatus = resolutionStatus;
	}
	
	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	
}
