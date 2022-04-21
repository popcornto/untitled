import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Exactly one arguments are required.");
            return;
        }
        int k = 0;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("The arguments must be integers.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        try {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equals("")) {
                    break;
                }
                list.add(Integer.parseInt(input));
            }
        } catch (NumberFormatException e) {
            System.err.println("U GEY");
            return;
        }


        if (list.size() < k) {
            System.out.println("Der Array muss mindestens " + k + " Werte haben. ");
            return;
        } else {
            int[] arr = new int[list.size()];
            for (int i = 0; i < arr.length-1 ; i++) {
                arr[i] = list.get(i);
            }
            Arrays.sort(arr);
            System.out.println("Der " + k + "." + " kleinste Wert ist " + arr[k]);
        }
    }
}