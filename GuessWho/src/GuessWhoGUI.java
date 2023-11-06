import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class GuessWhoGUI extends JFrame implements ActionListener {
	// Initializes the audio clips 
	private Clip clip;
	private Clip effect; 
	private Clip effectTwo;
	private Clip effectThree;
	
	// Initializes new JPanel for the score 
	JPanel scorePanel = new JPanel();
	// Initializes a new JMenu for the menu of the game, and the menu items 
	JMenuBar menubar = new JMenuBar(); 
	JMenu Menu = new JMenu("Menu");
	JMenuItem exitMenuItem = new JMenuItem("Exit");
	JMenuItem resetMenuItem = new JMenuItem("Reset"); 
	
	//Initializes a new JLabel for the number of guesses
	JLabel guessAmountLabel = new JLabel("0");
	
	//Initializes a new JButton for the user to guess the mystery character 
	JButton guessButton = new JButton("Guess Who?"); 
	
	//Initializes new JPanel for the grid of characters 
	JPanel characterPanel = new JPanel();
	
	//Initializes an array of the characters 
	Character[] characterLabelArray = new Character[24];
	
	//Initializes JButton that allows the user to ask a question 
	JButton askButton = new JButton("Ask Question");
	
	//Initializes the number of questions the user has asked 
	private int numGuess = 0;
	
	//Declares the mystery character 
	Character mysteryCharacter = new Character(); 
	
	//Initializes new JLabel that is a label for the number of guesses 
	JLabel numGuesses = new JLabel("The number of guesses: "); 
	
	//Initializes a JLabel that is the title of the game 
	JLabel title = new JLabel("Guess Who"); 
	
	//Creates a new JLabel for the stopwatch feature 
	JLabel stopwatchLabel = new JLabel(); 
	
	//Sets the DOOR image icon to a .jpeg image of a door 
	private final ImageIcon DOOR = new ImageIcon("images/door.jpeg");
	
	//Constructor class of the GuessWhoGUI 
	public GuessWhoGUI() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		//
		setSize(700, 800);
		setLayout(null);
		//Reads and opens audio .wav files 
		music(); 
		//Plays the background music
		play();
		//Loops the background music continously
		loop(); 
		
		//Reads the .csv file into an array of character objects with their respective elements/traits 
		inputCharacters();
		
		//Initializes a mystery character
		createMysteryCharacter(); 
		
		//Creates the score panel 
		setupScorePanel();
		
		//Creates the character panel
		setupCharacterPanel();
		
		//Sets the size and location of the button; adds the button 
		askButton.setBounds(250,700, 200, 50);
		askButton.addActionListener(this);
		add(askButton);
		
		//Sets the GUI to visible 
		setVisible(true);
		
		//Creates the menu bar 
		createMenuBar(); 
		
		
	}


	//Creates the menubar 
	private void createMenuBar() {
		//Creates the submenu items
		exitMenuItem.addActionListener(this);
		resetMenuItem.addActionListener(this);
		//Adds the submenu items to the menu
		Menu.add(exitMenuItem);
		Menu.add(resetMenuItem);
		//Adds menu to the menubar
		add(Menu); 
		menubar.add(Menu); 
		//Sets location of the menubar to north of the score panel 
		scorePanel.setLayout(new BorderLayout());
		scorePanel.add(menubar, BorderLayout.NORTH);
	}

	//https://stackoverflow.com/questions/11919009/using-javax-sound-sampled-clip-to-play-loop-and-stop-multiple-sounds-in-a-game - Helped with how to loop and play audio files. 
	private void music() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		//Reads each audio .wav file 
		File music = new File("Elevator Music.wav");
		File correct = new File("Check mark sound effect.wav");
		File wrong = new File("wrong.wav");
		File button = new File("Mouse Click - Sound Effect (HD).wav");
		//Uses an input stream that expresses .wav files in frames so that it can be played 
		AudioInputStream sound = AudioSystem.getAudioInputStream(music);
		AudioInputStream correctEffect = AudioSystem.getAudioInputStream(correct);
		AudioInputStream wrongEffect = AudioSystem.getAudioInputStream(wrong);
		AudioInputStream buttonEffect = AudioSystem.getAudioInputStream(button);
		//Receives the clip and opens them 
        clip = AudioSystem.getClip();
        clip.open(sound);
        
        effect = AudioSystem.getClip();
        effect.open(correctEffect); 
        
        effectTwo = AudioSystem.getClip();
        effectTwo.open(wrongEffect); 
        
        effectThree = AudioSystem.getClip();
        effectThree.open(buttonEffect);
	}
	   // These methods set the clips to the first frame and plays the clip 
	   public void play(){
	        clip.setFramePosition(0);  // sets clip time to 0 seconds 
	        clip.start();
	    }
	   public void playEffect() {
		   effect.setFramePosition(0);
		   effect.start();
	   }
	   public void playEffectTwo() {
		   effectTwo.setFramePosition(0);
		   effectTwo.start(); 
	   }
	   public void playEffectThree() {
		   effectThree.setFramePosition(0);
		   effectThree.start(); 
	   }
	   //Continuously loops the music 
	    public void loop(){
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	    }
	    //Stops the music 
	    public void stop(){
	        clip.stop();
	    }
	//Initializes a mystery character
	public void createMysteryCharacter() {
		//selects a random number between 1-24
		int randomNumber = (int)(Math.random()*25);
		//Initializes mystery character as a random character within the character array. 
		mysteryCharacter = characterLabelArray[randomNumber]; 
		
		System.out.println(randomNumber);
		System.out.println(mysteryCharacter);
	}
	//Reads the .csv file and places an object for each character in their resepective array indices. 
	public void inputCharacters() {
		//try
		try {
			//Creates new scanner object to read a .csv file, uses ',' and '\n' as delimiters in order to do so 
			Scanner input = new Scanner (new File("characters.csv"));
			input.useDelimiter(",|\n");
			
			//Sets index as 0
			int index = 0;
			
			//Reads the next line 
			input.nextLine(); 
			
			//While there are more lines to read 
			while(input.hasNext()) {
				
				//Creates a new character in the index of the characterLabelArray 
				characterLabelArray[index] = new Character();
				
				//Reads the .csv file to input the character object's respective elements 
				characterLabelArray[index].setName(input.next().replaceAll("\n", "").replaceAll("\r", ""));
				characterLabelArray[index].setHairColour(input.next());
				characterLabelArray[index].setClothingColour(input.next());
				characterLabelArray[index].setWearHat(input.nextBoolean());
				characterLabelArray[index].setHasClaw(input.nextBoolean());
				characterLabelArray[index].setHasVehicle(input.nextBoolean());
				characterLabelArray[index].setHasBeard(input.nextBoolean());
				characterLabelArray[index].setWearsEarrings(input.nextBoolean());
				characterLabelArray[index].setWearsHelmet(input.nextBoolean());
				characterLabelArray[index].setWearsTie(input.nextBoolean());
				characterLabelArray[index].setHasBeak(input.nextBoolean());
				characterLabelArray[index].setHasTail(input.nextBoolean());
				characterLabelArray[index].setHasGuitar(input.nextBoolean());
				characterLabelArray[index].setHasScales(input.nextBoolean());
				characterLabelArray[index].setFileName(input.next().replaceAll("\n" , "").replaceAll("\r", ""));
				characterLabelArray[index].setIcon(new ImageIcon(characterLabelArray[index].getFileName()));
				
				//Print the current index 
				System.out.println(characterLabelArray[index]);
				
				//Increment index by one 
				index++;
			}
		//Catch
		}catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	//Creates the character panel 
	private void setupCharacterPanel() {
		//Creates the size and location of the character panel; sets background colour 
		characterPanel.setBounds(50, 300, 600, 400);
		characterPanel.setBackground(Color.BLUE);
		//Sets a grid layout within the panel 
		characterPanel.setLayout(new GridLayout(4, 6));
		//For the length of the character array
		for(int index = 0; index < characterLabelArray.length; index++) 
			//Add the icons of each character 
			characterPanel.add(characterLabelArray[index]);
		//Add the character panel to the GUI 
		add(characterPanel); 
	}
	//Creates the score panel 
	public void setupScorePanel() {
		//Sets the size, location, background, and layout of the score panel 
		scorePanel.setBounds(50, 50,600,200);
		scorePanel.setBackground(Color.red);
		scorePanel.setLayout(null);
		
		//Sets the size and location of the number of guesses; adds to GUI 
		guessAmountLabel.setBounds(300, 100, 50, 25);
		scorePanel.add(guessAmountLabel);
		
		//Sets the size and location of the number of guesses; adds to GUI 
		numGuesses.setBounds(235, 80, 200, 25);
		scorePanel.add(numGuesses);
		
		//Sets the size, location, and font of the title; adds to GUI 
		title.setBounds(230, 30, 200, 25);
		scorePanel.add(title);
		title.setFont(new Font("Roboto Thin", Font.BOLD,27));
		
		//Sets the size and location of the guess button; adds to GUI
		guessButton.setBounds(200, 150, 200, 25);
		guessButton.addActionListener(this);
		scorePanel.add(guessButton);

		
		//https://docs.oracle.com/javase/8/docs/api/javax/swing/Timer.html 
		//https://stackoverflow.com/questions/41389645/java-how-to-make-a-timer-which-displays-elapsing-time-in-jlabel- Used to create a stopwatch for how long the user has been playing 
		add(scorePanel); 
		//Sets the size and location of the stop watch 
		stopwatchLabel.setBounds(560, 20, 100, 25); 
		//Initializes task performer as a new action listener 
        ActionListener taskPerformer = new ActionListener() {
        //starting time of the clock	
        long startTime = -1; 
        
        public void actionPerformed(ActionEvent evt) {
        	//if the starting time is less than 0 
        	if(startTime < 0) 
        		//Start time is equal to the current time in milliseconds 
        		startTime = System.currentTimeMillis();
        		//Current time 
        		long now = System.currentTimeMillis(); 
        		//
        		long clockTime = now - startTime; 
        		//Formats the time in minutes and seconds 
        		SimpleDateFormat df = new SimpleDateFormat("mm:ss"); 
        		stopwatchLabel.setText(df.format(clockTime));
        	}
        };
        //creates a new timer that occurs each second 
        new Timer(1000, taskPerformer).start(); 
        //adds the stopwatch to the score panel 
        scorePanel.add(stopwatchLabel);
	}

	//Handles the events that occur when the user clicks a button 
	public void actionPerformed(ActionEvent event) {
		//If the user clicks the exit menu item 
		if(event.getSource() == exitMenuItem) {
			//Terminates program
			System.exit(0); 
		} 
		//If the user clicks the reset menu item 
		if(event.getSource() == resetMenuItem) {
			this.dispose();
			//Stop the music 
				stop(); 
				//try 
				try {
					//Runs the class again 
					new GuessWhoGUI();
					//catch
				} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}
			
		//if the user clicks the ask question button 
		if(event.getSource() == askButton) {
			//plays the click button sound effect 
			playEffectThree();
			
			//Increments number of guesses by 1 
			numGuess++;
			//Updates the number of guesses 
			guessAmountLabel.setText(Integer.toString(numGuess));
			//Prompts the user for a keyword associated with each question 
			String question = JOptionPane.showInputDialog("Ask your question:");
			//For the respective keyword, answer the associated question 
			if(question.contains("hair")) {
				String hairColour = JOptionPane.showInputDialog("What colour hair?");
				checkHairColour(hairColour);
			}else if (question.contains("tail")) {
				String tail = JOptionPane.showInputDialog("Do they have a tail?");
				checkHasTail();
				
			}else if (question.contains("clothing")) {
				String clothingColour = JOptionPane.showInputDialog("What colour hair");
				checkClothingColour(clothingColour);
			}else if (question.contains("hat")){
				String hat = JOptionPane.showInputDialog("Do they wear a hat?");
				checkHasHat(); 
			}else if (question.contains("claw")) {
				String claw = JOptionPane.showInputDialog("Do they have a claw?");
				checkHasClaw(); 
			}else if (question.contains("vehicle")) {
				String vehicle = JOptionPane.showInputDialog("Do they have a vehicle?");
				checkHasVehicle(); 
			}else if (question.contains("beard")) {
				String beard = JOptionPane.showInputDialog("Do they have a beard?");
				checkHasBeard(); 
			}else if (question.contains("earrings")) {
				String earrings = JOptionPane.showInputDialog("Do they wear earrings?");
				checkWearsEarrings();
			}else if (question.contains("helmet")) {
				String helmet = JOptionPane.showInputDialog("Do they wear a helmet?");
				checkWearsHelmet(); 
			}else if (question.contains("tie")) {
				String tie = JOptionPane.showInputDialog("Do they wear a tie?");
				checkWearsTie(); 
			}else if (question.contains("beak")) {
				String beak = JOptionPane.showInputDialog("Do they have a beak?");
				checkHasBeak(); 
			}else if (question.contains("guitar")) {
				String beard = JOptionPane.showInputDialog("Do they have a beard?");
				checkHasGuitar(); 
			}else if (question.contains("scales")) {
				String beard = JOptionPane.showInputDialog("Do they have scales?");
				checkHasScales(); 
			}
			
			}
			//if the user clicks the guess button 
			if(event.getSource() == guessButton) {
				//Play press button sound effect 
				playEffectThree();
				//Prompts the user for the mystery character's name 
				String answer = JOptionPane.showInputDialog("Who do you think the mystery characer is? ");
				//if the character name matches the mystery character's 
				if(answer.equalsIgnoreCase(mysteryCharacter.getName())) {
					stop(); 
					playEffect(); 
					//Opens a pop up that informs the user that they won
					JOptionPane.showMessageDialog(this,  "You Win!"); 
					//Closes current window
					this.dispose();
					//try 
					try {
						//Creates new instance of the GUI class 
						new GuessWhoGUI();
					//catch
					} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 

				}else { //if the character name does not match the mystery character's 
					//Stops music, plays loss sound
					stop(); 
					playEffectTwo(); 
					//Opens a pop up that informs the user that they lost
					JOptionPane.showMessageDialog(this, "You Lose!");
					//Closes current window
					this.dispose();
					//try 
					try {
						//Creates new instance of the GUI class 
						new GuessWhoGUI();
					//catch
					} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
	}
	//the following methods checks if the mystery character does or does not have a particular trait, and puts a door on top of all the characters that do not have the matching trait with the mystery character
	//the trait that runs is dependant on what question the user is asking. 
	private void checkHasScales() {
		if(!mysteryCharacter.getHasScales()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasScales())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getHasScales()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasScales())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
	}
	private void checkHasGuitar() {
		if(!mysteryCharacter.getHasGuitar()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasGuitar())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getHasGuitar()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasGuitar())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
		
	}
	private void checkHasBeak() {
		if(!mysteryCharacter.getHasBeak()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasBeak())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getWearsTie()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasBeak())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
		
	}
	private void checkWearsTie() {
		if(!mysteryCharacter.getWearsTie()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getWearsTie())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getWearsTie()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getWearsTie())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
		
	}
	private void checkWearsHelmet() {
		if(!mysteryCharacter.getWearsHelmet()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getWearsHelmet())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getWearsHelmet()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getWearsHelmet())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
		
	}
	private void checkWearsEarrings() {
		if(!mysteryCharacter.getWearsEarrings()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getWearsEarrings())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getWearsEarrings()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getWearsEarrings())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
		
	}
	private void checkHasBeard() {
			if(!mysteryCharacter.getHasBeard()) {
				for(int index = 0; index < characterLabelArray.length; index++) {
					if(characterLabelArray[index].getHasBeard())
						characterLabelArray[index].setIcon(DOOR); 
				}
			}else if(mysteryCharacter.getHasBeard()) {
				for(int index = 0; index < characterLabelArray.length; index++) {
					if(characterLabelArray[index].getHasBeard())
						characterLabelArray[index].setIcon(DOOR);
				}
			}
			
		}
	private void checkHasVehicle() {
		if(!mysteryCharacter.getHasVehicle()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasVehicle())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getHasTail()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasVehicle())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
		
	}
	private void checkHasClaw() {
		if(!mysteryCharacter.getHasClaw()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasClaw())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getHasClaw()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasClaw())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
	}
	private void checkHasHat() {
		if(!mysteryCharacter.getWearHat()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getWearHat())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getWearHat()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getWearHat())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
	}
	private void checkClothingColour(String clothingColour) {
		if(!mysteryCharacter.getClothingColour().equalsIgnoreCase(clothingColour)) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHairColour().equalsIgnoreCase(clothingColour))
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if (mysteryCharacter.getClothingColour().equalsIgnoreCase(clothingColour)) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getClothingColour().equalsIgnoreCase(clothingColour)) 
					characterLabelArray[index].setIcon(DOOR); 
				}
			}
		
	}
	private void checkHairColour(String hairColour) {
		if(!mysteryCharacter.getHairColour().equalsIgnoreCase(hairColour)) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHairColour().equalsIgnoreCase(hairColour))
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if (mysteryCharacter.getHairColour().equalsIgnoreCase(hairColour)) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHairColour().equalsIgnoreCase(hairColour)) 
					characterLabelArray[index].setIcon(DOOR); 
				}
			}
		}
	private void checkHasTail() {
		if(!mysteryCharacter.getHasTail()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasTail())
					characterLabelArray[index].setIcon(DOOR); 
			}
		}else if(mysteryCharacter.getHasTail()) {
			for(int index = 0; index < characterLabelArray.length; index++) {
				if(characterLabelArray[index].getHasTail())
					characterLabelArray[index].setIcon(DOOR);
			}
		}
	}
}
