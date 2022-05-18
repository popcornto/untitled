import java.util.*;

public class Radixsort{

    public static int getMin(int[] data) {
        int min = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }

    public static int getMax(int[] data) {
        int max = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    private static int digit(int a, int b) {
        return (a / (int) Math.pow(256, b)) % 256;
    }

    public static int[] SortbyByte(int[] data, int b) {
        List<List> list = new LinkedList<>();
        //digit bestimmen
        int countdigit = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = data[i]; j > 0; j = j / 10) {
                countdigit++;
            }
        }
        //Liste mit 0-Einträgen füllen
        for (int i = 0; i < countdigit; i++) {
            list.add(new LinkedList<>());
        }
        //Einträge in Liste einfügen
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < data.length; i++) {

        }
        //Liste sortieren
        for (int i = countdigit - 2; i >= 0; i--) {
            list.get(i).addAll(list.get(i + 1));
        }
        //Liste in Array umwandeln
        int[] output = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            output[i] = (int) list.get(0).get(i);
        }
        return output;
    }

    private static int[] SortbyByte(int[] data) {
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

    private static int[] SortbyByte(int[] data, int l, int r, int b) {
        int[] output = new int[data.length];
        int[] count = new int[256];
        int[] helpArray = new int[r - l + 1];
        for (int i = l; i < r; i++) {
            helpArray[i] = data[i];
        }
        System.out.println(Arrays.toString(helpArray));
        for (int i = l; i < helpArray.length; i++) {
            System.out.println(digit(helpArray[i],b)+ " :" + helpArray[i]);
            count[255-digit(helpArray[i], b)]++;
        }
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }
        for (int i = helpArray.length - 1; i >= 0; i--) {
            output[count[255-digit(helpArray[i], b)] - 1] = helpArray[i];
            count[255-digit(helpArray[i], b)]--;
        }
        for (int i = 0; i < helpArray.length; i++) {
            helpArray[i] = output[i];
        }
        return helpArray;
    }
    private static void sortByByte(int[] data, int l, int r, int b) {
        int[] count = new int[256];
        int[] helpArray = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            System.out.println(digit(data[i],b));
            count[255-digit(data[i], b)]++;
        }
        System.out.println(Arrays.toString(count));
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        System.out.println(Arrays.toString(count));
        for (int i = r ; i >= l; i--) {
            helpArray[count[255-digit(data[i], b)-1]] = data[i];
            count[255-digit(data[i], b)]--;
        }
        System.out.println(Arrays.toString(count));
        for (int i = 0; i <= helpArray.length-1; i++) {
            data[i+l] = helpArray[i];
        }
        System.out.println(Arrays.toString(data));
    }
    public static void lsdRadix(int[] data) {
        if(data.length<2){
        }
        else {
            for (int i = 0; i < 3; i++) {
                sortByByte(data, 0, data.length-1, i);
            }
        }
    }
    public static void main(String[] args) {
        int[] data = {1661914940,1234524,23436,2454323,6423214};
        System.out.println(Arrays.toString(data));
        //System.out.println(Arrays.toString(SortbyByte(data, 1,3,0)));
        sortByByte(data, 1,3,0);

    }
}


