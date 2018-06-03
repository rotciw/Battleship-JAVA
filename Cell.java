package game;

public class Cell {
	
	private char value = '~';
	// Har ikke noe å si hva valuen er, er bare en stand-in
	private char horizontal = 'h';
	private boolean isShip = false;
	// Ingen er i utgangspunktet et skip
	
	public boolean isShip() {
		return isShip;
	}
	
	public char getValue() {
		return value;
	}
	
	public char horizontal() {
		return horizontal;
	}
	
	public void setShip() {
		isShip = true;
		//fordi hver celle har en default verdi på false ^
	}
	
	public String toString() {
		return ""+value;
	}
	
	//Om man åpner en celle vet man ikke hvor mange bomber som er i nærheten
	public void openCell() {
		if (isShip()) {
			this.value = 'X';
			return;
		} else {
			this.value = '.';
		}
	}
	
	public void setValue() {
		this.value = '.';
	}
	
	public void setHit() {
		this.value = '~';
		isShip=true;
	}
	
	public void undoCell() {
		this.value = '~';
	}
	
}
