package enedis.romaindavid.com.game;




public class CombinaisonResult implements Combinaison {
    private String combinaisonNumber;
    private String combinaisonSecret;



    public CombinaisonResult( String combinaisonSecret, String combinaisonNumber) {
        this.combinaisonNumber = combinaisonNumber;
        this.combinaisonSecret = combinaisonSecret;
    }

    @Override
    public String getCombinaisonNumber() {
        return combinaisonNumber;
    }



    @Override
    public String getCombinaisonSecret() {
        return combinaisonSecret;
    }




}
