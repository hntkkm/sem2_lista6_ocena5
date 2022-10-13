import java.awt.*;

public class Prostokat extends Thread{
    private int wysokosc, szerokosc;
    private boolean zatrzymaj;

    public Prostokat(int wysokosc, int szerokosc){
        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
        zatrzymaj = false;
        losowanieKoloru();  //tu jest mala zmiana
    }

    private void losowanieKoloru(){
        double[] kolor = Randomiser.losowanie(3, 255);
        GUI.getjLabels()[wysokosc][szerokosc].setBackground(new Color((int) kolor[0], (int) kolor[1], (int) kolor[2]));
    }

    public void run(){

        while (true) {
            while (!zatrzymaj) {
                synchronized (this) {
                    double naIleSleep = Randomiser.losowanie(1, Symulacja.szybkoscDzialania)[0];
                    double coRobic = Randomiser.losowanie(1, 1)[0];
                    try {
                        Thread.sleep((long) naIleSleep + (Symulacja.szybkoscDzialania / 2));

                        if (Symulacja.prawdopodobienstwo > coRobic) {
                            losowanieKoloru();

                        } else {
                            GUI.getAvailable().acquire();
                            GUI.getjLabels()[wysokosc][szerokosc].setBackground(sredniKolorSasiadow());
                            GUI.getAvailable().release();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Color sredniKolorSasiadow() {
        float[] kolorSasiadaUp = new float[3];
        float[] kolorSasiadaDown = new float[3];
        float[] kolorSasiadaLeft = new float[3];
        float[] kolorSasiadaRight = new float[3];
        int up = (wysokosc + 1 + GUI.getWysokosc()) % GUI.getWysokosc();
        int down = (wysokosc - 1 + GUI.getWysokosc()) % GUI.getWysokosc();
        int right = (szerokosc + 1 + GUI.getSzerokosc()) % GUI.getSzerokosc();
        int left = (szerokosc - 1 + GUI.getSzerokosc()) % GUI.getSzerokosc();
        int liczbaSasiadow = 0;

        if (!GUI.getProstokaty()[up][szerokosc].isZatrzymaj()) {
            GUI.getjLabels()[up][szerokosc].getBackground().getRGBColorComponents(kolorSasiadaUp);
            liczbaSasiadow++;
        }
        if (!GUI.getProstokaty()[down][szerokosc].isZatrzymaj()) {
            GUI.getjLabels()[down][szerokosc].getBackground().getRGBColorComponents(kolorSasiadaDown);
            liczbaSasiadow++;
        }
        if (!GUI.getProstokaty()[wysokosc][right].isZatrzymaj()) {
            GUI.getjLabels()[wysokosc][right].getBackground().getRGBColorComponents(kolorSasiadaRight);
            liczbaSasiadow++;
        }
        if (!GUI.getProstokaty()[wysokosc][left].isZatrzymaj()) {
            GUI.getjLabels()[wysokosc][left].getBackground().getRGBColorComponents(kolorSasiadaLeft);
            liczbaSasiadow++;
        }
        if (liczbaSasiadow != 0) {
            return new Color((kolorSasiadaUp[0] + kolorSasiadaDown[0] + kolorSasiadaLeft[0] + kolorSasiadaRight[0])/liczbaSasiadow,
                    (kolorSasiadaUp[1] + kolorSasiadaDown[1] + kolorSasiadaLeft[1] + kolorSasiadaRight[1])/liczbaSasiadow,
                    (kolorSasiadaUp[2] + kolorSasiadaDown[2] + kolorSasiadaLeft[2] + kolorSasiadaRight[2])/liczbaSasiadow);

        } else {
            return GUI.getjLabels()[wysokosc][szerokosc].getBackground();
        }
    }

    public boolean isZatrzymaj() {
        return zatrzymaj;
    }

    public void setZatrzymaj(boolean zatrzymaj) {
        this.zatrzymaj = zatrzymaj;
    }
}