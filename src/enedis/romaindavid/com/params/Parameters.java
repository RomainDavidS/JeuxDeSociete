package enedis.romaindavid.com.params;

public class Parameters {

    private static String modeGame ="dev";
    private  static int numberCasePossible = 4;
    private static  int numberTrialPossible = 20;

    public static String getModeGame() {
        return modeGame;
    }

    public static void setModeGame(String modeGame) {
        Parameters.modeGame = modeGame;
    }

    public static int getNumberCasePossible() {
        return numberCasePossible;
    }

    public static void setNumberCasePossible(int numberCasePossible) {
        Parameters.numberCasePossible = numberCasePossible;
    }

    public static int getNumberTrialPossible() {
        return numberTrialPossible;
    }

    public static void setNumberTrialPossible(int numberTrialPossible) {
        Parameters.numberTrialPossible = numberTrialPossible;
    }
}
