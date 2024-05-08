module com.intermediary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
	requires blockchain;
	requires javafx.graphics;
	requires firebase.admin;
	requires com.google.auth.oauth2;
	requires bcrypt;
	requires google.cloud.firestore;
	requires com.google.api.apicommon;
	requires com.google.auth;
	requires google.cloud.core;
	
    opens com.intermediary.controllers to javafx.fxml;
    
    opens com.intermediary to   google.cloud.firestore;
    
    exports com.intermediary;
}
