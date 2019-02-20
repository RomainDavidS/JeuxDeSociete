package enedis.romaindavid.com.param;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static enedis.romaindavid.com.game.RecherchePlusMoins.*;
import static enedis.romaindavid.com.game.Mastermind.*;

import static enedis.romaindavid.com.param.Parameter.getNumberCasePossible;
import static enedis.romaindavid.com.param.Parameter.getNumberTrialPossible;

/**
 * LOGS
 *         logger.debug("This is a debug message");
 *         logger.info("This is an info message");
 *         logger.warn("This is a warn message");
 *         logger.error("This is an error message");
 *         logger.fatal("This is a fatal message");
 */
public abstract class Title {
    private static Logger logger = LogManager.getLogger();

    public static void postTitlePlayerFirst(){
        logger.info("C'est l'ordinateur qui joue en premier");
    }

    public static void postTitlePcFirst(){
        logger.info("C'est le joueur qui joue en premier");
    }

    public static void postSecretNumberPc(){
        postSecretNumber( "PC", getSecretNumberPC() );
    }

    public static void postSecretNumberPlayer(){
        postSecretNumber("joueur", getSecretNumberPlayer() );
    }

    private static void postSecretNumber(String player,String secretNumber){
        logger.debug("(Combinaison secrète "+ player + " : "+  secretNumber + ")" );
    }

    public static void postTitleGameChallenger(){
        postTitleGame( getGameName(),"Challenger" );
    }

    private static void postTitleGameChallenger (String name ){
        postTitleGame( name,"Challenger" );
    }

    public static void postTitleGameDefender(){
        postTitleGame( getGameName(),"Défenseur" );
    }

    private static void postTitleGameDefender (String name ){
        postTitleGame( name,"Défenseur" );
    }

    public static void postTitleGameDual(){
        postTitleGame( getGameName(),"Duel" );
    }

    private static void postTitleGameDual (String name ){
        postTitleGame( name,"Duel" );
    }

    private static void postTitleGame(String name, String mode ){
        logger.info("*** Jeu du "+ getGameName() + " en mode "+  mode + ". Combinaison de "+ getNumberCasePossible() +" chiffres maximun et " + getNumberTrialPossible() + " essais possibles ***");
    }

    public static void postTitleTrial(){
        logger.info("Joueur : Essai n°" + getTrial() + " sur "+ getNumberTrialPossible() +". Veuillez saisir une combinaison.");
    }

    public static void postResultPlayer(){
        postResult( "Joueur", getTrial(), getCombinaisonNumberPlayer(), getPlayerResult() );
    }

    public static void postResultPC(){
        postResult( "PC", getTrial(), getCombinaisonNumberPC(), getPcResult() );
    }

    private static void postResult(String player,int numberTrial, String combinaison,String postResult ){
        logger.info(player + " : Proposition n° " + numberTrial + " sur "+ getNumberTrialPossible() + " : " + combinaison + " -> Réponse : " + postResult );
    }

    public static void postTitleControllerSeizureLength(){
        postTitleControllerSeizureLength( getNumberCasePossible() );
    }

    private static void postTitleControllerSeizureLength(int len){
        logger.error("La longueur de la combinaison doit avoir une longueur max de "+ len + " chiffres maximum.");
    }

    public static void postTitleControllerFormat(){
        logger.error( "Erreur de format. Veuillez saisir une valeur numérique" );
    }

    public static void postQuestionNumberSecret(){
        logger.info("Joueur : Veuillez saisir votre combinaison secrète");
    }

    public static void postLostResultPC(){
        postLostResult("PC", getSecretNumberPlayer());
    }

    public static void postLostResultPlayer(){
        postLostResult("Joueur", getSecretNumberPC() );
    }

    public static void postLostResult(String player, String secretNumber){
        logger.info( player + " : Désolé la combinaison secrète " + secretNumber + " n'a pas été trouvée." );
    }

    public static void postWinResultPC(int postTrial){
        postWinResult("PC", postTrial);
    }

    public static void postWinResultPlayer(int postTrial){
        postWinResult("Joueur",  postTrial);
    }

    public static void postWinResult(String player, int postTrial){
        System.out.println( player + " : Félicitation la combinaison secrète a été trouvée en "+ postTrial +" essais." );
    }

    public static void postTitleMainMenu(){
        logger.info("A quel jeu souhaitez-vous jouer ? 1 -> Recherche + - : 2 -> Mastermind.");
    }

    public static void postTitleControllerGameMenu(){
        logger.error("Votre choix doit égal à 1 ou 2.");
    }

    public static void postTitleControllerModeMenu(){
        logger.error("Votre choix doit être égal à 1, 2 ou 3.");
    }

    public static void postTitleModeMenuRechercherPlusMoins(){
        postTitleModeMenu("Recherche + -");
    }

    public static void postTitleModeMenuMastermind(){
        postTitleModeMenu("Mastermind");
    }

    private static void postTitleModeMenu(String game){
        logger.info(game + " choisissez votre mode : 1 - Challenger : 2 - Défenseur : 3 - Duel");

    }

    public static void mainTitle(){
        logger.info("Bonjour et bienvenue dans vos jeux de société.");
    }

}
