package com.intermediary.controllers;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PrimaryController extends MainController implements Initializable {
	@FXML
	private Button signoutButton;
	@FXML
    private ListView<Text> sideBarList;
	@FXML
	private Text selectedname;
	private Text prevSelected;
	
	private ArrayList<String> projectNames = new ArrayList<String>();

	
	
	
	@FXML
	private void switchToSecondary() throws Exception {
//        App.setRoot("secondary");  	
		manager.addSceneToStage("secondaryScene", "main");
	}
    
	@FXML
	private void signOut() throws Exception {
		manager.addSceneToStage("signIn", "authStage");
		manager.getStage("main").close();
		manager.showStage("authStage");
	}

	
	@FXML
	private void addProject() throws Exception {
//		manager.showStage("");
		manager.addSceneToStage("addProject", "addProject");
		manager.showStage("addProject");
	}
	
	
	@FXML
	private void addClient() throws Exception {
//		manager.showStage("");
		manager.addSceneToStage("addUser", "addUser");
		manager.showStage("addUser");
	}
	
	
	@FXML
	private void commit() throws Exception {
//		manager.showStage("");
		manager.addSceneToStage("commit", "commit");
		manager.showStage("commit");
	}
	
	
	
	@FXML
	private void addValidator() {
		//ask a user a select a java file
		// it must have a validate method
		//compile this file and then dynamically load it
	
		System.out.println("validator loaded successfully");
	}

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// stub project names
		for(int i = 0; i < 10; i++) {
			projectNames.add("Project " + i*200);
		}
		
		
		
		//testing
		for(int i = 0; i < 10; i++) {
			Text name = new Text(projectNames.get(i));
			name.setStyle("-fx-font-size: 20");
			sideBarList.getItems().add(name);
			name.setFill(Color.GREY);
		}
		
		
		sideBarList.setOnMouseClicked(e -> {
			    var model = sideBarList.getSelectionModel();
			    Text selected = model.getSelectedItem();
			    selected.setFill(Color.WHITE);
		  		selectedname.setText(selected.getText());
		  		prevSelected.setFill(Color.GREY);
		  		prevSelected  = selected;
		});
		
		sideBarList.getSelectionModel().selectFirst(); // the first item must be selected
		
		prevSelected = sideBarList.getSelectionModel().getSelectedItem();
		selectedname.setText(prevSelected.getText());
		prevSelected.setFill(Color.WHITE);
		
		
	}
	
	
	
	
}
