package enedis.romaindavid.com.game;

public class ModeGame implements Mode {

    @Override
    public void rumGame(Game game) {
      game.runGame();
    }
}
