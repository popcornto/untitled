import java.util.Arrays;
public class countSort {
    public static int getMin(int[] data){
        int min = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min){
                min = data[i];
            }
        }
        return min;
    }
    public static int getMax(int[] data){
        int max = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max){
                max = data[i];
            }
        }
        return max;
    }
    public static int[] count(int[] A, int min, int max){
        int[] C = new int[max - min + 1];
        for (int i = 0; i < A.length; i++) {
            C[A[i]-1]++;
        }
        return C;
    }
    public static int[] countSort(int[] data){
        int[] help=count(data,getMin(data),getMax(data));
        int[] out= new int[data.length];
        for(int i=1;i<help.length;i++){
            help[i]=help[i]+help[i-1];
        }
        for(int i=0;i<data.length;i++){
            out[help[data[i]-1]-1]=data[i];
            help[data[i]-1]--;
        }
        return out;
    }
    public static void main(String[] args) {
        int[] data = {1,4,1,2,7,5,2,45,43,65,76,87,12,45,655,6,1};
        int min = getMin(data);
        int max  = getMax(data);
        System.out.println("The minimum value is: "+getMin(data));
        System.out.println("The maximum value ist: "+getMax(data));
        System.out.println("Frequencies: "+Arrays.toString(count(data, min, max)));
        System.out.println("Before sorting: "+Arrays.toString(data));
        System.out.println("After sorting: "+Arrays.toString(countSort(data)));
    }

}
