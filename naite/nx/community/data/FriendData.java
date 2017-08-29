package nx.community.data;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class FriendData
{
	private static YamlConfiguration yaml = (YamlConfiguration) MessagesData.getConfig();
	
	private static HashMap<Player, List<Player>> inviteMsg = new HashMap<>();
	
	public static void getFriendInvite(String target, Player p)
	{
		if(!(Bukkit.getPlayer(target) != null))
		{
			p.sendMessage(yaml.getString("TeamSystem.Warn.NotOnline"));
			return;
		}
	}
	
}
