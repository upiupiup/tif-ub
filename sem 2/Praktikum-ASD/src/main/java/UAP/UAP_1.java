package UAP;
import java.util.*;

public class UAP_1 {
    private static boolean tinggiSampe(int[] tinggiTembok, int jumlahBlok, int tinggiTarget) {
        long blok = 0;
        blok = 0;
        int tinggiTembokLength;
        tinggiTembokLength = tinggiTembok.length;
        
        for (int i = 0; i < tinggiTembokLength; i++) {
            if (tinggiTembok[i] < tinggiTarget) {
                blok += (tinggiTarget - tinggiTembok[i]);
            }
            if (blok > jumlahBlok) {
                return false;
            }
        }
        
        return blok <= jumlahBlok;
    }
    
    public static void main(String[] args) {
        int jumlahKasus;
        int hasil;
        
        Scanner scanner = new Scanner(System.in);
        jumlahKasus = scanner.nextInt();
        
        for (int i = 0; i < jumlahKasus; i++) {
            int jumlahTembok;
            int jumlahBlok;
            int[] tinggiTembok;
            
            jumlahTembok = scanner.nextInt();
            jumlahBlok = scanner.nextInt();
            tinggiTembok = new int[jumlahTembok];
            
            for (int j = 0; j < jumlahTembok; j++) {
                tinggiTembok[j] = scanner.nextInt();
            }
            
            hasil = tinggiMax(tinggiTembok, jumlahBlok);
            System.out.println(hasil);
        }
    }
    
    private static int tinggiMax(int[] tinggiTembok, int jumlahBlok) {
        int rendah;
        int tinggi;
        int tinggiMaksimal;
        
        rendah = 1;
        tinggi = Integer.MAX_VALUE;
        tinggiMaksimal = 1;

        while (rendah <= tinggi) {
            int tengah;
            tengah = rendah + (tinggi - rendah) / 2;
            
            if (tinggiSampe(tinggiTembok, jumlahBlok, tengah)) {
                tinggiMaksimal = tengah;
                rendah = tengah + 1;
            } else {
                tinggi = tengah - 1;
            }
        }
        
        return tinggiMaksimal;
    }
    
}
