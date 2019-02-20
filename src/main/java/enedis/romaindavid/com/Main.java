package enedis.romaindavid.com;

import enedis.romaindavid.com.game.*;
import static enedis.romaindavid.com.param.Title.*;
import static enedis.romaindavid.com.param.Parameter.*;

public class Main {

    public static void main(String[] args) {
        readingProperties();
        Menu menu = new Menu();
        try {
            if( !args[0].isEmpty() )
                setModeDebug( args[0] );
        }catch ( ArrayIndexOutOfBoundsException e){
        }

        mainTitle();

        menu.menuGame();
    }



}
