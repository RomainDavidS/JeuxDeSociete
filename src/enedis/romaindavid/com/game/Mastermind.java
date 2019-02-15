package enedis.romaindavid.com.game;


import static enedis.romaindavid.com.algorithme.Plugin.*;
import static enedis.romaindavid.com.param.Parameter.getBaseNumberPossible;
import static enedis.romaindavid.com.param.Parameter.getNumberCasePossible;

public class Mastermind extends Game {


    @Override
    String generateRandomString() {
        String generate = "";
        int randNumber = 0;
        for (int i = 0; i< getNumberCasePossible(); i++){
            randNumber = generateRandom( getBaseNumberPossible() );
            generate += formatNumber( randNumber );
        }

        return generate;
    }

    @Override
    void generateCombinaisonPC() {

    }

    @Override
    String combinaisonResult(CombinaisonResult combinaison) {
        return null;
    }

    @Override
    boolean isCombinaisonTrouve(String postResult) {
        return false;
    }

    public Mastermind() {
        gameName = "Mastermind";
    }
















}
