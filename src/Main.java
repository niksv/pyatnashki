public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
        Field field = new Field(4);
        field.field = new short[][]{
                {9,11,2,1},
                {10,12,6,5},
                {8,4,15,14},
                {0,3,7,13}};
        field = new Field(4);
//        field.field = new short[][]{
//                {1,4,8,6},
//                {5,10,2,12},
//                {9,14,7,15},
//                {0,13,3,11}};
//        field.field = new short[][]{
//                {1,2,0,3},
//                {15,5,11,4},
//                {12,7,13,6},
//                {14,9,10,8}};

        field.field = new short[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {0,13,14,15}};
//        System.out.println(Field.isFinal(field.getLong()));
//        System.out.println(field.isFinal());
//        Field field = new Field(3);
//        field.field = new short[][]{
//                {5,4,2},
//                {7,1,3},
//                {8,6,0}};
//                {0,1},
//                {2,3},
//        };
//        Thread.sleep(1000);
        Solver solver = new Solver(LongField.getLong(field.field));
        solver.solveThreads(Integer.parseInt("1"), field.getLong());

    }
}
