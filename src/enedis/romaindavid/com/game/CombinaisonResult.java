package enedis.romaindavid.com.game;


import static enedis.romaindavid.com.game.rechercheplusmoins.RecherchePlusMoins.combinaisonResult;

public class CombinaisonResult implements Combinaison {
    String combinaisonNumber;
    String combinaisonSecret;

    @Override
    public String getCombinaisonNumber() {
        return combinaisonNumber;
    }

    public void setCombinaisonNumber(String combinaisonNumber) {
        this.combinaisonNumber = combinaisonNumber;
    }

    @Override
    public String getCombinaisonSecret() {
        return combinaisonSecret;
    }

    public void setCombinaisonSecret(String combinaisonSecret) {
        this.combinaisonSecret = combinaisonSecret;
    }

    @Override
    public String resultCombinaison() {

        return combinaisonResult( getCombinaisonNumber(), getCombinaisonSecret() );
    }
}
