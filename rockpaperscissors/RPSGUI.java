
package rockpaperscissors;

import java.awt.*; 
import javax.swing.*; 
import javax.swing.border.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RPSGUI extends JFrame implements RPSGameConstants {
    
    // User panels
    private JPanel parentPanel, userParentPanel, userPanel,userPlayArea; 
    
    // Computer panels
    private JPanel computerParentPanel,computerPanel, compPlayArea; 
    
    // button panel which will be placed in the user panel
    private JPanel buttonPanel; 
    private JButton rockButton, paperButton, scissorsButton;
    
    // status panel to hold game stats. statusPanel2 will be hidden 
    // unless user enables betting
    private JPanel statusParentPanel, statusPanelStats, statusPanelBetting;

    // computer panel components 
    private JLabel computerProfileImage, computerMove, computerName, 
            computerWinStatus; 
    
    // user panel components
    private JLabel userProfileImage, userMove, userWinStatus, userName; 

    // playt area components
    private JLabel winLabel, lossLabel; 
    
    // statusPanel components 
    private JLabel tieLabel, balanceLabel, betLabel; 
    private JTextField wins, losses, ties, balance, bet;
    
    private Container myCP; 
    
    private RPSGame rps;
    
    // If betting is enabled these attributes will be set
    boolean bettingEnabled;
    double betAmount; 
    
    public RPSGUI(boolean bettingEnabled, double betAmount){
        
        super("Rock Paper Scissors"); 
        
        setSize(500, 600);
        setResizable(false); // don't want user to resize the game window
      
        this.bettingEnabled = bettingEnabled;
        this.betAmount = betAmount;
        
        rps = new RPSGame(bettingEnabled, betAmount);

        myCP = getContentPane();
 
        // Set up the Parent Panel
        parentPanel = new JPanel(); 
        parentPanel.setBackground(Color.LIGHT_GRAY);
        parentPanel.setBorder(new EmptyBorder(20,20,20,20));
        parentPanel.setLayout(new BoxLayout(parentPanel,BoxLayout.Y_AXIS));
       
        // Set up the Computer Panel and it's components
        computerParentPanel = new JPanel();
        computerParentPanel.setBorder(BorderFactory.createEtchedBorder());
       
        computerPanel = new JPanel(); 
        computerPanel.setLayout(new FlowLayout()); 
        computerPanel.setBorder(BorderFactory.createEtchedBorder());
        
        computerProfileImage = new JLabel();
        computerProfileImage.setPreferredSize(new Dimension(50,50));
       
        // set up the computer play area panel
        compPlayArea = new JPanel(); 
        // use setPreferredSize if theres layout manger being used in the parent
        compPlayArea.setPreferredSize(new Dimension(
            RPSGameConstants.PLAY_AREA_WIDTH,RPSGameConstants.PLAY_AREA_HEIGHT));
        compPlayArea.setBackground(Color.WHITE);
        compPlayArea.setBorder(BorderFactory.createEtchedBorder());
        
        computerName = new JLabel("COMPUTER"); 
        
        computerWinStatus = new JLabel();
        computerWinStatus.setForeground(RPSGameConstants.RPS_GREEN);
        
        computerMove = new JLabel();
        
        // Set up the user Panel and it's components
        userName = new JLabel("THE CHALLENGER");
        userWinStatus = new JLabel();
        
        // feed this an RGB value so we can have matching colors. 
        userWinStatus.setForeground(RPSGameConstants.RPS_GREEN);
        
        userParentPanel = new JPanel();
        userParentPanel.setBorder(BorderFactory.createEtchedBorder());
        userParentPanel.setPreferredSize(new Dimension(200,200));
        
        userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout());
        userPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // set up the user play area panel 
        userPlayArea = new JPanel(); 
        userPlayArea.setPreferredSize(new Dimension(RPSGameConstants.PLAY_AREA_WIDTH, 
            RPSGameConstants.PLAY_AREA_HEIGHT));
        
        userPlayArea.setBackground(Color.WHITE); 
        userPlayArea.setBorder(BorderFactory.createEtchedBorder());
        
        userProfileImage = new JLabel();
        userProfileImage.setPreferredSize(new Dimension(50,50));

        userMove = new JLabel();
      
        //button panel for user controls
        buttonPanel = new JPanel(); 
        buttonPanel.setLayout(new FlowLayout()); 
        buttonPanel.setBorder(BorderFactory.createEtchedBorder());
        
        rockButton = new JButton();
        rockButton.addActionListener(new ButtonClick());
        paperButton = new JButton(); 
        paperButton.addActionListener(new ButtonClick());
        scissorsButton = new JButton();
        scissorsButton.addActionListener(new ButtonClick());
        
        // add images to buttons. 
        try{  
            setSizedImage(rockButton,"rock.png", BUTTON_WIDTH, 
                    BUTTON_HEIGHT);
            setSizedImage(paperButton, "paper.png", BUTTON_WIDTH, 
                    BUTTON_HEIGHT); 
            setSizedImage(scissorsButton, "scissors.png", BUTTON_WIDTH, 
                    BUTTON_HEIGHT);
            setSizedImage(computerProfileImage, "computer.png", BUTTON_WIDTH, 
                    BUTTON_HEIGHT);
            setSizedImage(userProfileImage, "person.png", BUTTON_WIDTH, 
                    BUTTON_HEIGHT);
	}catch (IOException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getClass().getName());
	}catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            System.out.println("Button image resource failed to load.");
        }
        
        // set up the Status Parent Panel
        statusParentPanel = new JPanel(); 
        statusParentPanel.setBackground(Color.LIGHT_GRAY);
        statusParentPanel.setBorder(new EmptyBorder(10,10,10,10));
        statusParentPanel.setBackground(Color.BLACK);
        
        statusPanelStats = new JPanel();
        statusPanelStats.setBackground(Color.BLACK);
        statusPanelStats.setLayout(new FlowLayout());
   
        winLabel = new JLabel("Wins:"); 
        winLabel.setForeground(Color.WHITE);
        wins = new JTextField(5); 
        // set initial value
        wins.setText(Integer.toString(rps.getUserWins()));
        wins.setEditable(false);
        
        lossLabel = new JLabel("Loses:"); 
        lossLabel.setForeground(Color.WHITE); 
        
        losses = new JTextField(5);
        //set initial loses
        losses.setText(Integer.toString(rps.getComputerWins()));
        losses.setEditable(false);
        
        tieLabel = new JLabel("Ties:"); 
        tieLabel.setForeground(Color.WHITE);
        ties = new JTextField(5);
        // set initial value 
        ties.setText(Integer.toString(rps.getTies()));
        ties.setEditable(false);
     
        statusPanelStats.add(winLabel);
        statusPanelStats.add(wins); 
        statusPanelStats.add(lossLabel); 
        statusPanelStats.add(losses);
        statusPanelStats.add(tieLabel); 
        statusPanelStats.add(ties);
        
        // statusPanel2 will only be displayed if betting is enabled 
        statusPanelBetting = new JPanel();
        statusPanelBetting.setBackground(Color.BLACK);
        statusPanelBetting.setLayout(new FlowLayout());
        
        betLabel = new JLabel("Bet:"); 
        betLabel.setForeground(Color.WHITE);
        
        bet = new JTextField(5); 
        bet.setEditable(false);
        
        balanceLabel = new JLabel("Balance:"); 
        balanceLabel.setForeground(Color.WHITE);
        
        balance = new JTextField(5); 
        balance.setEditable(false);
        
        statusPanelBetting.add(betLabel); 
        statusPanelBetting.add(bet);
        statusPanelBetting.add(balanceLabel); 
        statusPanelBetting.add(balance);
        
        statusParentPanel.add(statusPanelStats);
        
        // if betting is enabled add the statusPanel2
        if(bettingEnabled){
            bet.setText(String.format("$%.2f", rps.getBetAmount()));
            balance.setText(String.format("$%.2f", rps.getBalance()));
            statusParentPanel.add(statusPanelBetting);
        }

        compPlayArea.add(computerMove, BorderLayout.NORTH);
        compPlayArea.add(computerWinStatus, BorderLayout.SOUTH);
        computerPanel.add(computerProfileImage);
        computerPanel.add(compPlayArea);
        computerParentPanel.add(computerName, BorderLayout.NORTH);
        computerParentPanel.add(computerPanel, BorderLayout.CENTER);
        parentPanel.add(computerParentPanel);
        
        
        // build the user panel
        userPlayArea.add(userMove);
        userPlayArea.add(userWinStatus);
        
        // Build the control panel
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        
        userPanel.add(userProfileImage);
        userPanel.add(userPlayArea);
       
        userParentPanel.add(userName, BorderLayout.NORTH);
        userParentPanel.add(userPanel, BorderLayout.CENTER);
        userParentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        parentPanel.add(userParentPanel);
        parentPanel.add(Box.createRigidArea (new Dimension (0, 20)));
        parentPanel.add(statusParentPanel);
    
        myCP.add(parentPanel);
        
        setVisible(true);
        
    }
   
    private void setSizedImage(JButton comp, String image, 
            int imageWidth, int imageHeight ) throws IOException{
        
        // helper method to set button images. The code was getting very 
        // redundant
        
        Image compImage = ImageIO.read(getClass().getResource(image));
       
        Image scaledImage = compImage.getScaledInstance(
            imageWidth, imageHeight, Image.SCALE_SMOOTH);
                     
        comp.setIcon(new ImageIcon(scaledImage));
       
    }
    
    private void setSizedImage(JLabel comp, String image, 
            int imageWidth, int imageHeight ) throws IOException{
        
        // helper method to set button images. The code was getting very 
        // redundant
        
        Image compImage = ImageIO.read(getClass().getResource(image));
            
        Image scaledImage = compImage.getScaledInstance(
            imageWidth, imageHeight, Image.SCALE_SMOOTH);
                    
        comp.setIcon(new ImageIcon(scaledImage));
           
    }
    
    private class ButtonClick implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            
            userWinStatus.setText(""); 
            computerWinStatus.setText("");
            
            Moves computerPlay = rps.generateComputerPlay();
            
            if(event.getSource() == rockButton){
                
                rps.findWinner(Moves.ROCK, computerPlay);
                
                 // move rock to the play area
                if(rps.getUserWinStatus() && !rps.getComputerWinStatus()){
                    changeMove(userMove, "rockGreen.png", ROCK_ALT_IMAGE);
                    changeMove(userProfileImage, "personGreen.png", USER_ALT_IMAGE);
                    userWinStatus.setText(WINNER_LABEL);
                    wins.setText(Integer.toString(rps.getUserWins())); 
                    balance.setText(String.format("$%.2f", rps.getBalance()));
                }else if(!rps.getUserWinStatus() && rps.getComputerWinStatus()){
                    changeMove(userMove, "rock.png", ROCK_ALT_IMAGE);
                    changeMove(userProfileImage, "person.png", USER_ALT_IMAGE);
                    losses.setText(Integer.toString(rps.getComputerWins()));
                    balance.setText(String.format("$%.2f", rps.getBalance()));
                }else{
                    // we have a tie
                    ties.setText(Integer.toString(rps.getTies()));
                    balance.setText(String.format("$%.2f", rps.getBalance()));
                    changeMove(userMove, "rock.png", ROCK_ALT_IMAGE);
                    changeMove(userProfileImage, "person.png", USER_ALT_IMAGE);
                }
                
                updateComputerMove(computerMove, computerPlay);
              
                repaint();
                
            }else if(event.getSource() == paperButton){
                
                rps.findWinner(Moves.PAPER, computerPlay);
                
                if(rps.getUserWinStatus() && !rps.getComputerWinStatus()){
                    changeMove(userMove, "paperGreen.png", PAPER_ALT_IMAGE);
                    changeMove(userProfileImage, "personGreen.png", USER_ALT_IMAGE);
                    userWinStatus.setText(WINNER_LABEL);
                    wins.setText(Integer.toString(rps.getUserWins()));
                    balance.setText(String.format("$%.2f", rps.getBalance()));
                }else if(!rps.getUserWinStatus() && rps.getComputerWinStatus()){
                    changeMove(userMove, "paper.png", PAPER_ALT_IMAGE);
                    changeMove(userProfileImage, "person.png", USER_ALT_IMAGE);
                    losses.setText(Integer.toString(rps.getComputerWins()));
                    balance.setText(String.format("$%.2f", rps.getBalance()));
                }else{
                    ties.setText(Integer.toString(rps.getTies()));
                    balance.setText(String.format("$%.2f", rps.getBalance()));
                    changeMove(userMove, "paper.png", PAPER_ALT_IMAGE);
                    changeMove(userProfileImage, "person.png", USER_ALT_IMAGE);
                }
                
                updateComputerMove(computerMove, computerPlay);
                
                repaint();
                
            }else if(event.getSource() == scissorsButton){
                
                rps.findWinner(Moves.SCISSORS, computerPlay);

                if(rps.getUserWinStatus() && !rps.getComputerWinStatus()){
                    changeMove(userMove, "scissorsGreen.png", SCISSORS_ALT_IMAGE);
                    changeMove(userProfileImage, "personGreen.png", USER_ALT_IMAGE);
                    userWinStatus.setText(WINNER_LABEL);
                    wins.setText(Integer.toString(rps.getUserWins()));
                    balance.setText(String.format("$%.2f", rps.getBalance()));
                }else if(!rps.getUserWinStatus() && rps.getComputerWinStatus()){
                    changeMove(userMove, "scissors.png", SCISSORS_ALT_IMAGE);
                    changeMove(userProfileImage, "person.png", USER_ALT_IMAGE);
                    losses.setText(Integer.toString(rps.getComputerWins()));
                    balance.setText(String.format("$%.2f", rps.getBalance()));
                }else{
                    ties.setText(Integer.toString(rps.getTies()));
                    changeMove(userMove, "scissors.png", SCISSORS_ALT_IMAGE); 
                    changeMove(userProfileImage, "person.png", USER_ALT_IMAGE);
                    balance.setText(String.format("$%.2f", rps.getBalance()));
                }
                
                updateComputerMove(computerMove, computerPlay);
         
                repaint();
            }
        }   
    } // End ButtonClick class
    
    private void changeMove(JLabel comp, String image, String altImageText){
        
        // this code was being repeated a lot in the button click 
        // handler so I gave it its own method
        
        try{
            setSizedImage(comp, image, BUTTON_WIDTH, 
                BUTTON_HEIGHT);
        }catch(IOException ex){
            // place text on label if no image has been loaded
            comp.setText(altImageText);
            System.out.println(ex.getMessage());
        }catch (IllegalArgumentException ex){
            comp.setText(altImageText);
            System.out.println(ex.getMessage());
        }
    }
    
    private void updateComputerMove(JLabel computer, Moves computerMove){
        
        if(computerMove == Moves.ROCK ){
            if(rps.getComputerWinStatus()){
                changeMove(computer, "rockGreen.png", ROCK_ALT_IMAGE);
                changeMove(computerProfileImage, "computerGreen.png", COMPUTER_ALT_IMAGE);
                computerWinStatus.setText(WINNER_LABEL);
            }else{
                changeMove(computer, "rock.png", ROCK_ALT_IMAGE); 
                changeMove(computerProfileImage, "computer.png", COMPUTER_ALT_IMAGE);
            }
        }else if(computerMove == Moves.PAPER){
            if(rps.getComputerWinStatus() && !rps.getUserWinStatus()){
                changeMove(computer, "paperGreen.png", PAPER_ALT_IMAGE);
                changeMove(computerProfileImage, "computerGreen.png", COMPUTER_ALT_IMAGE);
                computerWinStatus.setText(WINNER_LABEL);
            }else{
                changeMove(computer, "paper.png", PAPER_ALT_IMAGE);
                changeMove(computerProfileImage, "computer.png", COMPUTER_ALT_IMAGE);
            }
        }else if(computerMove == Moves.SCISSORS){
            if(rps.getComputerWinStatus()){
                changeMove(computer, "scissorsGreen.png", SCISSORS_ALT_IMAGE);
                changeMove(computerProfileImage, "computerGreen.png", COMPUTER_ALT_IMAGE);
                computerWinStatus.setText(WINNER_LABEL);
            }else{
                changeMove(computer, "scissors.png", SCISSORS_ALT_IMAGE);
                changeMove(computerProfileImage, "computer.png", COMPUTER_ALT_IMAGE);
            }
        }  
    }
  
    public static void main(String[] args){

        boolean validBet = false;
        
        // determine if user wants to enable betting
        int enableBetting = JOptionPane.showConfirmDialog(null, 
                "Would you like to enable betting?", "RPS Game Settings", 
                JOptionPane.YES_NO_OPTION);
     
        
        double betValue = 0.00;
        
        if(enableBetting < 1){
            
            // find out how much the user wants to bet
            // make sure input is valid
            while(!validBet){
                
                String betAmount = JOptionPane.showInputDialog(null, 
                        "How much per match would you like bet?");
                
                try{
                    betValue = Double.parseDouble(betAmount);
                    validBet = true;
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "You must enter a valid "
                            + "monetary value.");
                }
            }
            
            RPSGUI rpsApp = new RPSGUI(true, betValue);
            rpsApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }else{
            RPSGUI rpsApp = new RPSGUI(false, betValue);
            rpsApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
   
    }
      
}
