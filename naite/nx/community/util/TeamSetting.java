package nx.community.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class TeamSetting
{
	private Player leader;
	private List<Player> members = new ArrayList<Player>();
	private String password;
	
	public TeamSetting(Player p, String pass)
	{
		leader = p;
		password = pass;
	}
	
	public void setLeader(Player p)
	{
		leader = p;
	}
	
	public Player getLeader()
	{
		return leader;
	}
	
	public void addMembers(Player p)
	{
		members.add(p);
	}
	
	public void removeMembers(Player p)
	{
		members.remove(p);
	}
	
	public List<Player> getMembers()
	{
		return members;
	}
	
	public void setPassword(String pass)
	{
		password = pass;
	}
	
	public String getPassword()
	{
		return password;
	}
}
