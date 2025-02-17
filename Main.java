import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int gameCount=0;
        boolean continueGame=true;

        System.out.println("Welcome to Simple Monopoly!");
        System.out.println("Press enter to start!");
        sc.nextLine();
        Game firstGame=new Game(sc);
        firstGame.initializeGame(sc);
        firstGame.mainGame();
        do{
            Game gm=new Game(sc);
            gm.initializeGame(sc);
            gm.mainGame();
            gameCount++;
            
            gm=null; //to kill the game

            System.out.print("Do you want to play again? Enter n to quit.");
            
            String selection=sc.next();
            continueGame = selection.equals("n") ? false : true;
        }while(continueGame);

        String res=gameCount==1 ? "You have played 1 game." : "You have played "+gameCount+" games!";
        System.out.println(res);
        System.out.println("We hope that you had fun! Goodbye!");
    }

    
}
