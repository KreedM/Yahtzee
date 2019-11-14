import java.util.Arrays;

public class ScoreBoard {
    private boolean[] scoreOccupied;
    private int[] scores;
    private final String[] moves = new String[] {"1", "2", "3", "4", "5", "6", "3K", "4K", "Y", "SS", "LS", "FH", "CH"};

    public String possibleMoves(YahtzeeDice dices) {
    	calcPoints(dices);
        String fin = "";
        for (int i = 0; i < scoreOccupied.length; i++) {
            if(!scoreOccupied[i]) {
                if(i == scoreOccupied.length-1) {
                    fin += moves[i] + "(" + scores[i] + ")";
                } else {
                    fin += moves[i] + "(" + scores[i] + "), ";
                }
            }
        }
        return fin;
    }

    public ScoreBoard() {
        scores  = new int[13];
        scoreOccupied = new boolean[13];
    }

    public boolean setMove(String input) {
        int i = parseMove(input);
        if(i > -1 && !scoreOccupied[i]) {
            scoreOccupied[i] = true;
            return true;
        }
        return false;
    }

    private int parseMove(String input) {
        for(int i = 0; i < moves.length; i++) {
            if(input.equals(moves[i])) {
                return i;
            }
        }
        return -1;
    }

    private void calcPoints(YahtzeeDice dices) {
        int points = 0;
        int i = 0;

        // 1-6
        for(i = 0; i < 6; i++) {
            if(!scoreOccupied[i]) {
                points = 0;
                points = checkNums(i+1, dices) * (i + 1);
                scores[i] = points;
            }
        }

        //3K - Y
        for(int j = 3; j < 6; j++) {
            if(!scoreOccupied[i]) {
            	points = 0;
                if (checkK(j, dices) && j == 5) {
                    points = 50;
                }
                else if (checkK(j, dices)) {
                    points = dices.getTotal();
                }
                scores[i] = points;
            }
            i++;
        }

        //SS
        points = 0;
        if(!scoreOccupied[9] && straight(dices) >= 4) {
            points = 30;
            scores[9] = points;
        }

        //LS
        points = 0;
        if(!scoreOccupied[10] && straight(dices) == 5) {
            points = 40;
            scores[10] = points;
        }

        //FH
        points = 0;
        if(!scoreOccupied[11] && fullHouse(dices)) {
            points = 25;
            scores[11] = points;
        }

        //Chance
        if(!scoreOccupied[12]) {
            points = dices.getTotal();
            scores[12] = points;
        }
    }

    private int checkNums(int n, YahtzeeDice dices) {
        int counter = 0;
        int[] values = dices.getDiceValues();
        for(int i = 0; i < values.length; i++) {
            if (values[i] == n) {
                counter++;
            }
        }
        return counter;
    }
    private boolean checkK(int n, YahtzeeDice dices) {
        for(int i = 1; i < 7; i++) {
            if(checkNums(i, dices) >= n) {
                return true;
            }
        }
        return false;
    }

    private int straight(YahtzeeDice dices) {
        int consecutiveNums = 1;
        int[] values = dices.getDiceValues();
        Arrays.sort(values);
        for(int i = 0; i < values.length-1; i++) {
            if(values[i] + 1 == values[i+1]) {
                consecutiveNums++;
            }
            else {
              consecutiveNums = 1;
            }
        }
        return consecutiveNums;
    }

    private boolean fullHouse(YahtzeeDice dices) {
        boolean triple = false;
        boolean pair = false;

        for(int i = 1; i < 7; i++) {
            if(checkNums(i, dices) == 3) {
                triple = true;
            } else if(checkNums(i, dices) == 2) {
                pair = true;
            }
        }

        if(triple && pair) {
            return true;
        }
        return false;
    }
    public int addScore() {
    	int total = 0;
	    for(int i = 0; i < 6; i++) {
	    	total += scores[i];
	    }
	    if(total >= 63) {
	    	total += 35;
	    }
	    for(int i = 6; i < scores.length; i++) {
	    	total += scores[i];
	    }
	    return total;
    }
}
