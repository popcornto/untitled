import java.util.*;

public class radsort {
    private static int digit(int a, int b) {
        return ((a >> (8 * b)) & 0xFF);
    }

/*
    private static int[] radixSort(int[] data) {
        int[] output = new int[data.length];
        int[] count = new int[256];
        System.out.println(Arrays.toString(count));
        for (int i = 0; i < data.length; i++) {

            count[digit(data[i], 0)]++;
        }
        System.out.println(Arrays.toString(count));
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }
        System.out.println(Arrays.toString(count));
        for (int i = data.length - 1; i >= 0; i--) {
            output[count[digit(data[i], 0)] - 1] = data[i];
            count[digit(data[i], 0)]--;
        }
        System.out.println(Arrays.toString(count));
        for (int i = 0; i < data.length; i++) {
            data[i] = output[i];
        }
        return data;
    }
*/
    /*private static void sortByByte(int[] data, int l, int r, int b) {
        int[] count = new int[256];
        int[] helpArray = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            System.out.println(digit(data[i], b));
            count[255 - digit(data[i], b)]++;
        }
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }
        for (int i = r; i >= l; i--) {
            helpArray[count[255 - digit(data[i], b) - 1]] = data[i];
            count[255 - digit(data[i], b)]--;
        }
        for (int i = l; i < r; i++) {
            data[i] = helpArray[i];
        }
        System.out.println(Arrays.toString(data));
    }*/
    public static int[] sortByByte(int[] data, int l, int r, int b) {

        int[] count = new int[256];
        int[] output = new int[r-l+1];
        for (int i = l; i <= r; i++) {
            count[255-((data[i] >> (8 * b)) & 0xFF)]++;
            System.out.println(digit(data[i], b) +" :" + data[i]);
        }
        for (int i = 1; i < 256; i++) {
            count[i] += count[i-1];
        }
        for (int i = r; i >= l; i--) {
            output[count[255-((data[i] >> (8 * b)) & 0xFF)] - 1] = data[i];
            count[255-((data[i] >> (8 * b)) & 0xFF)]--;
        }
        for (int i = l; i <= r; i++) {
            data[i] = output[i-l];
        }

        System.out.println(Arrays.toString(data));
        return output;
    }
    public static int[] sortbyByte(int[] data, int l, int r, int b) {
        int[] count = new int[257];
        int[] output = new int[r-l+1];
        for (int i = l; i <= r; i++) {
            System.out.println(digit(data[i],b)+ " :" + data[i]);
            count[255-((data[i] >> (8 * b)) & 0xFF)]++;
        }
        for (int i = 1; i < 256; i++) {
            count[i] += count[i-1];
        }
        for (int i = r; i >= l; i--) {
            output[count[255-((data[i] >> (8 * b)) & 0xFF)] - 1] = data[i];
            count[255-((data[i] >> (8 * b)) & 0xFF)]--;
        }
        for (int i = 0; i < 256; i++)
        {
                MSDRadix(data, l + count[256], count[0], b);
        }

        for (int i = l; i <= r; i++) {
            data[i] = output[i-l];
        }

        System.out.println(Arrays.toString(data));
        return output;
    }

    public static void LsdRadix(int[] data) {
        for (int i = 0; i<4; i++) {
            sortByByte(data, 0, data.length - 1, i);
        }
    }

   /* public static void lsdRadix(int[] data) {
        if (data.length < 2) {
        } else {
            for (int i = 0; i <= 3; i++) {
                sortByByte(data, 0, data.length - 1, i);
            }
        }
    }
*/
    public static int[] sort(int[] data) {
        int temp;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] < data[j]) {
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }

            }
        }
        return data;
    }

    static void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void MSDRadix(int[] data, int l, int r, int b) {
        int bruh = r - l + 1;

            if (l < r) {
                b = 4-(b+1);                                                                    //Rekursions-Anker
                int[] m = sortbyByte(data, l, r, b);                        //Den Index des Pivots bekommen nach der ersten Partitionierung
                sortbyByte(m, l, r, b-1);                                //Linke und Rechte seite aufteilen und Partitionieren
            }
        }


    public static void main(String[] args) {
        /*
        if (args.length != 3) { // die Eingabe wird auf einem einzigen Argument geprüft
            System.out.println("Exactly three arguments are required.");
            return;
        }
        int k = 0; // k is the number of elements in the array
        try {// versucht die Eingabe zu einer Zahl zu konvertieren
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) { // fängt NumberFormatException ab, wenn die Eingabe Integer Wert ist
            System.out.println("The arguments must be integers.");
            return;
        }
        if (Integer.parseInt(args[1]) < Integer.parseInt(args[0])) {
            System.out.println("l kann nicht größer als r sein!");
        } else {
            if (Integer.parseInt(args[2]) < 0 || Integer.parseInt(args[2]) > 3) {
                System.out.println("b ist falsch");
            } else {
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
                    if (list.size() < Integer.parseInt(args[1]) + 1 || Integer.parseInt(args[0]) < 0) {
                        System.out.println("l order r out of bounds!");
                    } else {
                        int[] data = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            data[i] = list.get(i);
                        }
                        sort(data);
                        System.out.println(Arrays.toString(data));
                        sortByByte(data, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                    }
                } else {
                    System.out.println("Das Array ist leer!");

                }
        */
        int[] data = {123432,12332,9786,987,5678,8769};
        System.out.println(Arrays.toString(data));
        //System.out.println(Arrays.toString(SortbyByte(data, 1,3,0)));
        MSDRadix(data, 0, data.length-1,3 );
    }

}


