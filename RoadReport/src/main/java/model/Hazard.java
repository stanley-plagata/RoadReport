package model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Hazard {

	private String address;
	private String hazardType;
	private int severity;
	private double lat;
	private double lng;
	private int hazardID;
	private int reportID;
	private int voteCount;
	private Date estimatedDateOfCompletion;
	private String resolutionStatus;
	private boolean check;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date reportDate;
	

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public void setResolutionStatus(String resolutionStatus) {
		this.resolutionStatus = resolutionStatus;
	}

	public int getVoteCount() {
		return voteCount;
	}
	
	public Date getReportDate() {
		return reportDate;
	}

	public String getResolutionStatus() {
		return resolutionStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHazardType() {
		return hazardType;
	}

	public void setHazardType(String hazardType) {
		this.hazardType = hazardType;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getHazardID() {
		return hazardID;
	}

	public void setHazardID(int hazardID) {
		this.hazardID = hazardID;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public Date getEstimatedDateOfCompletion() {
		return estimatedDateOfCompletion;
	}

	public void setEstimatedDateOfCompletion(Date estimatedDateOfCompletion) {
		this.estimatedDateOfCompletion = estimatedDateOfCompletion;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	

}
