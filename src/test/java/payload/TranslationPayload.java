package payload;

import net.minidev.json.JSONObject;

public class TranslationPayload {
	public static String build(String from, String to, String query) {
        JSONObject obj = new JSONObject();
        obj.put("from", from);
        obj.put("to", to);
        obj.put("e", "");
        obj.put("q", query);
        return obj.toString();
    }
}
