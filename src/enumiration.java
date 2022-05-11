import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class enumiration {

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

        int[] data = new int[list.size()]; // Erstellt ein Array mit der Länge der Arraylist
        for (int i = 0; i < data.length; i++) { // Füllt das Array mit den Elementen aus der Arraylist
            data[i] = list.get(i);
        }


        Arrays.sort(data); // sortiert das Array
        System.out.println(Arrays.toString(data)); // gibt das Array aus
        System.out.print(removeDuplicates2(data));// entfernt doppelte Elemente
        System.out.println(Arrays.toString(data)); // gibt das Array aus

        //find(list, 17);

    }

    public static int permutation(ArrayList<Integer> list, int k) {
        int numberofpermutation = 0;
        if (k == list.size() - 1) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
            return 1;
        } else {
            for (int i = k; i < list.size(); i++) {
                swaplist(list, k, i);
                numberofpermutation += permutation(list, k + 1);
                swaplist(list, k, i);
            }
        }
        return numberofpermutation;
    }

    public static void swaplist(ArrayList<Integer> list, int k, int i) {
        int temp = list.get(k);
        list.set(k, list.get(i));
        list.set(i, temp);
    }

    public static int removeDuplicates2(int[] data) {
        int count = 0;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] != data[i + 1]) {
                data[count] = data[i];
                count++;
            }
        }
        data[count] = data[data.length - 1];
        count++;
        return count;
    }

    public static int fakultät(int k) {
        if (k == 0) {
            return 1;
        }
        return k * fakultät(k - 1);
    }

    public static void find(ArrayList<Integer> data, int k) {
        if (data.size() == 1) {
            System.out.println(data.get(0));
        } else {
            int i = (k - 1) / (fakultät(data.size() - 1));
            int kn = k - i * (fakultät(data.size() - 1));
            System.out.print(data.get(i));
            data.remove(i);
            find(data, kn);
        }
    }

}