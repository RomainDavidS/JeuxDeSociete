package enedis.romaindavid.com.game;

public interface Mode {

    /**
     * for each game we must have a challenger mode
     * @param game the chosen game either Mastermind or RecherchePlusMoins
     */
    void challenger(Game game);

    /**
     * for each game we must have a defender mode
     * @param game the chosen game either Mastermind or RecherchePlusMoins
     */
    void defender(Game game);

    /**
     * for each game we must have a dual mode
     * @param game the chosen game either Mastermind or RecherchePlusMoins
     */
    void dual(Game game);
}
