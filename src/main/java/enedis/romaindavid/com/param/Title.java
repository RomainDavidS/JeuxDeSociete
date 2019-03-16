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

    private static Logger logger = LogManager.getLogger( Title.class );


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


    public void postTitlePlayerFirst(){
        postTitleInfo("C'est l'ordinateur qui joue en premier");
    }

    public void postTitlePcFirst(){
        postTitleInfo("C'est le joueur qui joue en premier");
    }

    public void postSecretNumberPc(){
        postSecretNumber( "PC", Game.getSecretNumberPC() );
    }

    public void postSecretNumberPlayer(){
        postSecretNumber("joueur", Game.getSecretNumberPlayer() );
    }

    private void postSecretNumber(String player,String secretNumber){
        postTitleDebug("(Combinaison secrète "+ player + " : "+  secretNumber + ")" );
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
        postTitleInfo("*** Jeu du "+ Game.getGameName() + " en mode "+  mode + ". Combinaison de "+ params.getNumberCasePossible() +" chiffres maximun et " + params.getNumberTrialPossible() + " essais possibles ***");

    }

    public void postTitleTrial(){
        postTitleInfo("Joueur : Essai n°" + Game.getTrial() + " sur "+ params.getNumberTrialPossible() +". Veuillez saisir une combinaison.");
    }

    public void postResultPlayer(){
        postResult( "Joueur", Game.getTrial(), Game.getCombinaisonNumberPlayer(), Game.getPlayerResult() );
    }

    public void postResultPC(){
        postResult( "PC", Game.getTrial(), Game.getCombinaisonNumberPC(), Game.getPcResult() );
    }

    private void postResult(String player,int numberTrial, String combinaison,String postResult ){
        postTitleInfo(player + " : Proposition n° " + numberTrial + " sur "+ params.getNumberTrialPossible() + " : " + combinaison + " -> Réponse : " + postResult );
    }

    public void postTitleControllerSeizureLength(){
        postTitleControllerSeizureLength( params.getNumberCasePossible() );
    }

    private void postTitleControllerSeizureLength(int len){
        postTitleError("La longueur de la combinaison doit avoir une longueur max de "+ len + " chiffres maximum.");
    }

    public void postTitleControllerFormat(){
        postTitleError( "Erreur de format. Veuillez saisir une valeur numérique" );
    }

    public void postQuestionNumberSecret(){
        postTitleInfo("Joueur : Veuillez saisir votre combinaison secrète");
    }

    public void postLostResultPC(){
        postLostResult("PC", Game.getSecretNumberPlayer() );
    }

    public void postLostResultPlayer(){
        postLostResult("Joueur", Game.getSecretNumberPC() );
    }

    public void postLostResult(String player, String secretNumber){
        postTitleError( player + " : Désolé la combinaison secrète " + secretNumber + " n'a pas été trouvée." );
    }

    public void postWinResultPC(int postTrial){
        postWinResult("PC", postTrial);
    }

    public void postWinResultPlayer(int postTrial){
        postWinResult("Joueur",  postTrial);
    }

    public void postWinResult(String player, int postTrial){
        String value = player + " : Félicitation la combinaison secrète a été trouvée en "+ postTrial +" essais.";
        postTitleInfo( value );
    }

    public void postTitleMainMenu(){
        postTitleInfo("A quel jeu souhaitez-vous jouer ? 1 -> Recherche + - : 2 -> Mastermind.");

    }

    public void postTitleControllerGameMenu(){
        postTitleError("Votre choix doit égal à 1 ou 2.");
    }

    public void postTitleControllerModeMenu(){
        postTitleError("Votre choix doit être égal à 1, 2 ou 3.");
    }

    public void postTitleModeMenuRechercherPlusMoins(){
        postTitleModeMenu("Recherche + -");
    }

    public void postTitleModeMenuMastermind(){
        postTitleModeMenu("Mastermind");
    }

    private void postTitleModeMenu(String game){
        postTitleInfo(game + " choisissez votre mode : 1 - Challenger : 2 - Défenseur : 3 - Duel");
    }

    public void mainTitle(){
        postTitleInfo("Bonjour et bienvenue dans vos jeux de société.");
    }

    public void postEndOfPartyMenu(){
        postTitleInfo("Voulez-vous recommencer une partie O/N ou quitter le jeu Q ?");
    }

    public void postErrorChoice(){
        postTitleError("Je n'ai pas compris votre choix.");
    }

    public void postTitleDev()  {
        String title = "*******************************\n";
        title +="**  Mode Développeur activé  **\n";
        title +="*******************************";

        postTitleInfo( title );
    }

    public void postTitlePlayer(){
        String title = "*******************************\n";
        title +="**     Mode Joueur activé    **\n";
        title +="*******************************";
        postTitleInfo( title );
    }

    public void postChoiceInfo(int value){
        logger.info( value );
    }

    public void postChoiceInfo( String value ){
        logger.info( value );
    }

    public void postTitleInfo(int value){
       System.out.println( value );
        postChoiceInfo( value );
    }

    public void postTitleInfo(String value){
        System.out.println( value );
        postChoiceInfo( value );
    }

    public void postTitleDebug(int value){
        System.out.println( value );
        logger.debug( value );

    }

    public void postTitleDebug(String value){
        System.out.println( value );
        logger.debug( value );
    }

    public void postTitleError(int value){
        logger.error( value );
    }

    public void postTitleError(String value){
        logger.error( value );
    }

}
