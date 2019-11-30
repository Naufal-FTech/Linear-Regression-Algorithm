package MetodeRegresi;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Phyton NF
 * Metode Regresi Lanjar/Linier y = a + bx
 */

class RegresiLanjar{
    DecimalFormat df = new DecimalFormat("0.0000");
    double[][] dataTabel, dataHasilTabel, matriksGauss, matriksGauss1;
    double[] xKuadrat, XiYi;
    double jumXi=0, jumYi=0, jumXkuadrat=0, jumXiYi=0;
    int jumlahData, xDitanya, baris = 2, kolom = 3;
            
    public void soal(){               
        Scanner scan1 = new Scanner (System.in);    
        System.out.println("Mencari persamaan garis lurus dari inputan data anda!");
        System.out.print("Berapa jumlah data yang diinginkan : ");
        jumlahData = scan1.nextInt();
        dataTabel = new double[jumlahData][jumlahData];
        System.out.println("\nMasukkan data-data anda di bawah ini!");
        for (int i = 0; i < jumlahData; i++) {
            for (int j = 0; j < 1; j++) {
                Scanner data = new Scanner (System.in);
                System.out.print("x"+(i + 1)+" = ");
                dataTabel[i][j] = data.nextDouble();
                System.out.print("y"+(i + 1)+" = ");
                dataTabel[i][j+1] = data.nextDouble();
            }
            System.out.println();
        }
        
        System.out.println("Berikut Tabel dari Inputan Data Anda");
        System.out.println("\t x \t | \t y");
        System.out.println("===================================");
        for (int i = 0; i < jumlahData; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.println("\t"+df.format(dataTabel[i][j])+" \t | \t"+df.format(dataTabel[i][j+1]));
            }           
        }
        
        //JAWABAN PENDAHULUAN
        System.out.println("\n\nJAWAB");
        System.out.println(" i\t| \t Xi \t | \t Yi \t | \t Xi^2 \t | \t XiYi \t");
        System.out.println("=============================================================================");
        xKuadrat = new double[jumlahData];
        XiYi = new double[jumlahData];
        for (int i = 0; i < jumlahData; i++) {
            for (int j = 0; j < 1; j++) {
                xKuadrat[i] = (dataTabel[i][j]*dataTabel[i][j]);
                XiYi[i] = (dataTabel[i][j]*dataTabel[i][j+1]);
                System.out.println(" "+(i+1)+"\t| \t"+df.format(dataTabel[i][j])+" \t | \t"+df.format(dataTabel[i][j+1])+" \t | \t"+df.format(xKuadrat[i])+" \t | \t"+df.format(XiYi[i]));
            }           
        }
                
        for (int i = 0; i < jumlahData; i++) {
            for (int j = 0; j < 1; j++) {
                jumXi = jumXi + dataTabel[i][j]; 
                jumYi = jumYi + dataTabel[i][j+1];
                jumXkuadrat = jumXkuadrat + xKuadrat[i];
                jumXiYi = jumXiYi + XiYi[i];
            }         
        }
        System.out.println("=============================================================================");
        System.out.println("Sigma \t| \t"+df.format(jumXi)+" \t | \t"+df.format(jumYi)+" \t | \t"+df.format(jumXkuadrat)+" \t | \t"+df.format(jumXiYi));
    }
    
    public void matriks(){
        dataHasilTabel = new double[baris][kolom];
        dataHasilTabel[0][0] = jumlahData;
        dataHasilTabel[0][1] = jumXi;
        dataHasilTabel[0][2] = jumYi;
        dataHasilTabel[1][0] = jumXi;
        dataHasilTabel[1][1] = jumXkuadrat;
        dataHasilTabel[1][2] = jumXiYi;
        System.out.println("\n\nBentuk Matriks yang akan digunakan dalam metode Gauss Jordan");       
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(df.format(dataHasilTabel[i][j])+"\t");               
            }
            System.out.println();
        }
    }
    
    public void hitung(){   //tinggal hitung matrix dg metode gauss jordan        
        Scanner scan2 = new Scanner(System.in);
        
        matriksGauss = new double[baris][kolom];    //Menampung elemen baru di array matriksGauss
        for (int i = 0; i < (baris-1); i++) {
            for (int j = 0; j < kolom; j++) {
                matriksGauss[i][j] = dataHasilTabel[i][j]/dataHasilTabel[i][i];               
            }
            for (int j = 0; j < kolom; j++) {
                matriksGauss[i+1][j] = dataHasilTabel[i+1][j] - (dataHasilTabel[i+1][i]*matriksGauss[i][j]);               
            }
        }
                
        matriksGauss1 = new double[baris][kolom];    //Menampung elemen baru di array matriksGauss1
        for (int i = 0; i < (baris-1); i++) {
            for (int j = 0; j < kolom; j++) {
                matriksGauss1[i+1][j] = matriksGauss[i+1][j]/matriksGauss[i+1][i+1];              
            }
            for (int j = 0; j < kolom; j++) {
                matriksGauss1[i][j] = matriksGauss[i][j] - (matriksGauss[i][i+1]*matriksGauss1[i+1][j]);               
            }
        }
        
        System.out.println("\nHasil matriks dari perhitungan Gauss Jordan");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(df.format(matriksGauss1[i][j])+"\t");                 
            }
            System.out.println();
        }        
        double a = matriksGauss1[0][2], b = matriksGauss1[1][2];
        System.out.println("\nJadi a = "+df.format(a)+" dan b = "+df.format(b));
              
        
        System.out.println("\nMaka persamaan yang terbentuk adalah f(x) = "+df.format(a)+" + "+df.format(b)+"x");
        System.out.print("Mencari nilai f(x) dengan x = ");
        xDitanya = scan2.nextInt(); 
        double fungsi = a + (b*xDitanya);
        System.out.println("Maka f("+xDitanya+") = "+df.format(fungsi));
    }
}

public class PerhitunganRegresiLanjar {
    public static void main(String[] args) {
        RegresiLanjar regresi = new RegresiLanjar();
        regresi.soal();
        regresi.matriks();        
        regresi.hitung();
    }
}
