package game;

public interface PersistentBattleShipGame {

	public void save(BattleShipBoard board, String filename);
	public void load(BattleShipBoard board, String filename);
	
	
}
