package enedis.romaindavid.com;

import enedis.romaindavid.com.algorithme.Plugin;
import enedis.romaindavid.com.game.*;
import enedis.romaindavid.com.param.*;


import static enedis.romaindavid.com.param.Title.*;
import static enedis.romaindavid.com.param.Parameter.*;

/**
 * Main class
 */
public class Main {

    public static void main(String[] args)  {
        Plugin.clearScreen();
        Parameter parameter = new Parameter();

        Menu menu = new Menu();

        try{
            if( args[0].equals("dev") )
                setModeDebug( true );
        }
        catch (ArrayIndexOutOfBoundsException e ){
            parameter.readingDebugProperty();
        }

        if ( getModeDebug() )
            postTitleDebug();
        else
            postTitlePlayer();

        mainTitle();

        menu.menuGame();
    }



}
