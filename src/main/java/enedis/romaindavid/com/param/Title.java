package enedis.romaindavid.com.param;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.concurrent.atomic.AtomicInteger;

import enedis.romaindavid.com.game.*;



/**
 * Set of the messages of the game
 */
public final class Title {

    private static Title singleton = new Title();

    public static Title getInstance(){
        return singleton;
    }

    private static class Holder {
        private static final AtomicInteger parameter = new AtomicInteger();
    }

    public static int getCounter(){
        return Holder.parameter.get();
    }

    public static int increment(){
        return Holder.parameter.incrementAndGet();
    }

    private static Logger logger = LogManager.getLogger();

    public static void postTitlePlayerFirst(){
        logConsoleInfo("C'est l'ordinateur qui joue en premier");
    }

    public static void postTitlePcFirst(){
        logConsoleInfo("C'est le joueur qui joue en premier");
    }

    public static void postSecretNumberPc(){
        postSecretNumber( "PC", Game.getSecretNumberPC() );
    }

    public static void postSecretNumberPlayer(){
        postSecretNumber("joueur", Game.getSecretNumberPlayer() );
    }

    private static void postSecretNumber(String player,String secretNumber){
        logConsoleDebug("(Combinaison secrète "+ player + " : "+  secretNumber + ")" );
    }

    public static void postTitleGameChallenger(){
        postTitleGame( Game.getGameName(),"Challenger" );
    }

    private static void postTitleGameChallenger (String name ){
        postTitleGame( name,"Challenger" );
    }

    public static void postTitleGameDefender(){
        postTitleGame( Game.getGameName(),"Défenseur" );
    }

    private static void postTitleGameDefender (String name ){
        postTitleGame( name,"Défenseur" );
    }

    public static void postTitleGameDual(){
        postTitleGame( Game.getGameName(),"Duel" );
    }

    private static void postTitleGameDual (String name ){
        postTitleGame( name,"Duel" );
    }

    private static void postTitleGame(String name, String mode ){
        Parameter.getCounter();
        logConsoleInfo("*** Jeu du "+ Game.getGameName() + " en mode "+  mode + ". Combinaison de "+ Parameter.getNumberCasePossible() +" chiffres maximun et " + Parameter.getNumberTrialPossible() + " essais possibles ***");
    }

    public static void postTitleTrial(){
        logConsoleInfo("Joueur : Essai n°" + Game.getTrial() + " sur "+ Parameter.getNumberTrialPossible() +". Veuillez saisir une combinaison.");
    }

    public static void postResultPlayer(){
        postResult( "Joueur", Game.getTrial(), Game.getCombinaisonNumberPlayer(), Game.getPlayerResult() );
    }

    public static void postResultPC(){
        postResult( "PC", Game.getTrial(), Game.getCombinaisonNumberPC(), Game.getPcResult() );
    }

    private static void postResult(String player,int numberTrial, String combinaison,String postResult ){
        logConsoleInfo(player + " : Proposition n° " + numberTrial + " sur "+ Parameter.getNumberTrialPossible() + " : " + combinaison + " -> Réponse : " + postResult );
    }

    public static void postTitleControllerSeizureLength(){
        postTitleControllerSeizureLength( Parameter.getNumberCasePossible() );
    }

    private static void postTitleControllerSeizureLength(int len){
        logConsoleError("La longueur de la combinaison doit avoir une longueur max de "+ len + " chiffres maximum.");
    }

    public static void postTitleControllerFormat(){
        logConsoleError( "Erreur de format. Veuillez saisir une valeur numérique" );
    }

    public static void postQuestionNumberSecret(){
        logConsoleInfo("Joueur : Veuillez saisir votre combinaison secrète");
    }

    public static void postLostResultPC(){
        postLostResult("PC", Game.getSecretNumberPlayer() );
    }

    public static void postLostResultPlayer(){
        postLostResult("Joueur", Game.getSecretNumberPC() );
    }

    public static void postLostResult(String player, String secretNumber){
        logConsoleInfo( player + " : Désolé la combinaison secrète " + secretNumber + " n'a pas été trouvée." );
    }

    public static void postWinResultPC(int postTrial){
        postWinResult("PC", postTrial);
    }

    public static void postWinResultPlayer(int postTrial){
        postWinResult("Joueur",  postTrial);
    }

    public static void postWinResult(String player, int postTrial){
        String value = player + " : Félicitation la combinaison secrète a été trouvée en "+ postTrial +" essais.";
        logger.info( value );
        System.out.println( value );
    }

    public static void postTitleMainMenu(){
        logConsoleInfo("A quel jeu souhaitez-vous jouer ? 1 -> Recherche + - : 2 -> Mastermind.");
    }

    public static void postTitleControllerGameMenu(){
        logConsoleError("Votre choix doit égal à 1 ou 2.");
    }

    public static void postTitleControllerModeMenu(){
        logConsoleError("Votre choix doit être égal à 1, 2 ou 3.");
    }

    public static void postTitleModeMenuRechercherPlusMoins(){
        postTitleModeMenu("Recherche + -");
    }

    public static void postTitleModeMenuMastermind(){
        postTitleModeMenu("Mastermind");
    }

    private static void postTitleModeMenu(String game){
        logConsoleInfo(game + " choisissez votre mode : 1 - Challenger : 2 - Défenseur : 3 - Duel");
    }

    public static void mainTitle(){
        logConsoleInfo("Bonjour et bienvenue dans vos jeux de société.");
    }

    public static void postEndOfPartyMenu(){
        logConsoleInfo("Voulez-vous recommencer une partie O/N ou quitter le jeu Q ?");
    }

    public static void postErrorChoice(){
        logConsoleError("Je n'ai pas compris votre choix.");
    }

    public static void postTitleDebug()  {
        String title = "*******************************\n";
        title +="**  Mode Développeur activé  **\n";
        title +="*******************************";
        logConsoleInfo( title );
    }

    public static void postTitlePlayer(){
        String title = "*******************************\n";
        title +="**     Mode Joueur activé    **\n";
        title +="*******************************";
        logConsoleInfo( title );
    }

    private static void logConsoleInfo(String value ){
        logger.info( value );
        System.out.println( value );
    }

    private static void logConsoleError(String value ){
        logger.error( value );
        System.out.println( value );
    }

    private static void logConsoleDebug(String value ){
        logger.debug( value );
        System.out.println( value );
    }
}
