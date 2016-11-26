import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BT2T2 extends JFrame implements ActionListener {
	int N = 9;
	int SIZE = 75;
	JPanel pan;
	Insets ins = new Insets(0, 0, 0, 0);
	//Array to store numbers from text file
	int[] numberArray = new int[81];

	public BT2T2() {
		setTitle("Exercise 02 - K21T2");
		setPreferredSize(new Dimension(N * SIZE, N * SIZE));
		// Read numbers from text file
		readTextFile();
		// Create GUI
		initGUI();
		pack();
		setLocationRelativeTo(null);
	}

	private void readTextFile() {
		File f = new File("testfiles/testcase_9x9b_numbers.txt");
		try {
			Scanner scn = new Scanner(f);
			int count = 0;
			while (scn.hasNextInt()) {
				numberArray[count++] = scn.nextInt();
			}
			scn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void initGUI() {
		pan = new JPanel();
		pan.setLayout(new GridLayout(N, N));
		for (int i = 0; i < N * N; i++) {
			// MyButton btnTmp = new MyButton(String.valueOf(numberArray[i]));
			MyButton btnTmp = new MyButton();
			if (numberArray[i] == -1) {
				btnTmp.setPaintCircle(Color.BLUE);
			} else if (numberArray[i] == 1) {
				btnTmp.setPaintCircle(Color.RED);
			}

			btnTmp.setBorderPainted(false);
			btnTmp.addActionListener(this);
			btnTmp.setMargin(ins);
			//btnTmp.setActionCommand(i + "");
			if ((i / N + i % N) % 2 == 1) {
				btnTmp.setBackground(Color.BLACK);
			} else {
				btnTmp.setBackground(Color.WHITE);
			}
			pan.add(btnTmp);
		}
		add(pan);
	}

	public static void main(String[] args) {
		BT2T2 f = new BT2T2();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	private class MyButton extends JButton {
		int circleSize = 30;
		Color color;
		boolean paintCircle;

		public MyButton() {
			super();
			color = Color.RED;
		}

		public MyButton(String name) {
			super(name);
			color = Color.RED;
		}

		public void setPaintCircle(Color c) {
			paintCircle = true;
			color = c;
			repaint();
		}

		public void setPaintCircle(boolean b) {
			paintCircle = b;
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (paintCircle) {
				int w = getWidth();
				int h = getHeight();
				int x = w / 2 - circleSize / 2;
				int y = h / 2 - circleSize / 2;
				circleSize = (w < h) ? w / 2 : h / 2;
				g.setColor(color);
				g.fillOval(x, y, circleSize, circleSize);
			}
		}
	}

}
