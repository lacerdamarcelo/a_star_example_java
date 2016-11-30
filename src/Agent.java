import java.util.ArrayList;


public abstract class Agent {
	
	private int[] position;
	private int[] target;
	private ArrayList<int[]> steps;
	
	public Agent(int[] initial_position){
		this.position = initial_position;
		this.target = new int[2];
		this.steps = new ArrayList<>();
	}	
	
	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public ArrayList<int[]> getSteps() {
		return steps;
	}

	public int[] getTarget() {
		return target;
	}

	public void setTarget(int[] target) {
		this.target = target;
	}

	public void setSteps(ArrayList<int[]> steps) {
		this.steps = steps;
	}
	
	public void update_path_to_target(char[][] map){
		this.steps = PathFindingAlgorithms.a_star_algorithm(map, this.position, this.target);
	}

	public void move(char[][] map){
		if(this.steps.size() != 0){
			int[] previ_position = this.position;
			position = this.steps.remove(0);
			map[previ_position[0]][previ_position[1]] = ' ';
		}
	}
	
}
