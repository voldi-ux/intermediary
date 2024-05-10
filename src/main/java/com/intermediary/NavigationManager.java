package com.intermediary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Stage;

//class to manage all the navigation stuff within the application
public class NavigationManager {
	private HashMap<String, Stage> stages = new HashMap<String, Stage>();
	private HashMap<String, Scene> scenes = new HashMap<>();
	private static NavigationManager manager = null;

	private NavigationManager() {
	};

	/// puts a scene to the scenes map
	public void AddScene(String name, Scene scene) throws Exception {
		if (scenes.containsKey(name)) {
			throw new Exception("scene name should be unique");
		}
		scenes.put(name, scene);
	}

	// puts a scene to the scenes map
	public void AddStage(String name, Stage stage) throws Exception {
		if (stages.containsKey(name)) {
			throw new Exception("stage name should be unique");
		}
		stages.put(name, stage);
	}

	public void addSceneToStage(String sceneName, String stageName) throws Exception {
		if (scenes.containsKey(sceneName) && stages.containsKey(stageName)) {
			stages.get(stageName).setScene(scenes.get(sceneName)); // sets the scene onto a stage
		} else {
			throw new Exception("Invalid scene or stage name");
		}
	}

	public void showStage(String name) throws Exception {
		if (!stages.containsKey(name)) {
			throw new Exception("no such stage exists" + name);
		}
		stages.get(name).show();
	}

	public Iterator<String> stages() {
		return stages.keySet().iterator();
	}

	public Iterator<String> scenes() {
		return scenes.keySet().iterator();
	}

	public static NavigationManager getManager() {
		if (manager == null)
			manager = new NavigationManager();

		return manager;
	}

	public Stage getStage(String name) throws Exception {
		if (!stages.containsKey(name)) {
			throw new Exception("no such stage exists: " + name);
		}
		return stages.get(name);

	}
}
