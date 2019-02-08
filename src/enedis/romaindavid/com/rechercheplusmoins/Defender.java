package enedis.romaindavid.com.rechercheplusmoins;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Defender extends Game {
    Scanner sc = new Scanner( System.in );

    String secretNumber ;
    String pcCombinaison;
    /**
     *
     *
     *
     *
     *
     *
     */
    private static final int numberPossible = 4;
    Map<Integer,String> possibleMap = new HashMap<Integer,String>();
    public void runGame()  {
        for ( int i=0; i <numberPossible ; i++ ){
            possibleMap.put( i, "0123456789");
        }

        System.out.println("Joueur : Veuillez saisir votre combinaison secrète");
        secretNumber = String.format("%0" + numberPossible + "d",  sc.nextInt() );
        System.out.println("(Combinaison secrète : "+  secretNumber + ")" );

        pcCombinaison = randomNumber( ) ;

        proposition();
    }
    private void proposition(){

        System.out.println("M l'ordinateur choisi la combinaison " + pcCombinaison);
        String result = searchFull(  pcCombinaison, secretNumber );

        System.out.println("Proposition de M l'ordinateur : " +  pcCombinaison + " -> Réponse : " + result);
/*
        if(!isCombinaisonTrouve( result ) )
           proposition();
        else
            System.out.println("Félicitation la combinaison secrète a été trouvée.");*/
    }
    public String newPcCombinaison(String var1, String var2  ){
        for ( int i=0; i <numberPossible ; i++ ){
            possibleMap.put( i, "0123456789");
        }

        String possible = possibleMap.get( 0 );
        String[] possibleArray = possible.split("");

        int intVar1 = Integer.valueOf( var1 );

        String newCombinaison = "";
        for (String str: possibleArray ) {
            int intStr = Integer.valueOf( str );

            if( var2.equals("+") )
                if( intStr < intVar1 )
                    newCombinaison += str;

            if ( var2.equals("-") )
                if( intStr > intVar1 )
                    newCombinaison += str;

            if ( var2.equals("=") )
                if( intStr == intVar1 ){
                    newCombinaison = str;
                    break;
                }

        }

        String[] newCombinaisonArray = newCombinaison.split("");
        possibleMap.replace(0, newCombinaison );
        int len = newCombinaisonArray.length ;

        if( len == 1)
            return newCombinaison + ": " + possibleMap.get( 0 );
        else{
            int newR = 0; //randomNumberInRange( Integer.valueOf( newCombinaisonArray[0] ),Integer.valueOf( newCombinaisonArray[ len -1 ] ));
            return String.valueOf( newR )+ ": " + possibleMap.get( 0 );
        }

    }


    public String searchFull(String choice, String toResearch){
        String[] choiceArray = choice.split("");
        String[] toResearcheArray = toResearch.split("");
        int lenArray = choiceArray.length - 1;
        String result = "";
        for(int i = 0 ; i<= lenArray;i++)
            result += searchOne( Integer.valueOf( choiceArray[ i ] ),Integer.valueOf( toResearcheArray[ i ] ) );

        return result;
    }

    public String searchOne( int choice, int toResearch ){

        if( choice > toResearch )
            return "+";
        else if ( choice < toResearch )
            return "-";
        else return "=";
    }
    public String randomNumber(){
        // create instance of Random class
        Random rand = new Random();
        int search = rand.nextInt(puissancede10( ) );

        return String.format("%0" + numberPossible + "d",  search );
    }
    public boolean isCombinaisonTrouve(String result ){
        boolean trouve = true;
        String[] resultArray = result.split("");
        for (String value : resultArray )
            if( !value.equals( "=" ) )
                return false;

        return trouve ;

    }
    public int puissancede10( ){
        return (int) Math.pow( 10, numberPossible) ;
    }
}
