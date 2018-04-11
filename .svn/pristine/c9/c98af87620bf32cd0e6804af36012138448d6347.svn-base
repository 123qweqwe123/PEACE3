package com.bdcor.pip.data.vo;

import java.util.LinkedList;

/**
 * 用于处理并发数据的数据队列
 * 在原来LinkedList的基础上增加线程安全的控制
 * @author dinglin
 * */
public class ThreadSafeLinkedList<E> extends LinkedList<E> {

	private int maxlength = 1000;
		
	public ThreadSafeLinkedList(int max){
		super();
		this.maxlength = max;
	}
	
	public ThreadSafeLinkedList(){
		super();
	}
	
	public synchronized void addLast(E e){
		super.addLast(e);
	}
	
	
	public synchronized E removeFirst(){
		return super.removeFirst();
	}
	
	public boolean isFilled(){
		if ( this.size() < this.maxlength ){
			return false;
		}
		return true;
	}
}
