package com.ericsson.other;

import java.util.List;

public class MessageRapper<T> {

	private List<T> data;
	private String notification1;
	private String notification2;
	private String notification3;
	
	public MessageRapper() {
	}

	public MessageRapper(List<T> data, String notification1, String notification2, String notification3) {
		this.data = data;
		this.notification1 = notification1;
		this.notification2 = notification2;
		this.notification3 = notification3;
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public String getNotification1() {
		return notification1;
	}
	public void setNotification1(String notification1) {
		this.notification1 = notification1;
	}
	public String getNotification2() {
		return notification2;
	}
	public void setNotification2(String notification2) {
		this.notification2 = notification2;
	}
	public String getNotification3() {
		return notification3;
	}
	public void setNotification3(String notification3) {
		this.notification3 = notification3;
	}
	
}
