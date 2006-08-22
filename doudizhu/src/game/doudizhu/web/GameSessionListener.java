package game.doudizhu.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicInteger;
import game.doudizhu.Game;

public class GameSessionListener implements HttpSessionListener {
	AtomicInteger sessionCounter = new AtomicInteger(0);
	
	
	public void sessionCreated(HttpSessionEvent e) {
		//String key = e.getSession().getId();
		sessionCounter.incrementAndGet();
	}

	public void sessionDestroyed(HttpSessionEvent e) {
		sessionCounter.decrementAndGet();
		Game.theGame().getDesktop().onSessionDestroyed(e.getSession().getId());
	}

}
