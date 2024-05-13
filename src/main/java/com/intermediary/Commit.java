package com.intermediary;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Commit extends HBox {
  private CommitDetails details;
  public  Commit(CommitDetails details) {
	   this.details = details;
	   getChildren().add(new Text("commit Id " + details.getId()));
   }  
}
