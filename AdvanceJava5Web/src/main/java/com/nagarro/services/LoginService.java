package com.nagarro.services;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.message.DisplayMessageToJSP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.constants.Constants;
import com.nagarro.dao.UserDao;
import com.nagarro.models.User;

import interfaces.LoginServiceInterface;

@Component
public class LoginService implements LoginServiceInterface {

	@Autowired
	UserDao userDao;


	@Override
	public boolean verifyUser(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userDao.getUser(username);
		HttpSession session = request.getSession();

		// Check if the user object is null
		if (Objects.isNull(user)) {
			// Return that the user does not exists in the database
			DisplayMessageToJSP message = new DisplayMessageToJSP("Invalid username Credential ! try with another",
					"error", "alert-danger");
			session.setAttribute("message", message);
			return false;
		} else if (!user.getPassword().equals(password)) {
			// Check if the username matches to password
			// else return incorrect password
			DisplayMessageToJSP message = new DisplayMessageToJSP("Invalid password Credential ! try with another",
					"error", "alert-danger");
			session.setAttribute("message", message);
			return false;
		}

		try {
			// if it is a valid user save user to session
			request.getSession().setAttribute(Constants.Session.USER_SESSION_ATTRIBUTE, user);
		} catch (IllegalStateException ise) {
			System.out.println(Constants.Exceptions.UNKNOWN_ERROR);
		}catch(Exception e) {
			System.out.println(Constants.Exceptions.UNKNOWN_ERROR);
		}
		// send user to the products jsp page
		System.out.println("Dao accessed.....");
		session.setAttribute("user",user );
		return true;
	}
}

