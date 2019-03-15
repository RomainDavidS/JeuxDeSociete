package enedis.romaindavid.com.game;

import enedis.romaindavid.com.algorithme.Plugin;
import enedis.romaindavid.com.param.Title;

import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * management of the different menus
 */
public class Menu {

    private Scanner sc = new Scanner( System.in );

    private int choiceGame;
    private int choiceMode;

    protected Title title = Title.getInstance();

    ModeGame modeGame = new ModeGame();

    /**
     * choice of the game
     */
    public void menuGame(){

        try {
            title.postTitleMainMenu();

            title.postChoiceInfo( choiceGame = sc.nextInt() );

            if( !(choiceGame == 1 || choiceGame == 2) ){
                title.postTitleControllerGameMenu();
                menuGame();
            }else
                menuMode();

        }catch ( InputMismatchException e ){
            title.postTitleControllerFormat();
            sc.nextLine();
            menuGame();
        }
    }

    /**
     * choice of the mode game
     */
    public void menuMode(){

        try {
            if( choiceGame == 1)
                title.postTitleModeMenuRechercherPlusMoins();
            else
                title.postTitleModeMenuMastermind();

            title.postChoiceInfo( choiceMode = sc.nextInt() );

            if ( choiceMode < 1 || choiceMode > 3 ){
                title.postTitleControllerModeMenu();
                menuMode();
            }else
                runGame();

        }catch ( InputMismatchException e){
            title.postTitleControllerFormat();
            sc.nextLine();
            menuMode();
        }
    }



    public void setChoiceGame(int choiceGame) {
        this.choiceGame = choiceGame;
    }

    /**
     * launching the "Mastermind" or "Recherche +/-" game in the selected mode
     */
    private void runGame() {
        Game game = gameChozen();
        if( choiceMode == 1)
            modeGame.challenger( game );
        else if ( choiceMode == 2)
            modeGame.defender( game );
        else
            modeGame.dual( game );
    }

    /**
     * instantiates a class according to the selected game
     * @return new RecherchePlusMoins() if game "Recherche +/-" chozen or  new Mastermind() if game "Mastermind" chozen
     */
    private Game gameChozen(){
        if( choiceGame == 1 )
            return new RecherchePlusMoins();
        else
            return new Mastermind();
    }

    /**
     * end-of-the-game menu to find out if we are starting a game or another game or we are leaving the application
     */
    public void endOfPartyMenu(){
        title.postEndOfPartyMenu();
        String choice = sc.nextLine().toUpperCase();
        title.postChoiceInfo( choice );
        Plugin.clearScreen();
        if ( choice.equals("O") || choice.equals("N" ))
            if (choice.equals("O") )
                menuMode();
            else
                menuGame();
         else if (choice.equals( "Q" ) ){
             System.exit(1);
        }
         else {
            title.postErrorChoice();
            endOfPartyMenu();
        }

    }

}
