package LC11;
import java.util.*;

public class LC11_1 {
    
    public static int hitungHuruf(String kata, char character) {
        int count;
        count = 0;
        
        for (int i = 0; i < kata.length(); i++) {
            if (kata.charAt(i) == character) {
                count++;
            }
        }
        return count;
    }
    
    public static void sort(String[] kata, int[] counts) {
       int jumlahHuruf = counts.length;
        for (int i = 0; i < jumlahHuruf - 1; i++) {
            for (int j = 0; j < jumlahHuruf - i - 1; j++) {
                if (counts[j] > counts[j + 1]) {
                    int temp = counts[j];
                    counts[j] = counts[j + 1];
                    counts[j + 1] = temp;
                    
                    String tempKata = kata[j];
                    kata[j] = kata[j + 1];
                    kata[j + 1] = tempKata;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String huruf;
        String inputKata;
        String[] kata;
        int[] jumlahHuruf;
        
        // input huruf yang ingin dicari
        huruf = input.nextLine();
        
        // input kata dari panitia
        inputKata = input.nextLine();
        kata = inputKata.split(" ");
        
        jumlahHuruf = new int[kata.length];
        
        for (int i = 0; i < kata.length; i++) {
            jumlahHuruf[i] = hitungHuruf(kata[i], huruf.charAt(0));
        }
        
        sort(kata, jumlahHuruf);
        
        System.out.println("Hasil Searching");
        for (int i = 0; i < kata.length; i++) {
            System.out.println(kata[i] + " = " + jumlahHuruf[i]);
        }
        
        input.close();
    }
    
}
