package enedis.romaindavid.com.game.rechercheplusmoins;

import static enedis.romaindavid.com.algorithme.Plugins.generateRandomInRange;
import static enedis.romaindavid.com.algorithme.Plugins.generateRandomString;
import static enedis.romaindavid.com.param.Parameter.getModeDebug;


public class PlusMoinsDual extends RecherchePlusMoins {

    @Override
    public void runGame()  {
        initGame();

        if ( getModeDebug().equals( "dev" ) ){
            System.out.println("(Combinaison secrète PC : "+  secretNumberPC + ")" );
            System.out.println("(Combinaison secrète Joueur : "+  secretNumberPlayer + ")" );
        }

        if( generateRandomInRange(0, 1) == 0 ) {
            System.out.println("C'est l'ordinateur qui joue en premier");
            modePlayerGame( "pc");
        }else {
            System.out.println("C'est le joueur qui joue en premier");
            modePlayerGame( "player" );
        }
    }

    @Override
    protected void initGame(){
        System.out.println("*** Jeu du Recherche +/- en mode Duel. ***");

        secretNumberPC = generateRandomString() ;
        gameCombinaison.setCombinaisonSecret( secretNumberPC );
        initMapPossible();
        initSecretNumberPlayer();
        combinaisonNumberPC = generateRandomString();
        modeGame = "dual";

    }
}
