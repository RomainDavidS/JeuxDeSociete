package enedis.romaindavid.com.game.rechercheplusmoins;

import static enedis.romaindavid.com.algorithme.Plugins.generateRandomString;
import static enedis.romaindavid.com.param.Parameter.getModeDebug;


public class PlusMoinsChallenger extends RecherchePlusMoins {

    @Override
    public void runGame()  {
        initGame();
        if ( getModeDebug().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  secretNumberPC + ")" );
        modePlayerGame( "player" );
    }

    @Override
    protected void initGame(){
        System.out.println("*** Jeu du Recherche +/- en mode Challenger. ***");
        secretNumberPC = generateRandomString() ;
        modeGame = "challenger";


    }

}
