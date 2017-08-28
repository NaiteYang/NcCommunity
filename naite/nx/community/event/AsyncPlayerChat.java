package nx.community.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener
{
	static boolean isCreateTeam = true;
	@EventHandler
	public void playerChat(AsyncPlayerChatEvent e)
	{
		/* if (isCreateTeam) //判斷玩家是否點擊創建隊伍
		{
			if (e.getMessage().equals("cancel")) //判斷玩家是否取消創建
			{
				e.setMessage("t");
				e.setCancelled(true);
				isCreateTeam = false;
			}else {
				String teamName = e.getMessage(); //取得玩家輸入隊伍名稱
				e.setCancelled(true);
				e.getPlayer().sendMessage("創建隊伍: " + teamName);
				isCreateTeam = false;
			}	
		} */
		
	}
}
