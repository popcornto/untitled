import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Quicksort2 {

    public static int partition(int[] data, int l, int r) {
        int pivot = data[l];
        int i = l;
        int j = r;
        while (i < j)
        {
            while (i < j && data[j] >= pivot)
                j--;
            data[i] = data[j];
            while (i < j && data[i] <= pivot)
                i++;
            data[j] = data[i];
        }
        data[i] = pivot;
        return i;


    }

    public static void qsort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int m1 = partition(a, l, r);
        qsort(a, l, m1 - 1);
        qsort(a, m1 + 1, r);
    }

    public static void qsort(int[] data) {
        qsort(data, 0, data.length - 1);
    }

    public static void isSorted(int data[]) {
        for (int i = data.length-1; i > 1; i--) {
            if (data[i - 1] < data[i]) {
                System.out.println("Not sorted");
                return;
            }
        }
        System.out.println("Sorted");
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

        if(list.size() <= 20){
            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);
            }
            System.out.println(Arrays.toString(arr));
            qsort(arr);
            System.out.println(Arrays.toString(arr));
        }


    }
}

