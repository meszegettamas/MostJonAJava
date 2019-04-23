package homework;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GUI extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	
	private TestEngine engine;
	private int actualboard;
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
	private JLabel messages = new JLabel("", JLabel.LEFT);
	private JTextField readName = new JTextField();
	private int sizeoffield;
	private int mines;
	private int time;
	
	GUI()
	{
		engine = new TestEngine();
		sizeoffield = engine.getSizeofgameboard();
		mines = engine.getNumberofmines();
		time = engine.getTime();
		actualboard = 1;
		setTitle("Minesweeper");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	void paintGameBoard()
	{

		mineCounter.setBounds(0, 0, sizeoffield*5 - 1, 50);
		mineCounter.setOpaque(true);
		mineCounter.setBackground(Color.WHITE);
		mineCounter.setBorder(mineBorder);
		mineCounter.setText(Integer.toString(mines));
		add(mineCounter);
		
		gameTime.setBounds(sizeoffield*5, 0, sizeoffield*5 - 1, 50);
		gameTime.setOpaque(true);
		gameTime.setBackground(Color.WHITE);
		gameTime.setBorder(timeBorder);
		gameTime.setText(Integer.toString(time));
		add(gameTime);
		
		settings.setBounds(sizeoffield*10, 0, sizeoffield*5 - 1, 50);
		settings.setText("1");
		settings.setBackground(Color.CYAN);
		add(settings);
		
		newGame.setBounds(sizeoffield*15, 0, sizeoffield*5 - 1, 50);
		newGame.setText("2");
		newGame.setBackground(Color.CYAN);
		add(newGame);
		
		leaderBoard.setBounds(sizeoffield*20, 0, sizeoffield*5, 50);
		leaderBoard.setText("3");
		leaderBoard.setBackground(Color.CYAN);
		add(leaderBoard);

		for(int y = 0;y < sizeoffield;y++)
		{
			for(int x = 0;x < sizeoffield;x++)
			{
				field[x][y] = new JButton();
				field[x][y].setBounds(x*25, y*25 + 50, 25, 25);
				add(field[x][y]);
			}
		}
	}
	
	void showSettings()
	{
		messages.setBounds(100, 10, 100, 20);
		messages.setText("Choose a level!");
		add(messages);
		
		for(int i = 0;i < 3;i++)
		{
			difficulities[i] = new JRadioButton();
			buttonGroup.add(difficulities[i]);
			if(i == 0)
			{
				difficulities[i].setBounds(115, 50, 52, 20);
				difficulities[i].setText("Easy");
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
			add(difficulities[i]);
		}
		
		okButton.setBounds(50, 200, 80, 30);
		okButton.setText("OK");
		add(okButton);
		
		cancelButton.setBounds(170, 200, 80, 30);
		cancelButton.setText("Cancel");
		add(cancelButton);
	}
	
	void readUserName()
	{
		messages.setBounds(20, 10, 240, 20);
		messages.setText("Give your name to get to the leaderboard!");
		
		add(messages);
		
		readName.setBounds(20, 50, 235, 20);
		add(readName);
		
		okButton.setBounds(20, 100, 80, 30);
		okButton.setText("OK");
		add(okButton);
		
		cancelButton.setBounds(175, 100, 80, 30);
		cancelButton.setText("Cancel");
		add(cancelButton);
	}
	
	void listResults()
	{
		
	}

	@Override
	public void run() {
		
		if(actualboard == 0)
		{
			paintGameBoard();
			setSize(sizeoffield*25 + 6,sizeoffield*25 + 79);
			setVisible(true);
			actualboard = 1;
		}
		else if(actualboard == 1)
		{
			setVisible(false);
			paintGameBoard();
			setSize(sizeoffield*25 + 6,sizeoffield*25 + 79);
			setVisible(true);
		}
		else if(actualboard == 2)
		{
			setVisible(false);
			showSettings();
			setSize(300,300);
			setVisible(true);
		}
		else if(actualboard == 3)
		{
			setVisible(false);
			readUserName();
			setSize(300,200);
			setVisible(true);
		}
		else
		{
			setVisible(false);
			listResults();
			setSize(300,300);
			setVisible(true);
		}
	}
}
