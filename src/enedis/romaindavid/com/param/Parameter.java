package enedis.romaindavid.com.param;

public class Parameter {
    public static String modeDebug ="dev";
    private static int numberCasePossible = 4;
    private static int baseNumberPossible = 8;
    private static int numberTrialPossible = 12;
    private static String valueNoPresent = "9";

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
