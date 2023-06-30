import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BathroomManager {

    private final int capacity;
    private final Queue<Person> queue;
    private int users;
    private Boolean userType; // null = unoccupied false = woman, true = man

    public BathroomManager(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    private synchronized boolean isEligible(Person person) {
        if (queue.isEmpty() || person.equals(queue.peek())) {
            if (Objects.isNull(userType)) {
                return true;
            } else if (users == capacity) {
                return false;
            }
            return userType == person instanceof Man;
        }
        return false;
    }

    public void useBathroom(Person person) throws InterruptedException {
        synchronized (this) {
            queue.offer(person);
        }
        while (!isEligible(person)) {
            synchronized (person) {
                person.wait();
            }
        }
        // person has entered successfully
        Person nextPerson = null;
        synchronized (this) {
            if (!queue.isEmpty()) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                nextPerson = queue.peek();
            }
            System.out.println(((person instanceof Man) ? "Man-" : "Woman-") + person.number + " entered the bathroom.");
            ++users;
            userType = person instanceof Man;
        }
        if (Objects.nonNull(nextPerson)) {
            synchronized (nextPerson) {
                nextPerson.notify();
            }
        }
    }

    public void exitBathroom(Person person) {
        Person nextPerson = null;
        synchronized (this) {
            System.out.println(((person instanceof Man) ? "Man-" : "Woman-") + person.number + " left the bathroom.");
            --users;
            if (users == 0) {
                userType = null;
            }
            if (!queue.isEmpty()) {
                nextPerson = queue.peek();
            }
        }

        if (Objects.nonNull(nextPerson)) {
            synchronized (nextPerson) {
                nextPerson.notify();
            }
        }
    }
}
