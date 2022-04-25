import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


    public class quicksort2 {
        public static int[] partition(int[] data, int l, int r) {
            int pivot1 = data[l];    //pivot1
            int pivot2 = data[r];  //pivot2
            int m1 = l;  //position des ersten pivot
            int m2 = r; //position des zweiten pivot
            ArrayList<Integer> ListOfM = new ArrayList<>(); //liste der positionen der pivot

            while (m1 < m2) { //solange m1 und m2 nicht gleich sind
                while (m1 < m2 && data[m2] <= pivot1)  //solange m1 und m2 nicht gleich sind und pivot2 kleiner oder gleich pivot1

                    m2--; //m2 nach links
                data[m1] = data[m2]; //m1 wird mit m2 überschrieben
                while (m1 < m2 && data[m1] >= pivot1)  //solange m1 und m2 nicht gleich sind und pivot1 kleiner oder gleich pivot2
                    m1++; //m1 nach rechts
                data[m2] = data[m1]; //m2 wird mit m1 überschrieben
            }
            data[m1] = pivot1; //pivot1 wird in data an der Position m1 geschrieben
            ListOfM.add(m1); //m1 wird in liste geschrieben

            for (int i = 0; i < data.length; i++) { //for schleife für alle elemente
                if (data[i] == pivot2) { //wenn element gleich pivot2
                    {
                        m2 = i; //m2 wird mit i überschrieben
                    }
                }
            }

            if (m2 == l) { //wenn m2 gleich l also m2 < m1

                while (m2 < m1) {  //solange m2 kleiner als m1
                    while (m2 < m1 && data[m1] <= pivot2)   //solange m2 und m1 nicht gleich sind und pivot2 kleiner oder gleich pivot1

                        m1--; //m1 nach links
                    data[m2] = data[m1]; //m2 wird mit m1 überschrieben
                    while (m2 < m1 && data[m2] >= pivot2) //solange m2 und m1 nicht gleich sind und pivot1 kleiner oder gleich pivot2
                        m2++; //m2 nach rechts
                    data[m1] = data[m2];    //m1 wird mit m2 überschrieben
                }
                data[m2] = pivot2; //pivot2 wird in m2 geschrieben
            } else {
                if (m2 > m1) { //wenn m2 größer als m1
                    m1 = l; //m1 wird mit l überschrieben
                    while (m2 > m1) { //solange m2 größer als m1
                        while (m2 > m1 && data[m1] >= pivot2) //solange m2 und m1 nicht gleich sind und pivot2 kleiner oder gleich pivot1

                            m1++; //m1 nach rechts
                        data[m2] = data[m1]; //m2 wird mit m1 überschrieben
                        while (m2 > m1 && data[m2] <= pivot2) //solange m2 und m1 nicht gleich sind und pivot1 kleiner oder gleich pivot2
                            m2--; //m2 nach links
                        data[m1] = data[m2]; //m1 wird mit m2 überschrieben
                    }
                }
            }
            data[m2] = pivot2; //pivot2 wird in data an der Position m2 geschrieben

            ListOfM.add(m2); //m2 wird in liste geschrieben

            int[] list = new int[2]; //liste mit 2 elementen
            if (ListOfM.get(0) > ListOfM.get(1)) //wenn erstes element größer als zweites element
            {
                list[0] = ListOfM.get(1); //erstes element wird mit zweites element überschrieben, da
                list[1] = ListOfM.get(0); //zweites element wird mit erstes element überschrieben
            } else {
                list[0] = ListOfM.get(0); //erstes element wird mit erstes element überschrieben
                list[1] = ListOfM.get(1); //zweites element wird mit zweites element überschrieben
            }

            return list; //Liste wird zurückgegeben
        }

        public static void qsort(int[] data, int l, int r) {
            if (l < r) {
                int[] m = partition(data, l, r);
                if (m[0] == l && m[1] == r) {
                    if (m[0] + 1 == m[1]) {
                        if (data[l] < data[r]) {
                            int temp = data[l];
                            data[l] = data[r];
                            data[r] = temp;
                        }
                    } else {
                        qsort(data, m[0] + 1, m[1] - 1);
                    }
                } else {
                    qsort(data, l, m[0] - 1);
                    qsort(data, m[0] + 1, m[1] - 1);
                    qsort(data, m[1] + 1, r);
                }
            }
        }

        public static boolean isSorted(int[] data) {
            for (int i = 0; i < data.length - 1; i++) {
                if (data[i] < data[i + 1]) {
                    return false;
                }
            }
            return true;
        }

        public static void qsort(int[] data) {
            qsort(data, 0, data.length - 1);
        }

        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            ArrayList<Integer> list = new ArrayList<>();
            while (scan.hasNext()) {
                list.add(scan.nextInt());
            }
            int[] data = new int[list.size()];
            for (int i = 0; i < data.length; i++) {
                data[i] = list.get(i);
            }
            Instant start = Instant.now();
            if (data.length < 20) {
                System.out.println("Liste vor dem Sortieren:" + Arrays.toString(data));
                qsort(data);
                System.out.println("Liste nach dem Sortieren:" + Arrays.toString(data));
            } else {
                qsort(data);
            }
            Instant finish = Instant.now();
            float time = Duration.between(start, finish).toMillis();
            int Min = data[data.length - 1];
            long Max = data[0];
            long Med = 0;
            for (int i = 0; i < data.length; i++) {
                Med = data[i] + Med;
            }
            Med = Med / data.length;
            System.out.println("Min: " + Min + ", " + "Med: " + Med + ", " + "Max: " + Max);
            if (isSorted(data)) {
                System.out.println("Ist die Liste korrekt sortiert?: Ja!");
            } else {
                System.out.println("Ist die Liste korrekt sortiert?: Nein!");
            }
            System.out.println("Zeit: " + time / 1000 + " Sekunden");


        }
    }

