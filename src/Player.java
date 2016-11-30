import java.util.ArrayList;


public class Player extends Agent{
	
	private int score;
	
	public Player(int[] initial_position){
		super(initial_position);
		this.score = 0;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void update_target(ArrayList<int[]> coins){
		double shortest_distance = Tools.distance(coins.get(0), this.getPosition());
		int shortest_distance_index = 0;
		for(int i = 1 ; i < coins.size() ; i++){
			double distance = Tools.distance(coins.get(i), this.getPosition());
			if(distance < shortest_distance){
				shortest_distance = distance;
				shortest_distance_index = i;
			}
		}
		this.setTarget(coins.get(shortest_distance_index));
	}
	
}
