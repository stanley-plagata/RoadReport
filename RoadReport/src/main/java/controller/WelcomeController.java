package controller;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Hazard;
import model.Login;
import model.User;
import model.Vote;
import service.HazardService;


@Controller

public class WelcomeController {
	
	@Autowired
	public HazardService hazardService;
	
	Hazard ajaxHazard;
	
	String username;
	
	String userSession;
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)

	public ModelAndView showWelcome(HttpServletRequest request, HttpServletResponse response, Model model) {

		ModelAndView mav = new ModelAndView("welcome");
		
		String session = (String) model.asMap().get("session");
		
		String name = (String) model.asMap().get("firstname");
		
		userSession = session;
		
		this.username = name;
		
		return mav;

	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView getMap(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("map");
		
		mav.addObject("session", userSession);
		mav.addObject("username", username);
		
		return mav;
	}
	
	@RequestMapping(value = "/resolveHazard", method = RequestMethod.GET)
	public ModelAndView getResolveHazards(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("resolveHazard");
		
		List<Hazard> hazards = hazardService.getUnresolvedHazards();
		
		mav.addObject("hazards", hazards);
		
		return mav;
	}
	
	// when the href link "reportHazard" is clicked within the welcome.jsp, it sends
	// a request which is mapped to this method
	// it pretty much initializes the web page
	@RequestMapping(value = "/reportHazard", method = RequestMethod.GET)
	public ModelAndView showReportHazard() {
		ModelAndView mav = new ModelAndView("reportHazard");
		// must add object to mav to bind a bean for the form to work.
		mav.addObject("hazard", new Hazard());
		
		mav.addObject("session", userSession);
		return mav;
	}
	
	// when the href link "reportHazard" is clicked within the welcome.jsp, it sends
	// a request which is mapped to this method
	// it pretty much initializes the web page
	@RequestMapping(value = "/reportConstructionHazard", method = RequestMethod.GET)
	public ModelAndView showReportConstructionHazard() {
		ModelAndView mav = new ModelAndView("reportConstructionHazard");
		// must add object to mav to bind a bean for the form to work.
		mav.addObject("hazard", new Hazard());
		
		mav.addObject("session", userSession);
		return mav;
	}
	
	@RequestMapping(value = "/reportHazardProcess", method = RequestMethod.POST)

	public ModelAndView reportHazardProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("hazard") Hazard hazard) {
		ModelAndView mav = null;
		if (hazardService.checkForExistingHazard(hazard)) {
			mav = new ModelAndView("reportHazard");
			
			mav.addObject("message", "This hazard has already been reported");
		}
		
		else {
			hazardService.insertHazard(hazard, false, username);
			
			 mav = new ModelAndView("map");
		}
		
		mav.addObject("session", userSession);

		return mav;
	}
	
	@RequestMapping(value = "/reportConstructionHazardProcess", method = RequestMethod.POST)

	public ModelAndView reportConstructionHazardProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("hazard") Hazard hazard) {
		
		ModelAndView mav = null;
		if (hazardService.checkForExistingHazard(hazard)) {
			mav = new ModelAndView("reportConstructionHazard");
			
			mav.addObject("message", "This hazard has already been reported");
		}
		else {
			hazardService.insertHazard(hazard, true, username);
			
			 mav = new ModelAndView("map");
		}
		
		mav.addObject("session", userSession);

		return mav;
	}
	
	@RequestMapping(value = "/hazardGet", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Hazard> AjaxGET() {
		
		List<Hazard> hazardList = hazardService.getHazards();

		return hazardList;
	}
	
	@RequestMapping(value = "/resolveHazardProcess/{id}", method = RequestMethod.GET)
	public ModelAndView getResolveHazardProcess(@PathVariable int id) {
		System.out.println(id);
		hazardService.updateResolutionStatus(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/resolveHazard");
		return mav;
	}
	
	@RequestMapping(value = "/hazardPost", method = RequestMethod.POST)
	public @ResponseBody String AjaxPOST(@RequestParam String json) 
			throws JsonParseException, JsonMappingException, IOException {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		ajaxHazard = mapper.readValue(json, Hazard.class);
		
		return "hazardDetails";
	}
	
	@RequestMapping(value = "/hazardDetails", method = RequestMethod.GET)
	public ModelAndView getWelcome() {
		Hazard displayHazard = hazardService.getSpecificHazard(ajaxHazard.getAddress(), ajaxHazard.getReportID(), ajaxHazard.getHazardID());
		ModelAndView mav = new ModelAndView("hazardDetails");
		mav.addObject("address", "Address : " + displayHazard.getAddress());
		mav.addObject("hazardType", "Hazard Type : " + displayHazard.getHazardType());
		mav.addObject("severity", "Severity : " + displayHazard.getSeverity());
		mav.addObject("resolutionStatus", "Status : " + displayHazard.getResolutionStatus());
		mav.addObject("reportDate", "Report Date : " + displayHazard.getReportDate());
		mav.addObject("voteCount", "Vote Count : " + displayHazard.getVoteCount());
		return mav;
	}
	
	@RequestMapping(value = "/upvote", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, Integer> upvote() {
		int voteCount;
		
		if (hazardService.checkVoteTable(username, ajaxHazard.getReportID())) {
			Vote vote = hazardService.getSpecificVote(username, ajaxHazard.getReportID());
			if (vote.getPrevious().equals("downvote")) {
				hazardService.updateFromVoteTable(username, ajaxHazard.getReportID(), "upvote");
				hazardService.updateVoteCount(ajaxHazard.getReportID(), true, 2);
				voteCount = hazardService.getVoteCount(ajaxHazard.getReportID());
			}
			else 
				voteCount = -9999;
		} 
		else {
			hazardService.insertIntoVoteTable(username, ajaxHazard.getReportID(), "upvote");
			hazardService.updateVoteCount(ajaxHazard.getReportID(), true, 1);
			voteCount = hazardService.getVoteCount(ajaxHazard.getReportID());
		}
		return Collections.singletonMap("response", voteCount);
	}
	
	@RequestMapping(value = "/downvote", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, Integer> downvote() {
		int voteCount;
		if (hazardService.checkVoteTable(username, ajaxHazard.getReportID())) {
			Vote vote = hazardService.getSpecificVote(username, ajaxHazard.getReportID());
			if (vote.getPrevious().equals("upvote")) {
				hazardService.updateFromVoteTable(username, ajaxHazard.getReportID(), "downvote");
				hazardService.updateVoteCount(ajaxHazard.getReportID(), false, 2);
				voteCount = hazardService.getVoteCount(ajaxHazard.getReportID());
			}
			else 
				voteCount = -9999;
		} 
		else {
			hazardService.insertIntoVoteTable(username, ajaxHazard.getReportID(), "downvote");
			hazardService.updateVoteCount(ajaxHazard.getReportID(), false, 1);
			voteCount = hazardService.getVoteCount(ajaxHazard.getReportID());
		}
		return Collections.singletonMap("response", voteCount);
	}

	
	
}