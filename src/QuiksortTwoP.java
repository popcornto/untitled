import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QuiksortTwoP {
    public static int[] partition(int[] data, int p1, int p2) {
        int temp1;
        int temp2;
        int[] arr = new int[2];
        temp1 = data[p1];
        temp2 = data[p1 + 1];
        for (int i = p1; i < data.length - 1; i++) {                                    //Partitionierung beginnt hier
            if (data[p1] < data[i]) {                                            //Elemente finden die größer als das Pivot sind
                data[p1] = data[i];                                            //
                int j = p1 + 1;
                while (j < i + 1) {                                                //Alles in der While-Schleife ist um die Elemente im Array zu verschieben zu der Stelle die hinter das Pivot gekommen ist
                    data[j] = temp1;                                            //Mit Hilfe von temp1 und temp2 die die nächsten Werte speichern wir das Array verschoben
                    temp1 = temp2;
                    temp2 = data[j + 1];
                    j++;
                }
                p1++;                                                        //Pivot bekommt einen neuen Index indem man l erhöht
                temp1 = data[p1];//Die Temporären Ints werden auf den neuen Index des Pivot angepasst

                temp2 = data[p1 + 1];
            }
        }
        int rw = p2;
        for (int i = 0; i < p2; i++) {                                        //Partitionierung beginnt hier
            if (data[p2] > data[i]) {                                    //Es wird nach Elementen gesucht die kleiner als der Pivot sind
                temp1 = data[rw];
                data[rw] = data[i];
                temp2 = data[rw - 1];
                for (int j = rw - 1; j > i - 1; j--) {                            //Hier wird der Array nach rechts verschoben zu der Stelle die hinter das pivot gekommen ist
                    data[j] = temp1;
                    temp1 = temp2;
                    if (j - 1 >= 1) {
                        temp2 = data[j - 1];
                    }
                }
                p2--;                                                //Index des Pivots wird verschoben da der Pivot verschoben ist

            }
        }
        arr[0] = p1;
        arr[1] = p2;
        return arr;
    }

    public static void ArraytoString(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }

    public static void qsort(int data[], int l, int r) {

        if (l < r) {

            int[] m = partition(data, l, r);
            if (m[0] == l && m[1] == r) {
                if (m[0] + 1 == m[1]) {
                    if (data[l] < data[r]) {
                        int temp = data[l];
                        data[l] = data[r];
                        data[r] = temp;
                    }
                } else {
                    qsort(data, m[0] + 1, m[1] - 1);
                }
            } else {
                qsort(data, l, m[0] - 1);
                qsort(data, m[0] + 1, m[1] - 1);
                qsort(data, m[1] + 1, r);
            }
        }
    }

    public static void qsort(int data[]) {
        qsort(data, 0, data.length - 1);
    }

    public static boolean isSorted(int[] data) {
        for (int i = data.length - 1; 1 < i; i--) { //[1, 2, 3, 4, 5, 6]
            if (data[i] > data[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
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
        int[] data = new int[list.size()];  // erstellt ein Array mit der Länge der Liste
        for (int i = 0; i < list.size(); i++) { // füllt das Array mit den Werten aus der Liste
            data[i] = list.get(i);
        }

        if (isSorted(data)) { // prüft ob das Array sortiert ist und der Methoden durschgang wird gespart
            System.out.println("Array ist schon sortiert");
        } else {
            Instant start = Instant.now();
            if (data.length < 20) {
                System.out.println("Liste vor dem Sortieren:" + Arrays.toString(data));
                qsort(data);
                System.out.println("Liste nach dem Sortieren:" + Arrays.toString(data));
            } else {
                qsort(data);
            }
            assert isSorted(data);
            Instant finish = Instant.now();
            float time = Duration.between(start, finish).toMillis();
            int Min = data[data.length - 1];
            long Max = data[0];
            long Med = 0;
            for (int i = 0; i < data.length; i++) { // *findet den mittelwert in dem man den durchschnitt berechnet der Zahlen die im Array liegen
                Med = data[i] + Med;
            }
            Med = Med / data.length; //*
            System.out.println("Min: " + Min + ", " + "Med: " + Med + ", " + "Max: " + Max);

            System.out.println("Zeit: " + time / 1000 + " Sekunden");
        }
    }
}