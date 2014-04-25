package com.ericsson.other;

public class Notification {

	private String notification;
	private String notification1;
	private String notification2;
	private String notification3;

	public Notification() {
		this.notification = null;
		this.notification1 = null;
		this.notification2 = null;
		this.setNotification3(null);
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
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
