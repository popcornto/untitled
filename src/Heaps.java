import java.util.*;

public class Heaps {
    public static void swap(int[] heap, int pos1, int pos2) {

        int tmp;
        tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tmp;
    }

    public static void ArraytoString(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }

    public static void minHeapify(int[] heap, int i, int n) {
        if (!(i > (n / 2)-1 && i <= n - 1)) {                         // Es wird geguckt ob i Kinder hat
            if (heap[i] > heap[2 * i ] || heap[i] > heap[2 * i+1 ]) {     // Wenn ja dann wird geguckt ob ein Kind kleiner ist
                // Swap with the left child and heapify
                // the left child
                if (heap[2 * i ] < heap[2 * i + 1]) {                   // Wenn ja dann mit if else wird das kleinere Kind bestimmt und sie werden ausgetauscht
                    swap(heap, i, 2 * i );                         // und dann wird minHeapify aufgerufen um diesen Teil zu korrigieren
                    minHeapify(heap, 2 * i , n);
                }

                // Swap with the right child and heapify
                // the right child
                else {
                    swap(heap, i, 2 * i + 1);
                    minHeapify(heap, 2 * i + 1, n);
                }
            }
        }
    }

    public static void buildMinHeap(int[] data, int n) {
        for (int j = (n/2); j > -1 ; j--) {                           //Vom ersten Knoten mit einem Kind wird minHeapify verwendet
            minHeapify(data, j, n);
        }
    }

    public static int extractMin(int[] data, int n) {
        int min = data[0];
        data[0] = data[n - 1];
        data[n - 1] = min;                                          // kleinste Wert wird mit Größtem getauscht
        n--;                                                        // n wird um 1 veringert damit der kleinste wert nicht mehr zum Heap gehört
        minHeapify(data, 0, n);                                  // Der rest des heaps wird wieder sortiert
        return min;
    }


    public static int heapSelect(int[] data, int k) {
        int n = data.length;
        buildMinHeap(data, n);
        for (int i = 0; i < k - 1; i++) {                           //Jeder Wert wird entfernt bis der k.ste wert erreicht wird
            extractMin(data, n);                                    //Dieser wird dann zurückgegeben
        }
        return data[0];
    }

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
        if (list.size() > 0) {
            int[] data = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                data[i] = list.get(i);
            }
        buildMinHeap(data, k);
        ArraytoString(data);
    }
}
}