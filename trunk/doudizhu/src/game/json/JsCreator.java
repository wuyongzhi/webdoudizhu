package game.json;

public class JsCreator {
	public static final String toJsObject(JsCreate[] jscreates) {
		if (jscreates==null)
			return "null";
		if (jscreates.length==0)
			return "{}";
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (JsCreate jscreate : jscreates) {
			if (jscreate==null) {
				sb.append("null,");
			} else {
				sb.append(jscreate.toJsObject(sb)).append(",");
			}
		}
		sb.setCharAt(sb.length()-1, '}');
		return sb.toString();
	}
}
