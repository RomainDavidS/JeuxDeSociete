package enedis.romaindavid.com.game;

import enedis.romaindavid.com.game.Game;
import java.util.InputMismatchException;

import static enedis.romaindavid.com.algorithme.Plugins.*;
import static enedis.romaindavid.com.param.Parameter.*;

public class RecherchePlusMoins  extends Game {

    public static String combinaisonResult(String combinaisonSeizure, String combinaisonSecret){

        String[] combinaisonSeizureArray = combinaisonSeizure.split("");
        String[] combinaisonSecretArray = combinaisonSecret.split("");

        int lenArray = combinaisonSeizureArray.length - 1;

        String resultCombinaison = "";

        for(int i = 0 ; i<= lenArray;i++)
            resultCombinaison += resultOne( toInt( combinaisonSeizureArray[ i ] ),toInt( combinaisonSecretArray[ i ] ) );

        return resultCombinaison;
    }

    private static String resultOne( int combinaisonSeizure, int combinaisonSecret ){
        if( combinaisonSeizure >combinaisonSecret )
            return "+";
        else if ( combinaisonSeizure < combinaisonSecret )
            return "-";
        else return "=";
    }

    protected boolean isCombinaisonTrouve(String resultCombinaison ){
        boolean trouve = true;
        String[] resultCombinaisonArray = resultCombinaison.split("");
        for (String value : resultCombinaisonArray )
            if( !value.equals( "=" ) )
                return false;

        return trouve ;
    }

    protected boolean isSeizureGoodLength(int seizure){
        return ( toStr( seizure ).length() <= getNumberCasePossible() );
    }

    protected void generateCombinaisonPC(){
        String newCombinaisonPC = "";
        String[] combinaisonNumberPcArray = combinaisonNumberPC.split("");
        String[] resultArray = result.split("");

        for(int i = 0; i < getNumberCasePossible();i++)
            newCombinaisonPC += newPcCombinaison( i, combinaisonNumberPcArray[ i ], resultArray[ i ]  );

        combinaisonNumberPC = newCombinaisonPC ;
    }

    private String newPcCombinaison(int index,String combinaisonNumber , String result  ){

        String possible = mapPossible.get( index );
        String[] possibleArray = possible.split("");
        if (possible.length() == 1)
            return mapPossible.get( index );

        int intCombinaisonNumber = toInt( combinaisonNumber );

        String newCombinaison = "";
        for (String str: possibleArray ) {
            int intStr = Integer.valueOf( str );

            if( result.equals("+") )
                if( intStr < intCombinaisonNumber )
                    newCombinaison += str;

            if ( result.equals("-") )
                if( intStr > intCombinaisonNumber )
                    newCombinaison += str;

            if ( result.equals("=") )
                if( intStr == intCombinaisonNumber ){
                    newCombinaison = str;
                    break;
                }

        }

        String[] newCombinaisonArray = newCombinaison.split("");
        mapPossible.replace(index, newCombinaison );

        int len = newCombinaisonArray.length ;

        if( len == 1)
            return newCombinaison;
        else{
            int newR = generateRandomInRange(toInt( newCombinaisonArray[0] ),toInt( newCombinaisonArray[ len -1 ] ));
            return toStr( newR );
        }

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

    protected void initMapPossible(){
        for ( int i=0; i < getNumberCasePossible() ; i++ )
            mapPossible.put( i, "0123456789");
    }

    @Override
    protected void playerProposition( String player ){

        trialPlayer++;

        try {

            System.out.println("Essai n°" + trialPlayer +" : Joueur veuillez saisir une combinaison");
            int seizure = sc.nextInt() ;

            if( isSeizureGoodLength( seizure ) )
                combinaisonNumberPlayer = formatNumber( seizure ) ;
            else{
                trialPlayer --;
                System.out.println("La longueur de la combinaison doit avoir une longueur max de "+ getNumberCasePossible() + " chiffres maximum.");
                playerProposition(  player );
            }

        }catch (InputMismatchException e){
            trialPlayer --;
            // On contrôle que le format est correct
            System.out.println( "Erreur de format. Veuillez saisir une valeur numérique" );
            sc.nextLine();
            playerProposition( player );
        }
        gameCombinaison.setCombinaisonSecret( secretNumberPC );
        gameCombinaison.setCombinaisonNumber( combinaisonNumberPlayer );

        result = gameCombinaison.resultCombinaison() ;
        System.out.println("Proposition joueur n° " + trialPlayer + " : " + combinaisonNumberPlayer + " -> Réponse : " + result );

        if (!isCombinaisonTrouve(result))
            if(!(trialPlayer == getNumberTrialPossible()) )
                modePlayerGame( player );
            else
                System.out.println( "Désolé la combinaison secrète " + gameCombinaison.getCombinaisonSecret() +" n'a pas été trouvée." );
        else
            System.out.println( "Félicitation joueur la combinaison secrète a été trouvée en "+ trialPlayer +" essais." );
    }

    @Override
    protected void pcProposition( String player){
        trialPC++;
        gameCombinaison.setCombinaisonSecret( secretNumberPlayer );
        gameCombinaison.setCombinaisonNumber( combinaisonNumberPC );
        System.out.println("M l'ordinateur choisi la combinaison " + combinaisonNumberPC );

        result = gameCombinaison.resultCombinaison() ;
        System.out.println("Proposition ordinateur n° " + trialPC + " : " + combinaisonNumberPC + " -> Réponse : " + result );

        if(!isCombinaisonTrouve( result ) )
            if(!(trialPC == getNumberTrialPossible()) ) {
                generateCombinaisonPC();
                modePlayerGame( player);
            }else
                System.out.println( "Désolé la combinaison secrète " + gameCombinaison.getCombinaisonSecret() +" n'a pas été trouvée." );
        else
            System.out.println( "L'ordinateur a trouvé le combinaison en "+ trialPC +" essais.");
    }

    @Override
    protected void challenger() {
        initChallenger();
        if ( getModeDebug().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  secretNumberPC + ")" );
        modePlayerGame( "player" );
    }

    @Override
    protected void initChallenger() {
        System.out.println("*** Jeu du Recherche +/- en mode Challenger. ***");
        secretNumberPC = generateRandomString() ;
        modeGame = "challenger";
    }

    @Override
    protected void defender() {
        initDefender();
        if ( getModeDebug().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  secretNumberPlayer + ")" );

        modePlayerGame( "pc");
    }

    @Override
    protected void initDefender() {
        System.out.println("*** Jeu du Recherche +/- en mode Défenseur. ***");
        initMapPossible();
        initSecretNumberPlayer();
        combinaisonNumberPC = generateRandomString();
        modeGame = "defender";
    }

    @Override
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

    @Override
    protected void initDual() {
        System.out.println("*** Jeu du Recherche +/- en mode Duel. ***");

        secretNumberPC = generateRandomString() ;
        gameCombinaison.setCombinaisonSecret( secretNumberPC );
        initMapPossible();
        initSecretNumberPlayer();
        combinaisonNumberPC = generateRandomString();
        modeGame = "dual";

    }
}
