import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quiksort {
    public static int partition(int[] data, int l, int r){
        int temp1=data[l];													// temp1 und temp2 werden deklariert um mit diesen später elemente im array verschieben zu können
        int temp2=data[l+1];
        if(l>=r){														//Wie in der aufgabe gewollt wenn l>=r ist heisst es das es schon sortiert ist und wird wieder zurückgegeben
            return l;
        }
        if(l<0) {															//Out of bounds
            System.out.println("l kann nicht kleiner als 0 sein!");
            return l;
        }
        if(r>data.length-1) {												//Out of bounds
            System.out.println("r darf nicht größer als der Array sein!");
            return l;
        }
        for(int i=l;i<r;i++) {												//Partitionierung beginnt hier
            if(data[l]<data[i]) {											//Elemente finden die größer als das Pivot sind
                data[l]=data[i];											//
                int j=l+1;
                while(j<i+1){												//Alles in der While-Schleife ist um die Elemente im Array zu verschieben zu der Stelle die hinter das Pivot gekommen ist
                    data[j]=temp1;
                    temp1=temp2;
                    temp2=data[j+1];
                    j++;
                }
                l++;														//Pivot bekommt einen neuen Index indem man l erhöht
                temp1=data[l];												//Die Temporären Ints werden auf den neuen Index des Pivot angepasst
                temp2=data[l+1];
            }
            if(l==r-1 && data[l]<data[r]) {									//Diese if-Verzweigung macht den letzten Schritt falls das letzte
                for(int q=0;q<r;q++) {										//Element nicht das kleinste ist wird es an seine Stelle gebracht und der
                    if(data[r]>data[q]) {									//rest wird wieder mit einer while-Schleife verschoben
                        int tempe=data[q];
                        int tempe1=0;
                        data[q]=data[r];
                        while(q<r) {
                            tempe1=data[q+1];
                            data[q+1]=tempe;
                            tempe=tempe1;
                            q++;
                        }

                    }
                }
            }
        }
        return l;
    }
    public static int ArraytoString(int[] data) {
        for(int i=0;i<data.length;i++) {
            System.out.print(data[i]+" ");
        }
        return 0;
    }
    public static void qsort(int[] data, int l, int r) {
        if(l<r){											//Rekursions-Anker
            int m=partition(data,l,r);						//Den Index des Pivots bekommen nach der ersten Partitionierung
            qsort(data,l,m-1);								//Linke und Rechte seite aufteilen und Partitionieren
            qsort(data,m+1,r);
        }
    }
    public static void qsort(int[] data) {
        int m=partition(data,0,data.length-1);				//Aufgabe 2.1 c)
        qsort(data,0,m);									//Einfach Rekursive Partitionierung wie in b) aber l=0 und r=data.length-1
        qsort(data,m+1,data.length-1);
    }
    public static void main(String[] args) {
        int[] data= {5,8,1,4,4,9,2,3};
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        try {
            while (scanner.hasNextLine()) { 						// liest die eingabe ein
                String input = scanner.nextLine();
                if (input.equals("")) { 							// wenn die eingabe leer ist, wird die ausgabe erstellt und geht aus der schleife raus
                    break;
                }
                list.add(Integer.parseInt(input)); 					// addiert die eingabe zur liste
            }
        } catch (NumberFormatException e) { 						// fängt NumberFormatException ab, wenn die Eingabe Integer Wert ist
            System.err.println("Der Input was kein Integer Wert.");
        }
        int[] arr = new int[list.size()]; 							// Erstellt ein Array mit der Länge der Arraylist
        for (int i = 0; i < arr.length-1 ; i++) { 					// Füllt das Array mit den Elementen aus der Arraylist
            arr[i] = list.get(i);
        }
        qsort(arr);// Sortiert das Array
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
