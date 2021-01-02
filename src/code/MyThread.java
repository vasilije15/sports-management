package code;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class MyThread extends Thread {

	
	// Thread which shows stats 
	
	private JPanel panel;
	// private Stats stats;
	private List<Double> statsList;
	private int speed;
	private JButton start;

	public MyThread(JPanel panel, List<Double> statsList, int speed, JButton start) {
		super();
		this.panel = panel;
		this.statsList = statsList;
		this.speed = speed;
		this.start = start;
	}

	@Override
	public void run() {

		// List<Double> statsList;
		// statsList = stats.allStatsList();

		start.setEnabled(false);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 400, 1000, 2);
		panel.removeAll();
		panel.repaint();

		JLabel lblAge = new JLabel("Age");
		JLabel lblHeight = new JLabel("Height (cm)");
		JLabel lblWeight = new JLabel("Weight (kg)");

		lblAge.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblHeight.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblWeight.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));

		lblAge.setBounds(110, 405, 60, 40);
		lblHeight.setBounds(400, 405, 200, 40);
		lblWeight.setBounds(750, 405, 200, 40);

		JButton btnColorMin = new JButton();
		btnColorMin.setBackground(new java.awt.Color(220, 239, 9));
		btnColorMin.setBounds(10, 20, 20, 20);

		JButton btnColorAvg = new JButton();
		btnColorAvg.setBackground(new java.awt.Color(34, 146, 249));
		btnColorAvg.setBounds(10, 50, 20, 20);

		JButton btnColorMax = new JButton();
		btnColorMax.setBackground(new java.awt.Color(249, 141, 34));
		btnColorMax.setBounds(10, 80, 20, 20);

		JLabel lblMin = new JLabel("MIN");
		lblMin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblMin.setBounds(40, 20, 30, 20);

		JLabel lblAvg = new JLabel("AVG");
		lblAvg.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblAvg.setBounds(40, 50, 30, 20);

		JLabel lblMax = new JLabel("MAX");
		lblMax.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblMax.setBounds(40, 80, 30, 20);

		int x = 20;
		for (int i = 0; i < 9; i++) {

			JButton b = new JButton();

			int height = (int) (statsList.get(i) * 1.8);
			panel.add(separator);

			b.setBounds(x, 400 - height, 80, height);

			if (i == 0 || i == 3 || i == 6) {
				b.setBackground(new java.awt.Color(220, 239, 9));
			}

			if (i == 1 || i == 4 || i == 7) {
				b.setBackground(new java.awt.Color(34, 146, 249));
			}

			if (i == 2 || i == 5 || i == 8) {
				b.setBackground(new java.awt.Color(249, 141, 34));
			}

			double value = (double) Math.round(statsList.get(i) * 10d) / 10;

			String text = "";
			
			if (value % 1 == 0) {
				int noPoint = (int) value;
				text = "" + noPoint;

			} else {
				text = "" + value;
			}

			final String btnText = text; 
			
			
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					b.setText(btnText);
					b.setFont(new Font("Tahoma", Font.PLAIN, 12));
				}
			});

			if (i == 2) {
				panel.add(lblAge);
				panel.add(btnColorMin);
				panel.add(lblMin);

			}
			if (i == 5) {
				panel.add(lblHeight);
				panel.add(btnColorAvg);
				panel.add(lblAvg);
			}

			if (i == 8) {
				panel.add(lblWeight);
				panel.add(btnColorMax);
				panel.add(lblMax);
			}

			panel.add(b);
			try {
				panel.repaint();
				sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (i == 2 || i == 5) {
				x = x + 180;
			} else {
				x = x + 80;
			}

		}
		start.setEnabled(true);

	}

}
