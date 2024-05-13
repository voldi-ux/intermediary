package com.intermediary.blockchain;

import java.util.ArrayList;

import com.intermediary.CommitDetails;
import com.intermediary.ProjectManager;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import javafx.scene.control.Alert;

public class blockchainExtended<T> extends Blockchain<T> {

	private int STAKE = 1000000;

	public void addBlock(T data) {
		CommitDetails details = (CommitDetails) data;
		// userid corresponds to the person that made the commit
		Transaction<T> t = new Transaction<>(details.getUserId(), null, data);
		ArrayList<Transaction<T>> transactions = new ArrayList<Transaction<T>>();
		// add the details to the blockchain after it has been suc
		addBlock(transactions); // delegating method to super class
	}

	public void addStakes(ArrayList<String> participants) {
		for(String user : participants) {
			registerStake(user, STAKE);
		}
	}
}
