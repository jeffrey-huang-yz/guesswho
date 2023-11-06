import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GuessWhoApp {
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		new GuessWhoGUI();
	}
}
