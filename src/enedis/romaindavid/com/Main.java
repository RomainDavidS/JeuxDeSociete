package enedis.romaindavid.com;

import enedis.romaindavid.com.algorithmes.Challenger;
import enedis.romaindavid.com.algorithmes.Duel;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Challenger combinaison = new Challenger();
       // combinaison.runGame();
        Duel duel = new Duel();
        duel.runGame();


    }
}
