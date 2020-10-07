package blockchain.entity;

public class Miner implements Runnable {
    private BlockChain chain;
    private String miner;

    public Miner(BlockChain chain) {
        this.chain = chain;
    }

    private synchronized void generateBlock() {
        String[] s = Thread.currentThread().getName().split("-");
        chain.addBlock(Integer.parseInt(s[s.length - 1]));

    }

    @Override
    public void run() {
        String[] s = Thread.currentThread().getName().split("-");
        this.miner = s[s.length - 1];
        String message = MessageGenerator.generateMessage();
        chain.sendMessage(message);
        generateBlock();
    }
}
