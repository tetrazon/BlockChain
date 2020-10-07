package blockchain.entity;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator {
    private static final String[] names = {"\nBob: ", "\nJack: ", "\nLinda: "};
    private static final String[] messages = {"Hi", "How are you?", "I'm fine"};

    private MessageGenerator() {
    }

    public static String generateMessage() {
        return names[(int) (System.currentTimeMillis() % 3)]
                + messages[(int) (System.currentTimeMillis() % 3)];
    }


}
