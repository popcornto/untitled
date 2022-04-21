import java.util.ArrayList;
import java.util.Scanner;

public class permutation {
    static int[] arr = {1, 2, 3, 4};

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
        permutation(list, 0);
        System.out.println(n + "!=" + faktorial(n));
    }

    public static void permutation(ArrayList<Integer> list, int k) {
        if (k == list.size() - 1) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + "");
            }
            System.out.println();
        } else {
            for (int i = k; i < list.size(); i++) {
                swaplist(list, k, i);
                permutation(list, k + 1);
                swaplist(list, k, i);
            }
        }

    }

    public static int faktorial(int n) {
        int i,fact=1;
        int number=n;
        for(i=1;i<=number;i++){
            fact=fact*i;
        }
        return fact;
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

    public static int printPermutationofarray(int[] arr, int n) {
        int numberofpermutations = 0;
        if (n == arr.length - 1) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");

            }

            System.out.println();

        } else {

            for (int i = n; i < arr.length; i++) {

                swaparray(arr, n, i);
                numberofpermutations++;
                printPermutationofarray(arr, n + 1);
                swaparray(arr, n, i);
            }
        }

        return numberofpermutations;
    }

    public static void main(String[] args) {
        permutaionoflist();
    }
}




