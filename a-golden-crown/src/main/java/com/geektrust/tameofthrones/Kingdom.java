package com.geektrust.tameofthrones;

import com.geektrust.tameofthrones.exceptions.InvalidMessageFormatException;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.geektrust.tameofthrones.Constants.*;

/**
 * Each Kingdom is part of Southeros universe.
 * Kingdoms have the following data members.
 * name - kingdom's name
 * emblem - kingdom's emblem
 * ally - true if the kingdom is an ally of ruler otherwise false
 *
 * @author sushil
 */
public class Kingdom {
    private final String name;
    private final String emblem;
    private final Set<Kingdom> allies;

    public Kingdom(String name, String emblem) {
        super();
        this.name = name;
        this.emblem = emblem;
        this.allies = new LinkedHashSet<>();
    }



    private Message getMessage(String inputLine) {
        String[] in = inputLine.split(SPACE, INPUT_LINE_SPLIT_LIMIT);
        Kingdom spaceKingdom = Southeros.getInstance().getKingdom(KINGDOM_SPACE);
        Kingdom receiver = Southeros.getInstance().getKingdom(in[0]);
        String message = in[1];
        return new Message(spaceKingdom, message, receiver);
    }

    /**
     * Message should contain alphabets and/or spaces only
     *
     * @param line line of the input file
     */
    private void validateMessage(String line) {
        if (line.split(SPACE).length < MINIMUM_LENGTH_FOR_CORRECT_FORMAT) {
            throw new InvalidMessageFormatException("Input line should be in the format: KingdomName <SPACE> MessageToKingdom. line:" + line);
        }
        if (!Pattern.matches(MESSAGE_REGEX, line)) {
            throw new InvalidMessageFormatException("Input contains characters other than alphabets & spaces. line:" + line);
        }
    }

    /* ==================================
     * PUBLIC METHODS
     * ==================================
     */

    public String getEmblem() {
        return this.emblem;
    }

    public String getName() {
        return name;
    }

    /**
     * Takes a message as input and verifies it using {@link MessageVerifier#verify(String, String) verifier}
     * and if it returns true, add the kingdom to the list of allies
     *
     * @param line input message (receiver kingdom <SPACE> actual message)
     */
    public void sendMessage(String line) {
        validateMessage(line);
        Message message = getMessage(line);
        Kingdom receiverKingdom = message.getReceiver();
        String msg = message.getMessage();
        boolean response = MessageVerifier.verify(msg, receiverKingdom.getEmblem());
        if (response) {
            this.allies.add(receiverKingdom);
        }
    }

    /**
     * @return list of allies(kingdom's name) for the kingdom
     */
    public List<String> getAllies() {
        return Optional.ofNullable(allies)
                .orElse(new LinkedHashSet<>())
                .stream()
                .map(Kingdom::getName)
                .collect(Collectors.toList());
    }

    /**
     * Checks if number of allies >= {@value Constants#WINS_NEEDED}
     *
     * @return true if number of allies for the kingdom is equal or greater than {@value Constants#WINS_NEEDED}, otherwise false
     */
    public boolean isRuler() {
        return this.allies.size() >= WINS_NEEDED;
    }
}