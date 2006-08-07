package game.doudizhu;

public class DesktopInfo {
	private Actor currentUser;

	private byte desktopState;
	private Actor[] actors;
	private Actor[] lookers;
	private Card[][] cards;
	private int currentCardPutter;
	private int actorCount;
	private int lookerCount;
	
	public int getActorCount() {
		return actorCount;
	}
	public void setActorCount(int actorCount) {
		this.actorCount = actorCount;
	}
	public Actor[] getActors() {
		return actors;
	}
	public void setActors(Actor[] actors) {
		this.actors = actors;
	}
	public Card[][] getCards() {
		return cards;
	}
	public void setCards(Card[][] cards) {
		this.cards = cards;
	}
	public int getCurrentCardPutter() {
		return currentCardPutter;
	}
	public void setCurrentCardPutter(int currentCardPutter) {
		this.currentCardPutter = currentCardPutter;
	}
	public Actor getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(Actor currentUser) {
		this.currentUser = currentUser;
	}
	public byte getDesktopState() {
		return desktopState;
	}
	public void setDesktopState(byte desktopState) {
		this.desktopState = desktopState;
	}
	public int getLookerCount() {
		return lookerCount;
	}
	public void setLookerCount(int lookerCount) {
		this.lookerCount = lookerCount;
	}
	public Actor[] getLookers() {
		return lookers;
	}
	public void setLookers(Actor[] lookers) {
		this.lookers = lookers;
	}

}
