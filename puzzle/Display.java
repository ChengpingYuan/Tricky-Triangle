package puzzle;

public class Display {
    Display(){System.out.println("===================");}
    //print the board state.
    void show(Board b){
        System.out.println("displaying");
        System.out.println("     "+b.cells.get(0)+"     ");
        System.out.println("   "+b.cells.get(1)+"   "+b.cells.get(2)+"   ");
        System.out.println("  "+b.cells.get(3)+"  "+b.cells.get(4)+"  "+b.cells.get(5)+"  ");
        System.out.println(" "+b.cells.get(6)+"  "+b.cells.get(7)+"  "+b.cells.get(8)+"  "+b.cells.get(9)+" ");
        System.out.println(b.cells.get(10)+"  "+b.cells.get(11)+"  "+b.cells.get(12)+"  "+b.cells.get(13)+"  "+b.cells.get(14));
    }

}
