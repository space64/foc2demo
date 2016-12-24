package w12;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

/**
 * @author Huyen
 *
 */
@SuppressWarnings("serial")
public class BT1T1 extends JFrame {
	JMenuBar mnbBar;
	JMenu mnuFile, mnuFormat;
	JMenuItem mniNew, mniOpen, mniSave, mniSaveAs, mniExit, mniChangeBgColor, mniChangeFontColor;
	JCheckBoxMenuItem mniWordWrap;
	JTextArea txaContent;
	JScrollPane scrPane;
	File f;
	/**
	 * Tạo JFileChoser để hiển thị cửa sổ cho phép người dùng chọn tập tin trong
	 * máy tính.
	 */
	JFileChooser fchOpenFile;
	File currentFile;

	LinkedList<HighScore> hcList = new LinkedList<>();

	/**
	 * Hàm dựng, được gọi khi tạo đối tượng của class này.
	 */
	public BT1T1() {
		setTitle("Text Editor");
		setPreferredSize(new Dimension(600, 500));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack(); // Thiết lập kích thước frame sao cho nội dung bên trong
		// bằng hoặc lớn hơn preferredSide
		setLocationRelativeTo(null); // Hiển thị cửa sổ giữa ở màn hình

		// Gọi phương thức tạo menu
		initializeMenu();

		// Tạo vùng hiển thị văn bản
		// Văn bản hiển thị trong textarea
		txaContent = new JTextArea();
		txaContent.setLineWrap(true);
		txaContent.setWrapStyleWord(true);
		// Dùng JScrollPane để nếu văn bản dài sẽ xuất hiện thanh cuộn
		scrPane = new JScrollPane(txaContent);
		txaContent.setEditable(false);
		// Thêm JScrollPane (chứa JTextArea) vào Frame
		getContentPane().add(scrPane);

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mniOpen) {
					// Xử lý khi nhấn menu Open
					openFile();

				} else if (e.getSource() == mniSave) {
					// Xử lý khi nhấn menu Exit
					save();

				} else if (e.getSource() == mniSaveAs) {
					// Xử lý khi nhấn menu Exit
					saveAs();

				} else if (e.getSource() == mniExit) {
					// Xử lý khi nhấn menu Exit
					closeApplication();

				} else if (e.getSource() == mniChangeBgColor) {
					// Xử lý khi nhấn menu Change BG Color
					changeBGColor();

				} else if (e.getSource() == mniChangeFontColor) {
					// Xử lý khi nhấn menu Change Font Color
					changeFontColor();
				} else if (e.getSource() == mniNew) {
					// Xử lý khi nhấn menu Change Font Color
					String input = JOptionPane.showInputDialog(BT1T1.this, "Input your score using format: score|name");
					String[] data = input.split("\\|");
					int score = Integer.parseInt(data[0]);
					String name = data[1];
					HighScore hs = new HighScore(score, name);
					int count = 0;
					for (Iterator iterator = hcList.iterator(); iterator.hasNext();) {
						HighScore highScore = (HighScore) iterator.next();
						if (hs.getScore() > highScore.getScore()) {
							hcList.add(count, hs);
							if (hcList.size() > 10) {
								hcList.removeLast();
							}
							showHighScore();
							JOptionPane.showMessageDialog(BT1T1.this, "New Highscore. Your rank:"+(count+1));
							break;
						}
						count++;
					}
					if(count>=hcList.size()){
						JOptionPane.showMessageDialog(BT1T1.this, "No Update High Score");
					}
				}
			}
		};
		// Thêm lắng nghe sự kiện cho các menu item
		mniNew.addActionListener(action);
		mniOpen.addActionListener(action);
		mniSave.addActionListener(action);
		mniSaveAs.addActionListener(action);
		mniExit.addActionListener(action);
		mniChangeBgColor.addActionListener(action);
		mniChangeFontColor.addActionListener(action);

		// FileChooser
		fchOpenFile = new JFileChooser();
		FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("Text file", "txt");
		fchOpenFile.setFileFilter(filterTXT);

		// Xử lý sự kiện đóng ứng dụng
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				closeApplication();
			}
		});
	}

	public void initializeMenu() {
		// Tạo các Object liên quan đến menu
		mnbBar = new JMenuBar();
		mnuFile = new JMenu("File");
		mnuFile.setMnemonic('f');
		mnuFormat = new JMenu("Format");
		mniNew = new JMenuItem("New Highscore");
		mniOpen = new JMenuItem("Open");
		mniOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		mniOpen.setMnemonic('o');
		mniSave = new JMenuItem("Save");
		mniSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		mniSave.setMnemonic('s');
		mniSaveAs = new JMenuItem("Save As");
		mniExit = new JMenuItem("Exit");
		mniExit.setMnemonic('x');
		mniChangeBgColor = new JMenuItem("Change BG color");
		mniChangeFontColor = new JMenuItem("Change Font color");
		mniWordWrap = new JCheckBoxMenuItem("Word Wrap");
		// Thêm menu item vào menu File
		mnuFile.add(mniNew);
		mnuFile.add(mniOpen);
		mnuFile.add(mniSave);
		mnuFile.add(mniSaveAs);
		mnuFile.addSeparator();
		mnuFile.add(mniExit);
		// Thêm menu item vào menu Format
		mnuFormat.add(mniChangeBgColor);
		mnuFormat.add(mniChangeFontColor);
		mnuFormat.add(mniWordWrap);
		// Thêm menu File và menu Format vào MenuBar
		mnbBar.add(mnuFile);
		mnbBar.add(mnuFormat);
		// Thiết lập MenuBar thành menu chính của Frame
		setJMenuBar(mnbBar);
	}

	/**
	 * Allow user to select and open text file.
	 */
	public void openFile() {
		// Hiển thị cửa sổ chọn tập tin, người dùng chọn
		// và nhấn Open thì kết quả lưu vào result là APPROVE_OPTION
		// nếu nhấn cancel thì result sẽ là CANCEL_OPTION
		int result = fchOpenFile.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			// Nếu kết quả là APPROVE thì tiến hành đọc tập tin được chọn

			try {
				currentFile = fchOpenFile.getSelectedFile();
				setTitle(currentFile.getName());
				Scanner scn = new Scanner(new FileReader(fchOpenFile.getSelectedFile()));
				int counter = 0;
				while (scn.hasNextLine()) {
					// Đọc dòng tiếp theo vào nối thêm vào textarea
					String line = scn.nextLine();
					String[] data = line.split("\\|");
					if (data.length == 2) {
						int score = Integer.parseInt(data[0]);
						String name = data[1];
						HighScore hs = new HighScore(score, name);
						hcList.add(hs);
						counter++;
					}
				}
				showHighScore();
				// txaContent.setText(builder.toString());
				txaContent.setCaretPosition(0);
				scn.close();
			} catch (FileNotFoundException e) {
				// Hiện thông báo khi không tìm thấy tập tin
				JOptionPane.showMessageDialog(null, "Selected file is not found");
			}
		}
	}

	private void showHighScore() {
		txaContent.setText("");
		int count = 0;
		txaContent.append(String.format("%-10s %20s\t%5s\r\n", "STT", "Ten", "Diem"));
		for (Iterator iterator = hcList.iterator(); iterator.hasNext();) {
			HighScore highScore = (HighScore) iterator.next();
			count++;

			txaContent.append(
					String.format("%-10s %-20s\t%5s\r\n", count, highScore.getUserName(), highScore.getScore()));

		}
	}

	private void save() {
		if (currentFile != null) {
			// Save
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile));
				writer.write(txaContent.getText());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			saveAs();
		}
	}

	private void saveAs() {
		// TODO Show file chooser here
		int result = fchOpenFile.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fchOpenFile.getSelectedFile()));
				writer.write(txaContent.getText());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Change background color of text area.
	 */
	public void changeBGColor() {
		Color newColor = JColorChooser.showDialog(this, "Choose new background color", txaContent.getBackground());
		txaContent.setBackground(newColor);
	}

	/**
	 * Change font color of text area.
	 */
	public void changeFontColor() {
		Color newColor = JColorChooser.showDialog(this, "Choose new font color", txaContent.getBackground());
		txaContent.setForeground(newColor);
	}

	/**
	 * Show confirmation and close app if user say yes.
	 */
	public void closeApplication() {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Confirm",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		BT1T1 f = new BT1T1();
		f.setVisible(true);

	}

	private class HighScore {
		private int score;
		private String userName;

		public HighScore(int score, String userName) {
			super();
			this.score = score;
			this.userName = userName;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

	}

}
