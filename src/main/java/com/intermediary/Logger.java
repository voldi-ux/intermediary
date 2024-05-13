package com.intermediary;

import java.util.ArrayList;

import javafx.scene.control.ListView;

public class Logger {
	private static ArrayList<String> logs = new ArrayList<>();
	private static ListView<String> loggerList;

	public static ArrayList<String> getLogs() {
		return logs;
	}

	public static void setLogs(ArrayList<String> logs) {
		Logger.logs = logs;
	}

	public static ListView<String> getLoggerList() {
		return loggerList;
	}

	public static void setLoggerList(ListView<String> loggerList) {
		Logger.loggerList = loggerList;
	}

	public static void addLog(String log) {
		logs.add(log);
	}

	public static void displayLogs() {
		if (loggerList != null) {
			loggerList.getItems().clear();
			for (String log : logs) {
				loggerList.getItems().add(log);
			}
		}
	}

	public static void addAndDisplay(String string) {
		addLog(string);
		displayLogs();

	}

	
	public static void reset() {
		if(loggerList!= null) {
			loggerList.getItems().clear();
			logs.clear();
		}
	}
}
