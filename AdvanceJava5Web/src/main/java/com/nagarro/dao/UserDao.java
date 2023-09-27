package com.nagarro.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import com.nagarro.constants.Constants;
import com.nagarro.models.User;

@Component
public class UserDao {
	private SessionFactory newSession = null;

	// Constructor
	public UserDao() {
		Configuration file = new Configuration().configure().addAnnotatedClass(User.class);

		ServiceRegistry register = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(file.getProperties())
				.build();

		newSession = file.buildSessionFactory(register);
	}

	// Get User From the Database
	public User getUser(String username) {
		try {
			Session session = newSession.openSession();
			// Execute a query for user
			Query<User> q = session.createQuery(Constants.Query.GET_USER_QUERY, User.class);
			// Set User query parameters
			q.setParameter("uName", username);
			// Get User
			User user = q.uniqueResult();
			// Close session
			session.close();
			// Return User
			return user;
		} catch (Exception e) {
			System.out.println(Constants.Exceptions.UNKNOWN_ERROR);
		}
		return null;
	}
}

