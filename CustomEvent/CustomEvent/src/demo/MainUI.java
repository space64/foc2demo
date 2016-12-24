package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class MainUI extends JFrame implements ClickListener{
	Canvas c1, c2;
	public MainUI(){
		setTitle("Demo");
		//setPreferredSize(new Dimension(600, 600));
		
		
		c1 = new Canvas("A");
		c1.setBackground(Color.GREEN);
		c1.setPreferredSize(new Dimension(200, 300));
		c1.addFireEventListener(this);
		c2 = new Canvas("B");
		c2.setBackground(Color.YELLOW);
		c2.setPreferredSize(new Dimension(200, 300));
		c2.addFireEventListener(this);
		
		setLayout(new GridLayout(0,2));
		getContentPane().add(c1);
		getContentPane().add(c2);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		MainUI f= new MainUI();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	@Override
	public void onMouseClick(ClickEvent e) {
		Canvas source = (Canvas)e.getSource();
		setTitle("Panel "+source.getName()+": mouse clicked at x:"+e.getP().getX()+" and y:"+e.getP().getY());
		
	}

}
