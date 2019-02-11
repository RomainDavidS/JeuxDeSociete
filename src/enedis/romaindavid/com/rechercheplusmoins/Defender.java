package enedis.romaindavid.com.rechercheplusmoins;


import static enedis.romaindavid.com.algorithme.Plugins.*;
import static enedis.romaindavid.com.param.Parameter.*;

public class Defender extends Game {


    public void runGame()  {

        initMapPossible();
        initSecretNumberPlayer();
        combinaisonNumberPC = generateRandomString();
        modeGame = "defender";
        if ( getModeGame().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  secretNumberPlayer + ")" );



        modePlayerGame( "pc");
    }








}
