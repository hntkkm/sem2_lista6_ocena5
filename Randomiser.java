import java.util.Random;

public class Randomiser {
    private static Random losuj = new Random();

    public static double[] losowanie(int ileLiczb, int max){
        double[] tablica = new double[ileLiczb];
        for (int i = 0; i < tablica.length; i++){
            tablica[i] = losuj.nextDouble(max);
        }
        return tablica;
    }
}
