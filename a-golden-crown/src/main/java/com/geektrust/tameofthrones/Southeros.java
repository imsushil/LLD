package com.geektrust.tameofthrones;

import com.geektrust.tameofthrones.exceptions.InvalidKingdomNameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.geektrust.tameofthrones.Constants.KINGDOMS_PATH;
import static com.geektrust.tameofthrones.Constants.SPACE;


/**
  * Southeros is the place which has several kingdoms like Land, Space, Air, Water, Ice, Fire
  * Each Kingdom has an emblem associated with it.
  * The Kingdoms are loaded into kingdoms map from kingdoms.txt file.
  * It is Singleton class.
  *
  * @author sushil
  */
public class Southeros {
	private static Southeros instance;
	private final Map<String, Kingdom> kingdoms;

	private Southeros() {
		this.kingdoms = new HashMap<>();
		this.init();
	}
	
	/* ===============================
	 * PRIVATE METHODS 
	 * ===============================
	 */

	/**
	 * Reads {@value Constants#KINGDOMS_PATH} file and populates the kingdoms map.
	 */
	private void init() {
		try (InputStream in = getClass().getResourceAsStream(KINGDOMS_PATH);
			 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			reader.lines().forEach(this::initKingdoms);
		} catch (IOException e) {
			throw new RuntimeException("Exception occurred while reading file: " + KINGDOMS_PATH, e);
		}
	}

	/**
	 * Helper method for {@link #init() init} method to populate kingdoms map
	 * @param line line in the file {@value Constants#KINGDOMS_PATH}
	 */
	private void initKingdoms(String line) {
		String[] in = line.split(SPACE);
		String kingdomName = in[0];
		String emblem = in[1];

		Kingdom kingdom = new Kingdom(kingdomName, emblem);
		this.kingdoms.put(kingdomName.toUpperCase(), kingdom);
	}
	
	/* ================================
	 * PUBLIC METHODS 
	 * ================================
	 */

	/**
	 * @return a kingdom for which {@link Kingdom#isRuler() isRuler} returns true
	 */
	public Optional<Kingdom> getRuler() {
		return kingdoms.values().stream()
				.filter(Kingdom::isRuler)
				.findFirst();
	}

	/**
	 * @param kingdomName name of the kingdom
	 * @return instance of Kingdom whose name is passed as input
	 */
	public Kingdom getKingdom(String kingdomName) {
		if(!this.kingdoms.containsKey(kingdomName)) {
			throw new InvalidKingdomNameException("Kingdom name " + kingdomName + " is not valid.");
		}
		return this.kingdoms.get(kingdomName);
	}

	/**
	 * creates an object of Southeros and always returns the same instance
	 * @return instance of Southeros
	 */
	public static Southeros getInstance() {
		if(instance == null) {
			instance = new Southeros();
		}
		return instance;
	}
}