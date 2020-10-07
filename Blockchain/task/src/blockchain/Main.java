package blockchain;

import blockchain.entity.BlockChain;
import blockchain.entity.MessageGenerator;
import blockchain.entity.Miner;
import blockchain.util.SerializationUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
           ExecutorService executorService = Executors.newFixedThreadPool(5);
           BlockChain blockChain = new BlockChain();
           while (true) {
               executorService.submit(new Miner(blockChain));
               Thread.sleep(100);
               if (blockChain.getSize() > 5) {
                   executorService.shutdown();
                   break;
               }
           }
           blockChain.printBlocks(5);
        System.out.println("validated: " + blockChain.validateBlocks());
        try {
            if (!executorService.awaitTermination(60, TimeUnit.MILLISECONDS)) {
                System.out.println("Still waiting termination");
                System.exit(0);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //SerializationUtils.serialize(blockChain, "blockChain.bin");
        //BlockChain blockChainFromFile = (BlockChain) SerializationUtils.deserialize("blockChain.bin");
        //System.out.println("serialization validated: " + blockChainFromFile.validateBlocks());
    }
}
