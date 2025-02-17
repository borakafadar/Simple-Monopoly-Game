import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
    

    private int coins;
    private String name;
    private char shortName;
    private ArrayList<Property> properties;
    private boolean isBot;
    private int currentSpace;
    private Game currentGame;
    private int skipTurnNo;
    private Scanner sc;


    public Player(String name, boolean isBot, Scanner scanner,Game game){
        this.name=name;
        this.shortName=Character.toLowerCase(name.charAt(0));
        this.sc=scanner;
        this.currentGame=game;
        this.properties=new ArrayList<>();
        this.isBot=isBot;
        this.coins=10;
        this.currentSpace=0;
        this.skipTurnNo=-1;
    }




    public void checkProperty(int playerLocation, ArrayList<Property> properties,Random rd){
        Property pr=properties.get(playerLocation);

        if(!isBot){
            if(pr.getOwner()==null&&pr.getType()!=0){
                System.out.println("Property "+pr.getName()+" is unowned!");
                if(this.getCoins()>=pr.getPrice()){
                    System.out.print("You can buy this property! To buy, type 1: ");
                    String selection=sc.next();
                    if(selection.equals("1")){
                        this.buyProperty(pr);
                    }
                    else{
                        System.out.println("You did not buy the property.");
                    }
                }else{
                    System.out.println("You cannot buy this property.");
                }
            }else if(pr.getOwner()==this){
                System.out.println("You are the owner of property "+pr.getName());
                if(this.getCoins()>pr.getHousePrice()){
                    System.out.print("You can buy a house for this property! To buy type 1: ");
                    String selection=sc.next();
                    if(selection.equals("1")){
                        pr.addHouse();
                        this.setCoins(this.getCoins()-pr.getHousePrice());
                        System.out.println("You bought a house for the property "+pr.getName());
                    }
                    else{
                        System.out.println("You did not buy a house.");
                    }
                }
            }else if(pr.getOwner()!=null&&pr.getOwner()!=this){
                System.out.println("You have landed in "+pr.getOwner().getName()+"'s property. You need to pay "+pr.getRent()+" coins.");
                this.setCoins(this.getCoins()-pr.getRent());
            }
            System.out.println("You have "+getCoins()+" coins.");
        }else{
            if(pr.getOwner()==null&&pr.getType()!=0){
                
                if(this.getCoins()>=pr.getPrice()){
                    int randomNo=rd.nextInt(1,3);
                    if(randomNo==2){
                        this.buyProperty(pr);
                    }
    
                }else{
                    System.out.println("You cannot buy this property.");
                }
            }else if(pr.getOwner()==this){
                
                if(this.getCoins()>pr.getHousePrice()){
                    
                    int randomNo=rd.nextInt(1,3);
                    if(randomNo==2){
                        pr.addHouse();
                        this.setCoins(this.getCoins()-pr.getHousePrice());
                        System.out.println(this.getName()+" bought a house for the property "+pr.getName());
                    }
                }
            }else if(pr.getOwner()!=null&&pr.getOwner()!=this){
                System.out.println(getName()+" has landed in "+pr.getOwner().getName()+"'s property."+getName() +" needs to pay "+pr.getRent()+" coins.");
                this.setCoins(this.getCoins()-pr.getRent());
                pr.getOwner().setCoins(pr.getOwner().getCoins()+pr.getRent());
            }
            System.out.println(getName()+" has "+getCoins()+" coins.");
        }
        
    }

    public void buyProperty(Property pr){
        pr.setOwner(this);
        properties.add(pr);
        this.setCoins(this.getCoins()-pr.getPrice());
        System.out.println(this.getName()+" bought the property "+pr.getName()+"!");
    }

    
    public void sellProperty(){
        Property toDeleteProperty=listAndSelectProperty();
        if(toDeleteProperty==null){
            return;
        }
        this.setCoins(this.getCoins()+toDeleteProperty.getPrice());
        properties.remove(toDeleteProperty);
        toDeleteProperty.setOwner(null);
        System.out.println("You have sold the property "+toDeleteProperty.getName());

    }

    public void sellProperty(int index){
        Property toDeleteProperty=properties.get(index);
        if(toDeleteProperty==null){
            return;
        }
        this.setCoins(this.getCoins()+toDeleteProperty.getPrice());
        toDeleteProperty.setOwner(null);
        System.out.println(this.getName()+" has sold the property "+toDeleteProperty.getName());
    }

    public Property listAndSelectProperty(){
        listProperties();
        if(properties.size()==0){
            return null;
        }
        System.out.print("Select one of the properties: ");
        int selection=sc.nextInt()-1;
        if(selection<0 || selection>=properties.size()){
            System.out.println("This is not a valid selection!");
            return null;
        }
        System.out.println("Property "+properties.get(selection).getName()+" has been selected!");
        return properties.get(selection);
    }

    public void listProperties(){
        if(properties.size()==0){
            System.out.println("You do not have any properties!");
            return;
        }
        for(int i=0;i<properties.size();i++){
            System.out.println((i+1)+". "+properties.get(i).toString());
            
        }
    }

    public void deletePlayer(){
        for(Property pr: properties){
            pr.setOwner(null);
        }
    }

    public void addHouse(){
        
        Property pr=null;
        for(Property prop : properties){
            if(prop.getIndex()==this.currentSpace){
                pr=prop;
            }
        }
        if(pr==null){
            return;
        }
        if(this.getCoins()<pr.getHousePrice()){
            System.out.println("You do not have money to build a house!");
            return;
        }
        if(pr.getOwner()!=this){
            System.out.println("You can't build a house for your opponent!");
            return;
        }
        pr.addHouse();
        this.setCoins(this.getCoins()-pr.getHousePrice());
        System.out.println("A house has been added to the property "+pr.getName());
    }

    public int getTotalHouseNumber(){
        int sum=0;
        for(Property pr: properties){
            sum+=pr.getHouseCount();
        }
        return sum;
    } 




    public void skipTurn(){
        this.skipTurnNo=currentGame.getTurnNo()+2;
    }
    public int getSkipTurnNo(){
        return this.skipTurnNo;
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
    public void setCoins(int coin){
        this.coins=coin;
    }
    public void setSpace(int index){
        this.currentSpace=index;
    }
    public int getPropertyCount(){
        return this.properties.size();
    }

    public boolean getBot(){
        return this.isBot;
    }

}
