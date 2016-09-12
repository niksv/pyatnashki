import java.util.Arrays;

class LongField {

    private static final long FIN_LONG = Long.valueOf("1147797409030816545");

    static boolean isFinal(long l) {
        return l == FIN_LONG;
    }

    public static void print(long l) {
        String res = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                long shift = 4 * (i * 4 + j);
                long temp = l >> shift;
                long val = temp & (long)(15);
                res += val + " ";
            }
            res += "\n";
        }
        res += "\n";
        System.out.println(res);
    }

    static long getLong(short[][] field){
        long res = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                long val = field[i][j];
                val = val << (4 * (i * field.length + j));
                res |= val;
            }
        }
        return res;
    }

    static int getZero(long l) {
        long mask = 15;
        for (int i = 0; i < 16; i++) {
            if ((l & mask) == 0) {
                return i;
            }
            mask = mask << 4;
        }
        return -1;
    }

    static long move(long field, int zero, int pos) {
        long value = (field >> (pos * 4)) & (long)15;
        field |= value << (zero * 4);
        field &= ~(((long)15) << (pos * 4));
        return field;
    }
}
