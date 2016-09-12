public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
        Field field = new Field(4);
//        field.field = new short[][]{
//                {9,11,2,1},
//                {10,12,6,5},
//                {8,4,15,14},
//                {0,3,7,13}};
        field = new Field(4);
//        field.field = new short[][]{
//                {1,4,8,6},
//                {5,10,2,12},
//                {9,14,7,15},
//                {0,13,3,11}};
        field.field = new short[][]{
                {5,11,7,9},
                {3,15,12,4},
                {1,14,13,2},
                {6,8,10,0}};

//        field.field = new short[][]{
//                {0,1,2,3},
//                {5,6,7,4},
//                {9,10,11,8},
//                {13,14,15,12}};
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
        solver.solve(field.getLong());

    }
}
