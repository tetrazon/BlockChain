package blockchain;

import blockchain.entity.BlockChain;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockChain blockChain = new BlockChain();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(10);
            blockChain.addBlock();
        }
        //System.out.println("validated: " + blockChain.validateBlocks());
        blockChain.printBlocks();
    }
}
