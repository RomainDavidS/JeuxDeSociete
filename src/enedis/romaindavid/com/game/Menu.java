package enedis.romaindavid.com.game;

import java.util.InputMismatchException;
import java.util.Scanner;

import static enedis.romaindavid.com.algorithme.Plugin.clearScreen;
import static enedis.romaindavid.com.param.Title.*;

public class Menu {

    private Scanner sc = new Scanner( System.in );



    private int choiceGame;
    private int choiceMode;

    ModeGame modeGame = new ModeGame();

    public void menuGame(){
        clearScreen();
        try {
            postTitleMainMenu();
            choiceGame = sc.nextInt();

            if( !(choiceGame == 1 || choiceGame == 2) ){
                postTitleControllerGameMenu();
                menuGame();
            }else
                menuMode();

        }catch ( InputMismatchException e ){
            postTitleControllerFormat();
            sc.nextLine();
            menuGame();
        }
    }

    public void menuMode(){
        clearScreen();
        try {
            if( choiceGame == 1)
                postTitleModeMenuRechercherPlusMoins();
            else
                postTitleModeMenuMastermind();

            choiceMode = sc.nextInt();

            if ( choiceMode < 1 || choiceMode > 3 ){
                postTitleControllerModeMenu();
                menuMode();
            }else
                runGame();

        }catch ( InputMismatchException e){
            postTitleControllerFormat();
            sc.nextLine();
            menuMode();
        }
    }

    public void setChoiceGame(int choiceGame) {
        this.choiceGame = choiceGame;
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
            return new RecherchePlusMoins( );
        else
            return new Mastermind( );
    }

    public void endOfPartyMenu(){
        System.out.println("Voulez-vous recommencer une partie O/N ?");
        String choice = sc.nextLine().toUpperCase();

        if ( choice.equals("O") || choice.equals("N" ))
            if (choice.equals("O") )
                menuMode();
            else
                menuGame();
         else {
            System.out.println("Je n'ai pas compris votre choix.");
            endOfPartyMenu();
        }

    }

}
