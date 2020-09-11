package blockchain.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class BlockChain implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int zerosQuantity;
    private List<Block> blockChain = new LinkedList<>();

    public static void setZerosQuantity(int zeros) {
        zerosQuantity = zeros;
    }

    public void addBlock() {
        blockChain.add(new Block(blockChain, zerosQuantity));
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
            if(!(blockChain.get(i - 1).getHash().equals(blockChain.get(i).getPreviousHash()))) {
                validated = false;
                break;
            }
        }
        return validated;
    }


}
