package nx.community.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import nx.community.util.MessagesButtonJson;

public class TeamData
{	
	private static YamlConfiguration yaml = (YamlConfiguration) MessagesData.getConfig();
	
	public static HashMap<String, TeamSetting> teamData = new HashMap<>();
	public static HashMap<Player, String> hasTeam = new HashMap<>();
	private static HashMap<Player, List<Player>> inviteMsg = new HashMap<>();
	
	public static void removeTeamData(Player p)
	{	
		inviteMsg.remove(p);
		teamData.remove(hasTeam.get(p));
		hasTeam.remove(p);
	}
	
	public static void removeAllTeamData()
	{
		inviteMsg.clear();
		teamData.clear();
		hasTeam.clear();
	}
	
	public static void getCreateTeam(String teamName, String pass, Player leader)
	{
	    if(hasTeam.containsKey(leader))
	    {
	        leader.sendMessage(yaml.getString("TeamSystem.Warn.AlreadyJoinTeam"));
	        return;
	    }
	    if(teamData.containsKey(teamName))
	    {
	        leader.sendMessage(yaml.getString("TeamSystem.Warn.AlreadyExistTeam"));
	        return;
	    }
	    teamData.put(teamName, new TeamSetting(leader, pass));
	    hasTeam.put(leader, teamName);
	    leader.sendMessage(yaml.getString("TeamSystem.Correct.CreateTeam"));
	}

	public static void getTeamInfo(Player p)
	{
		if(!hasTeam.containsKey(p))
		{
			p.sendMessage(yaml.getString("TeamSystem.Warn.NotJoinTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(hasTeam.get(p));
		p.sendMessage(yaml.getString("TeamSystem.Info.SeparatUp"));
		p.sendMessage(yaml.getString("TeamSystem.Info.TeamName") + hasTeam.get(p));
		p.sendMessage(yaml.getString("TeamSystem.Info.TeamLeader") + teamSetting.getLeader().getName());
		p.sendMessage(yaml.getString("TeamSystem.Info.TeamPassword") + teamSetting.getPassword());
		p.sendMessage(yaml.getString("TeamSystem.Info.TeamMembers"));
		for (Player playerMembers : teamSetting.getMembers())
		{
			p.sendMessage(yaml.getString("TeamSystem.Info.MembersRow") + playerMembers.getName());
		}
		p.sendMessage(yaml.getString("TeamSystem.Info.SeparatDown"));
	}
	
	public static void getPasswordAlter(String pass, Player leader)
	{
		if(!hasTeam.containsKey(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotCreateTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(hasTeam.get(leader));
		if(!teamSetting.getLeader().equals(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotLeader"));
			return;
		}
		teamSetting.setPassword(pass);
		leader.sendMessage(yaml.getString("TeamSystem.Correct.AlterPassword"));
	}

	public static void getLeaveTeam(Player p)
	{
		if(!hasTeam.containsKey(p))
		{
			p.sendMessage(yaml.getString("TeamSystem.Warn.NotJoinTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(hasTeam.get(p));
		if(teamSetting.getLeader().equals(p))
		{
			MessagesButtonJson.sendmsg(p, MessagesButtonJson.SugCmd(yaml.getString("TeamSystem.Warn.LeaderLeave"), "/nct disband", "&eCommand: &7/nct disband "));
			return;
		}
		teamSetting.getMembers().remove(p);
		teamSetting.getLeader().sendMessage(yaml.getString("TeamSystem.Warn.PlayerLeaveTeam").replace("{player}", p.getName()));
		hasTeam.remove(p);
		for (Player playerMembers : teamSetting.getMembers())
		{
			playerMembers.sendMessage(yaml.getString("TeamSystem.Warn.PlayerLeaveTeam").replace("{player}", p.getName()));
		}
		p.sendMessage(yaml.getString("TeamSystem.Correct.LeaveTeam"));
	}
	
	public static void getDisbandTeam(Player leader)
	{
		if(!hasTeam.containsKey(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotCreateTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(hasTeam.get(leader));
		if(!teamSetting.getLeader().equals(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotLeader"));
			return;
		}
		for (Player playerMembers : teamSetting.getMembers())
		{
			playerMembers.sendMessage(yaml.getString("TeamSystem.Warn.DisbandTeam"));
			hasTeam.remove(playerMembers);
		}
		teamData.remove(hasTeam.get(leader));
		hasTeam.remove(leader);
		leader.sendMessage(yaml.getString("TeamSystem.Correct.DisbandTeam"));
	}

	public static void getTeamInfo(String teamName, Player p)
	{
		if(!teamData.containsKey(teamName))
		{
			p.sendMessage(yaml.getString("TeamSystem.Warn.NotExistTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(teamName);
		p.sendMessage(yaml.getString("TeamSystem.Info.SeparatUp"));
		p.sendMessage(yaml.getString("TeamSystem.Info.TeamName") + teamName);
		p.sendMessage(yaml.getString("TeamSystem.Info.TeamLeader") + teamSetting.getLeader().getName());
		p.sendMessage(yaml.getString("TeamSystem.Info.TeamMembers") + teamSetting.getMembers());
		for (Player playerMembers : teamSetting.getMembers())
		{
			p.sendMessage(yaml.getString("TeamSystem.Info.MembersRow") + playerMembers.getName());
		}
		p.sendMessage(yaml.getString("TeamSystem.Info.SeparatDown"));
	}

	public static void getJoinTeam(String teamName, String pass, Player p)
	{
		if(hasTeam.containsKey(p))
		{
			p.sendMessage(yaml.getString("TeamSystem.Warn.AlreadyJoinTeam"));
			return;
		}
		if(!teamData.containsKey(teamName))
		{
			p.sendMessage(yaml.getString("TeamSystem.Warn.NotExistTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(teamName);
		if(!teamSetting.getPassword().equals(pass))
		{
			p.sendMessage(yaml.getString("TeamSystem.Warn.ErrorPassword"));
			return;
		}
		for (Player playerMembers : teamSetting.getMembers())
		{
			playerMembers.sendMessage(yaml.getString("TeamSystem.Warn.PlayerJoinTeam").replace("{player}", p.getName()));
		}
		hasTeam.put(p, teamName);
		teamSetting.addMembers(p);
		teamSetting.getLeader().sendMessage(yaml.getString("TeamSystem.Warn.PlayerJoinTeam").replace("{player}", p.getName()));
		p.sendMessage(yaml.getString("TeamSystem.Correct.JoinTeam"));
	}

	public static void getKickMember(String member, Player leader)
	{
		if(!hasTeam.containsKey(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotCreateTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(hasTeam.get(leader));
		if (!teamSetting.getLeader().equals(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotLeader"));
			return;
		}
		if(!teamSetting.getMembers().contains(Bukkit.getPlayer(member)))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.KickNotExist"));
			return;
		}
		for (Player playerMembers : teamSetting.getMembers())
		{
			playerMembers.sendMessage(yaml.getString("TeamSystem.Warn.PlayerLeaveTeam").replace("{player}", member));
		}
		hasTeam.remove(Bukkit.getPlayer(member));
		teamSetting.getMembers().remove(Bukkit.getPlayer(member));
		Bukkit.getPlayer(member).sendMessage("TeamSystem.Warn.KickOutTeam");
		leader.sendMessage(yaml.getString("TeamSystem.Warn.PlayerLeaveTeam").replace("{player}", member));
	}

	public static void getPromoteLeader(String member, Player leader)
	{
		if(!hasTeam.containsKey(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotCreateTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(hasTeam.get(leader));
		if(!teamSetting.getLeader().equals(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotLeader"));
			return;
		}
		if(!teamSetting.getMembers().contains(Bukkit.getPlayer(member)))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.PromoteNotExist"));
			return;
		}
		for (Player playerMembers : teamSetting.getMembers())
		{
			playerMembers.sendMessage(yaml.getString("TeamSystem.Warn.AlterLeader").replace("{player}", member));
		}
		teamSetting.getMembers().remove(Bukkit.getPlayer(member));
		teamSetting.addMembers(leader);
		teamSetting.setLeader(Bukkit.getPlayer(member));
		Bukkit.getPlayer(member).sendMessage(yaml.getString("TeamSystem.Warn.PromoteForLeader"));
		leader.sendMessage(yaml.getString("TeamSystem.Correct.AlterLeader"));
	}

	public static void getNameAlter(String teamName, Player leader)
	{
		if(!hasTeam.containsKey(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotCreateTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(hasTeam.get(leader));
		if(!teamSetting.getLeader().equals(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotLeader"));
			return;
		}
		for (Player playerMembers : teamSetting.getMembers())
		{
			hasTeam.put(playerMembers, teamName);
		}
		teamData.put(teamName, teamSetting);
		teamData.remove(hasTeam.get(leader));
		hasTeam.remove(leader);
		hasTeam.put(leader, teamName);
		leader.sendMessage(yaml.getString("TeamSystem.Correct.AlterName"));
	}

	public static void getTeamInvite(String target, Player leader)
	{
		if(!hasTeam.containsKey(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotCreateTeam"));
			return;
		}
		TeamSetting teamSetting = teamData.get(hasTeam.get(leader));
		if(!teamSetting.getLeader().equals(leader))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotLeader"));
			return;
		}
		if(teamSetting.getLeader().equals(Bukkit.getPlayer(target)))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.AlreadyOnTeam"));
			return;
		}
		if(teamSetting.getMembers().contains(Bukkit.getPlayer(target)))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.AlreadyOnTeam"));
			return;
		}
		if(!(Bukkit.getPlayer(target) != null))
		{
			leader.sendMessage(yaml.getString("TeamSystem.Warn.NotOnline"));
			return;
		}
		if (!inviteMsg.containsKey(Bukkit.getPlayer(target)))
		{
			List<Player> list = new ArrayList<Player>();
			list.add(leader);
			inviteMsg.put(Bukkit.getPlayer(target), list);
		}
		List<Player> list = inviteMsg.get(Bukkit.getPlayer(target));
		list.add(leader);
		inviteMsg.put(Bukkit.getPlayer(target), list);
		MessagesButtonJson.sendmsg(Bukkit.getPlayer(target), MessagesButtonJson.RunCmd(yaml.getString("TeamSystem.Warn.TeamInvite").replace("{player}", leader.getName()) + " &f[&a+&f] ", "/nct accept " + leader.getName(), "&eCommand: &7/nct accept " + leader.getName()));
		leader.sendMessage(yaml.getString("TeamSystem.Correct.InvitePlayer"));
	}

	public static void getAcceptInvite(String inviter, Player p)
	{
		if(hasTeam.containsKey(p))
		{
			p.sendMessage(yaml.getString("TeamSystem.Warn.AlreadyJoinTeam"));
			return;
		}
		if(!inviteMsg.containsKey(p))
		{
			p.sendMessage(yaml.getString("TeamSystem.Warn.NotExistInvite"));
			return;
		}
		TeamSetting teamSetting = teamData.get(hasTeam.get(Bukkit.getPlayer(inviter)));
		for (Player playerMembers : teamSetting.getMembers())
		{
			playerMembers.sendMessage(yaml.getString("TeamSystem.Warn.PlayerJoinTeam").replace("{player}", p.getName()));
		}
		Bukkit.getPlayer(inviter).sendMessage(yaml.getString("TeamSystem.Warn.PlayerJoinTeam").replace("{player}", p.getName()));
		hasTeam.put(p, hasTeam.get(Bukkit.getPlayer(inviter)));
		teamSetting.addMembers(p);
		p.sendMessage(yaml.getString("TeamSystem.Correct.JoinTeam"));
		inviteMsg.remove(p);
	}
}
