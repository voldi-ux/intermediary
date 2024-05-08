package com.intermediary.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField ;
import javafx.scene.control.TextField;

public class AuthController extends MainController {

	@FXML
	private PasswordField  signInPasswordInput;
	@FXML
	private TextField   signInEmailInput;
	@FXML
	private TextField   signUpEmailInput;
	@FXML
	private PasswordField  signUpPasswordInput;
	@FXML
	private PasswordField  signUpConfirmPasswordInput;

	// this method may be called when signing in or signing up
	@FXML
	private void signIn() throws Exception {
		Alert alert = new Alert(AlertType.INFORMATION);

		String email = signInEmailInput.getText();
		String pass = signInPasswordInput.getText();
		

		if (email.isEmpty() || pass.isEmpty() || email.length() < 5) {
			alert.setContentText("Please check your inputs again");
			alert.show();
			return;
		}

		boolean signedIn = authManager.signIn(email, pass);

		if (signedIn) {
			manager.addSceneToStage("main", "main");
			manager.getStage("authStage").close();
			manager.showStage("main");
		} else {
			alert.setContentText("Sorry we could not sign you in at the moment try again later");
			alert.show();
		}

	}

	@FXML
	private void signUp() throws Exception {
		Alert alert = new Alert(AlertType.INFORMATION);

		String email = signUpEmailInput.getText();
		String pass = signUpPasswordInput.getText();
		String confirmPass = signUpConfirmPasswordInput.getText();

		if (email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty() || email.length() < 5) {
			alert.setContentText("Please check your inputs again");
			alert.show();
			return;
		} else if (!pass.equals(confirmPass)) {
			alert.setContentText("make sure that your password matches");
			alert.show();
			return;
		}

		// the inputs are reasonably decent
		boolean signedIn = authManager.signUp(email, pass);

		if (signedIn) {
			manager.addSceneToStage("main", "main");
			manager.getStage("authStage").close();
			manager.showStage("main");
		} else {
			alert.setContentText("Sorry we could not sign you up at the moment try again later");
			alert.show();
		}

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
