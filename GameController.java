package game;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GameController {


	@FXML
	TextArea console ;

	@FXML
	TextArea console2;
	
	@FXML
	Label playerTurn, playerTurn2, hitOrMiss, hitOrMiss2;
	@FXML
	TextField inputField,inputField2, textField ;

	
	//Her maa du deklarerere spillet ditt dersom det heter noe annet enn TicTacToe
	BattleShipBoard game = new BattleShipBoard(10,10);
	BattleShipBoard game2 = new BattleShipBoard(10,10);
	
	public void initialize(){
		//Her maa du opprette et objekt av spillet ditt med de argumentene som behoves for det - resten av koden vil gaa ut ifra at du har kalt den game
		console.setText(game.toString());
		console2.setText(game2.toString());
		whichTurn();
	}
	
	private void whichTurn() {
		if((game2.whichPlayerTurn() + game.whichPlayerTurn()) % 2 == 0) {
			playerTurn.setVisible(true);
			playerTurn2.setVisible(false);
		}else {
			playerTurn.setVisible(false);
			playerTurn2.setVisible(true);
		}
	}
	private void update() {
		console.setText(game.toString());
		hitOrMiss2.setText(game.hitOrMiss());
		
		
	}
	private void update2() {
		console2.setText(game2.toString());
		hitOrMiss.setText(game2.hitOrMiss());		
	}

	@FXML
	public void sendInput(){
		String in = inputField.getText();
		System.out.println(in);
		game2.getInput(in);
		update2();
		System.out.println(game2.isHit());
		whichTurn();
	}
	@FXML
	public void sendInput2(){
		String in = inputField2.getText();
		System.out.println(in);
		System.out.println(game.isHit());
		game.getInput(in);
		update();
		whichTurn();
	}
	
	@FXML
	public void undo() {
		if((game2.whichPlayerTurn() + game.whichPlayerTurn()) % 2 == 0) {
			game.undo();
			game.undo();
			System.out.println("UNDO PLAYER 2");
		}else {
			game2.undo();
			game2.undo();
			System.out.println("UNDO PLAYER 1");
		}
		update2();
		update();
		whichTurn();
	}
	@FXML
	public void redo() {
		if((game2.whichPlayerTurn() + game.whichPlayerTurn()) % 2 == 0) {
			game2.redo();
			game2.redo();
			System.out.println("REDO PLAYER 1");
		}else {
			game.redo();
			game.redo();
			System.out.println("REDO PLAYER 2");
		}
		update2();
		update();
		whichTurn();
	}
	PersistentBattleShip io = new PersistentBattleShip();
	@FXML
	public void save() throws IOException {
		String text = textField.getText();
		io.save(game,text);
		io.save(game2,text + "1");
		System.out.println("SAVED");
	}

	@FXML
	public void load() throws IOException {
		String text = textField.getText();
		io.load(game,text);
		io.load(game2,text + "1");
		System.out.println("LOADED");
		update();
		update2();
		whichTurn();
	}
	
	

}


