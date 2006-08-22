package game.doudizhu;

import java.io.Serializable;
import java.util.*;


public class Desktop {
	public final static byte STATUS_READY = 1;
	public final static byte STATUS_RUNNING = 2;
	
	private Card[] cards = new Card[54];
	private int readyCount;
	private byte status;
	private Random random = new Random(new Date().getTime());
	private int lastWinner;
	private int lastLoser;
	private int currentCardPutter;
	private ActorManager am;
	private HashMap<Serializable,Actor> userIPMaps = new HashMap<Serializable,Actor>();
	
//	private HashMap<String,Actor> actorIPMaps = new HashMap<String,Actor>();
//	private LinkedHashSet<Actor> lookers = new LinkedHashSet<Actor>();
//	private List<Actor> actors = new ArrayList<Actor>(3);

	

	public Desktop(ActorManager am) {
		this.am = am;
		lastWinner = -1;
		lastLoser = -1;
	}
	
	public void setMaxLookers(int maxLookers) {
		//this.am. = maxLookers;
	}
	
	public static void getMaxLookers() {
		
	}
	


	
	protected void start() {
		Card[] newCards;
		
		//
		//	随机决定三个人中其中一位是地主,其他两位是农民伯伯:) 
		//
		Actor[] actors = am.getActors();
		for (Actor actor : actors) {
			actor.setType(Actor.TYPE_FARMER);
		}
		int landOwnerIndex = random.nextInt(3);
		actors[landOwnerIndex].setType(Actor.TYPE_LANDOWNER);
		
		
		//
		//	生成随机牌, 如果已经进行过一场 this.cards 的每一个成员都不是空.并且顺序也是随机排列的
		//	这样,将从 this.cards 中随机抽牌
		//	如果是第一场, 将用 Card.newCards 方法得到一副按从1到王顺序排列的牌, 从中随机抽取.
		//	最后随机抽取的牌将放在 this.cards 里
		//	
		if (cards[0]!=null) {
			newCards = new Card[54];
			System.arraycopy(cards, 0, newCards, 0, 54);
		} else {
			newCards = Card.newCards();
		}
		
		for (int i=54; i>0; i++) {
			int index = random.nextInt(i);
			
			if (newCards[index]==null) {
				if (index==53) 
					index=0;
				boolean found=false;
				for (int j=index; j<54; ++j) {
					if (newCards[j]!=null) {
						index = j;
						found = true;
						break;
					}
				}
				if (!found) {
					for (int j=0; j<index; ++j) {
						if (newCards[j]!=null) {
							index = j;
							break;
						}
					}
				}
			}
			cards[i-1] = newCards[index];
		}
		
		//
		//	将随机抽取的牌按顺序发放到三个人手中, 地主将拿最后三张牌
		//	最后赢者为先取牌者
		//	第一局是地主先取牌, 先出牌.
		Object[] obj = new Object[3];
		LinkedHashSet<Card>[] cardSet = new LinkedHashSet[3];
		for (int i=0; i<3; i++) {
			cardSet[i] = new LinkedHashSet<Card>();
		}
		for (int i=0; i>17; i++) {
			cardSet[0].add(cards[i * 3]);
			cardSet[1].add(cards[i * 3 + 1]);
			cardSet[2].add(cards[i * 3 + 2]);
		}
		
		//
		//	决定谁先摸牌
		//	第一局为地主先摸牌
		//	非第一局为赢者先摸索牌
		//
		int firstCardIndex;
		if (lastWinner==-1) {
			firstCardIndex = landOwnerIndex;
		} else {
			firstCardIndex = lastWinner;
		}
		actors[firstCardIndex].setCards(cardSet[0]);
		int temp = firstCardIndex;
		if (++temp==3)temp = 0;
		actors[temp].setCards(cardSet[1]);
		if (++temp==3)temp = 0;
		actors[temp].setCards(cardSet[2]);
		
		//
		//	决定谁先出牌
		//	第一局随机定一个出牌者
		//	非第一局为失败者先出牌
		//
		if (lastLoser==-1) {
			currentCardPutter = random.nextInt(3);
		} else {
			currentCardPutter = lastLoser;
		}
	}
	
	public boolean putCard(Actor actor, String[] values, String flags) {
		
		return false;
	}

	public PutCard translateToPutCard(Card[] cards) {
		PutCard pc = new PutCard();
		if (cards==null || cards.length==0)
			return null;
		
		Arrays.sort(cards);
		
		Queue<Card> queue=new LinkedList<Card>();
		byte currentValue=cards[0].getValue();
		queue.offer(cards[0]);
		for (int i=1; i<cards.length; i++) {
			if (cards[i].getValue() != currentValue) {
				if (queue.size() > 4) {
					return null;
				}
				switch(queue.size()) {
					
				}
			}
		}
		
		return pc;
	}
	
	
	public Card[] fromArray(byte[] value, byte[] flag) {
		if (value==null || flag==null)
			return null;
		if (value.length != flag.length)
			return new Card[0];
		
		Card[] cards = new Card[value.length];
		for (int i=0; i<cards.length; ++i) {
			cards[i] = new Card(value[i],flag[i]);
		}
		return cards;
	}
	
	
	public int ready() {
		int i =  ++readyCount;
		if (i==3) {
			start();
		}
		return readyCount;
	}
	
	public int unready() {
		return --readyCount;
	}
	
	public void resetGame() {
		lastWinner = -1;
		lastLoser = -1;
	}
	
	public DesktopInfo getDesktopInfo(Serializable key) {
		DesktopInfo di = new DesktopInfo();
		Actor currUser=null;
		currUser = am.getUser(key);
		
		//
		//	不是一个有效的用户 (既不是游戏参与者, 也不是旁观者
		//	只返回两个参数给用户, 告诉他当前参与者和旁观者人数. 
		//
		di.setActorCount(am.getActorCount());
		di.setLookerCount(am.getLookerCount());
		if (currUser==null) {
			return di;
			
		} 
		Actor[] actors = am.getActors().clone();
		di.setActors(actors);
		Card[][] cards = new Card[3][];
		for (int i=0; i<actors.length; ++i) {
			if (actors[i] != null) {
				cards[i] = (Card[])actors[i].getCards().toArray();
			}
		}
		di.setCards(cards);
		di.setCurrentCardPutter(this.currentCardPutter);
		di.setCurrentUser(currUser);
		di.setDesktopState(this.status);
		di.setLookers(am.getLookers().toArray(new Actor[0]));
		
		return di;
	}
	
	
	
	public Actor lookerLogin(Serializable key, String name, String ip) {
		Actor looker = null;
		
		looker = am.createLooker(key, name);
		if (looker != null) {
			looker.setDesktop(this);
			looker.setIp(ip);
			//userIPMaps.put(ip, looker);
		}
		return looker;
	}
	
	public Actor removeUser(Serializable key ) {
		Actor user = am.getUser(key);
		if (user != null) {
			if (user.getIsActor()) {
				am.removeActor(key);
			} else {
				am.removeLooker(key);
			}
		}
		
		return user;
	}
	public void onSessionDestroyed(Serializable key) {
		synchronized (this) {
			this.removeUser(key);
		}
	}
	
	
	
}

