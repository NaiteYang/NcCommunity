package nx.community.core;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import nx.community.cmd.FriendsCmd;
import nx.community.cmd.TeamsCmd;
import nx.community.data.MessagesData;
import nx.community.data.PlayerData;
import nx.community.data.TeamData;
import nx.community.event.EntityDamageByEntity;
import nx.community.event.PlayerJoin;
import nx.community.util.CommandMsg;

public class Core extends JavaPlugin
{
	public static Core plugin;

	public void onEnable()
	{
		plugin = this;
		
		// console
		Server server = getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		console.sendMessage(ChatColor.YELLOW + "NcCommunity v1.0.1 has been enabled");
		
		commands();
		reload();
		files();
		events();
		save();
	}
	
	public void onDisable()
	{
		save();
		remove();
	}
	
	public void reload()
	{
		MessagesData.reload();
		CommandMsg.reload();
		PlayerData.loadAllPlayerData();
	}
	public void files()
	{
		MessagesData.getConfig().options().copyDefaults(true);
	}
	public void save()
	{
		MessagesData.saveConfig();
	}
	public void commands()
	{
		getCommand("nct").setExecutor(new TeamsCmd());
		getCommand("ncf").setExecutor(new FriendsCmd());
	}
	public void events()
	{
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(),this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
	}
	public void remove()
	{
		TeamData.removeAllTeamData();
	}
}
