package demo;

import java.util.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	/**
	 * 
	 */
	private String name;
	//Store all listeners
	private List<ClickListener> listeners = new ArrayList<ClickListener>();
	
	public Canvas(String name){
		this.name = name;
		setFocusable(true);
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("X:"+e.getX()+" - Y"+e.getY());
				//Fire event
				fireEvent(e.getX(), e.getY());
			}
		});
	}
	
	/**
	 * Add listerner
	 * @param toAdd
	 */
	public void addFireEventListener(ClickListener toAdd){
		listeners.add(toAdd);
	}

	/**
	 * When event occur, notify all listeners
	 * @param x
	 * @param y
	 */
	public void fireEvent(int x, int y){
		System.out.println("Fire Event");
		for(ClickListener fel: listeners){
			ClickEvent click = new ClickEvent(this, 0, "", new Point(x, y));
			fel.onMouseClick(click);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
