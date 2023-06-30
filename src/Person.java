import java.util.concurrent.ThreadLocalRandom;

public class Person implements Runnable {
    protected int number;
    protected BathroomManager bathroomManager;

    @Override
    public void run() {
        try {
            bathroomManager.useBathroom(this);
            timeInBathroom();
            bathroomManager.exitBathroom(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void timeInBathroom() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
    }
}
