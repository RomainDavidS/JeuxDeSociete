package enedis.romaindavid.com.rechercheplusmoins;

import java.util.*;

import static enedis.romaindavid.com.algorithme.Plugins.*;
import static enedis.romaindavid.com.param.Parameter.*;

public class Defender extends Game {
    Scanner sc = new Scanner( System.in );
    GameCombinaison gameCombinaison = new GameCombinaison();
    int trial = 0;

    Map<Integer,String> mapPossible = new HashMap<Integer,String>();
    String result ;



    public void runGame()  {

        initMapPossible();
        initSecretNumberPlayer();

        if ( getModeGame().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  getSecretNumberPlayer() + ")" );

        setCombinaisonNumberPC( generateRandomString() );

        proposition();

    }

    private void initSecretNumberPlayer(){
        try{
            System.out.println("Joueur : Veuillez saisir votre combinaison secrète");
            int seizure = sc.nextInt();

            if( !isSeizureGoodLength( seizure ) ){
                System.out.println("La combinaison secrète doit avoir une longueur de "+ getNumberCasePossible() + " chiffres maximum.");
                initSecretNumberPlayer();
            }

            setSecretNumberPlayer( formatNumber( seizure) );

            gameCombinaison.setCombinaisonSecret( getSecretNumberPlayer() );

        }catch (InputMismatchException e){
            System.out.println( "Erreur de format. Veuillez saisir une valeur numérique" );
            sc.nextLine();
            initSecretNumberPlayer();
        }
    }

    private void initMapPossible(){
        for ( int i=0; i < getNumberCasePossible() ; i++ )
            mapPossible.put( i, "0123456789");
    }



        private void proposition(){
            trial++;
            gameCombinaison.setCombinaisonNumber( getCombinaisonNumberPC() );
            System.out.println("M l'ordinateur choisi la combinaison " + getCombinaisonNumberPC() );

            result = gameCombinaison.resultCombinaison() ;
            System.out.println("Proposition n° " + trial + " : " + getCombinaisonNumberPC() + " -> Réponse : " + result );

            if(!isCombinaisonTrouve( result ) ) {
                generateCombinaisonPC();

                proposition();
            }else
                System.out.println( "L'ordinateur a trouvé le combinaison en "+ trial +" essais.");
        }

        private void generateCombinaisonPC(){
            String newCombinaisonPC = "";
            String[] combinaisonNumberPcArray = getCombinaisonNumberPC().split("");
            String[] resultArray = result.split("");

            for(int i = 0; i < getNumberCasePossible();i++)
                newCombinaisonPC += newPcCombinaison( i, combinaisonNumberPcArray[ i ], resultArray[ i ]  );

            setCombinaisonNumberPC( newCombinaisonPC );
        }
    public String newPcCombinaison(int index,String combinaisonNumber , String result  ){

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




}
