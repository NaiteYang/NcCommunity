package nx.community.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import nx.community.core.Core;

public class PlayerData
{
	Player player;
	private File file = null;
	private YamlConfiguration yaml = null;
	private static HashMap<Player, PlayerData> playerData = new HashMap<>();
	
	private PlayerData(Player p)
	{
		player = p;
		loadFile();
		playerData.put(p, this);
	}
	
	public static void reloadPlayerData()
	{
		removeAllPlayerData();
		loadAllPlayerData();
	}
	
	public static void removePlayerData(UUID uuid)
	{
		playerData.remove(Bukkit.getPlayer(uuid));
	}
	
	public static void removePlayerData(Player p)
	{
		playerData.remove(p);
	}
	
	public static void removeAllPlayerData()
	{
		playerData.clear();
	}
	
	public static PlayerData getPlyerData(UUID uuid)
	{
		return getPlayerData(Bukkit.getPlayer(uuid));
	}
	
	public static PlayerData getPlayerData(Player p)
	{
		if(playerData.containsKey(p))
		{
			return playerData.get(p);
		}else{
			return new PlayerData(p);
		}
	}
	
	public static void loadAllPlayerData()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			getPlayerData(p);
		}
	}
	
	private void loadFile()
	{
		file = new File(Core.plugin.getDataFolder() + File.separator + "PlayerData" + File.separator + player.getUniqueId() + ".yml");
		if(!file.exists())
		{
			try{
				file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		yaml = YamlConfiguration.loadConfiguration(file);
		yaml.set("PlayerName", player.getName());
		
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
