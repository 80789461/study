package com.study.jdk;

public class Enty {
	private Enty header;
	private Enty befor;
	private Enty after;
	public Enty(Enty header,Enty befor,Enty after){
		this.header=header;
		this.befor=befor;
		this.after=befor;

		System.out.println("11  111112221122211111");

	}
	public Enty(Enty e){
		this(e, null, null);
	}
	public Enty(){
		
	}
	public void init(){
		this.header.befor=header=this.header.after;
	}
	public void put(Enty o){
		if(this.header==null){
			header=o;
			this.header.befor=header=this.header.after;
			return;
		}
		header.befor=o;   
		o.after=header;
		o.befor=after;
		after.after=o;
	}
	
	public static void main(String[] args) {
		
	}
}
