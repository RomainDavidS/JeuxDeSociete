package enedis.romaindavid.com.rechercheplusmoins;

import enedis.romaindavid.com.params.Parameters;

import java.util.InputMismatchException;

import java.util.Scanner;

import static enedis.romaindavid.com.algorithmes.Plugins.*;
import static enedis.romaindavid.com.params.Parameters.*;


public class Challenger extends Game {
    Scanner sc = new Scanner( System.in );
    GameCombinaison gameCombinaison = new GameCombinaison();
    private int trial = 0 ;

    public void runGame()  {

        setSecretNumberPC( generateRandomString() );
        gameCombinaison.setCombinaisonSecret( getSecretNumberPC() );
        if ( getModeGame().equals( "dev" ) )
            System.out.println("(Combinaison secrète : "+  getSecretNumberPC() + ")" );

        proposition();
    }
    private void proposition(){
        trial++;

        try {

            System.out.println("Essai n°" +trial +" : Veuillez saisir une combinaison");
            int seizure = sc.nextInt() ;

            if( toStr( seizure ).length() <= Parameters.getNumberCasePossible() )
                setCombinaisonNumberPlayer(formatNumber( seizure ) );
            else{
                trial --;
                System.out.println("La longueur de la combinaison doit avoir une longueur mas de "+Parameters.getNumberCasePossible() + " maximum.");
                proposition();
            }

        }catch (InputMismatchException e){
            trial --;
            // On contrôle que le format est correct
            System.out.println( "Erreur de format. Veuillez saisir une valeur numérique" );
            sc.nextLine();
            proposition();
        }

        gameCombinaison.setCombinaisonNumber( getCombinaisonNumberPlayer() );

        String result = gameCombinaison.resultCombinaison() ;
        System.out.println("Proposition n° " + trial + " : " + getCombinaisonNumberPlayer() + " -> Réponse : " + result );

            if (!isCombinaisonTrouve(result))
                if(!(trial == Parameters.getNumberTrialPossible()) )
                    proposition();
                else
                    System.out.println( "Désolé la combinaison secrète n'a pas été trouvée." );
            else
                System.out.println( "Félicitation la combinaison secrète a été trouvée en "+ trial +" essais." );


    }




}
