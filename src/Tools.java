import java.io.IOException;


public class Tools {
	
	//Clears the screen and prints the game scenary.
	public static void print_screen(char[][] screen_data) throws IOException{
		final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
		for(int i = 0 ; i < screen_data.length ; i++){
			for(int j = 0 ; j < screen_data[i].length ; j++){
				System.out.print(screen_data[i][j]);			
			}
			System.out.print('\n');
		}
	}
	
	public static double distance(int[] a, int[] b) {
        double diff_square_sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            diff_square_sum += (a[i] - b[i]) * (a[i] - b[i]);
        }
        return Math.sqrt(diff_square_sum);
    }

}
