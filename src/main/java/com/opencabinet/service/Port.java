package com.opencabinet.service;

import com.opencabinet.models.User;

import java.io.PrintWriter;
import java.util.*;

public class Port {

	private Map<String, User> connectedUsers;
	private int port;
	
	public Port(int port){
		connectedUsers = new HashMap<>();
		this.port=port;
	}

	/**
	 * Returns list of users currently connected to the port.
	 * @return
	 */
	public Map<String, User> getConnectedUsers() {

		return connectedUsers;
	}

	/**
	 * Adds user to list of users currently connected to the port.
	 * @param user
	 */
	public void addConnectedUser(User user) {
		this.connectedUsers.put(user.getUsername(), user);
	}

	public PrintWriter getUserWriter(String username) {
		return this.connectedUsers.get(username).getPrintWriter();
	}
	
	/**
	 * Removes a user from list of users connected to the port.
	 * @param username
	 */
	public void removeConnectedUser(String username) {
		this.connectedUsers.remove(username);
	}


	
	
}
