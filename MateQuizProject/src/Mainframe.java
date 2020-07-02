import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * 
 * @author mviduka
 * 
 *         MateQuizProject je moj zavrsni projekt iz OOP, ideja igre je Quiz.
 *         Kad zapocenete igru, otvara se popup koji trazi da unesete vase ime i
 *         onda zapicnjete igru, odgovarate na pitanja stiskajuci jedan od
 *         ponudenih odgovora, kad pritisnete odgovor on ce ili pocrvenjeti ili
 *         pozeleniti ovisno o tocnosti odgovora svaki odgovor se boduje. Tocan
 *         s jednim a netocan s nula bodova.
 *
 */

public class Mainframe extends JFrame {

	final JFrame parent = new JFrame();
	private JLabel lblPlayerName;
	private JLabel lblQuestion;
	private JButton btnAnswer1;
	private JButton btnAnswer2;
	private JButton btnAnswer3;
	private JButton btnAnswer4;

	private String finalAnswer;
	private int gameTime = 0;

	private int Score = 0;

	private String[] questionList = { "Od koliko se slova sastoji Hrvatska Abeceda?",
			"Koja je rijeka najduza na svijetu?", "'Daj, sjedni! Nije ti tata...'", "Junak Gothama je...",
			"Koji od navedenih planeta ima najvise prirodnih satelita?", "Kontinent s najvecom površinom je...",
			"Koje godine je poceo drugi Svjetski rat?", "Koja Zivotinja ima najbolji vid?",
			"Koliko centimetara iznosi jedan inch?", "Sto cemo najcesce razbijati za uskrs?" };

	private String[][] answerList = { { "29", "30", "31", "32" }, { "Nil", "Amazona", "Niger", "Dunav" },
			{ "Stolar", "Loncar", "Bacvar", "Staklar" }, { "Batman", "Superman", "Iron Man", "Spiderman" },
			{ "Jupiter", "Merkur", "Saturn", "Zemlja" }, { "Afrika", "Azija", "Europa", "Sj. Amerika" },
			{ "1939", "1938", "1940", "1941" }, { "Vepar", "Pas", "Sokol", "Riba" }, { "2,54", "5,52", "1,58", "7,96" },
			{ "Prozore", "Case", "Stolice", "Jaja" } };

	private String[] rightAnswerList = { "30", "Amazona", "Staklar", "Batman", "Saturn", "Azija", "1939", "Sokol",
			"2,54", "Jaja" };

	int[] numbersToExclude = {};

	public Mainframe() {

		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		createComponents();
		setUpForGame();

	}

	/**
	 * Metoda kreira sve osnovne komponente koje vidimo na Frameu.
	 */

	public void createComponents() {

		setSize(770, 570);
		setResizable(false);
		getContentPane().setLayout(null);

		lblPlayerName = new JLabel("Ime: ");
		lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPlayerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerName.setBounds(10, 11, 600, 34);
		getContentPane().add(lblPlayerName);

		lblQuestion = new JLabel("");
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setBounds(10, 121, 744, 74);
		getContentPane().add(lblQuestion);

		btnAnswer1 = new JButton("");
		btnAnswer1.setBounds(108, 280, 219, 62);
		getContentPane().add(btnAnswer1);

		btnAnswer2 = new JButton("");
		btnAnswer2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnswer2.setBounds(369, 280, 219, 62);
		getContentPane().add(btnAnswer2);

		btnAnswer3 = new JButton("");
		btnAnswer3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnswer3.setBounds(108, 404, 219, 62);
		getContentPane().add(btnAnswer3);

		btnAnswer4 = new JButton("");
		btnAnswer4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnswer4.setBounds(369, 404, 219, 62);
		getContentPane().add(btnAnswer4);
		setTitle("Quiz by Mate Viduka");

		setVisible(true);

	}

	/**
	 * setUpForGame metoda koja nas priprema za igru. Prvo kroz popup prozor unosimo
	 * ime, a onda igra za nas bira prvo pitanje i postavlja ga u lblQuestion i igra
	 * zapocinje. nakon sto odigramo igru otvara se prozor
	 * 
	 * @see ScoreBoard na kojem nam se igra zahvaljuje za igranje i govori nam
	 *      koliko smo bodova ostvarili
	 */

	public void setUpForGame() {

		String name = JOptionPane.showInputDialog(parent, "Dobrodošli u kviz znanja,Molimo unesite svoje ime!", null);

		lblPlayerName.setText("Ime: " + name);

		pickQuestion();

		while (gameTime < 6) {

			playGame();
		}

		setVisible(false);

		ScoreBoard sc = new ScoreBoard();
		sc.setVisible(true);
		sc.getLblNewLabel()
				.setText("Hvala na igranju " + lblPlayerName.getText() + "!!!\n Osvojili ste " + Score + " bodova!");
	}

	/**
	 * playGame metoda koja "Vrti" igru
	 * 
	 * Na sve botune smo postavili listenere tako da kad kliknemo jedan od ponudenih
	 * odgovora (tocnog ili ne tocnog) igra ce se nastaviti i ide na novo pitanje
	 */

	public void playGame() {

		btnAnswer1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (btnAnswer1.getText() == finalAnswer) {

					btnAnswer1.setBackground(Color.GREEN);
					btnAnswer1.setForeground(Color.BLACK);

				} else {

					btnAnswer1.setBackground(Color.RED);
					btnAnswer1.setForeground(Color.BLACK);

				}

			}
		});

		btnAnswer2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (btnAnswer2.getText() == finalAnswer) {

					btnAnswer2.setBackground(Color.GREEN);
					btnAnswer2.setForeground(Color.BLACK);

				} else {

					btnAnswer2.setBackground(Color.RED);
					btnAnswer2.setForeground(Color.BLACK);

				}

			}
		});

		btnAnswer3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (btnAnswer3.getText() == finalAnswer) {

					btnAnswer3.setBackground(Color.GREEN);
					btnAnswer3.setForeground(Color.BLACK);

				} else {
					gameTime++;
					btnAnswer3.setBackground(Color.RED);
					btnAnswer3.setForeground(Color.BLACK);

				}

			}
		});

		btnAnswer4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (btnAnswer4.getText() == finalAnswer) {

					btnAnswer4.setBackground(Color.GREEN);
					btnAnswer4.setForeground(Color.BLACK);

				} else {

					btnAnswer4.setBackground(Color.RED);
					btnAnswer4.setForeground(Color.BLACK);

				}

			}
		});

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		checkForChange();

	}

	/**
	 * 
	 * getRandomWithExclusion metoda koja je zadutena da nam ne dođe dva puta isto
	 * pitanje u igri.
	 * 
	 * @param rnd
	 * @param start
	 * @param end
	 * @param exclude
	 * 
	 */

	public int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
		int random = start + rnd.nextInt(end - start + 1 - exclude.length);
		for (int ex : exclude) {
			if (random < ex) {
				break;
			}
			random++;
		}
		return random;
	}

	static int[] addElement(int[] a, int e) {
		a = Arrays.copyOf(a, a.length + 1);
		a[a.length - 1] = e;
		return a;
	}

	/**
	 * @setToDefault metoda koja vraca sve botune i lblQuestion u pocetno stanje. Da
	 *               nema ove metode bodovanje i sam tok igre ne bi funkcionirao.
	 */
	private void setToDefault() {

		lblQuestion.setText("");
		btnAnswer1.setText("");
		btnAnswer1.setBackground(Color.GRAY);
		btnAnswer2.setText("");
		btnAnswer2.setBackground(Color.GRAY);
		btnAnswer3.setText("");
		btnAnswer3.setBackground(Color.GRAY);
		btnAnswer4.setText("");
		btnAnswer4.setBackground(Color.GRAY);

	}

	/**
	 * @pickQuestion metoda koja seta text od lblQuestion u pitanje koje se
	 *               nasumicno izvuce sa
	 * @see getRandomWithExclusion
	 * 
	 *      takoder metoda postavlja odgvore na botune.
	 */

	private void pickQuestion() {

		setToDefault();

		int random = getRandomWithExclusion(new Random(), 0, 9, numbersToExclude);
		numbersToExclude = addElement(numbersToExclude, random);

		String question = questionList[random];

		String answer1 = answerList[random][0];
		String answer2 = answerList[random][1];
		String answer3 = answerList[random][2];
		String answer4 = answerList[random][3];

		finalAnswer = rightAnswerList[random];

		lblQuestion.setText(question);
		btnAnswer1.setText(answer1);
		btnAnswer2.setText(answer2);
		btnAnswer3.setText(answer3);
		btnAnswer4.setText(answer4);

	}

	/**
	 * @checkForChange
	 * 
	 *                 u prvoj IF funkciji provjeravamo da li se pritisnuo tocan
	 *                 odgovor i ako je povecava
	 * @param Score za 1
	 * 
	 *              u drugoj IF funkciji provjeravamo da li smo kliknuli na botun i
	 *              ako jesmo vrijeme igre se povecava za 1 odnosno igra ide na
	 *              sljedece pitanje.
	 * 
	 */

	private void checkForChange() {

		if (btnAnswer1.getBackground() == Color.GREEN || btnAnswer3.getBackground() == Color.GREEN
				|| btnAnswer2.getBackground() == Color.GREEN || btnAnswer4.getBackground() == Color.GREEN) {

			System.out.println("TOCAN ODGOVOR!");
			Score++;

		}

		if (btnAnswer1.getBackground() == Color.GREEN || btnAnswer1.getBackground() == Color.RED
				|| btnAnswer2.getBackground() == Color.GREEN || btnAnswer2.getBackground() == Color.RED
				|| btnAnswer3.getBackground() == Color.GREEN || btnAnswer3.getBackground() == Color.RED
				|| btnAnswer4.getBackground() == Color.GREEN || btnAnswer4.getBackground() == Color.RED) {

			System.out.println("GameTime + 1");
			gameTime++;

			pickQuestion();

		}

	}

}
