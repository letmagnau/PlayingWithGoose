package it.susca.bitrock.game;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static List<Player> players = new ArrayList<>();
	private static boolean winner = false;
	private static boolean firstTurn = true;
	private static long delay = 2000;

	public static void main(String[] args) {
		try {
			InitGame.Init();
			System.out.print("Game is starting...\r\n");
			System.out.print("\r\n");
			while (!winner) {
				for (Player p : players) {
					if (p.getBlock()<0) {
						System.out.print("Player: " + p.name + " is blocked on "+ p.score  + "\r\n");
						System.out.print("\r\n");
						p.setBlock(p.getBlock()-1);
					} else {
						winner = GameFunction.game(p, firstTurn);
						System.out.print("\r\n");
						if (winner) {
							System.out.print("\r\n");
							System.out.print("Player: " + p.name + " wins!\r\n");
							break;
						}
					}

					Thread.sleep(delay);

				}
				firstTurn = false;
			}
			System.out.print("Bye!");
			System.exit(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
