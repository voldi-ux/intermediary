package com.intermediary.firebase;

import java.io.File;
import java.io.FileInputStream;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class Firebase {
	private static FirebaseApp firebase = null;

	private Firebase() {
	}

	public static FirebaseApp getFirebase() {

		if (firebase == null) {
			init();

		}

		return firebase;
	}

	private static void init() {
//		FileInputStream serviceAccount =
//				new FileInputStream("");
//
//				FirebaseOptions options = FirebaseOptions.builder()
//				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//				  .build();
//
//				FirebaseApp.initializeApp(options);

		

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
