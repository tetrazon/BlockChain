package blockchain;

import blockchain.entity.BlockChain;
import blockchain.util.SerializationUtils;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many zeros the hash must start with: ");
        int zeros = scanner.nextInt();
        BlockChain blockChain = new BlockChain();
        BlockChain.setZerosQuantity(zeros);
        //long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            blockChain.addBlock();
        }
        //System.out.println("validated: " + blockChain.validateBlocks());
        blockChain.printBlocks();
        SerializationUtils.serialize(blockChain, "blockChain.bin");
        BlockChain blockChainFromFile = (BlockChain) SerializationUtils.deserialize("blockChain.bin");
        System.out.println("serialization validated: " + blockChainFromFile.validateBlocks());
    }
}
