package com.geektrust.tameofthrones;

import com.geektrust.tameofthrones.exceptions.InvalidKingdomNameException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author sushil
 */
public class SoutherosTest {
    private Southeros southeros;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field instance = Southeros.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
        southeros = Southeros.getInstance();
    }

    @Test
    public void getKingdomTest() {
        List<String> kingdomNames = Arrays.asList("SPACE", "LAND", "AIR", "WATER", "ICE", "FIRE");
        List<String> namesOfKingdomsCreated = kingdomNames.stream()
                .map(southeros::getKingdom)
                .map(Kingdom::getName)
                .collect(Collectors.toList());
        Assert.assertEquals(namesOfKingdomsCreated, kingdomNames);
    }

    @Test(expected = InvalidKingdomNameException.class)
    public void getKingdomThrowsInvalidKingdomNameException() {
        southeros.getKingdom("SHIP");
    }

    @Test
    public void getRulerShouldReturnSpaceKingdom() {
        String kingdomName = "SPACE";
        Kingdom spaceKingdom = southeros.getKingdom(kingdomName);
        getInputForGetKingdomPositiveTest().forEach(spaceKingdom::sendMessage);
        Optional<Kingdom> kingdomOpt = southeros.getRuler();
        if(!kingdomOpt.isPresent()) {
            Assert.fail();
            return;
        }
        Kingdom rulerKingdom = kingdomOpt.get();
        Assert.assertEquals(rulerKingdom.getName(), kingdomName);
    }

    private List<String> getInputForGetKingdomPositiveTest() {
        return Arrays.asList("LAND PANDAUFSI",
                "AIR ZORRO",
                "WATER OCTO VJAVWBZ PUS",
                "FIRE DRAGON JXGMUT"
        );
    }

    @Test
    public void getRulerShouldReturnNone() {
        String kingdomName = "SPACE";
        Kingdom spaceKingdom = southeros.getKingdom(kingdomName);
        getInputForGetKingdomNegativeTest().forEach(spaceKingdom::sendMessage);
        Optional<Kingdom> kingdomOpt = southeros.getRuler();
        Assert.assertFalse("Ruler should be None.", kingdomOpt.isPresent());
    }

    private List<String> getInputForGetKingdomNegativeTest() {
        return Arrays.asList("LAND PANDAUFSI",
                "WATER OCTO VJAVWBZ PUS",
                "FIRE DRAGON JXGMUT"
        );
    }
}