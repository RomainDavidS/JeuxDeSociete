package enedis.romaindavid.com.algorithmes;


import java.util.Scanner;

public class Duel {
    Scanner sc = new Scanner( System.in );

    String secretNumber1 ;
    String secretNumber2 ;
    private static final int numberPossible = 4;

    public void runGame()  {

        System.out.println("Joueur 1 : Veuillez saisir votre combinaison secrète");
        secretNumber1 = String.format("%0" + numberPossible + "d",  sc.nextInt() );

        System.out.println("Joueur 2 : Veuillez saisir votre combinaison secrète");
        secretNumber2 = String.format("%0" + numberPossible + "d",  sc.nextInt() );
        System.out.println("(Combinaison secrète du joueur 1 : "+  secretNumber1 + ")" );
        System.out.println("(Combinaison secrète du joueur 2 : "+  secretNumber2 + ")" );
        proposition( 1);

    }
    private void proposition(int numberPlayer){
        String result;
        System.out.println("Joueur "+ numberPlayer + " : Veuillez saisir une combinaison");

        String choice = String.format("%0" + numberPossible + "d",  sc.nextInt());
        if(numberPlayer == 2)
            result = searchFull( choice, secretNumber1 );
        else
            result = searchFull( choice, secretNumber2 );

        System.out.println( "Joueur "+ numberPlayer + " : Proposition : " + choice + " -> Réponse : " + result);

        if(!isCombinaisonTrouve( result ) )
            proposition(reverseGamer( numberPlayer ) );
        else
            System.out.println( "Félicitation le Joueur "+ numberPlayer + " a trouvé la combinaison du joueur " + reverseGamer( numberPlayer ));
    }
    private int reverseGamer(int numberPlayer ){
        if(numberPlayer == 1 )
            return 2;
        else
            return 1;
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
