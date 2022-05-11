import java.util.*;

public class QuickSelect {
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

    public static int partition(int[] nums, int left, int right, int pIndex) {

        int pivot = nums[pIndex];// pIndex wird als Pivot verwendet


        swap(nums, pIndex, right);// Pivot Element wird an der letzten Position geschoben

        // Element die kleiner als Pivot sind werden an der linken Position geschoben
        // Element die größer als Pivot sind werden an der rechten Position geschoben
        // gleiche Element können entweder an der linken oder rechten Position geschoben werden
        pIndex = left;

        //jedes mal wenn ein Element kleiner als Pivot ist, wird es an der linken Position vom Pivot geschoben und der Pivotindex wird um 1 erhöht

        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, pIndex);
                pIndex++;
            }
        }


        swap(nums, pIndex, right);// Das Pivot wird an der richtigen Position geschoben


        return pIndex;// Index des Pivots wird zurückgegeben
    }

    public static int quickSelectRand(int[] nums, int left, int right, int k) {

        if (left == right) {//Das Array enthält nur ein Element wird dieses zurückgegeben
            return nums[left];
        }


        int pIndex = rand(left, right);// Das Pivot wird zufällig zwischen den ersten und letzten Element ausgewählt

        pIndex = partition(nums, left, right, pIndex);

        // Das Pivot ist in seiner letzten Sortierungsposition
        if (k == pIndex) {
            return nums[k];
        }

        // wenn k kleiner als pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex-1 aufgerufen.
        else if (k < pIndex) {
            return quickSelectRand(nums, left, pIndex - 1, k);
        }

        // wenn k größer als der pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex+1 aufgerufen.
        else {
            return quickSelectRand(nums, pIndex + 1, right, k);
        }
    }

    public static int quickSelectRand(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;

        if (left == right) {//Das Array enthält nur ein Element wird dieses zurückgegeben
            return nums[left];
        }


        int pIndex = rand(left, right);// Das Pivot wird zufällig zwischen den ersten und letzten Element ausgewählt

        pIndex = partition(nums, left, right, pIndex);


        if (k == pIndex) {// Das Pivot ist in seiner letzten Sortierungsposition
            return nums[k];
        } else if (k < pIndex) {// wenn k kleiner als pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex-1 aufgerufen.
            return quickSelectRand(nums, left, pIndex - 1, k);
        } else {// wenn k größer als der pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex+1 aufgerufen.
            return quickSelectRand(nums, pIndex + 1, right, k);
        }
    }

    public static int quickSelectFirst(int[] nums, int left, int right, int k) {
        // Das Array enthält nur ein Element wird dieses zurückgegeben
        if (left == right) {
            return nums[left];
        }

        // Das Linke Element wird als Pivot gewählt
        int pIndex = left;

        pIndex = partition(nums, left, right, pIndex);


        if (k == pIndex) {// Das Pivot ist in seiner letzten Sortierungsposition
            return nums[k];
        } else if (k < pIndex) {// wenn k kleiner als pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex-1 aufgerufen.
            return quickSelectRand(nums, left, pIndex - 1, k);
        } else {        // wenn k größer als der pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex+1 aufgerufen.

            return quickSelectRand(nums, pIndex + 1, right, k);
        }
    }

    public static int quickSelectFirst(int[] nums, int k) {
        int left = 0; // Der linke Index
        int right = nums.length - 1; // Der rechte Index

        if (left == right) {// Das Array enthält nur ein Element wird dieses zurückgegeben
            return nums[left];
        }


        int pIndex = left; // Das Linke Element wird als Pivot gewählt

        pIndex = partition(nums, left, right, pIndex);


        if (k == pIndex) { // Das Pivot ist in seiner letzten Sortierungsposition
            return nums[k];
        } else if (k < pIndex) { // wenn k kleiner als pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex-1 aufgerufen.
            return quickSelectRand(nums, left, pIndex - 1, k);
        } else { // wenn k größer als der pIndex ist. Wird ein rekusives aufrufen von quickSelectRand für pIndex+1 aufgerufen.
            return quickSelectRand(nums, pIndex + 1, right, k);
        }
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

            System.out.print(k+ ". kleinste Element ist: " + quickSelectFirst(data, k));
            System.out.println();
            System.out.println(Arrays.toString(data));
        }
    }
}
