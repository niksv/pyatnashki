import java.util.HashSet;
import java.util.Set;

public class Used {
    Set<Long> set = new HashSet<>();

//    public synchronized boolean contains(int hash) {
//        return set.contains(hash);
//    }

    synchronized public boolean add(long hash) {
        return set.add(hash);
    }



}
