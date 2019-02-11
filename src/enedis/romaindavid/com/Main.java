package enedis.romaindavid.com;


import enedis.romaindavid.com.game.*;

import static enedis.romaindavid.com.param.Parameter.*;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        setModeDebug( args[0] );
        System.out.println("Bonjour et bienvenue dans vos jeux de société.");

        menu.menuGame();
    }



}
