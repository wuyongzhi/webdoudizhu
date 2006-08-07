package game.doudizhu;

import java.io.Serializable; 
import java.util.*;

public interface ActorManager {
	public Actor getActor(Serializable key);
	public Actor getLooker(Serializable key);
	
	public Actor[] getActors();
	public Collection<Actor> getLookers();
	
	public Actor createLooker(Serializable key, String name);
	public Actor createActor(Serializable key, String name);
	public int getActorCount();
	public int getLookerCount();
	public int getMaxLookerCount();
}
