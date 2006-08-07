package game.doudizhu;

import java.util.*;

public class PutCard{
	public final static byte TYPE_MISS = -1;
	public final static byte TYPE_MISSILE = 1;		//火箭, 两张王
	public final static byte TYPE_BOMB = 2;			//炸弹, 四个同数
	public final static byte TYPE_SINGLE = 3;		//单牌, 一张牌
	public final static byte TYPE_DOUBLE = 4;		//一对, 两张同数
	public final static byte TYPE_TRIAD = 5;			//三张, 三张同数
	public final static byte TYPE_TRIAD_1 = 6;   	//三张同数, 带一张单牌
	public final static byte TYPE_TRIAD_2 = 7;   	//三张同数, 带一对
	public final static byte TYPE_QUATE_SEQ = 13;	//四张同数的牌, 不带牌
	public final static byte TYPE_QUATE_SEQ_1 = 14;	//四张同数的牌, 带单牌两张
	public final static byte TYPE_QUATE_SEQ_2 = 15;	//四张同数的牌, 带对牌两对
	
	
	private byte type = TYPE_MISS;
	private byte type_flag = 0;
	
	
//	byte one_seq = -1;
//	byte three_seq = -1;
//	byte two_seq = -1;
//	byte four_seq = -1;
//	
	
	private List<Byte> one = new LinkedList<Byte>();
	private List<Byte> two = new LinkedList<Byte>();
	private List<Byte> three = new LinkedList<Byte>();
	private List<Byte> four = new LinkedList<Byte>();
	
	public void putOne(Byte value) {
		this.one.add(value);
	}
	public void putTwo(Byte value) {
		this.two.add(value);
	}
	
	public void putThree(Byte value){
		this.three.add(value);
	}
	public void putFour(Byte value){
		this.four.add(value);
	}
	
	/**
	 * 返回 0 指出不是顺序牌; 非0的值, 指出是顺序牌,并且返回值是顺中最大的那个值
	 * @param l
	 * @return
	 */
	private byte isSequence(List<Byte> l) {
		byte prev = 0;
		for (Byte current : l){
			if (prev != 0) {
				if (current - prev != 1) {
					return 0;
				}
			
			} 
			prev = current;
		}
		return prev;
	}
	
	
	
	/**
	 * 一手牌出来后, 调用此函数检测出牌是否有效.
	 * 调用完成后, 实例成员 type 将会被置值. 
	 * 为 TYPE_XXXX_XXXX 这些常量之一. 
	 * 其中, TYPE_MISS 这个值指出出牌是无效的.
	 * 如果 type 不等于 TYPE_MISS , type_flag 将会标出该手牌的最大值
	 * 如果 type 等于 TYPE_MISS, type_flag 将是无效的. 不应该关心这个标记. 
	 *
	 */
	private void checkType() {
		int one_size = one.size();
		int two_size = two.size();
		int three_size = three.size();
		int four_size = four.size();
		
		byte b1,b2;
		byte isSequence;
		
		if (four_size > 0) {
			type_flag = isSequence(four);
			
			if (four_size>1 && type_flag==0) {
				type = TYPE_MISS;
				return;
			}
			
			//
			//	只有单个的算是炸弹
			//	
			if (four_size==1 ) {
				if (one_size==0 && two_size==0 && three_size==0) {
					type = TYPE_BOMB;
					return;
				}
			}
			
			if (one_size==0 && two_size==0 && three_size==0) {
				type = TYPE_QUATE_SEQ;
				return;
			}
			
			if (three_size!=0) {
				type = TYPE_MISS;
				return;
			}
			
			int append_count = 2 * four_size;
			if (one_size==append_count && two_size==0) {
				type = TYPE_QUATE_SEQ_1; 
			} else if (two_size == append_count && one_size==0) {
				type = TYPE_QUATE_SEQ_2;
			} else {
				type = TYPE_MISS;
			
			}
			
			return;
		}
		
		if (three_size > 0) {
			type_flag = isSequence(three);
			if (three_size>1 && type_flag==0) {
				type = TYPE_MISS;
				return;
			}

			if (one_size==0 && two_size==0) {
				type = TYPE_TRIAD;
			} else {
				if (one_size==1 && two_size==0) {
					type = TYPE_TRIAD_1;
				} else if (one_size==0 && two_size==1) {
					type = TYPE_TRIAD_2;
				} else {
					type = TYPE_MISS;
				}
			}
			return;
		}
		
		if (two_size > 0) {
			type_flag = isSequence(two);
			if (one_size!=0) {
				type = TYPE_MISS;
			} else {
				if (two_size==1) {
					type = TYPE_DOUBLE;
				} else	if (two_size < 3) {
					type = TYPE_MISS;
				} else if (two_size >=3 && type_flag==0) {
					type = TYPE_DOUBLE;
				}
			}
			return;
		}
		
		if (one_size>0) {
			type_flag = isSequence(one);
			if (one_size==1 || (one_size>=5 && type_flag>0) )
				type = TYPE_SINGLE;
			else
				type = TYPE_MISS;
			
			return;
		}
		
		//没牌, miss;
		type = TYPE_MISS;
		return;
	}
}
