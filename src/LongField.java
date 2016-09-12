import java.util.Arrays;

class LongField {
    private static final long FIN_LONG = Long.valueOf("1147797409030816545");
    private static int[][] weights;

    static {
        weights = new int[16][];
        for (int i = 0; i < 16; i++) {
            weights[i] = new int[16];
            int posX = (i - 1) / 4;
            int posY = (i - 1) % 4;

            if (i == 0) {
                posX = 3;
                posY = 3;
            }

            for (int j = 0; j < 16; j++) {
                int x = j / 4;
                int y = j % 4;
                weights[i][j] = Math.abs(posX - x) + Math.abs(posY - y);
            }
        }
    }

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

    static int getWeight(long field) {
        int weight = 0;
        long mask = ((long)15);
        for (int i = 0; i < 16; i++) {
            long value = (field >> (i * 4)) & mask;
            weight += weights[(int) value][i];
        }
        return weight;
    }
}
