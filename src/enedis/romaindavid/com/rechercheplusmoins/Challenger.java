package enedis.romaindavid.com.rechercheplusmoins;

import java.util.InputMismatchException;

import static enedis.romaindavid.com.algorithme.Plugins.*;
import static enedis.romaindavid.com.param.Parameter.*;


public class Challenger extends Game {


    public void runGame()  {

        secretNumberPC = generateRandomString() ;
        modeGame = "challenger";

        if ( getModeGame().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  secretNumberPC + ")" );


        modePlayerGame( "player" );
    }





}
