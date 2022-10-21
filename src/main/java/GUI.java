import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Semaphore;

public class GUI extends JFrame  {
    private JFrame ramka;
    private JPanel panel;
    private static int szerokosc;
    private static int wysokosc;
    private static JLabel[][] jLabels;
    private static Prostokat[][] prostokaty;
    private static Semaphore available = new Semaphore(1, true);

    public GUI() {
        ramka = new JFrame("Symulacja");
        ramka.setSize(700, 700);
        ramka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel();
    }

    public void start(Integer[] wymiarkiki){
        wysokosc = wymiarkiki[0];
        szerokosc = wymiarkiki[1];

        panel.setLayout(new GridLayout(wysokosc, szerokosc));
        jLabels = new JLabel[wysokosc][szerokosc];
        prostokaty = new Prostokat[wysokosc][szerokosc];

        for ( int i = 0; i < wysokosc; i++){
            for (int j = 0; j < szerokosc; j++) {
                jLabels[i][j] = new JLabel();                                    // tworzę tablicę label'ów
//                jLabels[i][j].setText(Integer.toString(j+i));
                jLabels[i][j].setSize(700 / wysokosc, 700 / szerokosc);
                jLabels[i][j].setOpaque(true);
                Prostokat watek = new Prostokat(i,j);            //dodaję wątki do label'ów
                prostokaty[i][j] = watek;
                watek.setName(String.valueOf(i+j));
                panel.add(jLabels[i][j]);
                JLabel temp = jLabels[i][j];
                int finalI = i;
                int finalJ = j;
                temp.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Prostokat prostokat = GUI.prostokaty[finalI][finalJ];
                        prostokat.setZatrzymaj(!prostokat.isZatrzymaj());

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
                panel.updateUI();
            }
        }
        for ( int i = 0; i < wysokosc; i++) {
            for (int j = 0; j < szerokosc; j++) {
                prostokaty[i][j].start();
            }
        }


        ramka.add(panel);
        ramka.setResizable(false);
    }
    public JFrame getRamka() {
        ramka.add(panel);
        return ramka;
    }

    public static JLabel[][] getjLabels() {
        return jLabels;
    }

    public static Semaphore getAvailable() {
        return available;
    }

    public static int getSzerokosc() {
        return szerokosc;
    }

    public static int getWysokosc() {
        return wysokosc;
    }

    public static Prostokat[][] getProstokaty() {
        return prostokaty;
    }
}




//            try {
//                Thread.sleep((long) szybkoscDzialania * (int) randomiser.losowanie(1)[1]);
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

//            double[] kolor = randomiser.losowanie(3);
//            Color tlo = new Color((int) kolor[0], (int) kolor[1], (int) kolor[2]);
//            label.setBackground(tlo);
//            thread.start();

