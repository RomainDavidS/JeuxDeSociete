package enedis.romaindavid.com.rechercheplusmoins;

import static enedis.romaindavid.com.algorithme.Plugins.*;

public class Game {

    public Game() {}

    private String secretNumberPC;
    private String secretNumberPlayer;

    private String combinaisonNumberPC;
    private String combinaisonNumberPlayer;

    private String player;

    public String getSecretNumberPC() {
        return secretNumberPC;
    }

    public void setSecretNumberPC(String secretNumberPC) {
        this.secretNumberPC = secretNumberPC;
    }

    public String getSecretNumberPlayer() {
        return secretNumberPlayer;
    }

    public void setSecretNumberPlayer(String secretNumberPlayer) {
        this.secretNumberPlayer = secretNumberPlayer;
    }

    public String getCombinaisonNumberPC() {
        return combinaisonNumberPC;
    }

    public void setCombinaisonNumberPC(String combinaisonNumberPC) {
        this.combinaisonNumberPC = combinaisonNumberPC;
    }

    public String getCombinaisonNumberPlayer() {
        return combinaisonNumberPlayer;
    }

    public void setCombinaisonNumberPlayer(String combinaisonNumberPlayer) {
        this.combinaisonNumberPlayer = combinaisonNumberPlayer;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

   static String result(String combinaisonSeizure, String combinaisonSecret){

        String[] combinaisonSeizureArray = combinaisonSeizure.split("");
        String[] combinaisonSecretArray = combinaisonSecret.split("");

        int lenArray = combinaisonSeizureArray.length - 1;

        String resultCombinaison = "";

        for(int i = 0 ; i<= lenArray;i++)
            resultCombinaison += resultOne( toInt( combinaisonSeizureArray[ i ] ),toInt( combinaisonSecretArray[ i ] ) );

        return resultCombinaison;
    }

    static String resultOne( int combinaisonSeizure, int combinaisonSecret ){
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

}
