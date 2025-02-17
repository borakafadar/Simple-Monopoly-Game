import java.util.ArrayList;

public class Property {

    private String propertyName;
    private int houseCount;
    private int propertyPrice;
    private int houseCost;
    private int propertyRent;
    private Player owner;
    private int index;
    private int type; //0=special, 1=A,B,C, 2=D,E,F, 3=G,H,I, 4=J,K,L

    public Property(String name,int price,int houseCost,int rent,int type,int index){
        this.index=index;
        this.propertyName=name;
        this.propertyPrice=price;
        this.houseCount=0;
        this.houseCost=houseCost;
        this.propertyRent=rent;
        this.type=type;
        this.owner=null;
    }

    public Player getOwner(){
        return this.owner;
    }
    public int getType(){
        return this.type;
    }
    public String getName(){
        return this.propertyName;
    }
    public int getPrice(){
        return this.propertyPrice;
    }
    public int getHousePrice(){
        return this.houseCost;
    }
    public void setOwner(Player pl){
        this.owner=pl;
        this.houseCount=0;
        setRent();    
    }
    public void addHouse(){
        this.houseCount++;
        setRent();
    }
    public int getRent(){
        return this.propertyRent;

    }

    private void setRent(){
        switch(type){
            case 1:
                if(houseCount==1){
                    this.propertyRent=2;
                }
                else if(houseCount==2){
                    this.propertyRent=3;
                }
                else if(houseCount==3){
                    this.propertyRent=4;
                }
                else if(houseCount==4){
                    this.propertyRent=6;
                }
                break;
            case 2:
                if(houseCount==1){
                    this.propertyRent=2;
                }
                else if(houseCount==2){
                    this.propertyRent=3;
                }
                else if(houseCount==3){
                    this.propertyRent=3;
                }
                else if(houseCount==4){
                    this.propertyRent=7;
                }
                break;
            case 3:
                if(houseCount==1){
                    this.propertyRent=3;
                }
                else if(houseCount==2){
                    this.propertyRent=4;
                }
                else if(houseCount==3){
                    this.propertyRent=6;
                }
                else if(houseCount==4){
                    this.propertyRent=7;
                }
                break;
            case 4:
                if(houseCount==1){
                    this.propertyRent=3;
                }
                else if(houseCount==2){
                    this.propertyRent=6;
                }
                else if(houseCount==3){
                    this.propertyRent=6;
                }
                else if(houseCount==4){
                    this.propertyRent=9;
                }
                break;
        }
    }



    public String propertyFirstLine(){
        StringBuffer str1=new StringBuffer();
        
        String ownerName = this.owner!=null ? Character.toString(this.owner.getShortName()) : ".";
        String houses = this.owner!=null ? Integer.toString(this.houseCount) : ".";
        str1.append(this.propertyName+"."+ownerName+houses+"|");
        return str1.toString();
    }

    public String propertySecondLine(ArrayList<Player> players){
        StringBuffer str2=new StringBuffer();
        for(Player pl : players){
            if(pl.getCurrentSpace()==this.index){
                str2.append(pl.getShortName());
            }
        }
        if(str2.length()!=4){
            while(str2.length()<4){
                str2.append(".");
            }
        }
        str2.append("|");
        return str2.toString();
    }

    public int getIndex(){
        return this.index;
    }

    public int getHouseCount(){
        return this.houseCount;
    }

    @Override
    public String toString(){
        return "The property "+ this.getName() +": Has "+this.houseCount+ " houses and its rent is "+getRent();
    }

}
