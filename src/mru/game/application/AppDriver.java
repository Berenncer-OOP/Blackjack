package mru.game.application;
import java.io.IOException;

import mru.game.controller.GameManager;

/**
 * Initiates GameManager using GameManager() which starts app file handling and menu controller
 */
public class AppDriver {

	public static void main(String[] args) throws IOException {
		
		// This is the starting point of the project!
		// hint - It usually calls method from GameManager class to start the app, so only one or two lines will be written here
		//related to issue #15, design
		@SuppressWarnings("unused")
		GameManager game = new GameManager();
	}

}
