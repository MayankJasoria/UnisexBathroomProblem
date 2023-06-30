import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UnisexBathroom {
    public static void main(String[] args) throws InterruptedException {
        int capacity = ThreadLocalRandom.current().nextInt(1, 6);
        final BathroomManager bathroomManager = new BathroomManager(capacity);

        int men = ThreadLocalRandom.current().nextInt(16);
        int women = ThreadLocalRandom.current().nextInt(16);
        System.out.println("Bathroom capacity: " + capacity + ", men: " + men + ", women: " + women);

        int people = men + women;
        List<Thread> threads = new ArrayList<>(people);
        for (int i = 0, menId = 1, womenId = 1; i < people; ++i) {
            Person person = null;
            if (women == 0) {
                person = new Man(menId++, bathroomManager);
                --men;
            } else if (men == 0) {
                person = new Woman(womenId++, bathroomManager);
                --women;
            } else {
                int selector = ThreadLocalRandom.current().nextInt(0, 2);
                if (selector == 0) {
                    person = new Man(menId++, bathroomManager);
                    --men;
                } else {
                    person = new Woman(womenId++, bathroomManager);
                    --women;
                }
            }
            Thread thread = new Thread(person);
            threads.add(thread);
            thread.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("All " + people + " people have used the bathroom.");
    }
}
