package enedis.romaindavid.com.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner( System.in );
    private int choiceGame;
    private int choiceMode;

    ModeGame modeGame = new ModeGame();

    public void menuGame(){
        try {
            System.out.println("A quel jeu souhaitez-vous jouer ? 1 -> Recherche +/- : 2 -> Mastermind.");
            choiceGame = sc.nextInt();

            if( !(choiceGame == 1 || choiceGame == 2) ){
                System.out.println("Votre choix doit égal à 1 ou 2.");
                menuGame();
            }else
                menuMode();


        }catch ( InputMismatchException e ){
            System.out.println( "Erreur de format. Veuillez saisir une valeur numérique" );
            sc.nextLine();
            menuGame();
        }
    }

    public void menuMode(){
        try {
            System.out.println("Choisir votre mode : 1 - Challenger : 2 - Défenseur : 3 - Duel");
            choiceMode = sc.nextInt();

            if ( choiceMode < 1 || choiceMode > 3 ){
                System.out.println("Votre choix doit être égal à 1, 2 ou 3.");
                menuMode();
            }else
                runGame();

        }catch ( InputMismatchException e){
            System.out.println( "Erreur de format. Veuillez saisir une valeur numérique" );
            sc.nextLine();
            menuMode();
        }
    }

    private void runGame() {
        Game game = gameChozen();
        if( choiceMode == 1)
            modeGame.challenger( game );
        else if ( choiceMode == 2)
            modeGame.defender( game );
        else
            modeGame.dual( game );
    }

    private Game gameChozen(){
        if( choiceGame == 1 )
            return new RecherchePlusMoins(  );
        else
            return new Mastermind( );
    }

}
