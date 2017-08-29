package nx.community.event;

import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import nx.community.data.MessagesData;
import nx.community.data.TeamData;
import nx.community.data.TeamSetting;

public class EntityDamageByEntity implements Listener
{
	private static YamlConfiguration yaml = (YamlConfiguration) MessagesData.getConfig();
	
	@EventHandler
	public void onEntityDamage (EntityDamageByEntityEvent e)
	{
	    if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player))
	    {
	        return;
	    }
	    Player damaged = (Player) e.getEntity();
	    Player damager = (Player) e.getDamager();
	    if(!TeamData.hasTeam.containsKey(damaged) || !TeamData.hasTeam.containsKey(damager))
	    {
	        return;
	    }
	    TeamSetting team = TeamData.teamData.get(TeamData.hasTeam.get(damaged));
	    List<Player> members = team.getMembers();
	    Player leader = team.getLeader();
	    if((members.contains(damaged) || leader.equals(damaged)) && (members.contains(damager) || leader.equals(damager)))
	    {
	        e.setCancelled(true);
	        damager.sendMessage(yaml.getString("TeamSystem.Warn.NotDamageMembers"));
	    }
	}
}