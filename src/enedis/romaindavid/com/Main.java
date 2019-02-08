package enedis.romaindavid.com;

import enedis.romaindavid.com.rechercheplusmoins.Challenger;
import enedis.romaindavid.com.rechercheplusmoins.Defender;
import enedis.romaindavid.com.rechercheplusmoins.Dual;


public class Main {

    public static void main(String[] args) {

        Challenger challengerGame = new Challenger();
        //challengerGame.runGame();


        Defender defender = new Defender();
      // defender.runGame();


        Dual dual = new Dual();
        dual.runGame();
    }

}
