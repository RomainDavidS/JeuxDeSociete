package enedis.romaindavid.com.param;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static enedis.romaindavid.com.algorithme.Plugin.toInt;

/**
 * Set of different settings of games
 */
public class Parameter {

    public static boolean modeDebug = false;
    private static int numberCasePossible;
    private static int baseNumberPossible;
    private static int numberTrialPossible ;
    private static String valueNoPresent;

    private Properties prop;
    InputStream input;

    public Parameter() {
        prop = new Properties();
        input = null;
        readingConfigFile();
    }

    /**
     * recovery of the parameters passed in config.properties
     */
    private void readingConfigFile(){

        try {
            input = new FileInputStream("src/resources/config.properties");
            prop.load( input );
            readingGeneralProperty();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * recovery of the general parameters passed in config.properties
     */
    private void readingGeneralProperty(){
        numberCasePossible = toInt( prop.getProperty( "numberCasePossible" ) );
        baseNumberPossible = toInt( prop.getProperty("baseNumberPossible") );
        numberTrialPossible = toInt( prop.getProperty("numberTrialPossible") );
        valueNoPresent = prop.getProperty("valueNoPresent");
    }

    /**
     * recovery of the debug parameter passed in config.properties
     */
    public void readingDebugProperty(){
        modeDebug = Boolean.valueOf( prop.getProperty( "modeDebug" ) );
    }

    public static boolean getModeDebug() {
        return modeDebug;
    }

    public static void setModeDebug(boolean modeDebug) {
        Parameter.modeDebug = modeDebug;
    }

    public static int getNumberCasePossible() {
        return numberCasePossible;
    }

    public static int getNumberTrialPossible() {
        return numberTrialPossible;
    }

    public static int getBaseNumberPossible() {
        return baseNumberPossible;
    }

    public static String getValueNoPresent() {
        return valueNoPresent;
    }
}
