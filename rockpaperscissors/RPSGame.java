package rockpaperscissors;

import java.util.Random;


public class RPSGame implements RPSGameConstants{
    
    private int computerWins; 
    private boolean bettingEnabled;
    double betAmount, balance;
    private int userWins; 
    private int userLoses;
    private int ties; 
    private boolean userWinStatus, computerWinStatus;
    private Random randnum;
    
    public RPSGame(boolean bettingEnabled, double betAmount){

        this.bettingEnabled = bettingEnabled;
        this.betAmount = betAmount;
        balance = betAmount; // beginning balance will be the initial bet amount
        
        computerWins = 0;
        userWins = 0;
        
        userWinStatus = false;
        computerWinStatus = false;
        ties = 0;
        randnum = new Random();

    }
    
    public int getComputerWins(){
        return computerWins;
    }
    
    public boolean getComputerWinStatus(){
        return computerWinStatus;
    }
    
    public int getUserWins(){
        return userWins;
    }
    

    public boolean getUserWinStatus(){
        return userWinStatus; 
    }
    
    public int getTies(){
        return ties;
    }
    
    public boolean getBettingEnabled(){
        return bettingEnabled;
    }
    
    public double getBetAmount(){
        return betAmount;
    }
    
    public double getBalance(){
        return balance;
    }
    
    public void setComputerWins(int computerWins){
        this.computerWins = computerWins;
    }
    
    public void setComputerWinStatus(boolean computerWinStatus){
        this.computerWinStatus = computerWinStatus; 
    }
    
    public void setUserWins(int userWins){
        this.userWins = userWins;
    }
    
    public void setUserWinStatus(boolean userWinStatus){
        this.userWinStatus = userWinStatus;
    }
    
    public void setTies(int ties){
        this.ties = ties;
    }
    
    public void setBetAmount(double betAmount){
        this.betAmount = betAmount;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    
    public Moves generateComputerPlay(){
        
        int randIndex = randnum.nextInt(Moves.values().length);
        Moves randomMove = Moves.values()[randIndex];
        
        return randomMove;
    }
    
    public void findWinner(Moves userMove, Moves computerMove){
        
        if(userMove == Moves.ROCK){
            if(computerMove == Moves.ROCK){
                setUserWinStatus(false);
                setComputerWinStatus(false);
                setTies(getTies() + 1);
            }else if(computerMove == Moves.PAPER){
                setComputerWins(getComputerWins() + 1);
                setComputerWinStatus(true);
                setUserWinStatus(false);
                setBalance(getBalance() - getBetAmount());
            }else if(computerMove == Moves.SCISSORS){
                setUserWins(getUserWins() + 1);
                setUserWinStatus(true); 
                setComputerWinStatus(false); 
                setBalance(getBalance() + getBetAmount());
            }
        }else if(userMove == Moves.PAPER){
            if(computerMove == Moves.ROCK){
                setUserWins(getUserWins() + 1); 
                setUserWinStatus(true); 
                setComputerWinStatus(false);
                setBalance(getBalance() + getBetAmount());
            }
            else if(computerMove == Moves.PAPER){
                setTies(getTies() + 1); 
                setUserWinStatus(false);
                setComputerWinStatus(false);
            }else if(computerMove == Moves.SCISSORS){
                setComputerWins(getComputerWins() + 1);
                setComputerWinStatus(true);
                setUserWinStatus(false);
                setBalance(getBalance() - getBetAmount());
            }
        }else if(userMove == Moves.SCISSORS){
            if(computerMove == Moves.ROCK){
                setComputerWins(getComputerWins() + 1); 
                setComputerWinStatus(true); 
                setUserWinStatus(false);
                setBalance(getBalance() - getBetAmount());
            }else if(computerMove == Moves.PAPER){
                setUserWins(getUserWins() + 1);
                setUserWinStatus(true);
                setComputerWinStatus(false);
                setBalance(getBalance() + getBetAmount());
            }else if(computerMove == Moves.SCISSORS){
                setTies(getTies() + 1);
                setComputerWinStatus(false);
                setUserWinStatus(false);
            } 
        }
    }  // end FindWinner method  
        
    @Override
    public String toString(){
        String gameDescription = String.format(
                "User wins: %d\nComputer wins:%d\nTies: %d\n", 
                        getUserWins(), getComputerWins(), getTies());
        
        if(userWinStatus){
            gameDescription += "Current winner: User"; 
        }else if(computerWinStatus){
            gameDescription += "Current winner: Computer";
        }else{
            gameDescription += "Match resulted in a tie.";
        }
        
        return gameDescription;
    }
} // end RPSGame class
