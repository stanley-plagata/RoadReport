package dao;

import java.io.IOException; 

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import model.Hazard;
import model.Report;
import model.User;
import model.Vote;
import model.Location;

public class HazardDaoImpl implements HazardDao {

	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insertHazard(Hazard hazard, boolean construction, String username) {
		GeoApiContext context = new GeoApiContext.Builder().
				apiKey("AIzaSyCka5tJuOaFcsKFvOrJdocRYu3sqwH7Tgg").build();
			try {
				GeocodingResult[] results = 
						GeocodingApi.geocode(context, hazard.getAddress()).await();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				
				double lat = results[0].geometry.location.lat;
				double lng = results[0].geometry.location.lng;
				String city = results[0].addressComponents[3].longName;
				String zipCode = "N/A";
				if( results[0].addressComponents.length > 6 )
					zipCode = results[0].addressComponents[7].longName; 
			
				String userSubmitsReportInsert = 
						"insert into userssubmitreports values(?,?)";
				String hazardsTableInsert = 
						"insert into hazards values(?,?,?,?,?)";
				String constructionHazardsTableInsert = 
						"insert into constructionhazards values(?,?,?,?,?)";
				String locationsTableInsert = 
						"insert into locations values(?,?,?,?,?)";
				
				int getHazardsCount = jdbcTemplate.
						queryForObject("Select COUNT(*) as count from hazards", Integer.class);
				
				if (getHazardsCount == 0) {
					if (construction) {
						jdbcTemplate.update(hazardsTableInsert, 
								new Object[] { 1, 1, lat, lng, "construction"});
						jdbcTemplate.update(constructionHazardsTableInsert, 
								new Object[] { 1, 1, lat, lng, hazard.getEstimatedDateOfCompletion() });
					}
					else {
						jdbcTemplate.update(hazardsTableInsert, 
								new Object[] { 1, 1, lat, lng, hazard.getHazardType()});
					}
					jdbcTemplate.update(userSubmitsReportInsert, 
							new Object[] { username, 1 } );
					String reportsTableInsert = 
							"insert into reports values(" + 1 + "," + hazard.getSeverity() 
							+ ", 0, CURRENT_TIMESTAMP(), \"unresolved\")";
					jdbcTemplate.update(reportsTableInsert);
				}			
				
				else {
					int reportID = getHazardsCount + 1;
					int hazardID = getHazardsCount + 1;
					if (construction) {
						jdbcTemplate.update(hazardsTableInsert, 
								new Object[] { reportID, hazardID, lat, lng,"construction"});
						jdbcTemplate.update(constructionHazardsTableInsert, 
								new Object[] { reportID, hazardID, lat, lng,hazard.getEstimatedDateOfCompletion() });
					}
					else {
						jdbcTemplate.update(hazardsTableInsert, 
								new Object[] { reportID, hazardID, lat, lng, hazard.getHazardType()});
					}
					jdbcTemplate.update(userSubmitsReportInsert, 
							new Object[] { username, reportID } );
					String reportsTableInsert = "insert into reports values(" + reportID + "," 
							+ hazard.getSeverity() +", 0, CURRENT_TIMESTAMP(), \"unresolved\")";
					jdbcTemplate.update(reportsTableInsert);
					
				}
				
				jdbcTemplate.update(locationsTableInsert, new Object[] { lat, lng, hazard.getAddress(), zipCode, city });  
				
			} catch (ApiException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public boolean checkForExistingHazard(Hazard hazard) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCka5tJuOaFcsKFvOrJdocRYu3sqwH7Tgg").build();
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, hazard.getAddress()).await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			double lat = results[0].geometry.location.lat;
			double lng = results[0].geometry.location.lng;
			
			String sql = "select * from locations where longitude = " + lng + " and latitude = " + lat + ";";
			List<Location> check = jdbcTemplate.query(sql, new LocationMapper());
			
			return check.size() > 0;
			
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public List<Hazard> getHazards() {
		String sql = "Select * from locations, hazards, reports where hazards.latitude = locations.latitude "
				+ "AND hazards.longitude = locations.longitude AND reports.reportID = hazards.reportID;";

		List<Hazard> hazards = jdbcTemplate.query(sql, new HazardMapper());
		
		return hazards;
	}
	
	
	public List<Hazard> getUnresolvedHazards() {
		String sql = "Select * from locations, hazards, reports where hazards.latitude = locations.latitude "
				+ "AND hazards.longitude = locations.longitude AND reports.reportID = hazards.reportID AND reports.ResolutionStatus = 'unresolved';";

		List<Hazard> hazards = jdbcTemplate.query(sql, new HazardMapper());
		
		return hazards;
	}
	
	public Hazard getSpecificHazard(String address, int reportID, int hazardID) {
		
		String sql = "Select DISTINCT * from locations, hazards, reports where locations.address ='" + 
		address + "'  AND reports.reportID = '" + reportID + "' AND hazards.HazardID = '" + hazardID + "'";
		
		List<Hazard> hazard = jdbcTemplate.query(sql, new HazardMapper());
		
		return hazard.get(0);
	}
	
	public void updateResolutionStatus(int reportID) {
		String sql = "update reports set resolutionstatus = 'resolved' where reportID = " + reportID + ";";
		String sql2 = "delete from locations where latitude in (select latitude from hazards where reportID = " + reportID + ") and longitude in (select longitude from hazards where reportID = " + reportID + ");";
		
		jdbcTemplate.update(sql);
		jdbcTemplate.update(sql2);
	}
	
	public int getVoteCount(int reportID) {
		String sql = "Select * from reports where reportID = " + reportID;
		List<Report> report = jdbcTemplate.query(sql, new ReportMapper());
		
		return report.get(0).getVoteCount();
	}
	
	public void updateVoteCount(int reportID, boolean increment, int number) {
		String sql;
		if (increment)
			sql = "update reports set reports.votecount = reports.votecount + " + number + " where reportID = " + reportID;
		else 
			sql = "update reports set reports.votecount = reports.votecount - " + number + " where reportID = " + reportID;
		jdbcTemplate.update(sql);
	}
	
	public boolean checkVoteTable(String username, int reportID) {
		String sql = "select * from votes where username = '" + username + "' and reportID = " + reportID + ";";
		List<Vote> votes = jdbcTemplate.query(sql, new VoteMapper());
		if (votes.size() > 0)
			return true;
		else 
			return false;
	}
	
	public Vote getSpecificVote(String username, int reportID) {
		String sql = "select * from votes where username = '" + username + "' and reportID = " + reportID + ";";
		List<Vote> votes = jdbcTemplate.query(sql, new VoteMapper());
		return votes.get(0);
	}
	
	public void insertIntoVoteTable(String username, int reportID, String previous) {
		String sql = "insert into votes values(?,?,?)";
		jdbcTemplate.update(sql, new Object[] { username, reportID, previous } );
	}
	
	public void deleteFromVoteTable(String username, int reportID) {
		String sql = "delete from votes where username = '" + username + "' and reportID = " + reportID + ";";
		jdbcTemplate.update(sql);
	}
	
	public void updateFromVoteTable(String username, int reportID, String previous) {
		String sql = "update votes set previous = '" + previous + 
				"' where username = '" + username + "' and reportID = " + reportID + ";";
		jdbcTemplate.update(sql);
	}
}

class HazardMapper implements RowMapper<Hazard> {

	public Hazard mapRow(ResultSet rs, int arg1) throws SQLException {

		Hazard hazard = new Hazard();
		
		hazard.setAddress(rs.getString("address"));

		hazard.setLat(rs.getDouble("latitude"));
		
		hazard.setLng(rs.getDouble("longitude"));
		
		hazard.setHazardType(rs.getString("hazardtype"));
		
		hazard.setSeverity(rs.getInt("severity"));
		
		hazard.setVoteCount(rs.getInt("votecount"));
		
		hazard.setReportDate(rs.getDate("reportdate"));
		
		hazard.setReportID(rs.getInt("reportID"));
		
		hazard.setHazardID(rs.getInt("hazardID"));
		
		hazard.setResolutionStatus(rs.getString("resolutionStatus"));

		return hazard;
	}

}

class ReportMapper implements RowMapper<Report> {

	public Report mapRow(ResultSet rs, int arg1) throws SQLException {

		Report report = new Report();
		
		report.setReportID(rs.getInt("reportID"));
		
		report.setSeverity(rs.getInt("severity"));
		
		report.setVoteCount(rs.getInt("votecount"));
		
		report.setReportDate(rs.getDate("reportdate"));
		
		report.setResolutionStatus(rs.getString("resolutionstatus"));

		return report;
	}
}
	
class VoteMapper implements RowMapper<Vote> {

	public Vote mapRow(ResultSet rs, int arg1) throws SQLException {

		Vote vote = new Vote();
		
		vote.setReportID(rs.getInt("reportID"));
		
		vote.setUsername(rs.getString("username"));
		
		vote.setPrevious(rs.getString("previous"));

		return vote;
	}
}

class LocationMapper implements RowMapper<Location> {

	public Location mapRow(ResultSet rs, int arg1) throws SQLException {

		Location location = new Location();
		
		location.setAddress(rs.getString("address"));
		
		location.setLat(rs.getDouble("Latitude"));
		
		location.setLng(rs.getDouble("Longitude"));

		return location;
	}
}






