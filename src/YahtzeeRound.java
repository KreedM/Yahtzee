import java.util.Arrays;
import java.util.Scanner;
public class YahtzeeRound {
	private YahtzeeDice dices;
	private Scanner sc;
	private ScoreBoard scores;
	public ScoreBoard getScores() {
		return scores;
	}
	public YahtzeeRound() {
		dices = new YahtzeeDice();
		sc = new Scanner(System.in);
		scores = new ScoreBoard();
	}
	public void playRound() {
		dices.roll();
		System.out.println(dices.toString());
		for(int i = 2; i > 0; i--) {
			System.out.println(i + " reroll(s) left.");
			System.out.println(scores.possibleMoves(dices));
			String rerolls;
			do {
				System.out.print("Enter the dices you want to reroll (ex: 12345) ");
				rerolls = sc.nextLine();
			}
			while(!checkRerolls(rerolls));
			int[] hold = interpretInput(rerolls);
			if(hold.length == 0) {
				System.out.println(dices.toString());
				break;
			}
			for(int j = 0; j < hold.length; j++) {
				dices.getDice()[hold[j]].roll();
			}
			System.out.println(dices.toString());
		}
		System.out.println(scores.possibleMoves(dices));
		String move;
		do {
			System.out.print("What is your move? ");
			move = sc.nextLine().toUpperCase();
		} while(!scores.setMove(move));
	}
	private static int[] interpretInput(String input) {
		int[] hold = new int[input.length()];
		for(int i = 0; i < input.length(); i++) {
			hold[i] = Integer.parseInt(input.substring(i, i + 1)) - 1;
		}
		Arrays.sort(hold);
		return hold; 
	}
	private static boolean checkRerolls(String rerolls) {
		for(int i = 0; i < rerolls.length(); i++) {
			char x = rerolls.charAt(i);
			if(x != '1' && x != '2' && x != '3' && x != '4' && x != '5' && x != '6') {
				return false;
			}
		}
		return true;
	}
}
