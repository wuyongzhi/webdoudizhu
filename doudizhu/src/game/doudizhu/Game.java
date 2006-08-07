package game.doudizhu;

import java.util.*;
import game.doudizhu.web.*;

public class Game {
	
	private Desktop desktop = new Desktop(new WebActorManager(10));
	private static Game game = new Game();
	

	public Desktop getDesktop() {
		return desktop;
	}


	public void setDesktop(Desktop desktop) {
		this.desktop = desktop;
	}
	
	public static Game theGame() {
		return game;
	}
	
	
}
