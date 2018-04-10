package controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Login;

import model.User;

import service.UserService;

@Controller

public class LoginController {

	@Autowired

	UserService userService;

	// when the href link "login" is clicked within the home.jsp, it sends a request
	// which is mapped to this method showLogin()
	@RequestMapping(value = "/login", method = RequestMethod.GET)

	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("login");

		mav.addObject("login", new Login());

		return mav;

	}

	// within the login.jsp, there is a form with action = loginProcess, which means
	// that when the login button is pressed, it sends a request here
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)

	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login, RedirectAttributes redirect) {

		ModelAndView mav = null;

		User user = userService.validateUser(login);

		if (null != user) {
			// modelAndView takes the name of the jsp you want to display, in this case it
			// gets the Welcome.jsp
			mav = new ModelAndView();
			mav.setViewName("redirect:/welcome");
			
			redirect.addFlashAttribute("firstname", user.getUsername());
			
			redirect.addFlashAttribute("session", user.getAccounttype());


		} else {

			mav = new ModelAndView();
			mav.setViewName("redirect:/login");
			redirect.addFlashAttribute("message", "Username or Password is wrong!!");

		}

	    
	     return mav;

	}

}