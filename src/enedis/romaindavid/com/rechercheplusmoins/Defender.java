package enedis.romaindavid.com.rechercheplusmoins;


import static enedis.romaindavid.com.algorithme.Plugins.*;
import static enedis.romaindavid.com.param.Parameter.*;

public class Defender extends Game {


    public void runGame()  {

        initMapPossible();
        initSecretNumberPlayer();

        if ( getModeGame().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  secretNumberPlayer + ")" );

        combinaisonNumberPC = generateRandomString();

        modePlayerGame( "defender","pc");
    }








}
