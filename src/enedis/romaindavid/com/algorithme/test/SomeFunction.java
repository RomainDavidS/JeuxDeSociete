package enedis.romaindavid.com.algorithme.test;





import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.*;


import java.util.*;
import java.util.stream.Collectors;

public class SomeFunction {
   static int  present = 0 ;
   static int bp = 0 ;
   static int mp = 0 ;
   static String secretNumberPC;
   static int trialPC = 0;

    /**
     * rouge, jaune, bleu, orange, vert, blanc, violet, rose
     */
    /**
     *  Scénario combinaisonPC
     *
     * 1) recherche des présents
     *  - générer les combinaisons de chiffres identiques => Random pour l'ordre
     *  - quand le nombre de chiffre présent est trouvé on passe au point 2
     *
     * 2) recherche des places
     *  - pour chaque chiffres on recherche la bonne places de présents avec des non présents => RAndom pour la place
     *  -
     * saisir la combinaison trouvé
     *
     *
     */

    public static void main(String[] args) {

        generateCombi();

    }
    public static String generateRandomString(){
        int randNumber = generateRandom( puissanceDe10( getNumberCasePossible() )) ;
        return formatNumber( randNumber );
    }
    private static void generateCombi(){
        int totalPresent = 0;
        secretNumberPC = generateRandomString() ;
        System.out.println("Secret " + secretNumberPC);

        String order = generateOrder( 10);

        String[] orderArray = order.split("");
        HashMap<String,Integer> mapPresent = new HashMap();

        for (String o : orderArray ) {

            String combinaisonS = generateCombinaisonEqual( o );
            search(secretNumberPC , combinaisonS );
            mapPresent.put( o, present );
            totalPresent += present;
            if(totalPresent == getNumberCasePossible() )
                break;

        }
        // Recherche un valeur qui n'est pas présent
        String valueNoPresent = "";

        for ( Map.Entry< String, Integer> entry : mapPresent.entrySet() ) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if( value == 0 ){
                valueNoPresent = key ;
                break;
            }
        }
        System.out.println("Value no présent  " + valueNoPresent);
        Map <Integer,String> mapValuePresent = new HashMap<Integer,String>();

        int testBp = 0;

        for (int i = 0 ; i < getNumberCasePossible(); i++ )
            mapValuePresent.put( i , valueNoPresent );

        System.out.println("Avant le tri: "+ mapPresent);

       mapPresent =  triAvecValeur( mapPresent );
        System.out.println("Après le tri: "+mapPresent);
        for ( Map.Entry<String,Integer > entry : mapPresent.entrySet() ) {
            String key = entry.getKey() ;
            Integer value = entry.getValue();

            if( value != 0 ) {

                System.out.println("Key : " + key + " - value : " + value);

                order = generateOrder( getNumberCasePossible() );

                System.out.println("order : " + order);

                orderArray = order.split("");

                int beforeBp = 0;

                int t = 0;

                for (String o : orderArray ) {

                    if ( mapValuePresent.get( toInt( o ) ).equals( valueNoPresent) ) {
                        System.out.println("Test : " + t++ );
                        mapValuePresent.replace( toInt(o), key);
                        String combinaisonTest = concatMap( mapValuePresent );

                        search( secretNumberPC, combinaisonTest); // trialPC ++


                        if ( mp == 1 )
                            mapValuePresent.replace(toInt(o), valueNoPresent);
                        else{
                            beforeBp++;
                            testBp++;


                            if( beforeBp == value )
                                break;
                        }
                    }
                }
                //
            }
            if( testBp == getNumberCasePossible() )
                break;
            //
        }
        System.out.println("Secret : " + secretNumberPC + " - Combinaison trouve en "+ trialPC + " essais : " + concatMap( mapValuePresent) );
    }
    private static String concatMap(  Map <Integer,String>  mapEntry ){
        String map = "";
        for ( Map.Entry<Integer,String > entry : mapEntry.entrySet() )
            map += entry.getValue();

        return map;
    }
    private static String generateCombinaisonEqual(String figure){
        String combinaison="";
        for (int i = 0 ; i < getNumberCasePossible() ; i++ )
            combinaison += figure;


        return combinaison;
    }

    private static String generateOrder( int limit){
        String order = "";

        for( int i = 1 ; i <= limit ; i++ ) {

            do{
                int rand = generateRandom( limit );
                if( !order.contains( toStr( rand ) ) )
                    order += toStr( rand );

            }while( order.length() < i  );



        }
        return order ;


    }

    protected static void search(String combinaison, String saisie){
        present = 0;
        bp = 0 ;
        mp = 0 ;
        trialPC++;

        String[] cArray = combinaison.split("");
        String[] sArray = saisie.split("");

        Map< Integer,Integer > occurenceC = new HashMap< Integer, Integer>();
        Map< Integer,Integer > occurenceS = new HashMap< Integer, Integer>();

        Map< Integer,Integer > occurenceB = new HashMap< Integer, Integer>();
        Map< Integer,Integer > occurenceP = new HashMap< Integer, Integer>();

        for(int i = 0 ; i < 10; i++){
            occurenceC.put( i  , 0 );
            occurenceS.put( i  , 0 );
            occurenceB.put( i  , 0 );
            occurenceP.put( i  , 0 );
        }

        for (String  c  : cArray )
            occurenceC.replace(toInt(c), occurenceC.get(toInt(c)) + 1);

        int n = 0;
        for (String s : sArray ) {

            if ( s.equals(cArray[ n++ ] ) )
                occurenceB.replace(toInt(s), occurenceB.get(toInt(s)) + 1);

            occurenceS.replace(toInt(s), occurenceS.get(toInt(s)) + 1);
        }



        for(int i = 0; i < 10; i++ ){

            if( occurenceS.get( i )  < occurenceC.get( i ) ) {
                occurenceP.replace(i, occurenceS.get(i) );
                present += occurenceS.get(i);
            } else {
                occurenceP.replace(i, occurenceC.get(i));
                present += occurenceC.get(i);
            }
        }



        for(int i = 0; i < 10; i++ )
            if( occurenceB.get( i ) >=  occurenceP.get( i ) )
                bp += occurenceP.get(i);
            else
                bp += occurenceB.get(i);

         mp = present - bp ;

        System.out.println( " Présent : "+ present + " : bien placé = " + bp + " : mal placé = " + mp  );




    }

}
