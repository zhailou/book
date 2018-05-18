package cn.edu.nyist.bookMv1.vo;

import java.io.Serializable;

public class TypeVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;

	public TypeVo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
