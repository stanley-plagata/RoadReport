package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import dao.HazardDao;
import model.Hazard;
import model.Report;
import model.Vote;

public class HazardServiceImpl implements HazardService {

	@Autowired
	public HazardDao hazardDao;

	public void insertHazard(Hazard hazard, boolean construction, String username) {
		hazardDao.insertHazard(hazard, construction, username);
	}
	
	public List<Hazard> getHazards() {
		return hazardDao.getHazards();
	}
	
	public Hazard getSpecificHazard(String address, int reportID, int hazardID) {
		return hazardDao.getSpecificHazard(address, reportID, hazardID);
	}
	
	public int getVoteCount(int reportID) {
		return hazardDao.getVoteCount(reportID);
	}
	
	public void updateVoteCount(int reportID, boolean increment, int number) {
		hazardDao.updateVoteCount(reportID, increment, number);
	}

	public void updateResolutionStatus(int reportID) {
		hazardDao.updateResolutionStatus(reportID);
	}
	
	public List<Hazard> getUnresolvedHazards() {
		return hazardDao.getUnresolvedHazards();
	}
	
	public boolean checkVoteTable(String username, int reportID) { 
		return hazardDao.checkVoteTable(username, reportID);
	}
	
	public void insertIntoVoteTable(String username, int reportID, String previous) {
		hazardDao.insertIntoVoteTable(username, reportID, previous);
	}
	
	public void deleteFromVoteTable(String username, int reportID) {
		hazardDao.deleteFromVoteTable(username, reportID);
	}
	
	public Vote getSpecificVote(String username, int reportID) {
		return hazardDao.getSpecificVote(username, reportID);
	}
	
	public void updateFromVoteTable(String username, int reportID, String previous) {
		hazardDao.updateFromVoteTable(username, reportID, previous);
	}
	
	public boolean checkForExistingHazard(Hazard hazard) {
		return hazardDao.checkForExistingHazard(hazard);
	}
}