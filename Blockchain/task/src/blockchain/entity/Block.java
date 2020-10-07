package blockchain.entity;

import blockchain.util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int zerosQuantity;
    private final long id;
    private long timeStamp;
    private final String previousHash;
    private final String hash;
    private int magicNumber;
    private double generationTimeSec;
    private int miner;
    private String zerosString;
    private List<String> messageHistory;

    private String messagesAsString() {
        StringBuilder messagesSB = new StringBuilder();
        for (String message : messageHistory) {
            messagesSB.append(message);
        }
        return messagesSB.toString();
    }

    public Block(List<Block> blockChain, int miner, ArrayList<String> messageHistory) {
        this.messageHistory = (List<String>) messageHistory.clone();
        timeStamp = System.currentTimeMillis();
        this.miner = miner;
        id = blockChain.size() + 1;
        if (id == 1) {
            previousHash = "0";
        } else {
            previousHash = blockChain.get(blockChain.size() - 1).hash;
        }
        hash = generateHash();
    }

    private String generateHash() {
        String foundHash;
        char[] zeroChars = new char[zerosQuantity];
        for (int i = 0; i < zerosQuantity; i++) {
            zeroChars[i] = '0';
        }

        String stringOfZeros = String.valueOf(zeroChars);
        long startTime = System.currentTimeMillis();
        magicNumber = 0;
        Random randomInt = new Random(System.currentTimeMillis());
        //messageHistory = messageGenerator.getMessageListAsString();
        foundHash = StringUtil.applySha256(previousHash + id + timeStamp + magicNumber + miner + zerosQuantity + messageHistory);
        while (true){
            if (foundHash.substring(0, zerosQuantity).equals(stringOfZeros)){
                generationTimeSec = (System.currentTimeMillis() - startTime) / 1000.;
                break;
            }
            magicNumber = randomInt.nextInt();
            foundHash = StringUtil.applySha256(previousHash + id + timeStamp + magicNumber);
        }

        if (generationTimeSec < 16) {
            changeN(1);
            zerosString = "N was increased to 1";
        } else if (generationTimeSec >= 16 && generationTimeSec <= 60 ) {
            zerosString = "N stays the same";
        } else {
            changeN(-1);
            zerosString = "N was decreased by 1";
        }
        return foundHash;
    }

    @Override
    public String toString() {

        return "Block:"
                +"\nCreated by miner # " + miner
                + "\nId: " + id
                + "\nTimestamp: " + timeStamp
                + "\nMagic number: " + magicNumber
                + "\nHash of the previous block:\n" + previousHash
                + "\nHash of the block:\n" + hash
                + "\nBlock data:" + (id == 1 ? " no messages" : messagesAsString())
                + "\nBlock was generating for " + generationTimeSec +" seconds"
                + "\n" + zerosString + "\n";
    }

    private synchronized  void changeN(int n) {
        zerosQuantity += n;
    }

    public long getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }
}
