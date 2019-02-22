package enedis.romaindavid.com.game;

public class ModeGame implements Mode {


    /**
     * Starts games in Challenger mode
     * @param game 2 class names possible Mastermind or RecherchePlusMoins
     */
    @Override
    public void challenger(Game game) {
        game.challenger();
    }

    /**
     * Starts games in Challenger mode
     * @param game 2 class names possible Mastermind or RecherchePlusMoins
     */
    @Override
    public void defender(Game game) {
        game.defender();
    }

    /**
     * Starts games in Challenger mode
     * @param game 2 class names possible Mastermind or RecherchePlusMoins
     */
    @Override
    public void dual(Game game) {
        game.dual();
    }
}
