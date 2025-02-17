import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private int turnNo;
    private ArrayList<Property> properties;
    private ArrayList<Player> players;
    private Random rd;
    private Scanner sc;
    private DiceAndMovement dc;
    private final int LIMIT_POVERTY=1;

    public Game(Scanner sc){
        this.turnNo=0;
        this.properties=new ArrayList<>();
        this.players=new ArrayList<>();
        this.rd=new Random();
        this.sc=sc;        
        dc=new DiceAndMovement(players,sc,this.properties,this);
    }

    public void initializeGame(Scanner sc){
        createProperties();
        createPlayers(sc);
    }

    private void createProperties() {
        properties.add(new Property("0", 0, 0, 0, 0,properties.size()));
        for(int i=65;i<=67;i++){
            properties.add(new Property(Character.toString(i),2,1,1,1,properties.size()));
        }
        properties.add(new Property("1", 0, 0, 0, 0,properties.size()));
        for(int i=68;i<=70;i++){
            properties.add(new Property(Character.toString(i),4,1,2,2,properties.size()));
        }
        properties.add(new Property("2", 0, 0, 0, 0,properties.size()));
        for(int i=71;i<=73;i++){
            properties.add(new Property(Character.toString(i),4,1,2,3,properties.size()));
        }
        properties.add(new Property("3", 0, 0, 0, 0,properties.size()));
        for(int i=74;i<=76;i++){
            properties.add(new Property(Character.toString(i),4,1,2,4,properties.size()));
        }
    }
    private void createPlayers(Scanner sc){
        System.out.print("Enter your name: ");
        String name=sc.next();
        players.add(new Player(name, false,sc,this));



        System.out.print("How many players do you want to play against: ");
        int playerCount=sc.nextInt();
        ArrayList<String> botNames = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "David", "Emma", "Sophie", "Liam", "Noah", "Ethan", "Mia"));

        for(int i=1;i<=playerCount;i++){
            int index=rd.nextInt(0,botNames.size());
            players.add(new Player(botNames.get(index),true,sc,this));
            botNames.remove(index);
        }
        for(int i=1;i<players.size();i++){
            botNames.add(players.get(i).getName());
        }
        System.out.println();
        System.out.println("--------------");
    }

    public int getTurnNo(){
        return this.turnNo;
    }



    public void printMap(){
        System.out.println();
        
        String line1="|";
        for(int i=0;i<5;i++){
            line1+=properties.get(i).propertyFirstLine();
        }
        line1+="\n|";
        for(int i=0;i<5;i++){
            line1+=properties.get(i).propertySecondLine(players);
        }
        String line2="\n";
        for(int i=15;i>=13;i--){
            line2+="|"+properties.get(i).propertyFirstLine()+"              "+"|"+properties.get(20-i).propertyFirstLine();
            line2+="\n|"+properties.get(i).propertySecondLine(players)+"              "+"|"+properties.get(20-i).propertySecondLine(players)+"\n";
        }        

        String line3="|";
        for(int i=12;i>7;i--){
            line3+=properties.get(i).propertyFirstLine();
        }
        line3+="\n|";
        for(int i=12;i>7;i--){
            line3+=properties.get(i).propertySecondLine(players);
        }

        System.out.println(line1+line2+line3+"");
        

    }

    public void printPlayersAndStats(){
        System.out.println();
        for(int i=0;i<players.size();i++){
            Player pl=players.get(i);
            System.out.println((i+1)+". "+pl.getName()+": has "+pl.getPropertyCount()+" properties and "+pl.getCoins()+" coins.");
        }
    }

    public void checkPlayers(){
        for(int i=0;i<players.size();i++){
            Player pl=players.get(i);
            if(pl.getCoins()<0){
                pl.deletePlayer();
                System.out.println(pl.getName()+" has lost the game!");
                players.remove(pl);
                i--;
            }
        }
    }


    public void mainGame(){

        boolean isWon=false;
        while(turnNo<100){
            printMap();
            System.out.println("\nTurn Number: "+this.turnNo);
            printPlayersAndStats();
            
            
            for(Player pl : players){
                System.out.println("\n--------------\n");
                int currentHouseCount=pl.getTotalHouseNumber();
                dc.move(pl);
                if(properties.get(pl.getCurrentSpace()).getType()==0){
                    continue;
                }
                if(currentHouseCount==pl.getTotalHouseNumber()){
                    if(pl.getBot()==false){
                        System.out.print("Do you want to sell a property?(y/n) ");
                        String selection =sc.next();
                        if(selection.equals("y")){
                            pl.sellProperty();
                        }else{
                            System.out.println("Nothing happened. ");
                        }
                    }
                    else{
                        if(pl.getCoins()<=LIMIT_POVERTY&&pl.getPropertyCount()>0){
                            int selection=rd.nextInt(0,pl.getPropertyCount());
                            pl.sellProperty(selection);
                        }
                    }
                }
                
            }
            System.out.println("\n--------------");
            checkPlayers();
            if(players.size()==1){
                isWon=true;
                break;
            }
            turnNo++;
        }

        if(isWon){
            System.out.println(players.get(0).getName()+" has won, congratulations!");
        }
        if(turnNo>=100){
            System.out.println("Nobody won, the game ended in a tie.");
        }
        
    }
}
