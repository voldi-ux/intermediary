package com.intermediary.controllers;


import javafx.fxml.FXML;

public class SecondaryController extends MainController {

	@FXML
	private void switchToPrimary() throws Exception {
		manager.addSceneToStage("main", "main");
	}
}