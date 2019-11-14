public class YahtzeeRunner {
	public static void main(String[] args) {
		System.out.println("Welcome to Yahtzee!");
		//1 - one, 2- two...3K - Three of a Kind, 4K - Four of a Kind, FH - Full House
	    //SS - Small Straight, LS - Large Straight, C - Chance, Y - Yahtzee
		System.out.println("1 - One, 2 - Two ... 3K - Three of a Kind, 4K - Four of a Kind, FH - Full House, SS - Small Straight, LS - Large Straight, CH - Chance, Y - Yahtzee");
		System.out.println("Press ENTER if you don't want to reroll.");
		YahtzeeRound rounds = new YahtzeeRound();
		for (int i = 1; i <= 13; i++) {
			System.out.println("\nROUND #" + i);
			rounds.playRound();
		}
		System.out.println("Your final score is " + rounds.getScores().addScore());
	}
}
