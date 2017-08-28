package nx.community.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import nx.community.util.TeamData;

public class TeamsCmd implements CommandExecutor 
{	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			sender.sendMessage("§cYou must be a player to use this commmand.");
			return false;
		}
		
		Player p = (Player) sender;
		
		if(label.equalsIgnoreCase("nct"))
		{
			if(args.length == 0)
			{
				CommandMsg.TeamsHelp(p);
				return true;
			}
			
			if(args.length == 1)
			{
				if(!(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h") ||
					 args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("i") ||
					 args[0].equalsIgnoreCase("leave") || args[0].equalsIgnoreCase("l") ||
					 args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("a") ||
					 args[0].equalsIgnoreCase("disband") || args[0].equalsIgnoreCase("d")))
				{
					CommandMsg.ErrorCmdValue(p);
					return true;
				}
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h"))
				{
					CommandMsg.TeamsHelp(p);
					return true;
				}
				if(args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("i"))
				{
					TeamData.getTeamInfo(p);
					return true;
				}
				if(args[0].equalsIgnoreCase("leave") || args[0].equalsIgnoreCase("l"))
				{
					TeamData.getLeaveTeam(p);
					return true;
				}
				if(args[0].equalsIgnoreCase("disband") || args[0].equalsIgnoreCase("d"))
				{
					TeamData.getDisbandTeam(p);
					return true;
				}
			}
			
			if(args.length == 2)
			{
				if(!(args[0].equalsIgnoreCase("apass") || args[0].equalsIgnoreCase("ap") ||
					 args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("i") ||
					 args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("in") ||
					 args[0].equalsIgnoreCase("promote") || args[0].equalsIgnoreCase("p") ||
					 args[0].equalsIgnoreCase("kick") || args[0].equalsIgnoreCase("k") ||
					 args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("a") ||
					 args[0].equalsIgnoreCase("aname") || args[0].equalsIgnoreCase("an")))
				{
					CommandMsg.ErrorCmdValue(p);
					return true;
				}
				if(args[0].equalsIgnoreCase("apass") || args[0].equalsIgnoreCase("ap"))
				{
					TeamData.getPasswordAlter(args[1], p);
					return true;
				}
				if(args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("i"))
				{
					TeamData.getTeamInfo(args[1], p);
					return true;
				}
				if(args[0].equalsIgnoreCase("kick") || args[0].equalsIgnoreCase("k"))
				{
					TeamData.getKickMember(args[1], p);
					return true;
				}
				if(args[0].equalsIgnoreCase("promote") || args[0].equalsIgnoreCase("p"))
				{
					TeamData.getPromoteLeader(args[1], p);
					return true;
				}
				if(args[0].equalsIgnoreCase("aname") || args[0].equalsIgnoreCase("an"))
				{
					TeamData.getNameAlter(args[1], p);
					return true;
				}
				if(args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("in"))
				{
					TeamData.getTeamInvite(args[1], p);
					return true;
				}
				if(args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("a"))
				{
					TeamData.getAcceptInvite(args[1], p);
					return true;
				}
			}
			
			if(args.length == 3)
			{
				if(!(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("c") ||
					 args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("j")))
				{
					CommandMsg.ErrorCmdValue(p);
					return true;
				}
				if(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("c"))
				{
					TeamData.getCreateTeam(args[1], args[2], p);
					return true;
				}
				if(args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("j"))
				{
					TeamData.getJoinTeam(args[1], args[2], p);
					return true;
				}
			}
			if(args.length >= 4)
			{
				CommandMsg.ErrorCmdValue(p);
				return true;
			}
		}
		return false;
	}
}
