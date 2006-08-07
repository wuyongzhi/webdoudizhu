package game.doudizhu;

import java.util.*;

public class PutCard{
	public final static byte TYPE_MISS = -1;
	public final static byte TYPE_MISSILE = 1;		//���, ������
	public final static byte TYPE_BOMB = 2;			//ը��, �ĸ�ͬ��
	public final static byte TYPE_SINGLE = 3;		//����, һ����
	public final static byte TYPE_DOUBLE = 4;		//һ��, ����ͬ��
	public final static byte TYPE_TRIAD = 5;			//����, ����ͬ��
	public final static byte TYPE_TRIAD_1 = 6;   	//����ͬ��, ��һ�ŵ���
	public final static byte TYPE_TRIAD_2 = 7;   	//����ͬ��, ��һ��
	public final static byte TYPE_QUATE_SEQ = 13;	//����ͬ������, ������
	public final static byte TYPE_QUATE_SEQ_1 = 14;	//����ͬ������, ����������
	public final static byte TYPE_QUATE_SEQ_2 = 15;	//����ͬ������, ����������
	
	
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
	 * ���� 0 ָ������˳����; ��0��ֵ, ָ����˳����,���ҷ���ֵ��˳�������Ǹ�ֵ
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
	 * һ���Ƴ�����, ���ô˺����������Ƿ���Ч.
	 * ������ɺ�, ʵ����Ա type ���ᱻ��ֵ. 
	 * Ϊ TYPE_XXXX_XXXX ��Щ����֮һ. 
	 * ����, TYPE_MISS ���ֵָ����������Ч��.
	 * ��� type ������ TYPE_MISS , type_flag �����������Ƶ����ֵ
	 * ��� type ���� TYPE_MISS, type_flag ������Ч��. ��Ӧ�ù���������. 
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
			//	ֻ�е���������ը��
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
		
		//û��, miss;
		type = TYPE_MISS;
		return;
	}
}
