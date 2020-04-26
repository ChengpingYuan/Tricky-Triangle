package puzzle;

import org.apache.commons.lang3.SerializationUtils;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    Solver(int start){
        this.start=start;
        System.out.println("starting solver: "+start);
        this.legal_moves.add(Triplet.with(0,1,3));
        this.legal_moves.add(Triplet.with(0,2,5));
        this.legal_moves.add(Triplet.with(1,3,6));
        this.legal_moves.add(Triplet.with(1,4,8));
        this.legal_moves.add(Triplet.with(2,4,7));
        this.legal_moves.add(Triplet.with(2,5,9));
        this.legal_moves.add(Triplet.with(3,6,10));
        this.legal_moves.add(Triplet.with(3,7,12));
        this.legal_moves.add(Triplet.with(4,7,11));
        this.legal_moves.add(Triplet.with(4,8,13));
        this.legal_moves.add(Triplet.with(5,8,12));
        this.legal_moves.add(Triplet.with(5,9,14));
        this.legal_moves.add(Triplet.with(3,4,5));
        this.legal_moves.add(Triplet.with(6,7,8));
        this.legal_moves.add(Triplet.with(7,8,9));
        this.legal_moves.add(Triplet.with(10,11,12));
        this.legal_moves.add(Triplet.with(11,12,13));
        this.legal_moves.add(Triplet.with(12,13,14));

        this.legal_moves.add(Triplet.with(3,1,0));
        this.legal_moves.add(Triplet.with(5,2,0));
        this.legal_moves.add(Triplet.with(6,3,1));
        this.legal_moves.add(Triplet.with(8,4,1));
        this.legal_moves.add(Triplet.with(7,4,2));
        this.legal_moves.add(Triplet.with(9,5,2));
        this.legal_moves.add(Triplet.with(10,6,3));
        this.legal_moves.add(Triplet.with(12,7,3));
        this.legal_moves.add(Triplet.with(11,7,4));
        this.legal_moves.add(Triplet.with(13,8,4));
        this.legal_moves.add(Triplet.with(12,8,5));
        this.legal_moves.add(Triplet.with(14,9,5));
        this.legal_moves.add(Triplet.with(5,4,3));
        this.legal_moves.add(Triplet.with(8,7,6));
        this.legal_moves.add(Triplet.with(9,8,7));
        this.legal_moves.add(Triplet.with(12,11,10));
        this.legal_moves.add(Triplet.with(13,12,11));
        this.legal_moves.add(Triplet.with(14,13,12));

        this.board=new Board();
        this.board.cells.set(start,0);
        this.solution=new ArrayList<Board>();
        this.solution.add(this.board);
    }
    int start;
    List<Triplet<Integer,Integer,Integer>> legal_moves=new ArrayList<Triplet<Integer, Integer, Integer>>();
    Board board;
    List<Board> solution;
    private boolean solved=false;
    //check if the move is legit
    boolean checkMove(Triplet<Integer,Integer,Integer> move, Board b){
        if (b.cells.get(move.getValue0()) == 1 && b.cells.get(move.getValue1()) == 1 && b.cells.get(move.getValue2()) == 0)
        return true;
        else return false;}
    //make the move
    Board move(Triplet<Integer, Integer, Integer> move, Board board) {
        if (board.cells.get(move.getValue0()) == 1 && board.cells.get(move.getValue1()) == 1 && board.cells.get(move.getValue2()) == 0) {
            board.cells.set(move.getValue0(),0);
            board.cells.set(move.getValue1(),0);
            board.cells.set(move.getValue2(),1);
            board.left--;
            return board;
        }
        else {return null;}
    }

    //take a move back
    Board moveback(Triplet<Integer,Integer,Integer> move, Board board){
        board.cells.set(move.getValue0(),1);
        board.cells.set(move.getValue1(),1);
        board.cells.set(move.getValue2(),0);
        board.left++;
        return board;
    }
    //run the solver
    void run(){
        List<Triplet<Integer,Integer,Integer>> solver_moves=new ArrayList<Triplet<Integer, Integer, Integer>>();
        solve(this.board,solver_moves);
        System.out.println("=====Solution======");
        Display display=new Display();
        for(Board solution:this.solution){
            display.show(solution);
        }

    }
    //solve the game recursively
    void solve(Board board, List<Triplet<Integer,Integer,Integer>> solver_moves){
        if((board.left < 2 || solved) == true){
            solved=true;
            return;
        }
        for(int peg=0;peg<15;peg++)
        {
            if(board.cells.get(peg)==0) continue;
            for(Triplet<Integer,Integer,Integer> move: this.legal_moves){
                if(move.getValue0()==peg){
                    if(checkMove(move,board)==false) continue;
                    Board saved= SerializationUtils.clone(board);
                    move(move,saved);
                    this.solution.add(saved);
                    solver_moves.add(move);
                    solve(saved,solver_moves);
                    if (saved.left<2||solved==true)
                    {solved=true;
                        return;}
                    moveback(move,saved);
                    this.solution.remove(this.solution.size()-1);
                    solver_moves.remove(solver_moves.size()-1);
                }
            }
        }
    }
}
