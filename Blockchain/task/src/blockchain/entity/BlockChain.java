package blockchain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BlockChain implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Block> blockChain = new LinkedList<>();
    private List<String> messages = new ArrayList<>();

    public void sendMessage(String message) {
        messages.add(message);
    }

    public void addBlock(int miner) {
            blockChain.add(new Block(blockChain, miner, (ArrayList<String>) messages));
            messages.clear();
    }
    public int getSize() {
        return blockChain.size();
    }

    public void printBlocks(int quantity){
        if (quantity <0) {
            throw new IllegalArgumentException("Bad quantity");
        }
        if (quantity == 0) {
            return;
        }
        if (quantity == 1) {
            System.out.println(blockChain.get(0));
            return;
        }

        blockChain = blockChain.subList(0, quantity);
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
            if(!(blockChain.get(i - 1).getHash().equals(blockChain.get(i).getPreviousHash()))) {
                validated = false;
                break;
            }
        }
        return validated;
    }


}
