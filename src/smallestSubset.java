import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class smallestSubset {
    public static void find(ArrayList<Integer> data, int k) {
        if (data.size() == 1) { //Basis Element für 1 elementige Listen wird als abbruchkriterium benutzt
            System.out.println(data.get(0));
        } else {
            int i = (k - 1) / (fakultät(data.size() - 1));
            int kn = k - i * (fakultät(data.size() - 1));
            System.out.print(data.get(i) + " "); // Ausgabe des ersten Elements
            data.remove(i); //da die kleinste i te  stelle ermittelt worden ist kann man sie aus der liste entfernen, damit es keinen stackoverflow gibt
            find(data, kn); //rekusiver Aufruf für das nächste Element
        }
    }

    public static int fakultät(int k) { //berechnet die Fakultät
        if (k == 0) {
            return 1;
        }
        return k * fakultät(k - 1);
    }

    public static void main(String[] args) {
        if (args.length != 1) { // die Eingabe wird auf einem einzigen Argument geprüft
            System.out.println("Exactly one arguments are required.");
            return;
        }
        int smallest;
        try {// versucht die Eingabe zu einer Zahl zu konvertieren
            smallest = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) { // fängt NumberFormatException ab, wenn die Eingabe Integer Wert ist
            System.out.println("The arguments must be integers.");
            return;
        }
        if (smallest <= 0) {
            throw new IllegalArgumentException("Die kleinste Zahl kann nicht kleiner oder gleich als Null sein");
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
        int[] arr = new int[list.size()]; // Erstellt ein Array mit der Länge der Arraylist
        for (int i = 0; i < arr.length; i++) { // Füllt das Array mit den Elementen aus der Arraylist
            arr[i] = list.get(i);
        }
        Arrays.sort(arr); // Sortiert das Array

        System.out.println("Sorted input: ");
        System.out.println(Arrays.toString(arr));
        System.out.println();
        System.out.println("The " + smallest + "-smallest permutation is: ");
        find(list, smallest);
    }
}
