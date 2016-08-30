import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Store {
    Queue<Field> queue;

    public Store(Field field) {
        this.queue = new LinkedList<>();
        this.queue.add(field);
    }

    synchronized public Field get() {
        return queue.poll();
    }

    public void put(Field field) {
//        if(queue.size() % 1000000 == 0) {
//            System.out.println("Queue size: " + queue.size());
//        }
        queue.add(field);
    }

    synchronized public void putAll(List<Field> fields) {
//        if(queue.size() % 1000000 == 0) {
//            System.out.println("Queue size: " + queue.size());
//        }
        queue.addAll(fields);
    }
}