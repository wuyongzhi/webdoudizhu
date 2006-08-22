package game.json;

public class JsCreator {
	public static final String toJsObject(JsCreate[] jscreates) {
		if (jscreates==null)
			return "null";
		if (jscreates.length==0)
			return "[]";
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i=0; i<jscreates.length; ++i) {
			if (jscreates[i]==null) {
				sb.append("null,");
			} else {
				jscreates[i].toJsObject(sb).append(",");
			}
		}    
		sb.setCharAt(sb.length()-1, ']');
		
		return sb.toString();
	}
}
