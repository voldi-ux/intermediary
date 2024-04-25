package com.intermediary.firebase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
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
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("auth_acount.json");
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			
			FirebaseApp.initializeApp(options);
			
			firebase  = FirebaseApp.getInstance();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("initializing firebase completed");

		

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
