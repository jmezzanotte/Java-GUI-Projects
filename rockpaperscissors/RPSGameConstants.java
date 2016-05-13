
package rockpaperscissors;

import java.awt.*;

/**
 *
 * @author JohnMezzanotte
 */
public interface RPSGameConstants {
    // moves for our RPSGame. I want to be sure both my RPSGame class 
    // and the GUI interface use the same moves. 
    enum Moves {ROCK, PAPER, SCISSORS};
    
    public final int PLAY_AREA_HEIGHT = 100; 
    public final int PLAY_AREA_WIDTH = 350; 
    
    public final int BUTTON_WIDTH = 50; 
    public final int BUTTON_HEIGHT = 50; 
    
    public final int PROFILE_WIDTH = 50; 
    public final int PROFILE_HEIGHT = 50;
    
    public final Color RPS_GREEN = new Color(124,218,31);
    
    public final String ROCK_ALT_IMAGE = "ROCK"; 
    public final String PAPER_ALT_IMAGE = "PAPER"; 
    public final String SCISSORS_ALT_IMAGE = "SCISSORS";
    
    public final String USER_ALT_IMAGE = "USER"; 
    public final String COMPUTER_ALT_IMAGE = "COMPUTER";
   
    public final String WINNER_LABEL = "WINNER";
    
}
            
            