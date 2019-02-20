package enedis.romaindavid.com.game;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.*;
import static enedis.romaindavid.com.param.Title.*;


abstract class Game {

    protected static String secretNumberPC;

    protected static String combinaisonNumberPC;

    protected static String secretNumberPlayer;

    protected static String combinaisonNumberPlayer;

    protected static String playerResult;

    protected static String pcResult;

    protected static String gameName;

    protected int choiceGame ;

    protected String gameMode ; // Challenger || Défenseur || Duel

    protected Scanner sc = new Scanner( System.in );

    protected static int trial;

    protected int trialPlayer = 1 ;

    protected int trialPC = 1 ;

    protected Map<Integer,String> mapPossible = new HashMap<Integer,String>();


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

    public static String getPlayerResult() {
        return playerResult;
    }

    public static String getPcResult() {
        return pcResult;
    }

    /**
     * Method to launch Challenger mode
     */
    public void challenger(){
        gameMode = "Challenger";
        postTitleGameChallenger ();
        secretNumberPC = generateSecretRandomString() ;
        isPostSecretNumberPc() ;
        choiceProposal();
    }

    /**
     * Method to launch Defender mode
     */
    public void defender(){
        gameMode = "Défenseur";
        postTitleGameDefender();
        initSecretNumberPlayer();
        choiceProposal();
    }

    /**
     * Method to launch Dual mode
     */
    public void dual(){
        gameMode = "Duel";
        postTitleGameDual();
        initSecretNumberPlayer();
        secretNumberPC = generateSecretRandomString();
        isPostSecretNumberPc() ;
        isPostSecretNumberPlayer();
        choiceFirstPlayer();
    }

    abstract String generateSecretRandomString();

    abstract String generateCombinaisonRandomString();

    abstract void generateCombinaisonPC();

    abstract String combinaisonResult( String secretNumber );

    abstract String combinaisonResult( String secretNumber,String combinaisonNumber);

    abstract boolean isCombinaisonTrouve(String result);

    /**
     * For dual mode this method of raffling which of the players will play first
     */
    private void choiceFirstPlayer(){
        if( generateRandomInRange(0, 1) == 0 ) {
            postTitlePlayerFirst();
            choiceProposal("pc");
        }else {
            postTitlePcFirst();
            choiceProposal( "player" );
        }
    }

    /**
     * We check if the developer mode is enabled
     * @return if we are in developer mode returns true otherwise false
     */
    private boolean isModeGameDev(){
        return  ( getModeDebug().equals( "dev" ) ) ;

    }

    /**
     * Check if the challenger mode was chosen
     * @return If the challenger mode was chosen returns true otherwise false
     */
    private boolean isChallengerMode(){
        return  isGameMode( "Challenger" );
    }

    /**
     * Check if the defender mode was chosen
     * @return If the defender mode was chosen returns true otherwise false
     */
    private boolean isDefenderMode(){
        return isGameMode( "Défenseur" );
    }

    /**
     * Check if the dual mode was chosen
     * @return If the dual mode was chosen returns true otherwise false
     */
    private boolean isDualMode(){
        return isGameMode("Duel" );
    }

    /**
     * Allows you to check a mode if a typical mode has been chosen
     * @param mode the game mode to check  "Challenger", "Défenseur" ou "Duel"
     * @return if the mode passed in parameter was chosen one returns true otherwise false
     */
    private boolean isGameMode(String mode ){
        return ( gameMode.equals( mode ) );
    }

    /**
     * the secret number of the computer is displayed if the developer mode has been activated
     */
    private void isPostSecretNumberPc(){
        if( isModeGameDev() )
            postSecretNumberPc();
    }

    /**
     * the secret number of the player is displayed if the developer mode has been activated
     */
    private void isPostSecretNumberPlayer(){
        if( isModeGameDev() )
            postSecretNumberPlayer();
    }

    /**
     * Next if we are in Challenger or Defender mode we will guide a typical proposal on the combination choice. Either computer or player
     */
    protected void choiceProposal(){
        choiceProposal( "" );
    }

    /**
     * Next if we are in Challenger,  Defender or Dual mode we will guide a typical proposal on the combination choice. Either computer or player
     * @param playerType typical proposition is "pc" or "player"
     */
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

    /**
     * typical proposal "player"
     */
    private void playerProposal(){
        playerProposal("player" );
    }

    /**
     * when we are in dual mode will allow to switch on a proposal type "pc" while we are currently on a proposal type "player"
     */
    private void playerProposalToPc(){
        playerProposal("pc" );
    }

    /**
     * typical proposal "player"
     */
    private void pcProposal(){
        pcProposal("pc");
    }

    /**
     * when we are in dual mode will allow to switch on a proposal type "player" while we are currently on a proposal type "pc"
     */
    private void pcProposalToPlayer(){
        pcProposal("player" );
    }

    /**
     * Next if we are in Challenger or Dual mode we will guide a typical proposal on the combination choice. Either computer or player
     * @param playerType typical proposition is "pc" or "player"
     */
    private void playerProposal(String playerType){
       trial = trialPlayer ;
        if( !tryGoodSeizure() )
            playerProposal(  playerType );

       playerResult = combinaisonResult( secretNumberPC, combinaisonNumberPlayer ) ;
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
       }else
           return false;

    }

    protected void resultPlayer( String playerType ){
        postResultPlayer();
        if (!isCombinaisonTrouve( playerResult ))
            if(!(trialPlayer == getNumberTrialPossible()) ) {
                trialPlayer++;
                choiceProposal( playerType );

            } else {
                postLostResultPlayer();
                endOfPartyMenu();
            }
        else {
            postWinResultPlayer(trialPlayer);
            endOfPartyMenu();
        }

    }

    // Méthodes PC
    private void pcProposal(String choice){
        trial = trialPC ;
        pcResult = combinaisonResult( secretNumberPlayer ) ;

        postResultPC();

        if( !isCombinaisonTrouve( pcResult ) )
            if(!(trialPC == getNumberTrialPossible()) ) {
                trialPC++;
                choiceProposal( choice );
            }else {
                postLostResultPC( );
                endOfPartyMenu();
            }
        else {
            postWinResultPC(trialPC);
            endOfPartyMenu();
        }
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

    private void endOfPartyMenu(){
        Menu menu = new Menu();
        menu.setChoiceGame( choiceGame );
        menu.endOfPartyMenu();
    }







}
