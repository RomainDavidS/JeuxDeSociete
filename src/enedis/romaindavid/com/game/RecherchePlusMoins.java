package enedis.romaindavid.com.game;


import java.util.InputMismatchException;

import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.*;

public class RecherchePlusMoins  extends Game {
    public RecherchePlusMoins(String gameName) {
        setGameName ( gameName );
    }

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

        String newCombinaison  = generateCombinaison( result,  possibleArray,toInt( combinaisonNumber ) );

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

    private String generateCombinaison(  String result, String[] possibleArray,int intCombinaisonNumber ){
        String combinaison = "";
        for (String str: possibleArray ) {
            int intStr = Integer.valueOf( str );
            if( result.equals("+") )
                if( intStr < intCombinaisonNumber )
                    combinaison += str;

            if ( result.equals("-") )
                if( intStr > intCombinaisonNumber )
                    combinaison += str;

            if ( result.equals("=") )
                if( intStr == intCombinaisonNumber ){
                    combinaison = str;
                    break;
                }
        }
        return combinaison;
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
        resultPlayerProposition( player);
    }

    @Override
    protected void resultPlayerProposition( String player ){
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


}
