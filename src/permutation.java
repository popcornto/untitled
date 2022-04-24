import java.util.ArrayList;
import java.util.Scanner;

public class permutation {


    public static int Permutation(int[] arr, int n) {
        int count = 0; // permutations counter wird iniziallisiert mit 0 und wird bei jeder Permutation um 1 erhöht
        if (n == arr.length - 1) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");

            }

            System.out.println();
            return 1;

        } else {
            for (int i = n; i < arr.length; i++) {
                swaparray(arr, n, i);
                count += Permutation(arr, n + 1);
                swaparray(arr, n, i);
            }
        }
        return count;
    }

    public static void permutaionoflist() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in the list");
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<Integer>();
        System.out.println("Enter the elements");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        System.out.println("The permutation of the list is");
        System.out.println("Es gibt " + permutation(list, 0) + " Permutationen");
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

    public static void printPermutation(int[] arr, int n) {
        System.out.println("Es gibt " +Permutation(arr, n)+ " Permutationen");
    }
    public static void swaplist(ArrayList<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static void swaparray(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        permutaionoflist();
        printPermutation(new int[]{1, 2, 3}, 0);
    }

}




