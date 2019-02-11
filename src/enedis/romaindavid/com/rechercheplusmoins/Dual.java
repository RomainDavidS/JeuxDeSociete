package enedis.romaindavid.com.rechercheplusmoins;



import static enedis.romaindavid.com.algorithme.Plugins.*;
import static enedis.romaindavid.com.param.Parameter.getModeGame;

public class Dual extends Game {


    public void runGame()  {
        initGame();

        if ( getModeGame().equals( "dev" ) ){
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
    private void initGame(){
        secretNumberPC = generateRandomString() ;
        gameCombinaison.setCombinaisonSecret( secretNumberPC );
        initMapPossible();
        initSecretNumberPlayer();
        combinaisonNumberPC = generateRandomString();
        modeGame = "dual";
    }

}
