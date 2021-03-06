package homework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Engine engine;
	private boolean init_state = true;
	private boolean valid_data = true;
	private JLabel mineCounter = new JLabel("", JLabel.CENTER);
	private JLabel gameTime = new JLabel("", JLabel.CENTER);
	private Border mineBorder = BorderFactory.createLineBorder(Color.RED, 1);
	private Border timeBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
	private JButton settings = new JButton();
	private JButton newGame = new JButton();
	private JButton leaderBoard = new JButton();
	private JButton[][] field = new JButton[22][22];
	private JRadioButton[] difficulties = new JRadioButton[3];
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton okButton = new JButton();
	private JButton cancelButton = new JButton();
	private JLabel messages = new JLabel("", JLabel.CENTER);
	private JLabel nameLabel = new JLabel("", JLabel.LEFT);
	private JLabel ipLabel = new JLabel("", JLabel.LEFT);
	private JLabel[][] results = new JLabel[10][5];
	private JTextField readName = new JTextField();
	private JTextField readIP = new JTextField();
	private String[][] leaderboard = new String[10][4];
	private String userName;
	private String userIP;
	private int actualboard = 1;
	private int field_size = 10;
	private int mines = 10;
	private int difficulty = 1;
	private int coordinate_x;
	private int coordinate_y;
	private int[][] field_state = new int[22][22];
	private int[][] field_content = new int[22][22];
	private int mouseclick;
    private MouseListener mouseListener = new MouseAdapter() {
        public void mouseReleased(MouseEvent mouseEvent) {
            if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                //System.out.println("Left button pressed.");
    			for(int y = 0;y < field_size;y++)
    			{
    				for(int x = 0;x < field_size;x++)
    				{	
    					if(mouseEvent.getSource().equals(field[x][y]))
    					{
    						coordinate_x = x;
    						coordinate_y = y;
    						mouseclick = 0;
    						engine.setFieldbutton(true);
    						engine.readClickMeaning();
    					}
    				}
    			}
    			
    			if(engine.isLost() == true) {
    				
    				JOptionPane.showMessageDialog(null, "You have lost!");
    				for(int y = 0;y < field_size;y++)
    				{
    					for(int x = 0;x < field_size;x++)
    					{
    						field[x][y].removeMouseListener(mouseListener);
    					}
    				}
    				removeActionListeners();
    			}
    			
    			if(engine.isWon() == true) {
    				JOptionPane.showMessageDialog(null, "You have won!");
    				getContentPane().removeAll();
    				repaint();
    				actualboard = 3;
    				paintBoards();
    			}
    				
            }
            if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                //System.out.println("Right button pressed.");
    			for(int y = 0;y < field_size;y++)
    			{
    				for(int x = 0;x < field_size;x++)
    				{	
    					if(mouseEvent.getSource().equals(field[x][y]))
    					{
    						coordinate_x = x;
    						coordinate_y = y;
    						mouseclick = 1;
    						engine.setFieldbutton(true);
    						engine.readClickMeaning();
    					}
    				}
    			}
    			if(engine.isWon() == true) {
    				JOptionPane.showMessageDialog(null, "You have won!");
    				getContentPane().removeAll();
    				repaint();
    				actualboard = 3;
    				paintBoards();
    			}
            }
        }
    };
	
	GUI()
	{
		setTitle("Minesweeper");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		paintBoards();
		addActionListeners();
		init_state = false;
		/*for(int x = 0;x < 10;x++)
		{
			for(int y = 0;y < 4;y++)
			{
				if(y == 0)
				{
					leaderboard[x][y] = "a";
				}
				else if(y == 1)
				{
					leaderboard[x][y] = "b";
				}
				else if(y == 2)
				{
					leaderboard[x][y] = "c";
				}
				else
				{
					leaderboard[x][y] = "2019/05/19 16:18:43";
				}
			}
		}*/
	}
	
	public int getCoordinate_x() {
		return coordinate_x;
	}

	public int getCoordinate_y() {
		return coordinate_y;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setActualboard(int actualboard) {
		this.actualboard = actualboard;
	}
	
	public int getActualboard() {
		return actualboard;
	}

	public int getMouseclick() {
		return mouseclick;
	}

	public int getDifficulty() {
		return difficulty;
	}
	
	public void setField_size(int field_size) {
		this.field_size = field_size;
	}

	public void setLeaderboard(String[][] leaderboard) {
		this.leaderboard = leaderboard;
	}
	
	public boolean isValid_data() {
		return valid_data;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setGameTime(int time)
	{
		gameTime.setText(Integer.toString(time));
	}
	
	public void updateButtonAppearance()
	{
		field_state = engine.getState();
		field_content = engine.getBoard();
		
		for (int y = 0; y < field_size; y++) {
			for (int x = 0; x < field_size; x++) {
				
				if(field_state[x][y] == 0)
				{
					field[x][y].setIcon(null);
				}
				
				else if(field_state[x][y] == 1)
				{
					field[x][y].setFont(new Font("Times New Roman", Font.BOLD, 12));
					if(engine.isLost() == true)
					{
						field[x][y].setIcon(null);
					}
					switch (field_content[x][y])
					{
						case 0: 
							field[x][y].setBackground(Color.white);
						break;
						case 1: 
							field[x][y].setBackground(Color.white);
							field[x][y].setForeground(Color.blue);
							field[x][y].setText("1");
						break;
						case 2: 
							field[x][y].setBackground(Color.white);
							field[x][y].setForeground(Color.green);
							field[x][y].setText("2");
						break;
						case 3: 
							field[x][y].setBackground(Color.white);
							field[x][y].setForeground(Color.orange);
							field[x][y].setText("3");
						break;
						case 4: 
							field[x][y].setBackground(Color.white);
							field[x][y].setForeground(new Color(0,0,128));
							field[x][y].setText("4");
						break;
						case 5: 
							field[x][y].setBackground(Color.white);
							field[x][y].setForeground(new Color(178,34,34));
							field[x][y].setText("5");
						break;
						case 6: 
							field[x][y].setBackground(Color.white);
							field[x][y].setForeground(new Color(72,209,204));
							field[x][y].setText("6");
						break;
						case 7: 
							field[x][y].setBackground(Color.white);
							field[x][y].setForeground(Color.black);
							field[x][y].setText("7");
						break;
						case 8: 
							field[x][y].setBackground(Color.white);
							field[x][y].setForeground(Color.darkGray);
							field[x][y].setText("8");
						break;
						default:
							field[x][y].setBackground(Color.red);
							field[x][y].setIcon(new ImageIcon(GUI.class.getResource("/icons/mine.png")));
						break;
					}
				}
				
				else if(field_state[x][y] == 2)
				{
					field[x][y].setIcon(new ImageIcon(GUI.class.getResource("/icons/flag.png")));
				}
			}
		}
	
		mines = engine.getMinesRemaining();
		mineCounter.setText(Integer.toString(mines));
	}

	public void paintGameBoard()
	{
		mineCounter.setBounds(0, 0, field_size*8 - 1, 50);
		mineCounter.setOpaque(true);
		mineCounter.setBackground(Color.WHITE);
		mineCounter.setBorder(mineBorder);
		if(init_state == true)
		{
			if(difficulty == 1)
			{
				mines = 10;
			}
			else if(difficulty == 2)
			{
				mines = 40;
			}
			else
			{
				mines = 100;
			}
			mineCounter.setText(Integer.toString(mines));
		}
		else
		{	
			mines = engine.getMinesRemaining();
			mineCounter.setText(Integer.toString(mines));
		}
		getContentPane().add(mineCounter);
		
		gameTime.setBounds(field_size*8, 0, field_size*8 - 1, 50);
		gameTime.setOpaque(true);
		gameTime.setBackground(Color.WHITE);
		gameTime.setBorder(timeBorder);
		if(init_state == true)
		{
			gameTime.setText("0");
		}
		getContentPane().add(gameTime);
		
		settings.setBounds(field_size*16, 0, field_size*8, 50);
		settings.setIcon(new ImageIcon(GUI.class.getResource("/icons/gear.png")));
		settings.setBackground(Color.CYAN);
		getContentPane().add(settings);
		
		newGame.setBounds(field_size*24, 0, field_size*8, 50);
		newGame.setIcon(new ImageIcon(GUI.class.getResource("/icons/smiley.png")));
		newGame.setBackground(Color.CYAN);
		getContentPane().add(newGame);
		
		leaderBoard.setBounds(field_size*32, 0, field_size*8, 50);
		leaderBoard.setIcon(new ImageIcon(GUI.class.getResource("/icons/cup.png")));
		leaderBoard.setBackground(Color.CYAN);
		getContentPane().add(leaderBoard);

		for(int y = 0;y < field_size;y++)
		{
			for(int x = 0;x < field_size;x++)
			{
				field[x][y] = new JButton();
				field[x][y].setBounds(x*40, y*30 + 50, 40, 30);
				
				if(init_state == false)
				{
					updateButtonAppearance();
				}

				getContentPane().add(field[x][y]);
				field[x][y].addMouseListener(mouseListener);
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
			difficulties[i] = new JRadioButton();
			buttonGroup.add(difficulties[i]);
			if(i == 0)
			{
				difficulties[i].setBounds(115, 50, 55, 20);
				difficulties[i].setText("Easy");
				difficulties[i].doClick();
				
			}
			else if(i == 1)
			{
				difficulties[i].setBounds(115, 100, 70, 20);
				difficulties[i].setText("Medium");
			}
			else
			{
				difficulties[i].setBounds(115, 150, 52, 20);
				difficulties[i].setText("Hard");
			}
			getContentPane().add(difficulties[i]);
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
		messages.setBounds(20, 10, 350, 40);
		messages.setFont(new Font("Times New Roman", Font.BOLD, 13));
		messages.setText("Give your name and your IP address to get to the leaderboard!");
		getContentPane().add(messages);
		
		nameLabel.setBounds(20, 60, 50, 25);
		nameLabel.setText("Name:");
		getContentPane().add(nameLabel);
		
		readName.setBounds(70, 60, 300, 25);
		getContentPane().add(readName);
		
		ipLabel.setBounds(20, 110, 50, 25);
		ipLabel.setText("IP:");
		getContentPane().add(ipLabel);
		
		readIP.setBounds(70, 110, 300, 25);
		getContentPane().add(readIP);
		
		okButton.setBounds(70, 180, 80, 30);
		okButton.setText("OK");
		getContentPane().add(okButton);
		
		cancelButton.setBounds(240, 180, 80, 30);
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
					results[x][y].setText(leaderboard[x][y-1]);
					getContentPane().add(results[x][y]);
				}
				else if(y == 2)
				{
					results[x][y] = new JLabel("", Label.RIGHT);
					results[x][y].setBounds(300, x*30 + 50, 50, 30);
					results[x][y].setText(leaderboard[x][y-1]);
					getContentPane().add(results[x][y]);
				}
				else if(y == 3)
				{
					results[x][y] = new JLabel("", Label.RIGHT);
					results[x][y].setBounds(350, x*30 + 50, 100, 30);
					results[x][y].setText(leaderboard[x][y-1]);
					getContentPane().add(results[x][y]);
				}
				else
				{
					results[x][y] = new JLabel("", Label.RIGHT);
					results[x][y].setBounds(450, x*30 + 50, 200, 30);
					results[x][y].setText(leaderboard[x][y-1]);
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
			setSize(field_size*40 + 13,field_size*30 + 87);
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
			setSize(400,280);
			setVisible(true);
		}
		else
		{
			listResults();
			setSize(700,480);
			setVisible(true);
		}
	}
	
	public void addActionListeners()
	{
		settings.addActionListener(this);
		newGame.addActionListener(this);
		leaderBoard.addActionListener(this);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	
	public void removeActionListeners()
	{
		leaderBoard.removeActionListener(this);
		settings.removeActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(settings))
		{
			getContentPane().removeAll();
			repaint();
			engine.setSettings(true);
			engine.readClickMeaning();
		}
		else if(event.getSource().equals(newGame))
		{
			getContentPane().removeAll();
			repaint();
			engine.setNewgame(true);
			engine.readClickMeaning();
			if(leaderBoard.getActionListeners().length == 0)
			{
				leaderBoard.addActionListener(this);
				settings.addActionListener(this);
			}
		}
		else if(event.getSource().equals(leaderBoard))
		{
			getContentPane().removeAll();
			repaint();
			engine.setLeaderboard(true);
			engine.readClickMeaning();
		}
		else if(event.getSource().equals(okButton))
		{
			getContentPane().removeAll();
			repaint();
			if(actualboard == 2)
			{
				for(int i = 0;i < 3;i++)
				{
					if(difficulties[i].isSelected() == true)
					{
						difficulty = i+1;
					}
				}
				init_state = true;
			}
			
			if(actualboard == 3)
			{
				try
				{
					userName = readName.getText();
					userIP = readIP.getText();
				}
				catch (NullPointerException npe)
				{
					System.out.print("Invalid name or IP!");
					valid_data = false;
				}
			}
			engine.setOkbutton(true);
			engine.readClickMeaning();
			init_state = false;
			valid_data = true;
			readName.setText("");
			readIP.setText("");		
		}
		else if(event.getSource().equals(cancelButton))
		{
			getContentPane().removeAll();
			repaint();
			engine.setCancelbutton(true);
			engine.readClickMeaning();
			if(engine.isWon() == true)
			{
				for(int y = 0;y < field_size;y++)
				{
					for(int x = 0;x < field_size;x++)
					{
						field[x][y].removeMouseListener(mouseListener);
					}
				}
				removeActionListeners();
			}
		}
	}
	
}