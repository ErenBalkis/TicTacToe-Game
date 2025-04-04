package XOX_Oyunu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class XOX_Oyunu {
    
    static ArrayList<Integer>oyuncununHucresi = new ArrayList<>();
    static ArrayList<Integer>bilgisayarHucresi = new ArrayList<>();

    public static void main(String[] args) {
        
        /*char[][] oyunTahtasi={
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '} 
        };*/
        char[][] oyunTahtasi={
            {'1','|','2','|','3'},
            {'-','+','-','+','-'},
            {'4','|','5','|','6'},
            {'-','+','-','+','-'},
            {'7','|','8','|','9'} 
        };
        OyunTahtasiniYazdir(oyunTahtasi);
        
        while(true){
        Scanner input=new Scanner(System.in);
        System.out.println("Hucre giriniz:(1-9)");
        int oyuncuHucresi=input.nextInt();
        if(oyuncuHucresi<=0 || oyuncuHucresi>9){
                System.out.println("Lutfen (1-9) araliginda bir deger giriniz:");
                oyuncuHucresi =input.nextInt();
            }
        while(oyuncununHucresi.contains(oyuncuHucresi) || bilgisayarHucresi.contains(oyuncuHucresi)){
            System.out.println("Bu alan dolu! Yeni hucre giriniz:");
            oyuncuHucresi =input.nextInt();
        }
        hucreSecimi(oyunTahtasi,oyuncuHucresi,"oyuncu");
        String sonuc = Kazanan();
        if(sonuc.length()>0){
            System.out.println(sonuc);
            break;
        }
        
        Random rasgele =new Random();
        int bilgHucresi =rasgele.nextInt(9) + 1;
        while(oyuncununHucresi.contains(bilgHucresi) || bilgisayarHucresi.contains(bilgHucresi)){
            bilgHucresi =rasgele.nextInt(9) + 1;
        }
        hucreSecimi(oyunTahtasi,bilgHucresi,"bilgisayar");
        
        OyunTahtasiniYazdir(oyunTahtasi);
        sonuc = Kazanan();
        if(sonuc.length()>0){
            System.out.println(sonuc);
            break;
        }
        
        }
    }
    
    public static void OyunTahtasiniYazdir(char[][] oyunTahtasi){
        for(char[] satir: oyunTahtasi){
            for(char c:satir){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    public static void hucreSecimi (char[][] oyunTahtasi, int hucre, String oyuncu){
        char sembol=' ';
        if(oyuncu.equals("oyuncu")){
            sembol='X';
            oyuncununHucresi.add(hucre);
        } else if(oyuncu.equals("bilgisayar")){
            sembol='O';
            bilgisayarHucresi.add(hucre);
        }
        
        switch(hucre){
            case 1:
                oyunTahtasi[0][0]=sembol;
                break;
            case 2:
                oyunTahtasi[0][2]=sembol;
                break;
            case 3:
                oyunTahtasi[0][4]=sembol;
                break;
            case 4:
                oyunTahtasi[2][0]=sembol;
                break;
            case 5:
                oyunTahtasi[2][2]=sembol;
                break;
            case 6:
                oyunTahtasi[2][4]=sembol;
                break;
            case 7:
                oyunTahtasi[4][0]=sembol;
                break;
            case 8:
                oyunTahtasi[4][2]=sembol;
                break;
            case 9:
                oyunTahtasi[4][4]=sembol;
                break;
            default:
                break;
            
        }
    }
    
    public static String Kazanan(){
        List ustSatir = Arrays.asList(1,2,3);
        List ortaSatir = Arrays.asList(4,5,6);
        List altSatir = Arrays.asList(7,8,9);
        List solSutun = Arrays.asList(1,4,7);
        List ortaSutun = Arrays.asList(2,5,8);
        List sagSutun = Arrays.asList(3,6,9);
        List capraz1 = Arrays.asList(1,5,9);
        List capraz2 = Arrays.asList(7,5,3);
        
        List<List> kazanan = new ArrayList<>();
        kazanan.add(ustSatir);
        kazanan.add(ortaSatir);
        kazanan.add(altSatir);
        kazanan.add(solSutun);
        kazanan.add(ortaSutun);
        kazanan.add(sagSutun);
        kazanan.add(capraz1);
        kazanan.add(capraz2);
        
        for(List l: kazanan){
            if(oyuncununHucresi.containsAll(l)){
                return "Tebrikler KAZANDINIZ! ^_^ ";
            } else if(bilgisayarHucresi.containsAll(l)){
                return "Bilgisayar kazandi! -_-"; // :(  :/
            } else if(oyuncununHucresi.size() + bilgisayarHucresi.size() ==9 ){
                return "Berabere! ^_-";
            }
        }
        return "";
    }   
}