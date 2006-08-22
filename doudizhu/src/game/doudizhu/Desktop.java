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
		//	�������������������һλ�ǵ���,������λ��ũ�񲮲�:) 
		//
		Actor[] actors = am.getActors();
		for (Actor actor : actors) {
			actor.setType(Actor.TYPE_FARMER);
		}
		int landOwnerIndex = random.nextInt(3);
		actors[landOwnerIndex].setType(Actor.TYPE_LANDOWNER);
		
		
		//
		//	���������, ����Ѿ����й�һ�� this.cards ��ÿһ����Ա�����ǿ�.����˳��Ҳ��������е�
		//	����,���� this.cards ���������
		//	����ǵ�һ��, ���� Card.newCards �����õ�һ������1����˳�����е���, ���������ȡ.
		//	��������ȡ���ƽ����� this.cards ��
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
		//	�������ȡ���ư�˳�򷢷ŵ�����������, �����������������
		//	���Ӯ��Ϊ��ȡ����
		//	��һ���ǵ�����ȡ��, �ȳ���.
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
		//	����˭������
		//	��һ��Ϊ����������
		//	�ǵ�һ��ΪӮ����������
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
		//	����˭�ȳ���
		//	��һ�������һ��������
		//	�ǵ�һ��Ϊʧ�����ȳ���
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
		//	����һ����Ч���û� (�Ȳ�����Ϸ������, Ҳ�����Թ���
		//	ֻ���������������û�, ��������ǰ�����ߺ��Թ�������. 
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

