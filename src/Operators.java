import java.io.IOException;
import java.util.ArrayList;


public class Operators {
	/*
	 * # - Obstacle
	 * E - Enemy
	 * P - Player
	 * O - Coin
	 */
	public static Object[] initialize(char[][] map){
		ArrayList<Agent> agents = new ArrayList<Agent>();
		ArrayList<int[]> coins = new ArrayList<int[]>();
		int player_index = 0;
		for(int i = 0 ; i < map.length ; i++){
			for(int j = 0 ; j < map[i].length ; j++){
				int[] position = {i, j};
				switch(map[i][j]){
					case 'E':
						agents.add(new Enemy(position));
						map[i][j] = ' ';
						break;
					case 'P':
						agents.add(new Player(position));
						player_index = agents.size() - 1;
						map[i][j] = ' ';
						break;
					case 'O':
						coins.add(position);
						break;
					default:
						continue;
				}
			}
		}
		((Player) agents.get(player_index)).update_target(coins);
		((Player) agents.get(player_index)).update_path_to_target(map);
		Object[] ret = {agents, coins};
		return ret;
	}
	
	public static void update_agents(char[][] map, ArrayList<Agent> agents, ArrayList<int[]> coins){
		Player player = null;
		for(int i = 0 ; i < agents.size() ; i++){
			if(agents.get(i) instanceof Player){
				agents.get(i).move(map);
				player = (Player) agents.get(i);
				int[] position = agents.get(i).getPosition();
				if(map[position[0]][position[1]] == 'O'){
					map[position[0]][position[1]] = ' ';
					int score = ((Player)agents.get(i)).getScore();
					((Player)agents.get(i)).setScore(score + 1);
					for(int j = 0 ; j < coins.size() ; j++){
						if(coins.get(j)[0] == position[0] && coins.get(j)[1] == position[1]){
							coins.remove(j);
							if(coins.size() != 0){
								((Player)(agents.get(i))).update_target(coins);
								((Player)(agents.get(i))).update_path_to_target(map);
							}
						}
					}
				}					
			}
		}
		for(int i = 0 ; i < agents.size() ; i++){
			if(agents.get(i) instanceof Enemy){
				((Enemy)(agents.get(i))).update_target(player);
				((Enemy)(agents.get(i))).update_path_to_target(map);
				agents.get(i).move(map);
			}
		}
	}
	
	public static void print_game(char[][] map, ArrayList<Agent> agents) throws IOException{
		int[] position;
		char[][] printing_map = map.clone();
		for(int i = 0 ; i < agents.size() ; i++){
			position = agents.get(i).getPosition();
			if(agents.get(i) instanceof Player)
				printing_map[position[0]][position[1]] = 'P';
			else
				if(agents.get(i) instanceof Enemy)
					printing_map[position[0]][position[1]] = 'E';
		}
		Tools.print_screen(printing_map);
	}
	
}
