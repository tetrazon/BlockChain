package blockchain;

import blockchain.entity.BlockChain;
import blockchain.util.SerializationUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Scanner scanner = new Scanner(System.in);
       // System.out.println("Enter how many zeros the hash must start with: ");
        //int zeros = scanner.nextInt();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        BlockChain blockChain = new BlockChain();
        while (blockChain.getSize() < 5) {
            executorService.submit(() -> blockChain.addBlock((int) Thread.currentThread().getId()));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(60, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //BlockChain.setZerosQuantity(zeros);
        //long start = System.currentTimeMillis();
        /*for (int i = 0; i < 5; i++) {
            blockChain.addBlock();
        }*/
        //System.out.println("validated: " + blockChain.validateBlocks());
        blockChain.printBlocks();
        //SerializationUtils.serialize(blockChain, "blockChain.bin");
        //BlockChain blockChainFromFile = (BlockChain) SerializationUtils.deserialize("blockChain.bin");
        //System.out.println("serialization validated: " + blockChainFromFile.validateBlocks());
    }
}
