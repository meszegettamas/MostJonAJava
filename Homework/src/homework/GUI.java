package homework;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class GUI extends JFrame{

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
	private int sizeoffield;
	private int mines;
	private int time;
	
	GUI()
	{
		setTitle("Minesweeper");
		engine = new TestEngine();
		sizeoffield = engine.getSizeofgameboard();
		mines = engine.getNumberofmines();
		time = engine.getTime();
		setSize(sizeoffield*25 + 6,sizeoffield*25 + 79);
		paintGameBoard();
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
}
