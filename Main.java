import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game gm=new Game(sc);
        gm.initializeGame(sc);
        gm.printMap();
    }

    
}
