import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        if (args.length != 1) { // die Eingabe wird auf einem einzigen Argument geprüft
            System.out.println("Exactly one arguments are required.");
            return;
        }
        int k = 0; // k is the number of elements in the array
        try {// versucht die Eingabe zu einer Zahl zu konvertieren
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) { // fängt NumberFormatException ab, wenn die Eingabe Integer Wert ist
            System.out.println("The arguments must be integers.");
            return;
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


        if (list.size() < k) { // Überprüft ob k kleiner als die Anzahl der Elemente ist, da sonst der Index außerhalb des Arrays liegt
            System.out.println("Der Array muss mindestens " + k + " Werte haben. ");
            return;
        } else {
            int[] arr = new int[list.size()]; // Erstellt ein Array mit der Länge der Arraylist
            for (int i = 0; i < arr.length-1 ; i++) { // Füllt das Array mit den Elementen aus der Arraylist
                arr[i] = list.get(i);
            }
            Arrays.sort(arr); // Sortiert das Array, da wir dann mit k den kleinsten element schneller und effizienter suchen können
            System.out.println("Der " + k + "." + " kleinste Wert ist " + arr[k]);
        }
    }
}