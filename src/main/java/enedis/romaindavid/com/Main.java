package enedis.romaindavid.com;

import enedis.romaindavid.com.algorithme.Plugin;
import enedis.romaindavid.com.game.*;
import enedis.romaindavid.com.param.*;

/**
 * Main class
 */
public class Main {

    public static void main(String[] args)  {
        Plugin.clearScreen();
        Parameter params = Parameter.getInstance();
        Title title = Title.getInstance();

        Menu menu = new Menu();
        try{
            if( args[0].equals("dev") )
                params.setModeDebug( true );
        }
        catch (ArrayIndexOutOfBoundsException e ){}

        if ( params.isModeDebug() )
            title.postTitleDev();
        else
            title.postTitlePlayer();

        title.mainTitle();

        menu.menuGame();
    }



}
