package enedis.romaindavid.com.game;

public class ModeGame implements Mode {



    @Override
    public void challenger(Game game) {
        game.challenger();
    }

    @Override
    public void defender(Game game) {
        game.defender();
    }

    @Override
    public void dual(Game game) {
        game.dual();
    }
}
