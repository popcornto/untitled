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
        Arrays.sort(data);// sortiert das Array
        System.out.println(Arrays.toString(data)); // gibt das Array aus
        System.out.print(removeDuplicates2(data));// entfernt doppelte Elemente
        System.out.println(Arrays.toString(data)); // gibt das Array aus
    }


    public static int removeDuplicates2(int[] data) {
        int count = 0;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] == data[i + 1]) {
                data[i] = 0;
                count++;
            }
        }
        Arrays.sort(data);
        int middle = data.length - count;
        int middle1 = middle;
        for (int i = 0; i < count; i++) {

            if (data.length % 2 == 0) {
                if (count != 0) {
                    int temp = data[middle];
                    data[middle] = data[i];
                    data[i] = temp;
                    middle++;
                }
            } else {
                if (count != 0) {
                    if (data[middle1] != data[i]) {
                        int temp = data[middle1];
                        data[middle1] = data[i];
                        data[i] = temp;
                        middle1++;
                    }
                }
            }

        }
        for (int i = 0; i < count; i++) {
            System.out.print(data[i] + " ");
        }
        return count;
    }

}