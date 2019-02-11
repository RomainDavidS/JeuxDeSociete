package enedis.romaindavid.com.game;

import enedis.romaindavid.com.game.mastermind.*;
import enedis.romaindavid.com.game.rechercheplusmoins.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner( System.in );
    private int choiceGame;
    private int choiceMode;

    public int getChoiceGame() {
        return choiceGame;
    }

    public int getChoiceMode() {
        return choiceMode;
    }

    public void menuGame(){
        try {
            System.out.println("A quel jeu souhaitez-vous jouer ? 1 -> Recherche +/- : 2 -> Mastermind.");
            choiceGame = sc.nextInt();

            if( !(choiceGame == 1 || choiceGame == 2) ){
                System.out.println("Votre choix doit égal à 1 ou 2.");
                menuGame();
            }


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
            }


        }catch ( InputMismatchException e){
            System.out.println( "Erreur de format. Veuillez saisir une valeur numérique" );
            sc.nextLine();
            menuMode();
        }
    }

    public  Game choiceGame(int game, int mode){

        switch (mode){
            case 1:
                if (game == 1)
                    return new PlusMoinsChallenger();
                else
                    return new MasterChallenger();
            case 2:
                if (game == 1)
                    return new PlusMoinsDefender();
                else
                    return new MasterDefender();
            case 3:
                if (game == 1)
                    return new PlusMoinsDual();
                else
                    return new MasterDual();
            default:
                return null;
        }
    }
}
