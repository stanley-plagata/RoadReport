package service;

import java.util.List;

import model.Hazard;
import model.Report;
import model.Vote;

public interface HazardService {

	void insertHazard(Hazard hazard, boolean construction, String username); 
	
	List<Hazard> getHazards();
	
	Hazard getSpecificHazard(String address, int reportID, int hazardID);
	
	int getVoteCount(int reportID);
	
	void updateVoteCount(int reportID, boolean increment, int number);
	
	void updateResolutionStatus(int reportID);
	
	List<Hazard> getUnresolvedHazards();
	
	boolean checkVoteTable(String username, int reportID);
	
	void insertIntoVoteTable(String username, int reportID, String previous);
	
	void deleteFromVoteTable(String username, int reportID);
	
	Vote getSpecificVote(String username, int reportID);
	
	void updateFromVoteTable(String username, int reportID, String previous);
	
	boolean checkForExistingHazard(Hazard hazard);
}