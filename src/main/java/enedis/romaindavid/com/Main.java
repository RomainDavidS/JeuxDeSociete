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
        Parameter.getCounter();
        Title.getCounter();

        Menu menu = new Menu();
        try{
            if( args[0].equals("dev") )
                Parameter.setModeDebug( true );
        }
        catch (ArrayIndexOutOfBoundsException e ){}

        if ( Parameter.isModeDebug() )
            Title.postTitleDebug();
        else
            Title.postTitlePlayer();

        Title.mainTitle();

        menu.menuGame();
    }



}
