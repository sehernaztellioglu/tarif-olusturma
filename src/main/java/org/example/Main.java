package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Main {



    static Scanner scanner = new Scanner(System.in);

    static String tatliInput = scanner.nextLine();

    static int sayac = 0;









    public static void main(String[] args) throws IOException {




        csvMalzeme("yumurta",20);
        csvMalzeme("süt",10);
        csvMalzeme("un",10);
        csvMalzeme("peynir",5);



        System.out.println("buzdolabı\n");
        csvMalzeme();



        csvTarifler("omlet",1,1,2,3);


        String kisininyazdigisey = tatliInput;
        yeterliVarmi(kisininyazdigisey);

        if(sayac == 4){
            System.out.println("omlet hazırlayabilirsiniz");
        }










    }


    static String csvMalzeme(String isim, int miktar) {
        try {

            FileWriter writer = new FileWriter("malzemeler.csv", true);
            writer.write(isim + "," + miktar + "\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("dosya hatası");
        }
    }



    static void yeterliVarmi (String yapilanSey) throws IOException {



        int yumurtaMiktariMalzeme = miktarBulMalzeme("yumurta");
        int sutMiktariMalzeme = miktarBulMalzeme("sut");
        int unMiktariMalzeme = miktarBulMalzeme("un");
        int peynirMiktariMalzeme = miktarBulMalzeme("peynir");

        int yumurtaMiktariTarif = miktarBulTarif("yumurta");
        int sutMiktariTarif = miktarBulTarif("sut");
        int unMiktariTarif= miktarBulTarif("un");
        int peynirMiktariTarif = miktarBulTarif("peynir");

        if (kelimeVarMi(tatliInput,yapilanSey)) {

            if (yumurtaMiktariMalzeme < yumurtaMiktariTarif) {
                System.out.println(yapilanSey + "için yeterli malzemeniz yoktur");
            }
            if (sutMiktariMalzeme < sutMiktariTarif) {
                System.out.println(yapilanSey + "için yeterli sutunuz yok");
            }
            if (unMiktariMalzeme < unMiktariTarif) {
                System.out.println(yapilanSey + "için yeterli ununuz yok");
            }
            if (peynirMiktariMalzeme < peynirMiktariTarif) {
                System.out.println(yapilanSey + "için yeterli peyniriniz yok");
            }

            if (yumurtaMiktariMalzeme > yumurtaMiktariTarif) {
                sayac++;
            }
            if (sutMiktariMalzeme > sutMiktariTarif) {
               sayac++;
            }
            if (unMiktariMalzeme > unMiktariTarif) {
                sayac++;
            }
            if (peynirMiktariMalzeme > peynirMiktariTarif) {
                sayac++;
            }

        }
    }



    static void csvTarifler(String isim, int sut,int peynir,int un,int yumurta) {
        try {

            FileWriter writer = new FileWriter("tarifler.csv", true);
            writer.write(isim + "," + sut + "," + peynir + "," + un + "," + yumurta +"\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("dosya hatası");
        }
    }



    static int miktarBulMalzeme(String aranan) throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("malzemeler.csv"))) {

            String satir;

            while ((satir = reader.readLine()) != null) {
                String[] veri = satir.split(",");

                if (veri[1].equalsIgnoreCase(aranan)) {
                    return Integer.parseInt(veri[2]);
                }
            }
        }

        return 0;
    }


    static int miktarBulTarif(String aranan) throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("tarifler.csv"))) {

            String satir;

            while ((satir = reader.readLine()) != null) {
                String[] veri = satir.split(",");

                if (veri[1].equalsIgnoreCase(aranan)) {
                    return Integer.parseInt(veri[2]);
                }
            }
        }

        return 0;
    }


    public static boolean kelimeVarMi(String Input, String expected) {


        String[] kelimeler = Input.split("\\s+");
        for (String eleman : kelimeler) {
            if (eleman.equals(expected)) {

                return true;
            }
        }
        return false;
    }


}

