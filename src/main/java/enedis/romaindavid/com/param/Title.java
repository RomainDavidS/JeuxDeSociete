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
    private Parameter params = Parameter.getInstance();

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

    public void postTitlePlayerFirst(){
       logger.info("C'est l'ordinateur qui joue en premier");
    }

    public void postTitlePcFirst(){
        logger.info("C'est le joueur qui joue en premier");
    }

    public void postSecretNumberPc(){
        postSecretNumber( "PC", Game.getSecretNumberPC() );
    }

    public void postSecretNumberPlayer(){
        postSecretNumber("joueur", Game.getSecretNumberPlayer() );
    }

    private void postSecretNumber(String player,String secretNumber){
        logger.debug("(Combinaison secrète "+ player + " : "+  secretNumber + ")" );
    }

    public void postTitleGameChallenger(){
        postTitleGame( Game.getGameName(),"Challenger" );
    }

    private void postTitleGameChallenger (String name ){
        postTitleGame( name,"Challenger" );
    }

    public void postTitleGameDefender(){
        postTitleGame( Game.getGameName(),"Défenseur" );
    }

    private void postTitleGameDefender (String name ){
        postTitleGame( name,"Défenseur" );
    }

    public void postTitleGameDual(){
        postTitleGame( Game.getGameName(),"Duel" );
    }

    private void postTitleGameDual (String name ){
        postTitleGame( name,"Duel" );
    }

    private void postTitleGame(String name, String mode ){
        Parameter.getCounter();
        logger.info("*** Jeu du "+ Game.getGameName() + " en mode "+  mode + ". Combinaison de "+ params.getNumberCasePossible() +" chiffres maximun et " + params.getNumberTrialPossible() + " essais possibles ***");
    }

    public void postTitleTrial(){
        logger.info("Joueur : Essai n°" + Game.getTrial() + " sur "+ params.getNumberTrialPossible() +". Veuillez saisir une combinaison.");
    }

    public void postResultPlayer(){
        postResult( "Joueur", Game.getTrial(), Game.getCombinaisonNumberPlayer(), Game.getPlayerResult() );
    }

    public void postResultPC(){
        postResult( "PC", Game.getTrial(), Game.getCombinaisonNumberPC(), Game.getPcResult() );
    }

    private void postResult(String player,int numberTrial, String combinaison,String postResult ){
        logger.info(player + " : Proposition n° " + numberTrial + " sur "+ params.getNumberTrialPossible() + " : " + combinaison + " -> Réponse : " + postResult );
    }

    public void postTitleControllerSeizureLength(){
        postTitleControllerSeizureLength( params.getNumberCasePossible() );
    }

    private void postTitleControllerSeizureLength(int len){
        logger.error("La longueur de la combinaison doit avoir une longueur max de "+ len + " chiffres maximum.");
    }

    public void postTitleControllerFormat(){
        logger.error( "Erreur de format. Veuillez saisir une valeur numérique" );
    }

    public void postQuestionNumberSecret(){
        logger.info("Joueur : Veuillez saisir votre combinaison secrète");
    }

    public void postLostResultPC(){
        postLostResult("PC", Game.getSecretNumberPlayer() );
    }

    public void postLostResultPlayer(){
        postLostResult("Joueur", Game.getSecretNumberPC() );
    }

    public void postLostResult(String player, String secretNumber){
        logger.error( player + " : Désolé la combinaison secrète " + secretNumber + " n'a pas été trouvée." );
    }

    public void postWinResultPC(int postTrial){
        postWinResult("PC", postTrial);
    }

    public void postWinResultPlayer(int postTrial){
        postWinResult("Joueur",  postTrial);
    }

    public void postWinResult(String player, int postTrial){
        String value = player + " : Félicitation la combinaison secrète a été trouvée en "+ postTrial +" essais.";
        logger.info( value );

    }

    public void postTitleMainMenu(){
        logger.info("A quel jeu souhaitez-vous jouer ? 1 -> Recherche + - : 2 -> Mastermind.");
    }

    public void postTitleControllerGameMenu(){
        logger.error("Votre choix doit égal à 1 ou 2.");
    }

    public void postTitleControllerModeMenu(){
        logger.error("Votre choix doit être égal à 1, 2 ou 3.");
    }

    public void postTitleModeMenuRechercherPlusMoins(){
        postTitleModeMenu("Recherche + -");
    }

    public void postTitleModeMenuMastermind(){
        postTitleModeMenu("Mastermind");
    }

    private void postTitleModeMenu(String game){
        logger.info(game + " choisissez votre mode : 1 - Challenger : 2 - Défenseur : 3 - Duel");
    }

    public void mainTitle(){
        logger.info("Bonjour et bienvenue dans vos jeux de société.");
    }

    public void postEndOfPartyMenu(){
        logger.info("Voulez-vous recommencer une partie O/N ou quitter le jeu Q ?");
    }

    public void postErrorChoice(){
        logger.error("Je n'ai pas compris votre choix.");
    }

    public void postTitleDebug()  {
        String title = "*******************************\n";
        title +="**  Mode Développeur activé  **\n";
        title +="*******************************";
        logger.info( title );
    }

    public void postTitlePlayer(){
        String title = "*******************************\n";
        title +="**     Mode Joueur activé    **\n";
        title +="*******************************";
        logger.info( title );
    }


}
