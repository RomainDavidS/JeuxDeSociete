package enedis.romaindavid.com.game;

public interface Mode {

    void challenger(Game game);
    void defender(Game game);
    void dual(Game game);
}
