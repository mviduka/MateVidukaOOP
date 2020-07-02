import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class ScoreBoard extends JFrame {

	private JLabel lblNewLabel;

	public ScoreBoard() {

		setSize(580, 540);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		lblNewLabel = new JLabel("text");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);

	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

}
