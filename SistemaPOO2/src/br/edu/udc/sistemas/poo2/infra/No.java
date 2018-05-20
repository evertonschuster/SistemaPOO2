package br.edu.udc.sistemas.poo2.infra;

public class No extends MyObject {
	
	private MyObject data;
	private No next;

	public No() {
		this.data = null;
		this.next = null;
	}

	public void setData(MyObject data) {
		this.data = data;
	}

	public MyObject getData() {
		return this.data;
	}

	public void setNext(No next) {
		this.next = next;
	}

	public No getNext() {
		return this.next;
	}

}
