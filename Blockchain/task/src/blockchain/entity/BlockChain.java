package blockchain.entity;

import blockchain.util.StringUtil;

import java.util.LinkedList;
import java.util.List;

public class BlockChain {
    private static int idGenerator = 1;
    private List<Block> blockChain = new LinkedList<>();

    public void addBlock() {
        blockChain.add(new Block());
    }

    public void printBlocks(){
        blockChain.forEach(System.out::println);
    }

    public boolean validateBlocks() {
        boolean validated = true;
        int sizeBlockChain = blockChain.size();
        if (sizeBlockChain == 0) {
            return false;
        }
        if (sizeBlockChain == 1) {
            return validated;
        }
        for (int i = 1; i < sizeBlockChain; i++) {
            if(!(blockChain.get(i - 1).hash.equals(blockChain.get(i).previousHash))) {
                validated = false;
                break;
            }
        }
        return validated;
    }
    private class Block {
        private final long id;
        private long timeStamp;
        private final String previousHash;
        private final String hash;

        public Block() {
            timeStamp = System.currentTimeMillis();
            id = idGenerator++;
            if (id == 1) {
                previousHash = "0";
            } else {
                previousHash = blockChain.get(blockChain.size() - 1).hash;
            }
            hash = StringUtil.applySha256(previousHash + id + timeStamp);
        }

        @Override
        public String toString() {
            return "BlockChain:\n" + "Id: " + id + "\nTimestamp: " + timeStamp + "\nHash of the previous block:\n"
                    + previousHash + "\nHash of the block:\n" + hash  + "\n";
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

}
