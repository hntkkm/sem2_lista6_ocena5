public class Symulacja {
    public static double prawdopodobienstwo;
    public static int szybkoscDzialania;

    public static void main(String[] args) {
        try {
            Integer[] daneWejsciowe = new Integer[2];
            for (int i =0; i<2; i++) {
                daneWejsciowe[i] = Integer.parseInt(args[i]);
                if ( daneWejsciowe[i] <= 0) {
                    System.out.println("Nie da sie symulacji dla danych liczb");
                    return;
                }
            }
            szybkoscDzialania = Integer.parseInt(args[2]);
            prawdopodobienstwo = Double.parseDouble(args[3]);
            GUI gui = new GUI();
            gui.start(daneWejsciowe);       //START
            gui.getRamka().setVisible(true);
        } catch (NumberFormatException e){
            System.out.println("Podaj liczby");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Podaj wiecej danych");
        }
    }
}
// todo exc dla liczb dodatnich
// todo javadoc