package com.intermediary.controllers;

import com.intermediary.NavigationManager;
import com.intermediary.auth.AuthenticationManager;

public class MainController {
	// all controllers should have these properties
	protected static NavigationManager manager = null;
	protected  AuthenticationManager authManager = AuthenticationManager.getAuthenticationManager();
	public static void setManager(NavigationManager manager) {
		MainController.manager = manager;
	}
	

}
