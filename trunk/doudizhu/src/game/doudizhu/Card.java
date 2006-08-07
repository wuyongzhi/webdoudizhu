package game.doudizhu;

public class Card implements game.json.JsCreate {
	public final static int[][] CARD_VALUES = 
	{	{1,12},{2,13},
		{3,1},{4,2},{5,3},{6,4},{7,5},{8,6},{9,7},{10,8},
		{11,9},{12,10},{13,11},{14,14},{15,15}
	};
	
	//public final static char[][] CARD_FACES = {{1,'1'}	};
	
	private final static Card[] CARDS={
		new Card((byte)1, (byte)1),
		new Card((byte)1, (byte)2),
		new Card((byte)1, (byte)3),
		new Card((byte)1, (byte)4),
		new Card((byte)2, (byte)1),
		new Card((byte)2, (byte)2),
		new Card((byte)2, (byte)3),
		new Card((byte)2, (byte)4),
		new Card((byte)3, (byte)1),
		new Card((byte)3, (byte)2),
		new Card((byte)3, (byte)3),
		new Card((byte)3, (byte)4),
		new Card((byte)4, (byte)1),
		new Card((byte)4, (byte)2),
		new Card((byte)4, (byte)3),
		new Card((byte)4, (byte)4),
		new Card((byte)5, (byte)1),
		new Card((byte)5, (byte)2),
		new Card((byte)5, (byte)3),
		new Card((byte)5, (byte)4),
		new Card((byte)6, (byte)1),
		new Card((byte)6, (byte)2),
		new Card((byte)6, (byte)3),
		new Card((byte)6, (byte)4),
		new Card((byte)7, (byte)1),
		new Card((byte)7, (byte)2),
		new Card((byte)7, (byte)3),
		new Card((byte)7, (byte)4),
		new Card((byte)8, (byte)1),
		new Card((byte)8, (byte)2),
		new Card((byte)8, (byte)3),
		new Card((byte)8, (byte)4),
		new Card((byte)9, (byte)1),
		new Card((byte)9, (byte)2),
		new Card((byte)9, (byte)3),
		new Card((byte)9, (byte)4),
		new Card((byte)10, (byte)1),
		new Card((byte)10, (byte)2),
		new Card((byte)10, (byte)3),
		new Card((byte)10, (byte)4),
		new Card((byte)11, (byte)1),
		new Card((byte)11, (byte)2),
		new Card((byte)11, (byte)3),
		new Card((byte)11, (byte)4),
		new Card((byte)12, (byte)1),
		new Card((byte)12, (byte)2),
		new Card((byte)12, (byte)3),
		new Card((byte)12, (byte)4),
		new Card((byte)13, (byte)1),
		new Card((byte)13, (byte)2),
		new Card((byte)13, (byte)3),
		new Card((byte)13, (byte)4),
		new Card((byte)14, (byte)1),
		new Card((byte)15, (byte)1)
		
	};
	
	private byte value;
	private byte flag;
	
	public Card(byte value, byte flag) {
		this.value = value;
		this.flag = flag;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
	}
	
	
	
	public int hashCode() {
		return value * 31 + flag;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Card) {
			Card another = (Card)obj;
			return value==another.getValue() && flag==another.getFlag();
		} else {
			return false;
		}
	}
	
	public int compareTo(Card another) {
		int result = value - another.getValue();
		if (result!=0)
			return result;
		return flag - another.getFlag();
	}
	
	public static Card[] newCards() {
		Card[] cards = new Card[54];
		System.arraycopy(CARDS, 0, cards, 0, 54);
		return cards;
	}
	
	
	public String toJsObject() {
		return "new Card(" + value + "," + flag + ")";
	}
	
	public StringBuilder toJsObject(StringBuilder sb) {
		return sb.append("new Card(").append(value).append(",").append(flag).append(")");
	}
}
