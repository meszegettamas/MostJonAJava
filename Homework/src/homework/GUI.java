package homework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private TestEngine engine;
	private int actualboard = 1;
	private JLabel mineCounter = new JLabel("", JLabel.CENTER);
	private JLabel gameTime = new JLabel("", JLabel.CENTER);
	private Border mineBorder = BorderFactory.createLineBorder(Color.RED, 1);
	private Border timeBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
	private JButton settings = new JButton();
	private JButton newGame = new JButton();
	private JButton leaderBoard = new JButton();
	private JButton[][] field = new JButton[30][30];
	private JRadioButton[] difficulities = new JRadioButton[3];
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton okButton = new JButton();
	private JButton cancelButton = new JButton();
	private JLabel messages = new JLabel("", JLabel.CENTER);
	private JLabel[][] results = new JLabel[10][5];
	private JTextField readName = new JTextField();
	private int sizeoffield = 26;
	private int mines = 10;
	private int time = 0;
	
	GUI()
	{
		setTitle("Minesweeper");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		paintBoards();
		actionListeners();
	}
	
	public void setEngine(TestEngine engine) {
		this.engine = engine;
	}

	public void setActualboard(int actualboard) {
		this.actualboard = actualboard;
	}

	public void paintGameBoard()
	{
		mineCounter.setBounds(0, 0, sizeoffield*5 - 1, 50);
		mineCounter.setOpaque(true);
		mineCounter.setBackground(Color.WHITE);
		mineCounter.setBorder(mineBorder);
		mineCounter.setText(Integer.toString(mines));
		getContentPane().add(mineCounter);
		
		gameTime.setBounds(sizeoffield*5, 0, sizeoffield*5 - 1, 50);
		gameTime.setOpaque(true);
		gameTime.setBackground(Color.WHITE);
		gameTime.setBorder(timeBorder);
		gameTime.setText(Integer.toString(time));
		getContentPane().add(gameTime);
		
		settings.setBounds(sizeoffield*10, 0, sizeoffield*5 - 1, 50);
		settings.setIcon(new ImageIcon(GUI.class.getResource("/icons/gear.png")));
		settings.setBackground(Color.CYAN);
		getContentPane().add(settings);
		
		newGame.setBounds(sizeoffield*15, 0, sizeoffield*5 - 1, 50);
		newGame.setIcon(new ImageIcon(GUI.class.getResource("/icons/smiley.png")));
		newGame.setBackground(Color.CYAN);
		getContentPane().add(newGame);
		
		leaderBoard.setBounds(sizeoffield*20, 0, sizeoffield*5, 50);
		leaderBoard.setIcon(new ImageIcon(GUI.class.getResource("/icons/cup.png")));
		leaderBoard.setBackground(Color.CYAN);
		getContentPane().add(leaderBoard);

		for(int y = 0;y < sizeoffield;y++)
		{
			for(int x = 0;x < sizeoffield;x++)
			{
				field[x][y] = new JButton();
				field[x][y].setBounds(x*25, y*25 + 50, 25, 25);
				getContentPane().add(field[x][y]);
			}
		}
	}
	
	public void showSettings()
	{
		messages.setBounds(100, 10, 100, 20);
		messages.setFont(new Font("Times New Roman", Font.BOLD, 14));
		messages.setText("Choose a level!");
		getContentPane().add(messages);
		
		for(int i = 0;i < 3;i++)
		{
			difficulities[i] = new JRadioButton();
			buttonGroup.add(difficulities[i]);
			if(i == 0)
			{
				difficulities[i].setBounds(115, 50, 52, 20);
				difficulities[i].setText("Easy");
				difficulities[i].doClick();
			}
			else if(i == 1)
			{
				difficulities[i].setBounds(115, 100, 70, 20);
				difficulities[i].setText("Medium");
			}
			else
			{
				difficulities[i].setBounds(115, 150, 52, 20);
				difficulities[i].setText("Hard");
			}
			getContentPane().add(difficulities[i]);
		}
		
		okButton.setBounds(50, 200, 80, 30);
		okButton.setText("OK");
		getContentPane().add(okButton);
		
		cancelButton.setBounds(170, 200, 80, 30);
		cancelButton.setText("Cancel");
		getContentPane().add(cancelButton);
	}
	
	public void readUserName()
	{
		messages.setBounds(20, 10, 235, 20);
		messages.setText("Give your name to get to the leaderboard!");
		getContentPane().add(messages);
		
		readName.setBounds(20, 50, 235, 20);
		getContentPane().add(readName);
		
		okButton.setBounds(20, 100, 80, 30);
		okButton.setText("OK");
		getContentPane().add(okButton);
		
		cancelButton.setBounds(175, 100, 80, 30);
		cancelButton.setText("Cancel");
		getContentPane().add(cancelButton);
	}
	
	public void listResults()
	{
		messages.setBounds(20, 10, 560, 30);
		messages.setFont(new Font("Times New Roman", Font.BOLD, 20));
		messages.setText("LEADERBOARD");
		getContentPane().add(messages);
		
		for(int x = 0;x < 10;x++)
		{
			for(int y = 0;y < 5;y++)
			{
				if(y == 0)
				{
					results[x][y] = new JLabel("", Label.RIGHT);
					results[x][y].setBounds(20, x*30 + 50, 20, 30);
					results[x][y].setText(Integer.toString(x+1) + ".");
					getContentPane().add(results[x][y]);
				}
				else if(y == 1)
				{
					results[x][y] = new JLabel("", Label.RIGHT);
					results[x][y].setBounds(40, x*30 + 50, 260, 30);
					results[x][y].setText("Name");
					getContentPane().add(results[x][y]);
				}
				else if(y == 2)
				{
					results[x][y] = new JLabel("", Label.RIGHT);
					results[x][y].setBounds(300, x*30 + 50, 50, 30);
					results[x][y].setText("Time");
					getContentPane().add(results[x][y]);
				}
				else if(y == 3)
				{
					results[x][y] = new JLabel("", Label.RIGHT);
					results[x][y].setBounds(350, x*30 + 50, 100, 30);
					results[x][y].setText("Medium");
					getContentPane().add(results[x][y]);
				}
				else
				{
					results[x][y] = new JLabel("", Label.RIGHT);
					results[x][y].setBounds(450, x*30 + 50, 150, 30);
					results[x][y].setText("2019.04.24.");
					getContentPane().add(results[x][y]);
				}
			}
		}
		
		okButton.setBounds(260, 400, 80, 30);
		okButton.setText("OK");
		getContentPane().add(okButton);
		
	}


	public void paintBoards()
	{
		if(actualboard == 1)
		{
			paintGameBoard();
			setSize(sizeoffield*25 + 6,sizeoffield*25 + 79);
			setVisible(true);
		}
		else if(actualboard == 2)
		{
			showSettings();
			setSize(300,300);
			setVisible(true);
		}
		else if(actualboard == 3)
		{
			readUserName();
			setSize(300,200);
			setVisible(true);
		}
		else
		{
			listResults();
			setSize(600,480);
			setVisible(true);
		}
	}
	
	public void actionListeners()
	{
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				repaint();
				engine.setSettings(true);
				engine.readClickMeaning();
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				repaint();
				engine.setOkbutton(true);
				engine.readClickMeaning();
			}
		});
		
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				repaint();
				engine.setNewgame(true);
				engine.readClickMeaning();
			}
		});
		
		leaderBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				repaint();
				engine.setLeaderboard(true);
				engine.readClickMeaning();
			}
		});
	}
}