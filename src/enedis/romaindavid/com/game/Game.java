package enedis.romaindavid.com.game;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public abstract class Game {

    protected String secretNumberPC;
    protected String secretNumberPlayer;

    protected String combinaisonNumberPC;
    protected String combinaisonNumberPlayer;

    protected String modeGame ;

    protected Scanner sc = new Scanner( System.in );
    protected CombinaisonResult gameCombinaison = new CombinaisonResult();
    protected int trialPlayer = 0 ;
    protected int trialPC = 0 ;

    protected Map<Integer,String> mapPossible = new HashMap<Integer,String>();
    protected String result ;



    protected void modePlayerGame( String player ){
        switch ( modeGame ){
            case "challenger":
                playerProposition( player );
                break;
            case "defender":
                pcProposition( player );
                break;
            case "dual":
                if( player.equals( "player") )
                    playerProposition(  "pc" );
                else
                    pcProposition(  "player" );

                break;
            default:
                break;
        }
    }

    protected abstract void playerProposition( String player );

    protected abstract void pcProposition( String player);

    protected abstract void challenger();
    protected abstract void initChallenger();

    protected abstract void defender();
    protected abstract void initDefender();

    protected abstract void dual();
    protected abstract void initDual();






}
