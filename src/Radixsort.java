import java.util.*;

public class Radixsort {
    private static int digit(int a, int b) {

        return (a / (int) Math.pow(256, b)) % 256;  //Mit dieser Methode wird der jeweilige Byte herausgefunden
    }


    /*private static int[] radixSort(int[] data) {
        int[] output = new int[data.length];
        int[] count = new int[256];
        System.out.println(Arrays.toString(count));
        for (int i = 0; i < data.length; i++) {
            count[digit(data[i], 0)]++;
        }
        System.out.println(Arrays.toString(count));
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }
        System.out.println(Arrays.toString(count));
        for (int i = data.length - 1; i >= 0; i--) {
            output[count[digit(data[i], 0)] - 1] = data[i];
            count[digit(data[i], 0)]--;
        }
        System.out.println(Arrays.toString(count));
        ;
        for (int i = 0; i < data.length; i++) {
            data[i] = output[i];
        }
        return data;
    }
*/
    private static void sortByByte(int[] data, int l, int r, int b) {
        int[] count = new int[256];                                     //Freuquenzarray wird zum sortieren benötigt
        int[] helpArray = new int[r - l + 1];                           //Im Hilfsarray speichern wir die Werte die wir sortieren wollen
        for (int i = l; i <= r; i++) {
            count[255 - digit(data[i], b)]++;
            //System.out.println(digit(data[i], b));                      //Frequenzarray erstrellen von den Bytes
        }
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];                                   //Keys bekommen
        }
        for (int i = r; i >= l; i--) {                                  //Countingsort wird hier angewendet
            helpArray[count[255 - digit(data[i], b)] - 1] = data[i];     //Hier wird sortiert und abgespeichert im Hilfsarray mithilfe des Frequenzarrays
            count[255 - digit(data[i], b)]--;
        }
        for (int i = 0; i < helpArray.length; i++) {
            data[i + l] = helpArray[i];                                 //kopieren der sortierten Zahlen in den Array
        }
        //System.out.println(Arrays.toString(data));
    }

    public static void lsdRadix(int[] data) {
        for (int i = 0; i <= 3; i++) {                                //sortByByte wird 4 mal aufgerufen um den Array zu sortieren
            sortByByte(data, 0, data.length - 1, i);            //Es fängt mit 0 an für den leas-significant-byte
        }
    }


    public static int[] sort(int[] data) {
        int temp;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] < data[j]) {
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }

            }
        }
        return data;
    }

    public static boolean isSorted(int[] data) {             //isSorted checht ob die Bytes an jeder Stelle b richtig sortiert sind
        for (int i = 3; i > -1; i--) {
            for (int j = 0; j < data.length - 1; j++) {
                if (digit(data[j], i) > digit(data[j + 1], i)) {
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSortedByByte(int[] data, int b) {
        for (int j = 0; j < data.length - 1; j++) {
            if (digit(data[j], b) > digit(data[j + 1], b)) {
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        if (args.length != 3) {      //l, r, b müssen eingegeben werden
            System.out.println("Exactly three arguments are required.");// die Eingabe wird auf 3 Argumente geprüft
            return;
        }
        int k = 0; // k is the number of elements in the array
        try {// versucht die Eingabe zu einer Zahl zu konvertieren
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) { // fängt NumberFormatException ab, wenn die Eingabe Integer Wert ist
            System.out.println("The arguments must be integers.");
            return;
        }
        if (Integer.parseInt(args[1]) < Integer.parseInt(args[0])) {            //Fehlermeldung wenn l>r ist
            System.out.println("l kann nicht größer als r sein!");
        } else {
            if (Integer.parseInt(args[2]) < 0 || Integer.parseInt(args[2]) > 3) {   //b darf nur zwischen 0-3 sein
                System.out.println("b ist falsch");
            } else {
                Scanner scanner = new Scanner(System.in);
                ArrayList<Integer> list = new ArrayList<>();
                try {
                    while (scanner.hasNextLine()) { // liest die eingabe ein
                        String input = scanner.nextLine();
                        if (input.equals("")) { // wenn die eingabe leer ist, wird die ausgabe erstellt und geht aus der schleife raus
                            break;
                        }
                        list.add(Integer.parseInt(input)); // addiert die eingabe zur liste
                    }
                } catch (NumberFormatException e) { // fängt NumberFormatException ab, wenn die Eingabe Integer Wert ist
                    System.err.println("Der Input was kein Integer Wert.");
                    return;
                }
                if (list.size() > 0) {
                    if (list.size() < Integer.parseInt(args[1]) + 1 || Integer.parseInt(args[0]) < 0) {
                        System.out.println("l order r out of bounds!");         //l ist kleiner als 0 oder r ist größer als der Array
                    } else {
                        int[] data = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            data[i] = list.get(i);
                        }
                        System.out.println(Arrays.toString(data));
                        long startTime = System.nanoTime();
                        //sortByByte(data,Integer.parseInt(args[0]),Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                        lsdRadix(data);
                        long endTime = System.nanoTime();
                        assert isSorted(data);
                        //assert isSortedByByte(data,Integer.parseInt(args[2]));
                        System.out.println("Output: " + Arrays.toString(data));
                        System.out.println("Zeit von lsd: " + (endTime - startTime) + " Nanosekunden");
                    }
                } else {
                    System.out.println("Das Array ist leer!");

                }
            }
        }
    }
}


