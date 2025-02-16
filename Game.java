import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private int turnNo;
    private ArrayList<Property> properties;
    private ArrayList<Player> players;
    private Random rd;

    public Game(){
        this.turnNo=0;
        this.properties=new ArrayList<>();
        this.players=new ArrayList<>();
        this.rd=new Random();
    }

    public void initializeGame(Scanner sc){
        createProperties();
        createPlayers(sc);
    }

    private void createProperties() {
        properties.add(new Property("0", 0, 0, 0, true,properties.size()));
        for(int i=65;i<=67;i++){
            properties.add(new Property(Character.toString(i),2,1,1,false,properties.size()));
        }
        properties.add(new Property("1", 0, 0, 0, true,properties.size()));
        for(int i=68;i<=70;i++){
            properties.add(new Property(Character.toString(i),4,1,2,false,properties.size()));
        }
        properties.add(new Property("2", 0, 0, 0, true,properties.size()));
        for(int i=71;i<=73;i++){
            properties.add(new Property(Character.toString(i),4,1,2,false,properties.size()));
        }
        properties.add(new Property("3", 0, 0, 0, true,properties.size()));
        for(int i=74;i<=76;i++){
            properties.add(new Property(Character.toString(i),4,1,2,false,properties.size()));
        }
    }
    private void createPlayers(Scanner sc){
        System.out.print("Enter your name: ");
        String name="bora";//sc.next();
        players.add(new Player(name, false));



        System.out.print("How many players do you want to play against: ");
        int playerCount=3;//sc.nextInt();
        ArrayList<String> botNames = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "David", "Emma", "Sophie", "Liam", "Noah", "Ethan", "Mia"));

        for(int i=1;i<=playerCount;i++){
            int index=rd.nextInt(0,botNames.size());
            players.add(new Player(botNames.get(index),true));
            botNames.remove(index);
        }
        for(int i=1;i<players.size();i++){
            botNames.add(players.get(i).getName());
        }
        
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
        
        System.out.println(line1+line2+line3);

    }
}
