package enedis.romaindavid.com.game;


import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.getModeDebug;
import static enedis.romaindavid.com.param.Parameter.getNumberCasePossible;



abstract class Game {

    protected String gameName;
    protected String modeName;

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

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

    protected abstract void resultPlayerProposition( String player );

    protected abstract void pcProposition( String player);


    protected void challenger() {
        initChallenger();
        if ( getModeDebug().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  secretNumberPC + ")" );
        modePlayerGame( "player" );
    }

    protected void initChallenger() {
        System.out.println("*** Jeu du "+ gameName + " en mode Challenger. ***");
        secretNumberPC = generateRandomString() ;
        modeGame = "challenger";
    }

    protected void defender() {
        initDefender();
        if ( getModeDebug().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  secretNumberPlayer + ")" );

        modePlayerGame( "pc");
    }

    protected void initDefender() {
        System.out.println("*** Jeu du " + gameName + " en mode Défenseur. ***");
        initMapPossible();
        initSecretNumberPlayer();
        combinaisonNumberPC = generateRandomString();
        modeGame = "defender";
    }

    protected void dual() {
        initDual();

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

    protected void initDual() {
        System.out.println("*** Jeu du "+ gameName + " en mode Duel. ***");

        secretNumberPC = generateRandomString() ;
        gameCombinaison.setCombinaisonSecret( secretNumberPC );
        initMapPossible();
        initSecretNumberPlayer();
        combinaisonNumberPC = generateRandomString();
        modeGame = "dual";

    }

    protected void initSecretNumberPlayer(){
        try{
            System.out.println("Joueur : Veuillez saisir votre combinaison secrète");
            int seizure = sc.nextInt();

            if( !isSeizureGoodLength( seizure ) ){
                System.out.println("La combinaison secrète doit avoir une longueur de "+ getNumberCasePossible() + " chiffres maximum.");
                initSecretNumberPlayer();
            }

            secretNumberPlayer = formatNumber( seizure) ;

        }catch (InputMismatchException e){
            System.out.println( "Erreur de format. Veuillez saisir une valeur numérique" );
            sc.nextLine();
            initSecretNumberPlayer();
        }
    }

    protected boolean isSeizureGoodLength(int seizure){
        return ( toStr( seizure ).length() <= getNumberCasePossible() );
    }

    protected void initMapPossible(){
        for ( int i=0; i < getNumberCasePossible() ; i++ )
            mapPossible.put( i, "0123456789");
    }





}
