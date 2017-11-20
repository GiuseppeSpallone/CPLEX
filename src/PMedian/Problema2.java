package PMedian;

import java.util.Random;

public class Problema2 {

    public static void main(String[] args){
        Modello2 modello2 = new Modello2();

        int o=10;
        int d[]={1,2,3,1,2,3,5,3,4,1};
        int c[][]= new int[o][d.length];
        Random r=new Random(2);
        for (int i=0;i<o;i++){
            for(int j=0;j<d.length;j++){
                c[i][j]=r.nextInt(31)+20;
            }
        }
        int p=2;

        modello2.run(o,d,c,p);
    }
}
