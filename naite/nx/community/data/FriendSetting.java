package nx.community.data;

import java.util.List;
import java.util.ArrayList;
import org.bukkit.entity.Player;

public class FriendSetting
{
	private Player player;
	private List<Player>friends = new ArrayList<Player>();
	
	public FriendSetting(Player p)
	{
		player = p;
	}
	
	public void setPlayer(Player p)
	{
		player = p;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void addFriends(Player p)
	{
		friends.add(p);
	}
	
	public void removeFriends(Player p)
	{
		friends.remove(p);
	}
	
	public List<Player> getFriends()
	{
		return friends;
	}
}
