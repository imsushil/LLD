package com.geektrust.tameofthrones;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import static com.geektrust.tameofthrones.Constants.*;

/**
 * MainApplication contains main method to run the program
 *
 * @author sushil
 */
public class MainApplication {

    public static void main(String[] args) throws IOException {
        Southeros southeros = Southeros.getInstance();
        String filePath = args[0];
        Kingdom spaceKingdom = Southeros.getInstance().getKingdom(KINGDOM_SPACE);

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(spaceKingdom::sendMessage);

            Optional<Kingdom> rulerOpt = southeros.getRuler();
            if (!rulerOpt.isPresent()) {
                System.out.println(NONE);
                return;
            }
            Kingdom ruler = rulerOpt.get();
            System.out.println(ruler.getName() + SPACE + String.join(SPACE, ruler.getAllies()));
        }
    }
}