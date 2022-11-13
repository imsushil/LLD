package com.geektrust.tameofthrones;

import com.geektrust.tameofthrones.exceptions.InvalidKingdomNameException;
import org.junit.Assert;
import org.junit.Test;

public class MessageVerifierTest {

	/*
	 * emblem: Owl
	 * message: OWLAOWLBOWLC
	 * returns true
	 * 
	 */
	@Test 
	public void givenCorrectMessageWhenVerifyThenTrue() {
		String emblem = "Panda";
		String message = "FDIXXSOKKOFBBMU";
		boolean result = MessageVerifier.verify(message, emblem);
		Assert.assertTrue(result); 
	}

	/*
	 * emblem: Owl
	 * message: OWLAOWLBOWLC
	 * returns false
	 * 
	 */
	@Test
	public void givenIncorrectMessageWhenVerifyThenFalse() throws InvalidKingdomNameException {
		String emblem = "Fire";
		String message = "JXGAMUT";
		boolean result = MessageVerifier.verify(message, emblem);
		Assert.assertFalse(result);
	}

}
