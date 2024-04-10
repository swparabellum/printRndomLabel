package printRndomLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import javax.swing.*;

public class RandomJLabel extends JFrame {

	Container myCon;

	JLabel[] numLbl;
	int counter = 1;

	JPanel btnPnl;
	JButton startBtn;
	JLabel timerLbl;
	boolean flag = false;

	public RandomJLabel() {
		setTitle("1~25 사이의 임의 레이블");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		myCon = getContentPane();
		myCon.setLayout(null);
		numLbl = new JLabel[25];

		btnPnl = new JPanel();

		startBtn = new JButton("시작");
		startBtn.addActionListener(new startActionListener());
		timerLbl = new JLabel();

		btnPnl.add(startBtn);
		btnPnl.add(timerLbl);
		btnPnl.setBounds(180, 330, 50, 50);
		myCon.add(btnPnl);
		setVisible(true);

	}// public RandomJLabel()

	class startActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (flag == false) {
				if ((startBtn) == (e.getSource())) {
					flag = true;
					for (int i = 0; i < 25; i++) {
						int rndX = (int) (Math.random() * 300);
						int rndY = (int) (Math.random() * 300);
						int j = i + 1;
						String num = String.valueOf(j);
						numLbl[i] = new JLabel(num);
						numLbl[i].setBounds(rndX, rndY, 25, 20);
						numLbl[i].setOpaque(true);
						numLbl[i].setBackground(Color.YELLOW);
						myCon.add(numLbl[i]);
						numLbl[i].addMouseListener(new numClickListener());
					}
					
					myCon.revalidate();
					myCon.repaint(); //myCon 다시 그리기...
					startBtn.setText("재시작");
					return; // 경고! 이거 없으면 무한 재귀에 빠져버려 프로그램이 뻗음!!
				}
			} else {
				for (int i = 0; i < 25; i++) {
					myCon.remove(numLbl[i]);
					//numLbl[i].setVisible(false);
				}
				flag = false;
				counter = 1;//카운터 초기화.
				actionPerformed(e); //재귀, 시작/정지버튼으로 바꾸려면 지울것...
			}
		}
	}// class startActionListener end

	class numClickListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JLabel tempLbl = (JLabel) e.getSource();
			if (tempLbl == numLbl[counter - 1]) { // ?????????? .getSource() 사용! getText() 불가능!
				numLbl[counter - 1].setVisible(false);
				//myCon.remove(numLbl[counter - 1]);
				counter++;
			}
		}

	}// class numClickListener end

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RandomJLabel();
	}
}// class RandomJLabel