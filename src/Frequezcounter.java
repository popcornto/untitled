import java.util.*;

public class Frequezcounter {
    public static int getMin(int[] data) {
        int min = data[0];
        for (int i = 0; i < data.length; i++) {//geht durch das Array und sucht das Minimum
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }

    public static int getMax(int[] data) {
        int max = data[0];
        for (int i = 0; i < data.length; i++) {//geht durch das Array und sucht das Maximum
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    public static int[] countFreqeuncy(int[] A, int min, int max) {
        int[] C = new int[max - min + 1];
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= min && A[i] <= max) {
                C[A[i] - min]++;    //mit jedem Vorkommen von i + min in der Liste A wird C an der Stelle des Wertes von A[i] um 1 erhöht

            }
        }
        return C;
    }

    public static int[] countSort(int[] data) {
        int[] help = countFreqeuncy(data, getMin(data), getMax(data));   //Frequenz der Werte wird in Hilfsarray getan
        int[] out = new int[data.length];                       //Neuen Array anlegen in dem die Sortierten werte abgespeichert werden
        for (int i = 1; i < help.length; i++) {
            help[i] = help[i] + help[i - 1];                    //Schlüssel werden erstellt indem man die werte nacheinander miteinander addiert
        }
        for (int i = 0; i < data.length; i++) {
            out[help[data[i] - getMin(data)] - 1] = data[i];     //Schlüssel werden benutzt um die Stellen zu bestimmen an denen der Wert abgespeichert wird
            help[data[i] - getMin(data)]--; //Schlüssel an der Stelle des Wertes um 1 veringern falls der Wert nochmal vorkommt
        }
        return out;
    }

    public static int exactSelect(int[] data, int k) {
        if (k > getMax(data) - getMin(data) + 1) {                              //k muss kleiner sein als die Anzahl der Werte
            System.out.println("Es gibt nicht so viele Elemente im Array!");
            return Integer.MAX_VALUE;
        }
        boolean[] idk = new boolean[getMax(data) - getMin(data) + 1];
        for (int i = 0; i < data.length; i++) {
            idk[data[i] - getMin(data)] = true;                               //wenn ein Wert in der Liste A enthalten ist, wird idk[i] auf true gesetzt
        }
        int count = 0;
        int r = 0;
        for (int i = 0; i < idk.length; i++) {
            if (k-1 == count) {                                 //for-Schleife um zu bestimmen welcher der k-kleinste Wert ist
                r = i;                                         //Wird gecheckt mit einem counter der immer um 1 steigt wenn der Wert nicht
            }
            if(idk[i]==true) {
                count++;                                      //enthalten ist und der count nicht gleich k ist
            }
        }

        return r+getMin(data);                                  //da r von 0 anfängt und dazu noch das get min addiert werden muss
    }

    public static void main(String[] args) {
        if (args.length != 1) { // die Eingabe wird auf einem einzigen Argument geprüft
            System.out.println("Exactly one arguments are required.");
            return;
        }
        int k; // k is the number of elements in the array
        try {// versucht die Eingabe zu einer Zahl zu konvertieren
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) { // fängt NumberFormatException ab, wenn die Eingabe Integer Wert ist
            System.out.println("The arguments must be integers.");
            return;
        }
        if(k < 0){
            throw new IllegalArgumentException("k  ist negativ");
        }
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
            int[] data = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                data[i] = list.get(i);
            }
            if (k<=0 || k > data.length){
                throw new IllegalArgumentException("k ist nicht gültig");
            }
            int min = getMin(data);
            int max = getMax(data);
            System.out.println("The minimum value is: " + getMin(data));
            System.out.println("The maximum value ist: " + getMax(data));
            System.out.println("Frequencies: " + Arrays.toString(countFreqeuncy(data, min, max)));
            System.out.println("Before sorting: " + Arrays.toString(data));
            System.out.println("After sorting: " + Arrays.toString(countSort(data)));
            System.out.println("Das " + k + "-kleinste Element ist: " + exactSelect(data, k));
        }
    }

}