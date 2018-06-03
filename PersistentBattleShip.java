package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class PersistentBattleShip implements PersistentBattleShipGame {

	@Override
	public void save(BattleShipBoard board, String filename) {
		try {
			PrintWriter writer = new PrintWriter(filename);
			for (int i = 0;i<board.getRows();i++) {
				for(int j=0;j<board.getColumns();j++) {
					Cell cell = board.getCell(i,j);
					if(cell.getValue() == '~') {
						if(cell.isShip()) {
							writer.print('X');
						}else {
							writer.print('~');
						}
					}
					else {
						writer.print(cell.getValue());
					}
				}
			
			}
			writer.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void load(BattleShipBoard board, String filename) {
	
		Scanner in;
	
		try {
			in = new Scanner(new FileReader(filename));
				String line = in.nextLine();
				
					for(int i=0;i<board.getRows();i++) {					
						for (int j=0;j<board.getColumns();j++) {
							if(line.charAt(j+(i*10))=='~') {
								board.setWave(i,j);	
							}else if(line.charAt(j+(i*10)) == '.') {
								board.setMiss(i, j);
							}else if(line.charAt(j+(i*10)) == 'X'){
								board.setHit(i, j);
							}
					}
				}
			
			in.close();
		}catch (FileNotFoundException e){
			System.err.println("Error: file "+filename +" not found");
			System.exit(1);
		}
		
	}

}
