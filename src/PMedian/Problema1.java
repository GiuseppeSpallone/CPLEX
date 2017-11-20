package PMedian;

import java.util.Random;

public class Problema1 {

    public static void main(String[] args) {
        Modello modello = new Modello();

        //insieme studenti
        Random r = new Random(3);
        int s = 100;
        int f = 1000;
        int p = 10;

        int studenti[] = new int[s];

        for (int i = 0; i < s; i++) {
            studenti[i] = r.nextInt(10) + 1;
        }

        //insieme fermate candidate
        int fermate[] = new int[f];

        //distranze studente - fermata candidata
        int distanze[][] = new int[s][f];

        for (int i = 0; i < s; i++) {
            int d = r.nextInt(50) + 10;

            int k = r.nextInt(10) - 4;

            if (k == 0) {
                k = -1;
            }

            for (int j = 0; j < f; j++) {
                distanze[i][j] = r.nextInt(100) + 10;
                //distanze[i][j] = d;
                d = d + k;
                if ((d + k) < 0) {
                    k = k * -1;
                    d = d + (2 * k);
                }

                if (r.nextInt(100) < 10) {
                    k = r.nextInt(10) - 4;
                }
            }
        }

        modello.run(studenti, fermate, distanze, p);

    }
}
