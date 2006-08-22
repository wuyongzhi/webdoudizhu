package game.doudizhu.web;

import java.io.Serializable;
import java.util.*;

import game.doudizhu.Actor;
import game.doudizhu.ActorManager;

public class WebActorManager implements ActorManager {
	
	private LinkedHashMap<Serializable,Actor> lookers = new LinkedHashMap<Serializable,Actor>();
	//private List<Actor> actors = new ArrayList<Actor>(3);
	private Actor[] actors = new Actor[3]; 
	private int maxUser;
	private HashSet<Serializable> userKeys = new HashSet<Serializable>(); //保存所有用户的key,防止重复登录;
	
	public WebActorManager(int maxLooker) {
		this.maxUser = maxLooker;
	}
	
	public void setMaxUserCount(int maxUser) {
		this.maxUser = maxUser;
	}
	
	public int getActorCount() {
		int count=0;
		for (Actor a : actors) {
			if (a!=null)
				count++;
		}
		return count;
	}
	
	private void saveUserKey(Serializable key) {
		this.userKeys.add(key);
	}
	private void removeUserKey(Serializable key) {
		userKeys.remove(key);
	}
	
	public Actor createActor(Serializable key, String name) {
		Actor actor = null;
		for (int i=0; i<actors.length; i++) {
			if (actors[i] == null) {
				actor = new Actor(key, name);
				actor.setActorIndex(i);
				actor.setIsActor(true);
				actors[i] = actor;
				saveUserKey(key);
				
				break;
			}
		}
		return actor;
	}
	
	public Actor createLooker(Serializable key, String name) {
		Actor looker = null;
		if (lookers.size() + getActorCount() < maxUser) {
			looker = new Actor(key, name);
			looker.setIsActor(false);
			lookers.put(key, looker);
			saveUserKey(key);
		}
		return looker;
	}

	public Actor removeActor(Serializable key) {
		Actor removedObj = null;
		for (int i=0; i<actors.length; i++) {
			if (actors[i]!=null && actors[i].getKey().equals(key)) {
				removedObj = actors[i];
				actors[i] = null;
				removeUserKey(key);
			}
		}
		return removedObj;
	}
	
	
	public Actor removeLooker(Serializable key) {
		Actor looker = lookers.remove(key);
		removeUserKey(key);
		return looker;
	}
	
	public Actor getActor(Serializable key) {
		for (Actor actor : actors) {
			if (actor!=null && actor.getKey().equals(key)) {
				return actor;
			}
		}
		return null;
	}
	public Actor getLooker(Serializable key) {
		return lookers.get(key);
	}

	public int getLookerCount() {
		return this.lookers.size();
	}

	public int getMaxLookerCount() {
		return this.maxUser;
	}
	
	public Actor[] getActors() {
		return this.actors;
	}
	
	public Collection<Actor> getLookers() {
		return this.lookers.values();
	}

	public Actor getUser(Serializable key) {
		Actor obj = getActor(key);
		if (obj == null) {
			obj = getLooker(key);
		}
		return obj;
	}
	

}
