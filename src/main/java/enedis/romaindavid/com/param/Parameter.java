package enedis.romaindavid.com.param;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import static enedis.romaindavid.com.algorithme.Plugin.toInt;

/**
 * Set of different settings of games
 */
public final class Parameter {

    private static boolean modeDebug;
    private static int numberCasePossible;
    private static int baseNumberPossible;
    private static int numberTrialPossible ;
    private static String valueNoPresent;

    private Properties prop;
    private InputStream input = null;


    private static Parameter singleton = new Parameter();

    public static Parameter getInstance()
    {
        return singleton;
    }

    private static class Holder {
        private static final AtomicInteger parameter = new AtomicInteger();
    }

    private Parameter() {
        prop = new Properties();
        readingConfigFile();
    }

    public static int getCounter(){
        return Holder.parameter.get();
    }

    public static int increment(){
        return Holder.parameter.incrementAndGet();
    }

    /**
     * recovery of the parameters passed in config.properties
     */
    private void readingConfigFile(){
        try {
            input =getClass().getClassLoader().getResourceAsStream("config.properties");
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
        modeDebug = Boolean.valueOf( prop.getProperty( "modeDebug" ) );
        numberCasePossible = toInt( prop.getProperty( "numberCasePossible" ) );
        baseNumberPossible = toInt( prop.getProperty("baseNumberPossible") );
        numberTrialPossible = toInt( prop.getProperty("numberTrialPossible") );
        valueNoPresent = prop.getProperty("valueNoPresent");
    }

    public static void setModeDebug(boolean modeDebug) {
        Parameter.modeDebug = modeDebug;
    }

    public static boolean isModeDebug() {
        return modeDebug;
    }

    public static int getNumberCasePossible() {
        return numberCasePossible;
    }

    public static int getBaseNumberPossible() {
        return baseNumberPossible;
    }

    public static int getNumberTrialPossible() {
        return numberTrialPossible;
    }

    public static String getValueNoPresent() {
        return valueNoPresent;
    }
}
