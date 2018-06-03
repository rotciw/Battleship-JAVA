package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BattleShipBoard {
	
	private List<List<Cell>> board = new ArrayList();
	
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	private int rows, columns;
	private int horizontal = (int)(Math.random()*2);
	// om horizontal er 1 eller 2 skal den velge horizontal eller vertical
	int sunkCount = 0;
	//Antall treff
	int missCount = 0;
	//Antall bom
	private Stack<BattleShipAction> actionsTaken = new Stack<>();
	private Stack<BattleShipAction> redoStack = new Stack<>();
	
	public  BattleShipBoard(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		for(int i=0;i<rows;i++) {
			board.add(new ArrayList<Cell>());
			
			//lage en ny liste i Cell
			for (int j=0;j<columns; j++) {
				Cell cell = new Cell();
				board.get(i).add(cell);
				
				//
			}
		}
		//plasserer båtene
		setBattleShip();
		setHangar();
		setSubmarine();
		setFrigate();
		setPatrolBoat();
	}
	
	public Cell getCell(int row, int column) {
		if (row < 0 || column < 0 || row >= this.rows || column >= this.columns) {
			throw new Exception("You are outside of the board");
		}
			return this.board.get(row).get(column);
	}
	
	
	
	// generell funksjon for å plassere skipene avhengig av disse variablene
	//Brukes videre for å plassere de fem ulike skipene
	public void placeShip(int length, int width, int tempRow, int tempColumn) {
		for (int i=0;i<length;i++) {
			for (int j=0;j<width;j++) {
				// Går gjennom hele brettet
				this.getCell(tempRow+i, tempColumn+j).setShip();
			}
		this.getCell(tempRow+i, tempColumn).setShip();
		}
	}
	
	
	public void setHangar() {
		int length = 2;
		int width = 3;
		int tempRow = (int)(Math.random()*4);
		//Setter venstreside av skipet mellom rad 1 og 4
		int tempColumn = (int)(Math.random()*2);
		//Setter venstreside av skipet mellom kolonne 1 og 2
		if (horizontal == 1) {
			// Hvis vertikal
			placeShip(length, width, tempRow, tempColumn);
			}
		if (horizontal == 0) {
			//Hvis horisontal
			placeShip(width, length, tempColumn, tempRow);
		}
	}
	
	
	public void setBattleShip() {
		int length = 4;
		int width = 1;
		int tempRow = 5+(int)(Math.random()*2);
		//Setter venstreside av skipet mellom rad 5 og 9
		int tempColumn = (int)(Math.random()*4);
		//Setter venstreside av skipet mellom kolonne 1 og 5
		if (horizontal == 1) {
			// Hvis vertikal
			placeShip(length, width, tempRow, tempColumn);
			}
		if (horizontal == 0) {
			//Hvis horisontal
			placeShip(width, length, tempColumn, tempRow);
		}
	}
	
	public void setSubmarine() {
		int length = 3;
		int width = 1;
		int tempRow = (int)(Math.random()*2);
		//Setter venstreside av skipet mellom rad 1 og 5
		int tempColumn = 5+(int)(Math.random()*3);
		//Setter venstreside av skipet mellom kolonne 1 og 9
		if (horizontal == 1) {
			// Hvis vertikal
			placeShip(length, width, tempRow, tempColumn);
			}
		if (horizontal == 0) {
			//Hvis horisontal
			placeShip(width, length, tempColumn, tempRow);
		}
	}
	
	public void setFrigate() {
		int length = 3;
		int width = 1;
		int tempRow = 5+(int)(Math.random()*3);
		//Setter venstreside av skipet mellom rad 1 og 5
		int tempColumn = 4+(int)(Math.random()*4);
		//Setter venstreside av skipet mellom kolonne 1 og 9
		if (horizontal == 1) {
			// Hvis vertikal
			placeShip(length, width, tempRow, tempColumn);
			}
		if (horizontal == 0) {
			//Hvis horisontal
			placeShip(width, length, tempColumn, tempRow);
		}
	}
	
	public void setPatrolBoat() {
		int length = 2;
		int width = 1;
		int tempRow = (int)(Math.random()*9);
		//Setter venstreside av skipet mellom rad 1 og 10
		int tempColumn = 9+(int)(Math.random()*1);
		//Setter venstreside av skipet mellom kolonne 1 og 9
		if (horizontal == 1) {
			// Hvis vertikal
			placeShip(length, width, tempRow, tempColumn);
			}
		if (horizontal == 0) {
			//Hvis horisontal
			placeShip(width, length, tempColumn, tempRow);
		}
	}
	
	public void openCell(int row, int column) {
		if (getCell(row,column).getValue() == 'X' || getCell(row,column).getValue() == '.') {
			throw new Exception("You have already shot here.");
			//Hvis det verdien er endret (Er skutt) så skal det ikke gå an å skyte igjen
		}else {
			getCell(row,column).openCell();
			//Åpne celle metoden fra Cell klassen
			actionsTaken.push(new BattleShipAction(row,column));
			
			//Disse to er for å teste det i konsollen
		}
	}
	
	
	public Cell tempCell(int row, int column) {
		return board.get(row).get(column);
	}
	//Setter bølger(~) ved hjelp av Cell klassen
	public void setWave(int row, int column) {
		tempCell(row,column).undoCell();
	}
	//setter miss (.)
	public void setMiss(int row, int column) {
		tempCell(row,column).setValue();
	}
	//setter hit(X) uten at det skal synes
	public void setHit(int row, int column) {
		tempCell(row,column).setHit();
	}
	

	public int isGameOver() {
		for (int i=0;i<this.rows;i++) {
			for (int j=0;j<this.columns;j++) {
				if(board.get(i).get(j).getValue() == 'X') {
					sunkCount ++;
					//teller antall treff
				}if(board.get(i).get(j).getValue()== '.') {
					missCount ++;
					//teller antall bom
				}
			}
		}
		if (sunkCount != 18) {
			//Hvis det ikke er antall ruter som er oppgitt plassert på brettet
			return 0;
			// 0 er false Du har ikke vunnet enda
		}
		return 1;
			// ellers true Du har vunnet
	}
	
	//Skal telle antall treff eller bom
	public String hitOrMiss() {
		int sunkCount = 0;
		int missCount = 0;
		for (int i=0;i<this.rows;i++) {
			for (int j=0;j<this.columns;j++) {
				if(board.get(i).get(j).getValue() == 'X') {
					sunkCount ++;
					//teller antall treff
				}if(board.get(i).get(j).getValue()== '.') {
					missCount ++;
				}
			}
		}
		String text = "";
		text += "Hit: " + sunkCount + " Miss: " + missCount;
		return text;
	
	}
	
	// Skal vise om spilleren traff eller bommet
	public String isHit() {
		for (int i=0;i<this.rows;i++) {
			for (int j=0;j<this.columns;j++) {
				if(board.get(i).get(j).getValue() == 'X') {
					return "It hit!";
					//teller antall treff
				}else if(board.get(i).get(j).getValue() == '.') {
					return "It missed...";
				}
			}
		}return "It Missed...";
	}
	
	//Skal holde styr på hvilken spiller sin tur det er
	public int whichPlayerTurn() {
		int countShots = 0;
		for (int i=0;i<this.rows;i++) {
			for (int j=0;j<this.columns;j++) {
				if(board.get(i).get(j).getValue() == 'X' || board.get(i).get(j).getValue() == '.') {
					//Hent alle skuddene på brettet og tell opp
					countShots ++;
				}
			}
		}
		return countShots;
	}
	
	
	public String playerString() {
		if(whichPlayerTurn() == 0) {
			return "Player 1's turn";
		}if (whichPlayerTurn() == 1) {
			return "Player 2's turn";
		}
		return "";
	}
	
	public static int redoCounter = 0;
	public void undo() {
		if (actionsTaken.empty()){
			throw new IllegalArgumentException("You cannot undo further.");
		}
		BattleShipAction currentMove = actionsTaken.pop();
		redoStack.push(currentMove);
		int row = currentMove.getRow();
		int column = currentMove.getCol();
		getCell(row,column).undoCell();		
	}
	
	
	
	public void redo() {
		if(redoStack.empty()) {
			throw new IllegalArgumentException("You don't have anything to redo.");
		}
		BattleShipAction currentMove = redoStack.pop();
		actionsTaken.push(currentMove);
		int row = currentMove.getRow();
		int column = currentMove.getCol();
		getCell(row,column).openCell();
	}
	
	public void getInput(String in) {
		String[] inputs = in.split(",");
		int x = Integer.parseInt(inputs[0]);
		int y = Integer.parseInt(inputs[1]);
		this.openCell(x, y);
		actionsTaken.push(new BattleShipAction(in));
		redoStack.clear();
	}
	
	public String showBoard() {
		//Printer brettet
				String board = "";
				
				int counterColumn = 0;
				int counterRow = 0;
				
				for (int a=0; a<this.columns; a++) {
					counterColumn +=1;
					board += "\t" + counterColumn;
				}
				board += "\n";
				for(int i=0; i<this.rows;i++) {
					counterRow +=1 ;
					board += counterRow +"\t";
					//starten
					for (int j=0;j<this.columns;j++) {
						board += getCell(i,j);
						board += "\t";
						//slutten
					}
					board += "\n";
				}
		
				if(this.isGameOver()==1) {
					board +="\n Spillet er over. Den andre spilleren vant.";
				}	
				return board;
	}
	
	public String toString() {
		String game = "";
		game += showBoard();
		return game;
	}
	
	public static void main(String[] args) {
		BattleShipBoard game = new BattleShipBoard(10,10);		
		
		
		game.openCell(1, 1);
		
	
		System.out.println(redoCounter);
		PersistentBattleShip io = new PersistentBattleShip();
		io.load(game, "save2.txt");
		
	
		
		System.out.println(game.toString());
		
	}
}
