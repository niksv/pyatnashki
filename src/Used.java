import java.util.HashSet;
import java.util.Set;

class Used {
    Set<Long> set = new HashSet<>();

    synchronized boolean add(Long hash) {
        return set.add(hash);
    }
}
