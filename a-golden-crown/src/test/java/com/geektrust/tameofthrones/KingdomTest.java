package com.geektrust.tameofthrones;

import com.geektrust.tameofthrones.exceptions.InvalidMessageFormatException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class KingdomTest {
	private Kingdom kingdom;
	
	@Before
	public void setUp() {
		kingdom = new Kingdom("SPACE", "Gorilla");
	}



	@Test
	public void getAlliesShouldReturnTheAllies() throws IllegalAccessException, NoSuchFieldException {
		Field allies = kingdom.getClass().getDeclaredField("allies");
		allies.setAccessible(true);
		Set<Kingdom> samplesAllies = new LinkedHashSet<>();
		samplesAllies.add(new Kingdom("LAND", "Panda"));
		samplesAllies.add(new Kingdom("AIR", "Owl"));
		samplesAllies.add(new Kingdom("ICE", "Mammoth"));
		allies.set(kingdom, samplesAllies);
		Assert.assertEquals("'LAND AIR ICE' should be the allies.", kingdom.getAllies(), Arrays.asList("LAND", "AIR", "ICE"));
	}

	@Test
	public void getAlliesShouldReturnEmptyListIfSpaceKingdomHasNoAllies() {
		Assert.assertTrue("SPACE kingdom should have any allies.", kingdom.getAllies().isEmpty());
	}

	@Test
	public void isRulerPositiveTest() throws NoSuchFieldException, IllegalAccessException {
		Field allies = kingdom.getClass().getDeclaredField("allies");
		allies.setAccessible(true);
		Set<String> samplesAllies = new LinkedHashSet<>();
		samplesAllies.add("LAND");
		samplesAllies.add("AIR");
		samplesAllies.add("ICE");
		allies.set(kingdom, samplesAllies);
		Assert.assertTrue("SPACE should be the ruler.", kingdom.isRuler());
	}

	@Test
	public void isRulerNegativeTest() throws IllegalAccessException, NoSuchFieldException {
		Field allies = kingdom.getClass().getDeclaredField("allies");
		allies.setAccessible(true);
		Set<String> samplesAllies = new LinkedHashSet<>();
		allies.set(kingdom, samplesAllies);
		Assert.assertFalse("SPACE should NOT be the ruler.", kingdom.isRuler());
	}

	/*
	 * POSITIVE TEST
	 * 
	 * kingdom is LAND and its emblem is Panda
	 * Input message: LAND OFBBMUFDICCSO
	 * Output: true
	 * 
	 * Asserts true if message contains the emblem
	 * 
	 */
	@Test
	public void sendingMessageToLandKingdomShouldAddItAsAnAlly() {
		String inputLine = "LAND OFBBMUFDICCSO";
		kingdom.sendMessage(inputLine);
		Assert.assertTrue("Land kingdom should be an ally of Space Kingdom.", kingdom.getAllies().contains("LAND"));
	}
	
	
	/*
	 * NEGATIVE TEST
	 * 
	 * kingdom is LAND and its emblem is Panda
	 * Input message: LAND OTBBMUFDICCSO
	 * Output: false
	 * 
	 */
	@Test
	public void sendingMessageToLandKingdomShouldNotAddItAsAnAlly() {
		String inputLine = "LAND OTBBMUFDICCSO";
		kingdom.sendMessage(inputLine);
		Assert.assertFalse("Land kingdom shouldn't be an ally of Space Kingdom.", kingdom.getAllies().contains("LAND"));
	}

	@Test(expected = InvalidMessageFormatException.class)
	public void sendingInvalidFormattedMessageToLandKingdomShouldThrowInvalidMessageFormatException1() {
		String inputLine = "LAND ";
		kingdom.sendMessage(inputLine);
		Assert.assertFalse("Land kingdom shouldn't be an ally of Space Kingdom.", kingdom.getAllies().contains("LAND"));
	}

	@Test(expected = InvalidMessageFormatException.class)
	public void sendingInvalidFormattedMessageToLandKingdomShouldThrowInvalidMessageFormatException2() {
		String inputLine = "LAND ASD234ASCZX";
		kingdom.sendMessage(inputLine);
		Assert.assertFalse("Land kingdom shouldn't be an ally of Space Kingdom.", kingdom.getAllies().contains("LAND"));
	}
}
