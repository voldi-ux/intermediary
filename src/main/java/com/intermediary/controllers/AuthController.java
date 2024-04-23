package com.intermediary.controllers;



import javafx.fxml.FXML;

public class AuthController extends MainController {
	// this method may be called when signing in or signing up

	@FXML
	private void signIn() throws Exception {
		manager.addSceneToStage("main", "main");
		manager.getStage("authStage").close();
		manager.showStage("main");
	}

	@FXML
	private void showSignInScene() throws Exception {
		manager.addSceneToStage("signIn", "authStage");
	}

	@FXML
	private void showSignUpScene() throws Exception {
		manager.addSceneToStage("signUp", "authStage");
	}
}
