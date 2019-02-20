package enedis.romaindavid.com.param;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static enedis.romaindavid.com.algorithme.Plugin.toInt;

public class Parameter {
    public static String modeDebug ="";
    private static int numberCasePossible;
    private static int baseNumberPossible;
    private static int numberTrialPossible ;
    private static String valueNoPresent;


    public static void readingProperties(){
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("resources/config.properties");
            prop.load(input);
            numberCasePossible = toInt( prop.getProperty( "numberCasePossible" ) );
            baseNumberPossible = toInt( prop.getProperty("baseNumberPossible") );
            numberTrialPossible = toInt( prop.getProperty("numberTrialPossible") );
            valueNoPresent = prop.getProperty("valueNoPresent");
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


    public static String getModeDebug() {
        return modeDebug;
    }

    public static void setModeDebug(String modeDebug) {
        Parameter.modeDebug = modeDebug;
    }

    public static int getNumberCasePossible() {
        return numberCasePossible;
    }

    public static void setNumberCasePossible(int numberCasePossible) {
        Parameter.numberCasePossible = numberCasePossible;
    }

    public static int getNumberTrialPossible() {
        return numberTrialPossible;
    }

    public static void setNumberTrialPossible(int numberTrialPossible) {
        Parameter.numberTrialPossible = numberTrialPossible;
    }

    public static int getBaseNumberPossible() {
        return baseNumberPossible;
    }

    public static void setBaseNumberPossible(int baseNumberPossible) {
        Parameter.baseNumberPossible = baseNumberPossible;
    }

    public static String getValueNoPresent() {
        return valueNoPresent;
    }

    public static void setValueNoPresent(String valueNoPresent) {
        Parameter.valueNoPresent = valueNoPresent;
    }
}
