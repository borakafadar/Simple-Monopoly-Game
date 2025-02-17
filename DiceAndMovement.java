import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class DiceAndMovement {
    public Random rd;
    private ArrayList<Player> players;
    private ArrayList<Property> properties;
    private Game currentGame;

    public DiceAndMovement(ArrayList<Player> players,Scanner sc,ArrayList<Property> properties,Game game){
        this.rd=new Random();
        this.players=players;
        this.properties=properties;
        this.currentGame=game;
    }

    public int sixSidedDice(Random rd){
        return rd.nextInt(1,7);
    }

    public void zerothSpace(Player pl){
        System.out.println( pl.getName()+" has landed in the start! Here is 3 coins for them.");
        pl.setCoins(pl.getCoins()+3);
    }

    public void firstSpace(Player pl){
        System.out.println("Player "+pl.getName()+" has landed in Property 1! They are going to roll to trigger a special effect.");
        int selection=sixSidedDice(rd);
        System.out.println("Their roll is "+selection);
        
        switch(selection){
            case 1:
                System.out.println("Player "+pl.getName()+" lost 2 coins!");
                pl.setCoins(pl.getCoins()-2);
                break;
            case 2:
                System.out.println("Player "+pl.getName()+" lost 1 coin!");
                pl.setCoins(pl.getCoins()-1);
                break;
            case 3:
                System.out.println("Player "+pl.getName()+" moved 1 space!");
                move(pl,1);
                break;
            case 4:
                System.out.println("Player "+pl.getName()+" moved 2 spaces!");
                move(pl,2);
                break;
            case 5:
                System.out.println("Player "+pl.getName()+" moved 1 space and got 1 coin!");
                move(pl,1);
                pl.setCoins(pl.getCoins()+1);
                break;
            case 6:
                System.out.println("Player "+pl.getName()+" moved 2 spaces and got 2 coins!");
                move(pl,2);
                pl.setCoins(pl.getCoins()+2);
                break;
        }
    }

    public void secondSpace(Player pl){
        System.out.println("Player "+ pl.getName()+" has landed in Property 2! They are going to get a coin from every other player.");
        for(Player pla : players){
            if(pla==pl){
                continue;
            }
            else{
                pl.setCoins(pl.getCoins()+1);
                pla.setCoins(pla.getCoins()-1);
            }
        }
        System.out.println("Player "+pl.getName()+" got "+(players.size()-1)+" coins!");
    }

    public void thirdSpace(Player pl){
        System.out.println("Player "+ pl.getName()+" has landed in Property 3! They are going to skip a turn.");
        pl.skipTurn();
    }


    public void checkSpace(Player pl){
        int playerLocation=pl.getCurrentSpace();
        switch(playerLocation){
            case 0:
                zerothSpace(pl);
                break;
            case 4:
                firstSpace(pl);
                break;
            case 8:
                secondSpace(pl);
                break;
            case 12:
                thirdSpace(pl);
                break;
            case 1,2,3,5,6,7,9,10,11,13,14,15:
                pl.checkProperty(playerLocation,properties,rd);
                break;
        }
    }




    public void move(Player pl){
        if(pl.getSkipTurnNo()>currentGame.getTurnNo()){
            System.out.println("You still have to wait your turn "+pl.getName()+"!");
            return;
        }
        int movement=sixSidedDice(rd);
        int toGoSpace=movement +pl.getCurrentSpace()+1;
        if(toGoSpace>16){
            pl.setCoins(pl.getCoins()+3);
            toGoSpace=toGoSpace%16;
        }
        pl.setSpace(toGoSpace-1);
        System.out.println(pl.getName()+" moved for "+movement+" spaces.");
        checkSpace(pl);
    }

    public void move(Player pl,int spaces){
        int toGoSpace=spaces +pl.getCurrentSpace()+1;
        if(toGoSpace>16){
            pl.setCoins(pl.getCoins()+3);
            toGoSpace=toGoSpace%16;
        }
        pl.setSpace(toGoSpace);
        checkSpace(pl);

    }
}
