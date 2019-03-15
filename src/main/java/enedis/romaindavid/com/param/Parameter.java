package enedis.romaindavid.com.param;

import enedis.romaindavid.com.algorithme.Plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Set of different settings of games
 */
public final class Parameter {

    private boolean modeDebug;
    private int numberCasePossible;
    private int baseNumberPossible;
    private int numberTrialPossible ;
    private String valueNoPresent;

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
        numberCasePossible = Plugin.toInt( prop.getProperty( "numberCasePossible" ) );
        baseNumberPossible = Plugin.toInt( prop.getProperty("baseNumberPossible") );
        numberTrialPossible = Plugin.toInt( prop.getProperty("numberTrialPossible") );
        valueNoPresent = prop.getProperty("valueNoPresent");
    }

    public void setModeDebug(boolean modeDebug) {
        this.modeDebug = modeDebug;
    }

    public boolean isModeDebug() {
        return modeDebug;
    }

    public int getNumberCasePossible() {
        return numberCasePossible;
    }

    public int getBaseNumberPossible() {
        return baseNumberPossible;
    }

    public int getNumberTrialPossible() {
        return numberTrialPossible;
    }

    public String getValueNoPresent() {
        return valueNoPresent;
    }
}
