import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class DiceAndMovement {
    public Random rd;
    private ArrayList<Player> players;
    private ArrayList<Property> properties;
    private Scanner sc;

    public DiceAndMovement(ArrayList<Player> players,Scanner sc,ArrayList<Property> properties){
        this.rd=new Random();
        this.players=players;
        this.properties=properties;
        this.sc=sc;
    }

    public int sixSidedDice(Random rd){
        return rd.nextInt(1,7);
    }

    public void zerothSpace(Player pl){
        pl.setCoins(pl.getCoins()+3);
    }

    public void firstSpace(Player pl){
        int selection=sixSidedDice(rd);
        switch(selection){
            case 1:
            //todo
        }
    }

    public void secondSpace(Player pl){
        //todo
    }
    public void thirdSpace(Player pl){
        //todo
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
                pl.checkProperty(playerLocation,sc,properties);
                break;
        }
    }




    public void move(Player pl){
        int movement=sixSidedDice(rd);
        int toGoSpace=movement +pl.getCurrentSpace()+1;
        if(toGoSpace>16){
            pl.setCoins(pl.getCoins()+3);
            toGoSpace=toGoSpace%16;
        }
        pl.setSpace(pl.getCurrentSpace());

    }
    public void move(Player pl,int spaces){
        int toGoSpace=spaces +pl.getCurrentSpace()+1;
        if(toGoSpace>16){
            pl.setCoins(pl.getCoins()+3);
            toGoSpace=toGoSpace%16;
        }
        pl.setSpace(pl.getCurrentSpace());

    }
}
