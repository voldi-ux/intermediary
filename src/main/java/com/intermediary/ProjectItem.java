package com.intermediary;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ProjectItem extends HBox {
	private Project project;
	private Text name = new Text();

	public ProjectItem(Project p) {
		this.project = p;
		name.setText(p.getName());
		this.getChildren().add(name);
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Text getName() {
		return name;
	}

	public void setName(Text name) {
		this.name = name;
	}
}
