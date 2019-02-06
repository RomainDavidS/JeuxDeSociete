package enedis.romaindavid.com.algorithmes;

import java.util.Random;
import java.util.Scanner;

public class Combinaison {
    Scanner sc = new Scanner( System.in );

    String secretNumber ;
    private static final int numberPossible = 4;

    public Combinaison() {   }

    public void runGame()  {
         secretNumber = randomNumber( ) ;
        System.out.println("(Combinaison secrète : "+  secretNumber + ")" );
        proposition();

    }
    public void proposition(){

        System.out.println("Veuillez saisir votre combinaison");

        String choice = String.format("%0" + numberPossible + "d",  sc.nextInt());
        String result = searchFull( choice, secretNumber );

        System.out.println("Proposition : " + choice + " -> Réponse : " + result);

        if(!isCombinaisonTrouve( result ) )
            proposition();
        else
            System.out.println("Félicitation la combinaison secrète a été trouvée.");
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
