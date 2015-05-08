package com.sjsu.coupons.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sjsu.coupons.domain.User;
import com.sjsu.coupons.service.UserHandler;

@Controller
@RequestMapping(value = ("/coupons/users"))
public class UserController {
	
	UserHandler usrHndlr;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String createUser(@ModelAttribute User user,RedirectAttributes redirectAttributes) throws SQLException
	{
		System.out.println("email id obtained is:: "+user.email);
		usrHndlr= new UserHandler();

		boolean userVal=usrHndlr.checkIfUserExists(user.getEmail());

		if(userVal)
		{
		System.out.println("USer exists.. So authenticating");
		redirectAttributes.addFlashAttribute("user",user);
		return "redirect:/UserHomePage";
		}
		else
		{
		return "coupons";
		}
	}

	@RequestMapping(value="/fblogin",method=RequestMethod.POST)
	public String loginFbUser(@ModelAttribute User user,Model model,RedirectAttributes redirectAttributes) throws SQLException
	{
		System.out.println("yup in here"+user.getEmail()+"FB name is:::"+user.getName()+user.getPassword());
		model.addAttribute("user",user);

		boolean userVal=true;//usrHndlr.checkIfUserExists(user.getEmail());

		usrHndlr=new UserHandler();
		if(userVal)
		{
			System.out.println("USer exists.. So authenticating");
			redirectAttributes.addFlashAttribute("user",user);
			return "redirect:/UserHomePage";
		}
		else
		{
			System.out.println("FB user does not exists.. So signing up");
			usrHndlr.signUpUser(user);
			redirectAttributes.addFlashAttribute("user",user);
			return "redirect:/UserHomePage";
		}
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String signUpUser(@ModelAttribute User user,Model model,RedirectAttributes redirectAttributes) throws SQLException
	{
		usrHndlr= new UserHandler();
		boolean userVal=usrHndlr.checkIfUserExists(user.getEmail());

		if(!userVal)
		{
			System.out.println("Email id does not exist.So signing up the user");
			usrHndlr.signUpUser(user);
			redirectAttributes.addFlashAttribute("user",user);
			return "redirect:/UserHomePage";
		}
		else
		{
			return "coupons";
		}
	}
	
}
