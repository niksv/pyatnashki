import java.util.Comparator;

public class FieldComparator implements Comparator<Long> {
    @Override
    public int compare(Long o1, Long o2) {
        return Integer.compare(LongField.getWeight(o1), LongField.getWeight(o2));
    }
}
