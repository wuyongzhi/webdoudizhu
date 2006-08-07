package test;

import game.doudizhu.Card;

import java.io.Serializable;
import java.util.*;


public class Test implements Runnable{
	int i;
	
	public static void serialize(Serializable key) {
		Integer i = new Integer(32);
		Object[] o  = new Object[1];
		o[0] = i;
		Object[] o2 = o.clone();
		System.out.print(o[0]==o2[0]);
		Object[] o3  = {new Object()};
	
	}
	public static void main(String args[]) {
		String str = "hello";
		serialize(str);
		//Test t = new Test();
		//Thread a = new Thread(t);
		//Thread b = new Thread(t);
		
		//a.start();
		//b.start();
		/*try {
		a.join();
		b.join();
		}catch(Exception ex) {
			
		}*/
		
	}
	
	public synchronized void a() {
		System.out.println("a method");
		b();
	}
	public void run() {
		threadFunc();
		
	}
	public synchronized void threadFunc() {
		try {
			if (i!=32) {
				i=32;
				System.out.println("111111111, and sleeping");
				Thread.sleep(3000);
				this.wait();
				System.out.println("111111111 wake up!");
			}
			else {
				i = 32;
				System.out.println("222222222, notify 111111111");
				this.notify();
				System.out.println("222222222 sleeping");
				Thread.sleep(2000);
				System.out.println("222222222 wake up");
			}
		} catch (Exception ex) {
			
		}
	}
	public synchronized void b() {
		
		LinkedHashSet<Card>[] temp = new LinkedHashSet[3];
		for (LinkedHashSet<Card> t : temp) {
			System.out.println(t);
		}
	}
}
