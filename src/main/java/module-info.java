module com.intermediary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
	requires blockchain;
	requires javafx.graphics;
 
	
    opens com.intermediary.controllers to javafx.fxml;
    exports com.intermediary;
}
