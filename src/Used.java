import java.util.HashSet;
import java.util.Set;

public class Used {
    Set<Integer> set = new HashSet<>(5000000);

//    public synchronized boolean contains(int hash) {
//        return set.contains(hash);
//    }

    synchronized public boolean add(int hash) {
        return set.add(hash);
    }



}
