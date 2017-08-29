package nx.community.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import nx.community.data.PlayerData;

public class PlayerJoin implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		PlayerData.getPlayerData(event.getPlayer());
	}
}
