package puzzle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board implements Serializable {
    public List<Integer> cells; //the game space
    public int left;//peg left
    List<Integer> getCells(){return this.cells;}
    int getLeft(){return this.left;}

    Board(){
        this.cells=new ArrayList<Integer>(Collections.nCopies(15,1));
        this.left=14; }

    }


