import java.util.ArrayList;

public class Property {

    private String propertyName;
    private int houseCount;
    private int propertyPrice;
    private int houseCost;
    private int propertyRent;
    private Player owner;
    private int index;
    private boolean isSpecial;

    public Property(String name,int price,int houseCost,int rent,boolean special,int index){
        this.index=index;
        this.propertyName=name;
        this.propertyPrice=price;
        this.houseCount=0;
        this.houseCost=houseCost;
        this.propertyRent=rent;
        this.isSpecial=special;
        this.owner=null;
    }

    public String propertyFirstLine(){
        StringBuffer str1=new StringBuffer();
        
        String ownerName = this.owner!=null ? this.owner.getName() : ".";
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
}
