import java.util.ArrayList;

public class Player {
    //private static int playerNo=0;

    private int coins;
    private String name;
    private char shortName;
    private ArrayList<Property> properties;
    private boolean isBot;
    private int currentSpace;


    public Player(String name, boolean isBot){
        this.name=name;
        this.shortName=Character.toLowerCase(name.charAt(0));
        this.properties=new ArrayList<>();
        this.isBot=isBot;
        this.coins=10;
        this.currentSpace=0;
    }





    public int getCoins(){
        return this.coins;
    }
    public String getName(){
        return this.name;
    }
    public char getShortName(){
        return this.shortName;
    }
    public int getCurrentSpace(){
        return this.currentSpace;
    }
    

}
