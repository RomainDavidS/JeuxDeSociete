package enedis.romaindavid.com;


import enedis.romaindavid.com.game.*;
import java.util.Scanner;

import static enedis.romaindavid.com.param.Parameter.*;

public class Main {

    private static Game game;


    public static void main(String[] args) {
        Menu menu = new Menu();

        ModeGame modeGame = new ModeGame();

        setModeDebug( args[0] );

        System.out.println("Bonjour et bienvenue dans vos jeux de société.");

        menu.menuGame();
        menu.menuMode();
        game = menu.choiceGame( menu.getChoiceGame(), menu.getChoiceMode() );

        modeGame.rumGame( game );
            
    }



}
