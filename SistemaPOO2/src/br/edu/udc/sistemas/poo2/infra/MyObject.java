package br.edu.udc.sistemas.poo2.infra;

public class MyObject {
	
	protected Integer id;

	public MyObject() {
		
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public boolean equals(MyObject obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (this.id == obj.getId()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ID = " + this.id;
	}
	
	public String getString() {
		return String.valueOf(this.id);
	}
	
	public void setString(String str) {
		this.id = Integer.parseInt(str);
	}

	public Object newInstance() {
		return new Object();
	}

	public MyObject clone() {
		MyObject obj = new MyObject();
		obj.setId(this.id);
		return obj;
	}

}
