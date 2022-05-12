import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Quiksort {
    public static int partition(int[] data, int l, int r) {
        int temp1 = data[l];      // temp1 und temp2 werden deklariert um mit diesen später elemente im array verschieben zu können
        int temp2 = data[l + 1];
        if (l >= r) {   //Wie in der aufgabe gewollt wenn l>=r ist heisst es das es schon sortiert ist und wird wieder zurückgegeben
            return l;
        }
        if (l < 0) {
            throw new IllegalArgumentException("l ist kleiner als 0");
        }
        if (r > data.length - 1) {                                                //Out of bounds
            throw new IllegalArgumentException("r ist größer als die Länge des Arrays");
        }

        for (int i = l; i < r; i++) {          //Partitionierung beginnt hier
            if (data[l] < data[i]) {           //Elemente finden die größer als das Pivot sind
                data[l] = data[i];             //pivot wird vom wert ersetzt der größer als das pivat ist
                int j = l + 1;                 //nach dem ersetzt braucht man die zahl die nach dem pivot steht damit wir die zu der stelle verschieben könne die wir aus getauscht haben
                while (j < i + 1) {//Alles in der While-Schleife ist um die Elemente im Array zu verschoben zu der Stelle die hinter das Pivot gekommen ist
                    data[j] = temp1;//nach rechts
                    temp1 = temp2;
                    temp2 = data[j + 1]; //das element wird für den nächsten swap gespeichert
                    j++;
                }
                l++;                              //Pivot bekommt einen neuen Index indem man l erhöht
                temp1 = data[l];                 //Die Temporären Ints werden auf den neuen Index des Pivot angepasst
                temp2 = data[l + 1];
            }
            if (l == r - 1 && data[l] < data[r]) {         //Diese if-Verzweigung macht den letzten Schritt falls das letzte
                for (int q = 0; q < r; q++) {              //Element nicht das kleinste ist wird es an seine Stelle gebracht und der
                    if (data[r] > data[q]) {               //rest wird wieder mit einer while-Schleife verschoben hinter r
                        int tempe = data[q];               //durch gegangen und ander richtigenstelle gebracht
                        int tempe1;
                        data[q] = data[r];
                        while (q < r) {                    //array wird um eine stelle verschoben
                            tempe1 = data[q + 1];
                            data[q + 1] = tempe;
                            tempe = tempe1;
                            q++;
                        }

                    }
                }
            }
        }
        return l;
    }

    public static void qsort(int[] data, int l, int r) {
        if (l < r) {                                            //Rekursions-Anker
            int m = partition(data, l, r);                        //Den Index des Pivots bekommen nach der ersten Partitionierung
            qsort(data, l, m - 1);                                //Linke und Rechte seite aufteilen und Partitionieren
            qsort(data, m + 1, r);                                  //die Hälften werden partitioniert
        }
    }

    public static boolean isSorted(int[] data) {
        for (int i = data.length - 1; 1 < i; i--) { //[1, 2, 3, 4, 5, 6]
            if (data[i] > data[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void qsort(int[] data) {
        int m = partition(data, 0, data.length - 1);
        qsort(data, 0, m);                                    //Einfach Rekursive Partitionierung wie in b) aber l=0 und r=data.length-1
        qsort(data, m + 1, data.length - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   //Scanner für die Eingabe
        ArrayList<Integer> list = new ArrayList<>(); //wird mit den eingaben gefüllt
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
        int[] data = new int[list.size()]; //alle Werte der Liste werden in ein Array geschrieben
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }

        if (isSorted(data)) {
            System.out.println("Array ist schon sortiert");
            return;
        }
        assert !isSorted(data);
        Instant start = Instant.now();                  //Startzeit
        if (data.length < 20) {    //wenn die Länge des Arrays kleiner als 20 ist, wird die qsort-Methode aufgerufen und vor- und nach der sortierung ausgedruckt
            System.out.println("Liste vor dem Sortieren:" + Arrays.toString(data));
            qsort(data);
            System.out.println("Liste nach dem Sortieren:" + Arrays.toString(data));
        } else {
            qsort(data);
        }
        Instant finish = Instant.now();
        assert isSorted(data);  //assertion, ob das Array sortiert ist
         //Endzeit
        float time = Duration.between(start, finish).toMillis();
        int Min = data[data.length - 1]; //letzter Wert des Arrays ist auch der kleinste Wert nachdem sortiert wurde
        long Max = data[0]; //erster Wert des Arrays ist auch der größte Wert nachdem sortiert wurde
        long Med = 0;// *findet den mittelwert in dem man den durchschnitt berechnet der Zahlen die im Array liegen
        for (int i = 0; i < data.length; i++) {
            Med = data[i] + Med;
        }
        Med = Med / data.length; //*
        System.out.println("Min: " + Min + ", " + "Med: " + Med + ", " + "Max: " + Max);

        System.out.println("Zeit: " + time / 1000 + " Sekunden"); //Zeit in Sekunden
    }
}