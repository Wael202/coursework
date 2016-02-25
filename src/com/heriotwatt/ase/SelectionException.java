package com.heriotwatt.ase;

import java.util.TreeSet;

public class SelectionException extends Exception {

	public SelectionException(String selection){
		
		super("You have entered wrong selection= " + selection);
		
	}
}
