package game.doudizhu;

import java.util.*;
import java.io.Serializable;

public class Actor implements game.json.JsCreate {
	public final static byte TYPE_FARMER= 1;
	public final static byte TYPE_LANDOWNER = 2;
	
	public final static byte STATUS_DESKTOP_GAMING = 5;
	public final static byte STATUS_DESKTOP_READY = 4;
	public final static byte STATUS_DESKTOP_SITDOWN=3;
	public final static byte STATUS_DESKTOP_LOOK=2;
	public final static byte STATUS_ROOM=1;
	
	private boolean isActor;
	
	private Serializable key;
	private int keyHashCode;
	private String name;
	private byte actorType;
	private byte location;
	private byte status;
	private Desktop desktop;
	private Room room;
	private String ip;
	private int actorIndex;
	private LinkedHashSet<Card> cards;
	

	public Actor(Serializable key, String name) {
		this.name = name;
		this.key = key;
		this.keyHashCode = key.hashCode();
	}
	
	
	public LinkedHashSet<Card> getCards() {
		return cards;
	}

	public void setCards(LinkedHashSet<Card> cards) {
		this.cards = cards;
	}

	public Desktop getDesktop() {
		return desktop;
	}

	public void setDesktop(Desktop desktop) {
		this.desktop = desktop;
	}
	
	

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}


	/**
	 * Àë¿ª×ùÎ». 
	 * @return
	 */
//	public synchronized Desktop leaveDesktop() {
//		if (desktop!=null) {
//			desktop.removeActor(this);
//		}
//		return desktop;
//	}

	/**
	 * ×øÏÂ
	 * @param desktop
	 * @return
	 */
//	public synchronized boolean enterDesktop(Desktop desktop) {
//		Desktop newDesktop = desktop;
//		if (newDesktop.addActor(this)) {
//			leaveDesktop();
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	public byte getLocation() {
		return location;
	}

	public void setLocation(byte location) {
		this.location = location;
	}

	public String getName() {   
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getType() {
		return actorType;
	}

	public void setType(byte type) {
		this.actorType = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int hashCode() {
		return this.keyHashCode;
	}
	
	public boolean equals(Object obj) {
		if (this==obj)
			return true;
		if (obj instanceof Actor) {
			return this.key.equals( ((Actor)obj).getKey());
		} else {
			return false;
		}
	}
	
	public Serializable getKey() {
		return this.key;
	}

	public boolean getIsActor() {
		return isActor;
	}

	public void setIsActor(boolean isActor) {
		this.isActor = isActor;
	}

	public int getActorIndex() {
		return actorIndex;
	}

	public void setActorIndex(int actorIndex) {
		this.actorIndex = actorIndex;
	}
	
	public String toJsObject() {
		StringBuilder sb = new StringBuilder(64);

		return this.toJsObject(sb).toString();
	}


	public StringBuilder toJsObject(StringBuilder sb) {
//		initialize: function(isActor,sessionKey,keyHashCode,name,actorType,location,status,ip,actorIndex) {

		return	sb.append("new Actor(")
		.append(isActor).append(",")
		.append("\"").append(this.key).append("\",")
		.append(this.keyHashCode).append(",")
		.append("\"").append(this.name).append("\",")
		.append(actorType).append(",")
		.append(location).append(",")
		.append(status).append(",")
		.append("\"").append(ip).append("\",")
		.append(actorIndex).append(")");
 
	}
}
