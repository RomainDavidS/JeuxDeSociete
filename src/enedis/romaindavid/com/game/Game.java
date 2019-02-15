package enedis.romaindavid.com.game;


import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.*;
import static enedis.romaindavid.com.param.Title.*;


abstract class Game {

    private static String secretNumberPC;
    private static String secretNumberPlayer;
    protected static String gameName;
    private static int trial;
    private static String combinaisonNumberPlayer;
    protected static String combinaisonNumberPC;
    protected static String result;
    private static CombinaisonResult gameCombinaison;

    public static String getSecretNumberPC() {
        return secretNumberPC;
    }

    public static String getSecretNumberPlayer() {
        return secretNumberPlayer;
    }

    public static String getGameName() {
        return gameName;
    }

    public static int getTrial() {
        return trial;
    }

    public static String getCombinaisonNumberPlayer() {
        return combinaisonNumberPlayer;
    }

    public static String getCombinaisonNumberPC() {
        return combinaisonNumberPC;
    }

    public static String getResult() {
        return result;
    }

    public static CombinaisonResult getGameCombinaison() {
        return gameCombinaison;
    }


    protected String gameMode ; // Challenger || Défenseur || Duel

    protected Scanner sc = new Scanner( System.in );

    protected int trialPlayer = 1 ;

    protected int trialPC = 1 ;

    protected Map<Integer,String> mapPossible = new HashMap<Integer,String>();

    public void challenger(){

        gameMode = "Challenger";

        postTitleGameChallenger ();

       secretNumberPC = generateRandomString()  ; // à revoir

        isPostSecretNumberPc() ;

        choiceProposal();

    }

    public void defender(){
        gameMode = "Défenseur";

        postTitleGameDefender();

        initSecretNumberPlayer();

        isPostSecretNumberPlayer();
        combinaisonNumberPC = generateRandomString() ; // à revoir
        choiceProposal();
    }

    public void dual(){
        gameMode = "Duel";

        postTitleGameDual();
        initSecretNumberPlayer();
        secretNumberPC = generateRandomString()  ; //

        isPostSecretNumberPc() ;
        isPostSecretNumberPlayer();

        combinaisonNumberPC = generateRandomString() ; // à revoir

        if( generateRandomInRange(0, 1) == 0 ) {
            System.out.println("C'est l'ordinateur qui joue en premier");
            choiceProposal("pc");
        }else {
            System.out.println("C'est le joueur qui joue en premier");
            choiceProposal( "player" );
        }


    }
    abstract String generateRandomString();

    abstract void generateCombinaisonPC();

    abstract String combinaisonResult( CombinaisonResult combinaison);

    abstract boolean isCombinaisonTrouve(String postResult );

    private boolean isModeGameDev(){
        return  ( getModeDebug().equals( "dev" ) ) ;

    }

    private boolean isChallengerMode(){
        return  isGameMode( "Challenger" );
    }

    private boolean isDefenderMode(){
        return isGameMode( "Défenseur" );
    }

    private boolean isDualMode(){
        return isGameMode("Duel" );
    }

    private boolean isGameMode(String mode ){
        return ( gameMode.equals( mode ) );
    }

    private void isPostSecretNumberPc(){
        if( isModeGameDev() )
            postSecretNumberPc();
    }

    private void isPostSecretNumberPlayer(){
        if( isModeGameDev() )
            postSecretNumberPlayer();
    }

    protected void choiceProposal(){
        choiceProposal( "" );
    }

    protected void choiceProposal( String playerType ){
        if( isChallengerMode())
            playerProposal();
        else if (isDefenderMode() )
            pcProposal();
        else if ( isDualMode() )
            if( playerType.equals( "player") )
                playerProposalToPc();
            else
                pcProposalToPlayer();
    }

    private void playerProposal(){
        playerProposal("player" );
    }

    private void playerProposalToPc(){
        playerProposal("pc" );
    }

    private void pcProposal(){
        pcProposal("pc");
    }

    private void pcProposalToPlayer(){
        pcProposal("player" );
    }

    //Méthodes Player
    private void playerProposal(String playerType){
       trial = trialPlayer ;
        if( !tryGoodSeizure() )
            playerProposal(  playerType );

        gameCombinaison = new CombinaisonResult( secretNumberPC, combinaisonNumberPlayer);
       result = combinaisonResult( gameCombinaison ) ;
        resultPlayer( playerType );
    }

    private boolean tryGoodSeizure(){
        try {
            return isGoodSeizure();
        } catch (InputMismatchException e){
            postTitleControllerFormat();
            sc.nextLine();
            return false;
        }
    }

    private boolean isGoodSeizure(){
            postTitleTrial();
            int seizure = sc.nextInt() ;
            return isSeizureGoodLength( seizure ) ;
    }

    protected boolean isSeizureGoodLength(int seizure){
       if ( toStr( seizure ).length() <= getNumberCasePossible() ){
          combinaisonNumberPlayer = formatNumber( seizure ) ;
           return true;
       }else {
           postTitleControllerSeizureLength();
           return false;
       }
    }

    protected void resultPlayer( String playerType ){
        postResultPlayer();
        if (!isCombinaisonTrouve( result ))
            if(!(trialPlayer == getNumberTrialPossible()) ) {
                trialPlayer++;
                choiceProposal( playerType );

            } else
                postLostResultPlayer();
        else
            postWinResultPlayer(  trialPlayer  );
    }

    // Méthodes PC
    private void pcProposal(String choice){
       trial = trialPC ;
       gameCombinaison = new CombinaisonResult( secretNumberPlayer, combinaisonNumberPC) ;
       result = combinaisonResult( gameCombinaison ) ;
        postResultPC();


        if(!isCombinaisonTrouve( result ) )
            if(!(trialPC == getNumberTrialPossible()) ) {
                trialPC++;
                generateCombinaisonPC();
                choiceProposal( choice );
            }else
               postLostResultPC();
        else
            postWinResultPC( trialPC );
    }

    private void initSecretNumberPlayer(){
        if(!trySecretNumber() )
            initSecretNumberPlayer();
    }

    private boolean trySecretNumber(){
        try{
            return isGoodSecretNumber();
        }catch (InputMismatchException e){
            postTitleControllerFormat();
            sc.nextLine();
            return false;
        }
    }

    private boolean isGoodSecretNumber(){
        postQuestionNumberSecret();
        int seizure = sc.nextInt();
        if( !isSeizureGoodLength( seizure ) ) {
            postTitleControllerSeizureLength();
            return false;
        }else {
           secretNumberPlayer = formatNumber(seizure);
            return true;
        }
    }








}
