package com.intermediary.controllers;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WelcomeController extends MainController implements Initializable {

	private static final long TIME_OUT_DURATION = 10 * 1000;
	@FXML
	private WebView svgRenderer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/// animation for the welcome
		/// this data must be read as a textfile for better code presentation
		var svg = "<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 200 200'><circle fill='#FF156D' stroke='#FF156D' stroke-width='15' r='15' cx='40' cy='65'><animate attributeName='cy' calcMode='spline' dur='2' values='65;135;65;' keySplines='.5 0 .5 1;.5 0 .5 1' repeatCount='indefinite' begin='-.4'></animate></circle><circle fill='#FF156D' stroke='#FF156D' stroke-width='15' r='15' cx='100' cy='65'><animate attributeName='cy' calcMode='spline' dur='2' values='65;135;65;' keySplines='.5 0 .5 1;.5 0 .5 1' repeatCount='indefinite' begin='-.2'></animate></circle><circle fill='#FF156D' stroke='#FF156D' stroke-width='15' r='15' cx='160' cy='65'><animate attributeName='cy' calcMode='spline' dur='2' values='65;135;65;' keySplines='.5 0 .5 1;.5 0 .5 1' repeatCount='indefinite' begin='0'></animate></circle></svg>";
		svgRenderer.getEngine().loadContent(svg, "image/svg+xml");

		Task<Void> t = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				try {
					Thread.sleep(TIME_OUT_DURATION); // waits for a while before moving to the main screen
					// later we will have to determine which scene to go to depeending on whehter we
					// are
					// logged in or not
					Platform.runLater(() -> {
						try {
							Stage s = manager.getStage("welcome");
							s.close(); // close the welcome screen
							
							if(!authManager.isLogedIn()) {
								manager.addSceneToStage("signIn", ""
										+ "authStage");
								manager.showStage("authStage");
								
							} else {
								
								manager.addSceneToStage("main", "main");
								manager.showStage("main");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					});

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};

		setTimeOut(t);
	}

	public void setTimeOut(Task<Void> t) {
		new Thread(t).start();
		;
	}

}
