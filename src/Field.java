import sun.rmi.runtime.Log;

import java.util.Arrays;

public class Field implements Cloneable {
    public short[][] field;
//    int size;

    public Field(int size) {
//        this.size = size;
        field = new short[size][];
        for (int i = 0; i < size; i++) {
            field[i] = new short[size];
        }
    }

    public Field(long l) {
        field = new short[4][];
        for (int i = 0; i < 4; i++) {
            field[i] = new short[4];
            for (int j = 0; j < 4; j++) {
                long shift = 4 * (i * field.length + j);
                long temp = l >> shift;
                long val = temp & (long)(15);
                field[i][j] = (short) val;
            }
        }
    }

    public static boolean isFinal(long l) {
        return l == Long.valueOf("1147797409030816545");
    }

//    public boolean isFinal() {
//        for (int i = 0; i < field.length * field.length - 1; i++) {
//            if (this.field[i / field.length][i % field.length] != (i + 1))
//                return false;
//        }
//        return field[field.length - 1][field.length - 1] == 0;
//    }

    public long getLong(){
        long res = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                long val = field[i][j];
                val = val << (4 * (i*field.length + j));
                res |= val;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        String res = "";

        for (short[] line : field) {
            res += Arrays.toString(line) + "\n";
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field1 = (Field) o;

        return Arrays.deepEquals(field, field1.field);

    }

    @Override
    public int hashCode() {
        int res = 1;
        for (short[] shorts : field) {
            for (short i : shorts) {
                res = res * 31 + i;
            }
        }
        return res;
    }

    @Override
    protected Field clone() throws CloneNotSupportedException {
        Field field = new Field(this.field.length);
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field.length; j++) {
                field.field[i][j] = this.field[i][j];
            }
        }
        return field;
    }
}
