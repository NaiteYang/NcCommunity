package nx.community.core;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import nx.community.cmd.CommandMsg;
import nx.community.cmd.FriendsCmd;
import nx.community.cmd.TeamsCmd;
import nx.community.event.AsyncPlayerChat;
import nx.community.file.MessagesYml;
import nx.community.util.TeamData;

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
		MessagesYml.reload();
		CommandMsg.reload();
	}
	public void files()
	{
		MessagesYml.getConfig().options().copyDefaults(true);
	}
	public void save()
	{
		MessagesYml.saveConfig();
	}
	public void commands()
	{
		getCommand("nct").setExecutor(new TeamsCmd());
		getCommand("ncf").setExecutor(new FriendsCmd());
	}
	public void events()
	{
		getServer().getPluginManager().registerEvents(new AsyncPlayerChat(),this);
	}
	public void remove()
	{
		TeamData.removeAllTeamData();
	}
}
