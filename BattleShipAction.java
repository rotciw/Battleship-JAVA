package game;

public class BattleShipAction {
	
	private int row,col;
	private boolean isShip;

	public BattleShipAction(String input) {
		String[] args = input.split(",");
		row = Integer.parseInt(args[0]);
		col = Integer.parseInt(args[1]);
	}
	public BattleShipAction(int x, int y) {
		row = x;
		col = y;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean isShip() {
		return isShip;
	}
	
}
