package nx.community.util;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessagesButtonJson {

	public static String Text(String text){
		String json = "{\"text\":\"" + text + "\"}";
		return json;
	}
	public static String Text(String text, String value){
		String json = "{\"text\":\"" + text + "\","
				+ "\"hoverEvent\":{\"action\":\"show_text\",\"value\":"
				+ "{\"text\":\"\",\"extra\":[{\"text\":\"" + value + "\"}]}"
				+ "}}";
		return json;
	}
	public static String RunCmd(String text, String value){
		String json = "{\"text\":\"" + text + "\","
				+ "\"clickEvent\":{\"action\":\"run_command\",\"value\":\""
				+ value + "\"}}";
		return json;
	}
	public static String RunCmd(String text, String value, String show){
		String json = "{\"text\":\"" + text + "\","
				+ "\"clickEvent\":{\"action\":\"run_command\",\"value\":\""
				+ value + "\"}"
				+ ",\"hoverEvent\":{\"action\":\"show_text\",\"value\":"
				+ "{\"text\":\"\",\"extra\":[{\"text\":\"" + show + "\"}]}}"
				+ "}";
		return json;
	}
	public static String SugCmd(String text, String value){
		String json = "{\"text\":\"" + text + "\","
				+ "\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\""
				+ value + "\"}}";
		return json;
	}
	public static String SugCmd(String text, String value, String show){
		String json = "{\"text\":\"" + text + "\","
				+ "\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\""
				+ value + "\"}"
				+ ",\"hoverEvent\":{\"action\":\"show_text\",\"value\":"
				+ "{\"text\":\"\",\"extra\":[{\"text\":\"" + show + "\"}]}}"
				+ "}";
		return json;
	}
	public static String URL(String text, String value){
		String json = "{\"text\":\"" + text + "\","
				+ "\"clickEvent\":{\"action\":\"open_url\",\"value\":\""
				+ value + "\"}}";
		return json;
	}
	public static String URL(String text, String value, String show){
		String json = "{\"text\":\"" + text + "\","
				+ "\"clickEvent\":{\"action\":\"open_url\",\"value\":\""
				+ value + "\"}"
				+ ",\"hoverEvent\":{\"action\":\"show_text\",\"value\":"
				+ "{\"text\":\"\",\"extra\":[{\"text\":\"" + show + "\"}]}}"
				+ "}";
		return json;
	}
	public static void sendmsg(Player p, String... msg){
		String json = " [\"\"";
		for (String j : Arrays.asList(msg)){
			json = json + "," + j;
		}
		json = json + "]";
		json = json.replace("&", "§").replace("§§", "&");
		Bukkit.getServer().dispatchCommand(
		        Bukkit.getConsoleSender(),
		        "tellraw " + p.getName() + json);
	}
}
