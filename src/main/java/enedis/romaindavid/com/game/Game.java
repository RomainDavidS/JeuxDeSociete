package enedis.romaindavid.com.game;

import enedis.romaindavid.com.algorithme.Plugin;
import enedis.romaindavid.com.param.Parameter;
import enedis.romaindavid.com.param.Title;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


/**
 * main class of the application:
 * - 1 main method for each mode:
 *      - challenger for the "Challenger" mode
 *      - Defender for the "Defender" mode
 *      - dual for the "Duel" mode
 * - 1 specific main method by type of player on entering the combination:
 *      - playerProposal when it is the player who must seize a proposal
 *      - pcProposal when it is the computer that must enter a proposal.
 */
public abstract class Game {

    protected Title title = Title.getInstance();
    protected Parameter params = Parameter.getInstance();

    protected static String secretNumberPC;

    protected static String combinaisonNumberPC;

    protected static String secretNumberPlayer;

    protected static String combinaisonNumberPlayer;

    protected static String playerResult;

    protected static String pcResult;

    protected static String gameName;

    protected int choiceGame ;

    protected String gameMode ;

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
        title.postTitleGameChallenger ();
        secretNumberPC = generateSecretRandomString() ;
        isPostSecretNumberPc() ;
        choiceProposal();
    }

    /**
     * Method to launch Defender mode
     */
    public void defender(){
        gameMode = "Défenseur";
        title.postTitleGameDefender();
        initSecretNumberPlayer();
        choiceProposal();
    }

    /**
     * Method to launch Dual mode
     */
    public void dual(){
        gameMode = "Duel";
        title.postTitleGameDual();
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
        if(Plugin.generateRandomInRange(0, 1) == 0 ) {
            title.postTitlePlayerFirst();
            choiceProposal("pc");
        }else {
            title.postTitlePcFirst();
            choiceProposal( "player" );
        }
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
        if( params.isModeDebug() )
            title.postSecretNumberPc();
    }

    /**
     * the secret number of the player is displayed if the developer mode has been activated
     */
    private void isPostSecretNumberPlayer(){
        if( params.isModeDebug() )
            title.postSecretNumberPlayer();
    }

    /**
     * Next if we are in Challenger or Defender mode we will guide a typical proposal on the combination choice. Either computer or player
     */
    private void choiceProposal(){
        choiceProposal( "" );
    }

    /**
     * Next if we are in Challenger,  Defender or Dual mode we will guide a typical proposal on the combination choice. Either computer or player
     * @param playerType typical proposition is "pc" or "player"
     */
    private void choiceProposal( String playerType ){
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
     * typical proposal "pc"
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

    /**
     * we test if the format is numeric
     * @return true if the format is numeric otherwise false
     */
    private boolean tryGoodSeizure(){
        try {
            return isGoodSeizure();
        } catch (InputMismatchException e){
            title.postTitleControllerFormat();
            sc.nextLine();
            return false;
        }
    }

    /**
     * displays the combination request and we check if is compliant
     * @return true if the length matches otherwise false
     */
    private boolean isGoodSeizure(){
            title.postTitleTrial();
            int seizure = sc.nextInt() ;
            title.postChoiceInfo( seizure );
            return isSeizureGoodLength( seizure ) ;
    }

    /**
     * displays the combination request and we check if the length is compliant
     * @param seizure combination to be tested
     * @return true if the length matches otherwise false
     */
    private boolean isSeizureGoodLength(int seizure){
       if ( Plugin.toStr( seizure ).length() <= params.getNumberCasePossible() ){
          combinaisonNumberPlayer = Plugin.formatNumber( seizure ) ;
           return true;
       }else {
           title.postTitleControllerSeizureLength();
           return false;
       }
    }

    /**
     * displays the result after entering the player's combination
     * @param playerType typical proposition is "pc" or "player"
     */
    private void resultPlayer( String playerType ){
        title.postResultPlayer();
        if (!isCombinaisonTrouve( playerResult ))
            if(!(trialPlayer == params.getNumberTrialPossible()) ) {
                trialPlayer++;
                choiceProposal( playerType );

            } else {
                title.postLostResultPlayer();
                endOfPartyMenu();
            }
        else {
            title.postWinResultPlayer(trialPlayer);
            endOfPartyMenu();
        }

    }

    /**
     * Next if we are in Defender or Dual mode we will guide a typical proposal on the combination choice. Either computer or player
     * @param choice typical proposition is "pc" or "player"
     */
    private void pcProposal(String choice){
        trial = trialPC ;
        pcResult = combinaisonResult( secretNumberPlayer ) ;

        title.postResultPC();

        if( !isCombinaisonTrouve( pcResult ) )
            if(!(trialPC == params.getNumberTrialPossible()) ) {
                trialPC++;
                choiceProposal( choice );
            }else {
                title.postLostResultPC( );
                endOfPartyMenu();
            }
        else {
            title.postWinResultPC( trialPC );
            endOfPartyMenu();
        }
    }

    /**
     * in the Defender mode we display the menu for entering the secret combination of the player
     */
    private void initSecretNumberPlayer(){
        if(!trySecretNumber() )
            initSecretNumberPlayer();
    }

    /**
     * we control that the format of the combination is consistent
     * @return true if the format is compliant otherwise false
     */
    private boolean trySecretNumber(){
        try{
            return isGoodSecretNumber();
        }catch (InputMismatchException e){
            title.postTitleControllerFormat();
            sc.nextLine();
            return false;
        }
    }

    /**
     * we display the entry notification of the secret combination of the player and we control that it is consistent
     * @return true if compliant otherwise false
     */
    private boolean isGoodSecretNumber(){
        title.postQuestionNumberSecret();
        int seizure = sc.nextInt();

        if ( params.isModeDebug() )
            title.postChoiceInfo( seizure );

        if( !isSeizureGoodLength( seizure ) ) {
            title.postTitleControllerSeizureLength();
            return false;
        }else {
           secretNumberPlayer = Plugin.formatNumber(seizure);
            return true;
        }
    }

    /**
     * displays the end-of-party menu
     */
    private void endOfPartyMenu(){
        Menu menu = new Menu();
        menu.setChoiceGame( choiceGame );
        menu.endOfPartyMenu();
    }







}
