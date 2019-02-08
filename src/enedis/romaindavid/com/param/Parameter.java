package enedis.romaindavid.com.param;

public class Parameter {
    private static String modeGame ="dev";
    private static int numberCasePossible = 4;
    private static int numberTrialPossible = 20;

    public static String getModeGame() {
        return modeGame;
    }

    public static void setModeGame(String modeGame) {
        Parameter.modeGame = modeGame;
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
}
