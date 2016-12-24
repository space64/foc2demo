package demo;

import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;

public class ClickEvent extends ActionEvent {
	private Point p;
	public ClickEvent(Object source, int id, String command,Point p) {
		super(source, id, command);
		this.p = p;
	}
	public Point getP() {
		return p;
	}
	public void setP(Point p) {
		this.p = p;
	}

}
