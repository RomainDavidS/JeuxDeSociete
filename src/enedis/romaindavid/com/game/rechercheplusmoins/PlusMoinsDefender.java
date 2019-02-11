package enedis.romaindavid.com.game.rechercheplusmoins;

import static enedis.romaindavid.com.algorithme.Plugins.generateRandomString;
import static enedis.romaindavid.com.param.Parameter.getModeDebug;


public class PlusMoinsDefender extends RecherchePlusMoins{

    @Override
    public void runGame()  {
        initGame();
        if ( getModeDebug().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  secretNumberPlayer + ")" );

        modePlayerGame( "pc");
    }

    @Override
    protected void initGame(){
        System.out.println("*** Jeu du Recherche +/- en mode Défenseur. ***");
        initMapPossible();
        initSecretNumberPlayer();
        combinaisonNumberPC = generateRandomString();
        modeGame = "defender";

    }
}
