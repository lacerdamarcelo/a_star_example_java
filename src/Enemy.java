
public class Enemy extends Agent{
	
	private int update_counter;
	
	public Enemy(int[] initial_position){
		super(initial_position);
		this.update_counter = 0;
	}
	
	public void update_target(Player player){
		if(this.update_counter % (Settings.max_level + 1 - Settings.enemy_level) == 0){
			this.setTarget(player.getPosition());
		}
		this.update_counter++;
	}
	
}
