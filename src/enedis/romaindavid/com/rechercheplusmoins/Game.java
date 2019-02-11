package enedis.romaindavid.com.rechercheplusmoins;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static enedis.romaindavid.com.algorithme.Plugins.*;
import static enedis.romaindavid.com.param.Parameter.*;

abstract class Game {

    public Game() {}

    protected String secretNumberPC;
    protected String secretNumberPlayer;

    protected String combinaisonNumberPC;
    protected String combinaisonNumberPlayer;

    protected Scanner sc = new Scanner( System.in );
    protected GameCombinaison gameCombinaison = new GameCombinaison();
    protected int trialPlayer = 0 ;
    protected int trialPC = 0 ;

    protected String modeGame ;


    protected Map<Integer,String> mapPossible = new HashMap<Integer,String>();
    protected String result ;

    static String combinaisonResult(String combinaisonSeizure, String combinaisonSecret){

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
                System.out.println( "Désolé la combinaison secrète n'a pas été trouvée." );
        else
            System.out.println( "Félicitation joueur la combinaison secrète a été trouvée en "+ trialPlayer +" essais." );
    }


    protected void pcProposition( String player){
        trialPC++;
        gameCombinaison.setCombinaisonSecret( secretNumberPlayer );
        gameCombinaison.setCombinaisonNumber( combinaisonNumberPC );
        System.out.println("M l'ordinateur choisi la combinaison " + combinaisonNumberPC );

        result = gameCombinaison.resultCombinaison() ;
        System.out.println("Proposition ordinateur n° " + trialPC + " : " + combinaisonNumberPC + " -> Réponse : " + result );

        if(!isCombinaisonTrouve( result ) ) {
            generateCombinaisonPC();
            //pcProposition(mode,player);
            modePlayerGame( player);
        }
        else
            System.out.println( "L'ordinateur a trouvé le combinaison en "+ trialPC +" essais.");
    }
}
