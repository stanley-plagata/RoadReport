package model;

public class Vote {
	
	private String username;
	
	private int reportID;
	
	private String previous;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getReportID() {
		return reportID;
	}
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	
	
}
