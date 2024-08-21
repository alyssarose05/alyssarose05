import java.util.Scanner;
public class MetsvsYankees_ExtraCredit {
	public static Scanner input = new Scanner(System.in);
	public static int numGames;
	public static double metsWin;
	
	public static double metsWorldSeries() {
		
		// The Mets play <column size> games, and the Yankees play <row size> games.
		double P[][] = new double[numGames-2][numGames-2];
		
		// If a team hasn't won a game, the other team must win those games.
		P[0][0] = 1;
		for (int i = 1; i < P.length; i++) {
			P[i][0] = metsWin * P[i-1][0];
			P[0][i] = (1-metsWin) * P[0][i-1]; 
		}
		
		// When each team has won at least 1 game but doesn't have 4 games yet, either the Mets won a game, or the Yankees won a game.
		for (int i = 1; i < P.length-1; i++)
			for (int j = 1; j < P[0].length-1; j++)
				P[i][j] = (metsWin * P[i-1][j]) + ((1-metsWin) * P[i][j-1]);
		
		// For either team to win the World Series, they must win the last game.
		for (int i = 1; i < P.length; i++) {
			P[4][i] = .463 * P[3][i];
			P[i][4] = .537 * P[i][3];
		}
		
		// Add up all the Mets' 4-game win probabilities to get the probability of them winning the World Series.
		double result = 0;
		for (int i = 0; i < P.length-1; i++) result += P[4][i];
		return result;
	}

	public static void main(String[] args) {
		System.out.print("Enter the best-of-number of games and the probability of the Mets winning a single game against the Yankees: ");
		numGames = input.nextInt();
		metsWin = input.nextDouble();
		System.out.println("The probability of the Mets winning the World Series against the Yankees is: " + metsWorldSeries());
	}
}
