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

public class BT2T3 extends JFrame implements ActionListener {
	int N = 9;
	int SIZE = 75;
	JPanel pan;
	Insets ins = new Insets(0, 0, 0, 0);
	// Array to store numbers from text file
	int[] numberArray = new int[81];

	public BT2T3() {
		setTitle("Exercise 02 - K21T3");
		setPreferredSize(new Dimension(N * SIZE, N * SIZE));
		// Read numbers from text file
		readTextFile();
		// Create GUI
		initGUI();
		pack();
		setLocationRelativeTo(null);
	}

	private void readTextFile() {
		File f = new File("testfiles/testcase_9x9c_numbers.txt");
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
		pan.setBackground(Color.WHITE);
		for (int i = 0; i < N * N; i++) {
			// JButton btnTmp = new JButton(String.valueOf(numberArray[i]));
			JButton btnTmp = new JButton(String.valueOf(numberArray[i]));
			if (numberArray[i] == 1) {
				btnTmp.setBackground(Color.BLUE);
			} else if (numberArray[i] == 2) {
				btnTmp.setBackground(Color.RED);
			} else if (numberArray[i] == 3) {
				btnTmp.setBackground(Color.YELLOW);
			} else if (numberArray[i] == 4) {
				btnTmp.setBackground(Color.GREEN);
			} else {
				btnTmp.setOpaque(false);
				btnTmp.setContentAreaFilled(false);
			}

			btnTmp.setBorderPainted(false);
			btnTmp.setMargin(ins);
			btnTmp.addActionListener(this);
			//btnTmp.setActionCommand(i + "");

			pan.add(btnTmp);
		}
		add(pan);
	}

	public static void main(String[] args) {
		BT2T3 f = new BT2T3();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
