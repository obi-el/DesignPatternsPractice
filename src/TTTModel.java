import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;

public class TTTModel extends Observable{
	private int dim;
	private String none = "*";
	private String board[][]; 
    private boolean turn;
    private HashMap<Boolean,String> map;
    private enum Status {A,B,C,D};
    private  Status currentStatus;
	
    public TTTModel(int size){
    	dim = size;
		this.board = new String[dim][dim];
		initBoard();
		turn = true;
		currentStatus = Status.A;
		map = new HashMap<Boolean, String>();
		map.put(true, "X");
		map.put(false, "O");
	}
    
    /*
     * inverts current turn
     */
    public void changeTurn(){
    	turn = !turn;
    }
    
    /*
     * initialise the board
     */
    public void initBoard(){
    	for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				board[i][j] = none;
			}
    	}	
    }
    
    
    
    /*
     * gets the current letter turn, X or O
     */
    public String getTurnChar(){
    	return map.get(turn);
    }
    
    /*
     * gets the current letter turn
     */
    public String getEachTurnChar(int x, int y){
    	if(board[x][y] != none)return  board[x][y];
    	else return"*";
    }
    
    /**
	 * @return the dim
	 */
	public int getDim() {
		return dim;
	}

	/*
     * checks if current location is playable
     */
    private boolean canPlay(int x, int y){
    	if(hasWonCols(x) || hasWonRows(y) ||hasWonDiag(x,y)){
    	   
    		if(turn)currentStatus = Status.B;
    		else currentStatus = Status.C;
    		return false;
    	}
    	
    	
    	if(boardFull()){
    		 currentStatus = Status.D;
    		return false;
    	}
    	
    	if(board[x][y].equals(none))return true;
    	else return false;  	
    }
    
    /*
     * makes a play and changs the turn
     */
    public void play(int x , int y){
    	
    	if(!canPlay(x,y))return;
    	 
    	 System.out.println(hasWonCols(x));
 	    System.out.println(hasWonRows(y));
 	    System.out.println(hasWonDiag(x,y));
    	
    	board[x][y] = map.get(turn);
    	changeTurn();
    	this.setChanged();
    	this.notifyObservers();
    	
    	
    	
    }
    
    /*
     * check if the board is full
     */
    public boolean boardFull(){
    	boolean retval = true;
    	for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				if(board[i][j].equals(none))return false;
			}
    	}	
    	return retval;
    }

    public boolean hasWonCols(int y){  
    	int count = 0;
    	for(int i = 0; i < dim; i++){
    		if(!(board[i][y].equals(map.get(turn)))){
    			System.out.println(i + " " + y);
    			return false;
    	}
    		count++;
    		
    	}
    	System.out.println(count);
    	if (count == 3)return true;
    	else return false;
    }
    
    public boolean hasWonRows(int x){
    	
				
    	int count = 0;   
    	for(int i = 0; i < dim; i++){
    		 
    		if(!(board[x][i].equals(map.get(turn)))){
    			return false;
    		}
    	}
    	count++;
    	
    	if (count == 3)return true;
    	else return false;
    }
    
    public boolean hasWonDiag(int x, int y){
    	for(int i = 0;i<dim;i++){
    		if(board[i][(dim-1)-i].equals(none)  ||board[i][(dim-1)-i] != map.get(turn))
    			return false;
    		if(i == dim-1){
    			//report win for s
    			return true;
    		}
    	}
    	
    	if(x == y){
    		//we're on a diagonal
    		for(int i = 0; i < dim; i++){
    			if(board[i][i].equals(none)  ||board[i][i] != map.get(turn))
    				return false;
    			if(i == dim-1){
    				//report win for s
    			}
    		}
    	}
    	
    	return true;	
    }

    public String gameStatus(){
    	String result="";
    	switch(currentStatus){
    	case B: 
    		result = "X Won the game";
    		break;
    	case A: 
    		result = getTurnChar() + "'s turn";
    		break;
    	case C: 
    		result = "O Won the game";
    		break;	
    	case D: 
    		result = "Board Full, No one won the game";
    		break;
    	}
    	return result;
    }
    
    /*
     * Restart the game
     */
    public void restart() {
    	this.board = new String[dim][dim];
    	initBoard();
		turn = true;
		currentStatus = Status.A;
		map = new HashMap<Boolean, String>();
		map.put(true, "X");
		map.put(false, "O");
		
		this.setChanged();
		this.notifyObservers();
	}
    

	/**
	 * @return the currentStatus
	 */
	public Status getCurrentStatus() {
		return currentStatus;
	}
    
	public void printBoard(){
		for(int i = 0; i < dim; i++)
			for(int j = 0; j < dim; j++){
				System.out.print(board[i][j] + "\t");
			}    	
		    System.out.println();
    	
	}

	/**
	 * @return the board
	 */
	public String[][] getBoard() {
		return board;
	}

	public static void main(String[] args){
		TTTModel mod = new TTTModel(3);
		//mod.printBoard();
		System.out.println(mod.getCurrentStatus());
		mod.play(0,0);
		mod.play(0,1);
		mod.play(1,0);
		mod.play(1,1);
		mod.play(2,0);
		mod.play(2,1);
		//System.out.println(mod.getCurrentStatus());
		//mod.play(2,2);

		mod.printBoard();
		System.out.println(mod.getCurrentStatus());
	}
}

