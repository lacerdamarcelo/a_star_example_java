import java.util.ArrayList;


public class PathFindingAlgorithms {
	
	//This is a supporting class for the A* algorithm.
	public static class Cell{
	    
	    private Cell parent;
	    private int[] position;
	    private double g;
	    private double h;
	    private double f;

	    public Cell(int[] position){
	    	this.parent = null;
	        this.position = position;
	        this.g = 0;
	        this.h = 0;
	        this.f = 0;
	    }

		public int[] getPosition() {
			return position;
		}

		public void setPosition(int[] position) {
			this.position = position;
		}

		public double getG() {
			return g;
		}

		public void setG(double g) {
			this.g = g;
		}

		public double getH() {
			return h;
		}

		public void setH(double h) {
			this.h = h;
		}

		public double getF() {
			return f;
		}

		public void setF(double f) {
			this.f = f;
		}

		public Cell getParent() {
			return parent;
		}

		public void setParent(Cell parent) {
			this.parent = parent;
		}

	}
	
	public static ArrayList<int[]> a_star_algorithm(char[][] map, int[] initial_position, int[] target){
	    ArrayList<int[]> ret = new ArrayList<int[]>();
	    //TODO - YOUR ALGORITHM GOES HERE
	    return ret;
	}

}
