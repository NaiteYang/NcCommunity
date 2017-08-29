package nx.community.util;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import nx.community.data.MessagesData;

public class CommandMsg
{
	private static YamlConfiguration yaml = (YamlConfiguration) MessagesData.getConfig();
	public static void reload(){ColorSwitch.replaceColor(yaml);}
	
	public static void TeamsHelp(Player p)
	{
		p.sendMessage(yaml.getString("Help.Separat"));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.Info")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct info ", "&eCommand: &7/nct i <TeamName> "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.Join")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct join ", "&eCommand: &7/nct j <TeamName> <Password> "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.Leave")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct leave ", "&eCommand: &7/nct l "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.Create")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct create ", "&eCommand: &7/nct c <TeamName> <Password> "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.Accept")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct accept ", "&eCommand: &7/nct a <PlayerName> "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.Kick")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct kick ", "&eCommand: &7/nct k <MembersPlayer> "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.Invite")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct invite ", "&eCommand: &7/nct in <PlayerName> "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.Promote")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct promote ", "&eCommand: &7/nct p <MembersPlayer> "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.Disband")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct disband ", "&eCommand: &7/nct d "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.AlterName")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct aname ", "&eCommand: &7/nct an <NewName> "));
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.Text(yaml.getString("Help.AlterPassword")), MessagesButtonJson.SugCmd(" &f[&a+&f] ", "/nct apass ", "&eCommand: &7/nct ap <NewPassword> "));
		p.sendMessage(yaml.getString("Help.Separat"));
	}
	
	public static void ErrorCmdValue(Player p)
	{
		MessagesButtonJson.sendmsg(p, MessagesButtonJson.SugCmd(yaml.getString("Commands.ErrorValue"), "/nct help", "&eCommand: &7/nct help"));
	}

	public static void FriendsHelp(Player p)
	{
		p.sendMessage(yaml.getString("Help.Separat"));
		p.sendMessage(yaml.getString("Help.Separat"));
	}

}
