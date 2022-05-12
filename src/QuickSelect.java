import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class QuickSelect {
    public static void swaps(int[] heap, int pos1, int pos2) {

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
        if (!(i > (n / 2) - 1 && i <= n - 1)) {                         // Es wird geguckt ob i Kinder hat
            if (heap[i] > heap[2 * i] || heap[i] > heap[2 * i + 1]) {     // Wenn ja dann wird geguckt ob ein Kind kleiner ist
                // Swap with the left child and heapify
                // the left child
                if (heap[2 * i] < heap[2 * i + 1]) {                   // Wenn ja dann mit if else wird das kleinere Kind bestimmt und sie werden ausgetauscht
                    swaps(heap, i, 2 * i);                         // und dann wird minHeapify aufgerufen um diesen Teil zu korrigieren
                    minHeapify(heap, 2 * i, n);
                }

                // Swap with the right child and heapify
                // the right child
                else {
                    swaps(heap, i, 2 * i + 1);
                    minHeapify(heap, 2 * i + 1, n);
                }
            }
        }
    }

    public static void buildMinHeap(int[] data, int n) {
        for (int j = (n / 2); j > -1; j--) {                           //Vom ersten Knoten mit einem Kind wird minHeapify verwendet
            minHeapify(data, j, n);
        }
    }

    public static boolean checkIfMinHeap(int[] data) {
        int n = data.length;
        buildMinHeap(data, n);
        for (int i = 0; i < n; i++) {
            if (data[i] > data[(i - 1) / 2]) {
                System.out.println("Not a min heap");
                return false;
            }
        }
        return true;
    }

    public static int extractMin(int[] data, int n) {
        int min = data[0];
        data[0] = data[n - 1];
        data[n - 1] = min;                 // kleinste Wert wird mit Größtem getauscht
        n--;                              // n wird um 1 veringert damit der kleinste wert nicht mehr zum Heap gehört
        minHeapify(data, 0, n);        // Der rest des heaps wird wieder sortiert
        return min;
    }


    public static int heapSelect(int[] data, int k) {
        int n = data.length;
        buildMinHeap(data, n);
        for (int i = 0; i < k - 1; i++) {              //Jeder Wert wird entfernt bis der k.ste wert erreicht wird
            extractMin(data, n);                      //Dieser wird dann zurückgegeben
                                                        //bis wir das kste element erreicht haben
            n--;                                      //n wird um 1 veringert damit der kleinste wert nicht mehr zum Heap gehört
        }
        return data[0];
    }

    public static void swap(int[] data, int l, int r) { //swap funktion
        int temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }

    public static int rand(int min, int max) {
        if (min > max || (max - min + 1 > Integer.MAX_VALUE)) { //wenn min größer als max ist oder wenn die Anzahl der Elemente größer als Integer.MAX_VALUE ist
            throw new IllegalArgumentException("Invalid range");
        }
        return new Random().nextInt(max - min + 1) + min; //zufällige Zahl zwischen min und max wird erzeugt
    }

    public static int partition(int[] data, int l, int r, int pIndex) {
        int pivot = data[pIndex];// pIndex wird als Pivot verwendet


        swap(data, pIndex, r);// Pivot Element wird an der letzten Position geschoben

        // Element die kleiner als Pivot sind werden an der linken Position geschoben
        // Element die größer als Pivot sind werden an der rechten Position geschoben
        // gleiche Element können entweder an der linken oder rechten Position geschoben werden
        pIndex = l;

        //jedes mal wenn ein Element kleiner als Pivot ist, wird es an der linken Position vom Pivot geschoben und der Pivotindex wird um 1 erhöht

        for (int i = l; i < r; i++) {
            if (data[i] <= pivot) {
                swap(data, i, pIndex);
                pIndex++;
            }
        }


        swap(data, pIndex, r);// Das Pivot wird an der richtigen Position geschoben


        return pIndex;// Index des Pivots wird zurückgegeben
    }

    public static int quickSelectRand(int[] data, int left, int right, int k) {

        if (left == right) {//Das Array enthält nur ein Element wird dieses zurückgegeben
            return data[left];
        }


        int pIndex = rand(left, right);// Das Pivot wird zufällig zwischen den ersten und letzten Element ausgewählt

        pIndex = partition(data, left, right, pIndex);

        // Das Pivot ist in seiner letzten Sortierungsposition
        if (k == pIndex) {
            return data[k];
        }

        // wenn k kleiner als pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex-1 aufgerufen.
        else if (k < pIndex) {
            return quickSelectFirst(data, left, pIndex - 1, k);
        }

        // wenn k größer als der pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex+1 aufgerufen.
        else {
            return quickSelectFirst(data, pIndex + 1, right, k);
        }
    }

    public static int quickSelectRand(int[] data, int k) {
        int left = 0;
        int right = data.length - 1;

        if (left == right) {//Das Array enthält nur ein Element wird dieses zurückgegeben
            return data[left];
        }


        int pIndex = rand(left, right);// Das Pivot wird zufällig zwischen den ersten und letzten Element ausgewählt

        pIndex = partition(data, left, right, pIndex);


        if (k == pIndex) {// Das Pivot ist in seiner letzten Sortierungsposition
            return data[k];
        } else if (k < pIndex) {// wenn k kleiner als pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex-1 aufgerufen.
            return quickSelectRand(data, left, pIndex - 1, k);
        } else {// wenn k größer als der pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex+1 aufgerufen.
            return quickSelectRand(data, pIndex + 1, right, k);
        }
    }

    public static int quickSelectFirst(int[] data, int left, int right, int k) {
        // Das Array enthält nur ein Element wird dieses zurückgegeben
        if (left == right) {
            return data[left];
        }

        // Das Linke Element wird als Pivot gewählt
        int pIndex = left;

        pIndex = partition(data, left, right, pIndex);


        if (k == pIndex) {// Das Pivot ist in seiner letzten Sortierungsposition
            return data[k];
        } else if (k < pIndex) {// wenn k kleiner als pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex-1 aufgerufen.
            return quickSelectFirst(data, left, pIndex - 1, k);
        } else {        // wenn k größer als der pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex+1 aufgerufen.

            return quickSelectFirst(data, pIndex + 1, right, k);
        }
    }

    public static boolean isSorted(int[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1] > data[i]) {
                System.out.println("Ist nicht richtig sortiert");
                return false;
            }
        }
        System.out.println("Ist richtig sortiert");
        return true;
    }

    public static int quickSelectFirst(int[] data, int k) {
        int left = 0; // Der linke Index
        int right = data.length - 1; // Der rechte Index

        if (left == right) {// Das Array enthält nur ein Element wird dieses zurückgegeben
            return data[left];
        }


        int pIndex = left; // Das Linke Element wird als Pivot gewählt

        pIndex = partition(data, left, right, pIndex);


        if (k == pIndex) { // Das Pivot ist in seiner letzten Sortierungsposition
            return data[k];
        } else if (k < pIndex) { // wenn k kleiner als pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex-1 aufgerufen.
            return quickSelectFirst(data, left, pIndex - 1, k);
        } else { // wenn k größer als der pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex+1 aufgerufen.
            return quickSelectFirst(data, pIndex + 1, right, k);
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) { // die Eingabe wird auf einem einzigen Argument geprüft
            System.out.println("Exactly two arguments are required.");
            return;
        }
        int k; // k is the number of elements in the array
        try {// versucht die Eingabe zu einer Zahl zu konvertieren
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) { // fängt NumberFormatException ab, wenn die Eingabe Integer Wert ist
            System.out.println("The arguments must be integers.");
            return;
        }
        String quickr = "quickr";
        String quickf = "quickf";
        String heap = "heap";
        String arg = args[1];
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
            Instant start = Instant.now();                  //Startzeit
            if (args[1].equals("quickr")) {
                if (k > data.length || k < 1) {
                    System.out.println("k ist größer als die Länge des Arrays oder k ist kleiner als 1");
                    return;
                }
                assert !isSorted(data);
                System.out.println(quickSelectRand(data, k));
                System.out.println("sortiertes Array: " + Arrays.toString(data));
                System.out.println("das " + k+ " kleinste Element ist: " + quickSelectRand(data, k));
                assert isSorted(data);
            } else if (args[1].equals("quickf")) {
                if (k > data.length || k < 1) {
                    System.out.println("k ist größer als die Länge des Arrays oder k ist kleiner als 1");
                    return;
                }
                assert !isSorted(data);
                System.out.println("Vor dem Anwenden von quickSelcetFirst: " + Arrays.toString(data));
                quickSelectFirst(data, k);
                System.out.println("sortiertes Array: " + Arrays.toString(data));
                System.out.println(quickSelectFirst(data, k));
                assert isSorted(data);
            } else if (args[1].equals("heap")) {
                assert !checkIfMinHeap(data);
                buildMinHeap(data, k);
                ArraytoString(data);
                assert checkIfMinHeap(data);
                System.out.println("extracted Min is: " + extractMin(data, k));
                System.out.println("Heapselect: " + heapSelect(data, k));
            }
            Instant finish = Instant.now();
            //Endzeit
            float time = Duration.between(start, finish).toMillis();
            System.out.println("Zeit: " + time / 1000 + " Sekunden"); //Zeit in Sekunden
        }
    }
}
