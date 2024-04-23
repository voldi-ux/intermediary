package com.intermediary.controllers;

import com.intermediary.NavigationManager;

public class MainController {
	// all controllers should have this property and it must be the same
	protected static NavigationManager manager = null;

	public static void setManager(NavigationManager manager) {
		MainController.manager = manager;
	}

}
