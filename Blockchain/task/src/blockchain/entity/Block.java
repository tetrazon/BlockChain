package blockchain.entity;

import blockchain.util.StringUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Block implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idGenerator = 1;
    private final long id;
    private long timeStamp;
    private final String previousHash;
    private final String hash;
    private int magicNumber;
    private long generationTimeSec;

    public Block(List<Block> blockChain, int zerosQuantity) {
        timeStamp = System.currentTimeMillis();
        id = idGenerator++;
        if (id == 1) {
            previousHash = "0";
        } else {
            previousHash = blockChain.get(blockChain.size() - 1).hash;
        }
        hash = generateHash(zerosQuantity);
    }

    private String generateHash(int zerosQuantity) {
        String foundHash;
        char[] zeroChars = new char[zerosQuantity];
        for (int i = 0; i < zerosQuantity; i++) {
            zeroChars[i] = '0';
        }

        String stringOfZeros = String.valueOf(zeroChars);
        long startTime = System.currentTimeMillis();
        magicNumber = 0;
        Random randomInt = new Random(System.currentTimeMillis());
        foundHash = StringUtil.applySha256(previousHash + id + timeStamp + magicNumber);
        while (true){
            if (foundHash.substring(0, zerosQuantity).equals(stringOfZeros)){
                generationTimeSec = (System.currentTimeMillis() - startTime) / 1000;
                break;
            }
            magicNumber = randomInt.nextInt();
            foundHash = StringUtil.applySha256(previousHash + id + timeStamp + magicNumber);
        }
        return foundHash;
    }

    @Override
    public String toString() {
        return "Block:\n" + "Id: " + id
                + "\nTimestamp: " + timeStamp
                + "\nMagic number: " + magicNumber
                + "\nHash of the previous block:\n" + previousHash
                + "\nHash of the block:\n" + hash
                + "\nBlock was generating for " + generationTimeSec +" seconds" + "\n";
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
