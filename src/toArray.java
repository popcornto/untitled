import java.util.ArrayList;
import java.util.Scanner;

public class toArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        try {
            while (scanner.hasNextLine()) {// liest die eingabe ein des benutzers
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

        if (list.size() > 0) {
            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);
            }

            for (int Element : arr) {
                System.out.print(Element);
            }
        }
    }
}
