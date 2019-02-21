package enedis.romaindavid.com.game;


import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.*;

/**
 *  Classes of the "Recherche +/-" game :
 *     - method generateSecretRandomString : generate the secret combination of the computer
 *     - method generateCombinaisonRandomString : generate an automatic combination of the computer
 *     - method generateCombinaisonPC : generate a combination for the computer based on the result of the previous combination
 *     - method combinaisonResult : result of entering a combination made by the computer
 *     - method combinaisonResult : result of entering a combination made by the player
 *     - method isCombinaisonTrouve : we check if the combination was found either by the computer or by the player
 */
public class RecherchePlusMoins  extends Game {

    public RecherchePlusMoins() {
        gameName = "Recherche +/-" ;
        choiceGame = 1;
        initMapPossible();

    }

    /**
     * generate the secret combination of the computer
     * 
     * @return
     */
    @Override
    protected String generateSecretRandomString(){
        int randNumber = generateRandom( puissanceDe10( getNumberCasePossible() )) ;
        return formatNumber( randNumber );
    }

    @Override
    protected String generateCombinaisonRandomString() {
        return generateSecretRandomString();
    }

    @Override
    protected void generateCombinaisonPC(){
        String newCombinaisonPC = "";

        String[] combinaisonNumberPcArray = combinaisonNumberPC.split("");

        String[] resultArray = pcResult.split("");

        for(int i = 0; i < getNumberCasePossible(); i++)
            newCombinaisonPC += newPcCombinaison( i, combinaisonNumberPcArray[ i ], resultArray[ i ]  );

        combinaisonNumberPC = newCombinaisonPC ;
    }

    @Override
    protected String combinaisonResult(String secretNumber){
        if(trialPC == 1 )
            combinaisonNumberPC = generateCombinaisonRandomString() ;
        else
            generateCombinaisonPC();

        return combinaisonResult( secretNumber,combinaisonNumberPC);
    }

    @Override
    protected String combinaisonResult( String secretNumber,String combinaisonNumber ){

        String[] combinaisonSecretArray = secretNumber.split("");
        String[] combinaisonSeizureArray = combinaisonNumber.split("");


        int lenArray = combinaisonSeizureArray.length - 1;

        String resultCombinaison = "" ;

        for(int i = 0 ; i<= lenArray;i++)
            resultCombinaison += resultOne( toInt( combinaisonSeizureArray[ i ] ),toInt( combinaisonSecretArray[ i ] ) );

        return resultCombinaison;
    }

    @Override
    protected boolean isCombinaisonTrouve(String result ){
        boolean trouve = true;
        String[] resultCombinaisonArray = result.split("");
        for (String value : resultCombinaisonArray )
            if( !value.equals( "=" ) )
                return false;

        return trouve ;
    }

    /**
     *
     * methods specific to class RecherchePlusMoins
     */

    /**
     *
     * @param index
     * @param combinaisonNumber
     * @param result
     * @return
     */
    private String newPcCombinaison(int index,String combinaisonNumber , String result  ){

        String possible = mapPossible.get( index );

        String[] possibleArray = possible.split("");
        if (possible.length() == 1)
            return mapPossible.get( index );

        String newCombinaison  = generateCombinaison( result,  possibleArray,toInt( combinaisonNumber ) );

        String[] newCombinaisonArray = newCombinaison.split("");

        mapPossible.replace( index, newCombinaison );

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
            int intStr = toInt( str );
            if( result.equals( "+" ) )
                if( intStr < intCombinaisonNumber )
                    combinaison += str;

            if ( result.equals( "-" ) )
                if( intStr > intCombinaisonNumber )
                    combinaison += str;

            if ( result.equals("=") )
                if (intStr == intCombinaisonNumber) {
                    combinaison = str;
                    break;
                }
        }
        return combinaison;
    }



    /*
    Méthodes spécifique au jeux Recherche +/-
     */

    private void initMapPossible(){
        for ( int i = 0; i < getNumberCasePossible() ; i++ )
            mapPossible.put( i, "0123456789");
    }

    private static String resultOne( int combinaisonSeizure, int combinaisonSecret ){
        if( combinaisonSeizure > combinaisonSecret )
            return "+";
        else if ( combinaisonSeizure < combinaisonSecret )
            return "-";
        else return "=";
    }













}
