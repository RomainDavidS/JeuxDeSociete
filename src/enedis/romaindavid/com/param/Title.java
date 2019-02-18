package enedis.romaindavid.com.param;

import static enedis.romaindavid.com.game.RecherchePlusMoins.*;
import static enedis.romaindavid.com.game.Mastermind.*;

import static enedis.romaindavid.com.param.Parameter.getNumberCasePossible;

public abstract class Title {

    public static void postTitlePlayerFirst(){
        System.out.println("C'est l'ordinateur qui joue en premier");
    }
    public static void postTitlePcFirst(){
        System.out.println("C'est le joueur qui joue en premier");
    }
    public static void postSecretNumberPc(){
        postSecretNumber( "PC", getSecretNumberPC() );
    }

    public static void postSecretNumberPlayer(){
        postSecretNumber("joueur", getSecretNumberPlayer() );
    }

    private static void postSecretNumber(String player,String secretNumber){
        System.out.println("(Combinaison secrète "+ player + " : "+  secretNumber + ")" );
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
        System.out.println("*** Jeu du "+ getGameName() + " en mode "+  mode + ". ***");
    }

    public static void postTitleTrial(){
        System.out.println("Joueur : Essai n°" + getTrial() + ". Veuillez saisir une combinaison.");
    }

    public static void postResultPlayer(){
        postResult( "Joueur", getTrial(), getCombinaisonNumberPlayer(), getPlayerResult() );
    }

    public static void postResultPC(){
        postResult( "PC", getTrial(), getCombinaisonNumberPC(), getPcResult() );
    }

    private static void postResult(String player,int numberTrial, String combinaison,String postResult ){
        System.out.println(player + " : Proposition n° " + numberTrial + " : " + combinaison + " -> Réponse : " + postResult );
    }

    public static void postTitleControllerSeizureLength(){
        postTitleControllerSeizureLength( getNumberCasePossible() );
    }

    private static void postTitleControllerSeizureLength(int len){
        System.out.println("La longueur de la combinaison doit avoir une longueur max de "+ len + " chiffres maximum.");
    }

    public static void postTitleControllerFormat(){
        System.out.println( "Erreur de format. Veuillez saisir une valeur numérique" );
    }

    public static void postQuestionNumberSecret(){
        System.out.println("Joueur : Veuillez saisir votre combinaison secrète");
    }

    public static void postLostResultPC(){
        postLostResult("PC");
    }

    public static void postLostResultPlayer(){
        postLostResult("Joueur");
    }

    public static void postLostResult(String player){
        System.out.println( player + " : Désolé la combinaison secrète n'a pas été trouvée." );
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
        System.out.println("A quel jeu souhaitez-vous jouer ? 1 -> Recherche + 1- : 2 -> Mastermind.");
    }
    public static void postTitleControllerGameMenu(){
        System.out.println("Votre choix doit égal à 1 ou 2.");
    }
    public static void postTitleControllerModeMenu(){
        System.out.println("Votre choix doit être égal à 1, 2 ou 3.");
    }
    public static void postTitleModeMenuRechercherPlusMoins(){
        postTitleModeMenu("Recherche + -");
    }

    public static void postTitleModeMenuMastermind(){
        postTitleModeMenu("Mastermind");
    }
    private static void postTitleModeMenu(String game){
        System.out.println(game + " choisissez votre mode : 1 - Challenger : 2 - Défenseur : 3 - Duel");
    }
}
