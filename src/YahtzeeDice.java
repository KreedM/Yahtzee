public class YahtzeeDice {
	
	private Die[] dice = new Die[5];
	
	public YahtzeeDice() {
		for(int i = 0; i < 5; i++) {
			dice[i] = new Die();
		}
	}
	
	public YahtzeeDice(int sides) {
		for(int i = 0; i < 5; i++) {
			dice[i] = new Die(sides);
		}
	}
	
	public int roll() {
		for(int i = 0; i < 5; i++) {
			dice[i].roll();
		}
		return getTotal();
	}
	public int getTotal() {
		int total = 0;
		for(int i = 0; i < 5; i++) {
			total += dice[i].getCurrentValue();
		}
		return total;
	}
	public Die[] getDice() {
		return dice;
	}
	
	public int[] getDiceValues() {
		int[] intDice = new int[5];
		for(int i = 0; i < 5; i++) {
			intDice[i] = dice[i].getCurrentValue();
		}
		return intDice;
	}
	
	public String toString() {
		String total = "";
		for(int i = 0; i < 5; i++) {
			total += "\n" + "Dice #" + (i + 1) + ": " + dice[i].toString();
		}
		return total;
	}
}
