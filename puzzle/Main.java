package puzzle;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("hello");
        for (int i=0; i<5;i++){
            Solver solver =new Solver(i);
            Display disp= new Display();
            solver.run();
        }
    }
}
