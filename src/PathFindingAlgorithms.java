import java.util.ArrayList;


public class PathFindingAlgorithms {
	
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
		 //initialize the open list
	    ArrayList<Cell> open_list = new ArrayList<Cell>();
	    //initialize the closed list
	    ArrayList<Cell> closed_list = new ArrayList<Cell>();
	    //put the starting node on the open list (you can leave its f at zero)
	    Cell initial_node = new Cell(initial_position);
	    open_list.add(initial_node);

	    Cell last_cell = null;

	    //while the open list is not empty
	    while(open_list.size() != 0){
	        //find the node with the least f on the open list, call it "q"
	    	int smaller_f_index = 0;
	    	Cell smaller_f = open_list.get(0);
	    	for(int i = 1 ; i < open_list.size() ; i++){
	    		if(open_list.get(i).getF() < smaller_f.getF()){
	    			smaller_f_index = i;
	    			smaller_f = open_list.get(i);
	    		}
	    	}
	        //pop q off the open list
	    	Cell current_cell = open_list.remove(smaller_f_index);
	    	int current_cell_x = current_cell.getPosition()[1];
	    	int current_cell_y = current_cell.getPosition()[0];
	        // generate q's 8 successors and set their parents to q
	    	ArrayList<Cell> successors = new ArrayList<Cell>();
	    	boolean found_target = false;
	    	for(int i = current_cell_y - 1; i <= current_cell_y + 1 ; i++){
	    		if(current_cell_y - 1 >= 0 && current_cell_y + 1 < map.length){
		    		for(int j = current_cell_x - 1; j <= current_cell_x + 1 ; j++){
		    			if(current_cell_x - 1 >= 0 && current_cell_x + 1 < map[0].length && map[i][j] != '#' && (j != current_cell_x || i != current_cell_y)){
		    				int[] new_position = {i, j};
		    				Cell new_cell = new Cell(new_position);
		    				new_cell.setParent(current_cell);
		    				successors.add(new_cell);
		    				if(new_position[0] == target[0] && new_position[1] == target[1])
		    					found_target = true;
		    			}
			    	}
	    		}
	    	}
	    	if(!found_target){
		    	for(int i = 0 ; i < successors.size() ; i++){
		    		//if successor is the goal, stop the search
		    		int[] suc_position = successors.get(i).getPosition();
		    		//successor.g = q.g + distance between successor and q
		    	    //successor.h = distance from goal to successor
		    	    //successor.f = successor.g + successor.h
		    		successors.get(i).setG(current_cell.getG() + Tools.distance(current_cell.getPosition(), suc_position));
		    		successors.get(i).setH(Tools.distance(target, suc_position));
		    		successors.get(i).setF(successors.get(i).getG() + successors.get(i).getH());
		    		boolean found_smaller_open_list = false;
		    		for(int j = 0 ; j < open_list.size() ; j++){
		    			if(open_list.get(j).getPosition()[0] == suc_position[0] && open_list.get(j).getPosition()[1] == suc_position[1]){
		    				if(open_list.get(j).getPosition()[0] == suc_position[0] && open_list.get(j).getPosition()[1] == suc_position[1]){
		    					found_smaller_open_list = true;
		    					break;
		    				}
		    			}
		    		}
		    		if(!found_smaller_open_list){
		    			boolean found_smaller_closed_list = false;
		    			for(int j = 0 ; j < closed_list.size() ; j++){
							if(closed_list.get(j).getPosition()[0] == suc_position[0] && closed_list.get(j).getPosition()[1] == suc_position[1]){
								if(closed_list.get(j).getPosition()[0] == suc_position[0] && closed_list.get(j).getPosition()[1] == suc_position[1]){
									found_smaller_closed_list = true;
									break;
								}
			    			}			
						}
		    			if(!found_smaller_closed_list){
		    				open_list.add(successors.get(i));
		    			}
		    		}			
			        closed_list.add(successors.get(i));	
		    	}
	    	}else{
	    		last_cell = new Cell(target);
	    		last_cell.setParent(current_cell);
    			break;
	    	}
	    }
    	ArrayList<int[]> path = new ArrayList<int[]>();
    	Cell current_cell = last_cell;
    	while(current_cell.getParent() != null){
    		path.add(current_cell.getPosition());
    		current_cell = current_cell.getParent();
    	}
    	ArrayList<int[]> inverse_path = new ArrayList<int[]>();
    	for(int i = path.size() - 1 ; i >= 0 ; i--){
    		inverse_path.add(path.get(i));
    	}
    	return inverse_path;
	}

}
