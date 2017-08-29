package nx.community.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nx.community.data.FriendData;
import nx.community.util.CommandMsg;

public class FriendsCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			sender.sendMessage("§cYou must be a player to use this commmand.");
			return false;
		}
		
		Player p = (Player) sender;
		
		if(label.equalsIgnoreCase("ncf"))
		{
			if(args.length == 0)
			{
				CommandMsg.FriendsHelp(p);
				return true;
			}
			
			if(args.length == 1)
			{
				if(!(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h") ||
					 args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("a") ||
					 args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l")))
				{
					CommandMsg.ErrorCmdValue(p);
					return true;
				}
			}
			if(args.length == 2)
			{
				if(!(args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("in") ||
					 args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("re")))
				{
					CommandMsg.ErrorCmdValue(p);
					return true;
				}
				if(args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("in"))
				{
					FriendData.getFriendInvite(args[1], p);
					return true;
				}
			}
		}
		return false;
	}
}
