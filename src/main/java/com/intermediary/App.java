package com.intermediary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.intermediary.controllers.MainController;

/**
 * JavaFX App
 */
public class App extends Application {

	static NavigationManager manager = NavigationManager.getManager();

	@Override
	public void start(Stage welcome) throws Exception {
		welcome.initStyle(StageStyle.UNDECORATED);

		Scene welocme = new Scene(loadFXML("welcome"));
		Scene signIn = new Scene(loadFXML("signIn"));
		Scene signUp = new Scene(loadFXML("signUp"));
        Scene addProject = new Scene(loadFXML("add_project"));
        Scene addUsers = new Scene(loadFXML("add_user"));
        Scene commit = new Scene(loadFXML("commit"));
        
        
		Stage authStage = new Stage();
		Stage mainStage = new Stage();
		Stage addProjectStage = new Stage();
		Stage addUsersStage = new Stage();
		Stage commitStage = new Stage();
		
		
		//setting owners
		addProjectStage.initOwner(mainStage);
		addUsersStage.initOwner(mainStage);
		commitStage.initOwner(mainStage);
		
		// setting modality
		addProjectStage.initModality(Modality.APPLICATION_MODAL);
		addUsersStage.initModality(Modality.APPLICATION_MODAL);
		commitStage.initModality(Modality.APPLICATION_MODAL);
		
		
		//setting title of the main window
		mainStage.setTitle("Intermediary");
		// adding 
		manager.AddScene("welcome", welocme);
		manager.AddScene("signIn", signIn);
		manager.AddScene("signUp", signUp);
		manager.AddScene("addProject", addProject);
		manager.AddScene("addUser", addUsers);
		manager.AddScene("commit", commit);
		// main stage
		manager.AddStage("main", mainStage);
        manager.AddStage("welcome", welcome);
		manager.AddStage("authStage", authStage);
		manager.AddStage("addProject", addProjectStage);
		manager.AddStage("addUser", addUsersStage);
		manager.AddStage("commit", commitStage);

	    
		manager.addSceneToStage("welcome", "welcome");
		
		// pass the navigation manager to each and every 
		// controller.
		// this is  very efficient since 
		MainController.setManager(manager);

		manager.showStage("welcome");

	}

	
	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}