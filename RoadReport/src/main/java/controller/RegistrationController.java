package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.User;

import service.UserService;

@Controller

public class RegistrationController {

	@Autowired

	public UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)

	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("register");

		mav.addObject("user", new User());

		mav.addObject("typeList", getTypeList());

		return mav;

	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)

	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user, RedirectAttributes redirect) {

		ModelAndView mav = null;

		String username = user.getUsername();

		String password = user.getPassword();

		String field1 = user.getfield1();

		String field2 = user.getfield2();

		if (username == null || username.length() < 1 || username.length() > 20) {

			mav = new ModelAndView("register");

			mav.addObject("message", "Please enter valid username");

		}

		else if (userService.checkIfUsernameExists(user.getUsername())) {

			mav = new ModelAndView("register");

			mav.addObject("message", "Username is already taken");

		}
		
		else if (password == null || password.length() < 8 || password.length() > 50) {

			mav = new ModelAndView("register");

			mav.addObject("message", "Please enter valid password");

		}

		else if (field1 == null || field1.length() < 1 || field1.length() > 50) {

			mav = new ModelAndView("register");

			mav.addObject("message", "Please enter valid entry for field 1");

		}
		
		else if (user.getAccounttype().equals("Resolver") && userService.checkIfCompanyExists(field1)) {
			mav = new ModelAndView("register");

			mav.addObject("message", "Company name is already taken");
		}


		else if (field2 == null || field2.length() < 1 || field2.length() > 50) {

			mav = new ModelAndView("register");

			mav.addObject("message", "Please enter valid entry for field 2");

		}

		else {

			mav = new ModelAndView();
			mav.setViewName("redirect:/welcome");
			
			redirect.addFlashAttribute("firstname", user.getUsername());
			
			redirect.addFlashAttribute("session", user.getAccounttype());
			
			userService.register(user);

		}

		return mav;

	}

	@ModelAttribute("typeList")
	public Map<String, String> getTypeList() {
		Map<String, String> typeList = new HashMap<String, String>();
		typeList.put("Reporter", "Reporter");
		typeList.put("Resolver", "Resolver");

		return typeList;
	}

}