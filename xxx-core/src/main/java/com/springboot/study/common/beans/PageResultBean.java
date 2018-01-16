package com.springboot.study.common.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageResultBean<T> implements Serializable {


	public static final int NO_LOGIN = -1;

	public static final int SUCCESS = 0;

	public static final int FAIL = 1;

	public static final int NO_PERMISSION = 2;

	private String msg = "success";

	private int code = SUCCESS;

	private T data;

	public PageResultBean() {
		super();
	}

	public PageResultBean(T data) {
		super();
		this.data = data;
	}


	public PageResultBean(Throwable e) {
		super();
		this.msg = e.toString();
		this.code = FAIL;
	}

}
