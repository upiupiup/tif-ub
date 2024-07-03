
package LC11;

import java.util.*;

public class LC11_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int jumlahSepatu;
        int[] ukuranSepatu;
        int jumlahCari;
        int[] searchUkuran;
        
        
        jumlahSepatu = scanner.nextInt();
        ukuranSepatu = new int[jumlahSepatu];

        for (int i = 0; i < jumlahSepatu; i++) {
            ukuranSepatu[i] = scanner.nextInt();
        }

        jumlahCari = scanner.nextInt();
        searchUkuran = new int[jumlahCari];

        for (int i = 0; i < jumlahCari; i++) {
            searchUkuran[i] = scanner.nextInt();
        }

        bubbleSort(ukuranSepatu);

        printArray(ukuranSepatu, jumlahSepatu);

        searchAndPrint(ukuranSepatu, searchUkuran);

        scanner.close();
    }

    // kode github
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    // Menukar arr[j] dan arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // kode github
    public static int binarySearch(int[] arr, int data) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (data == arr[m]) {
                return m;
            }
            if (data < arr[m]) {
                r = m + 1;
            }
            if (data > arr[m]) {
                l = m - 1;
            }
        }
        return -1;
    }
    
    public static void printArray(int[] ukuranSepatu, int jumlahSepatu) {
        System.out.print("[");
        for (int i = 0; i < jumlahSepatu; i++) {
            System.out.print(ukuranSepatu[i]);
            if (i < jumlahSepatu - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    public static void searchAndPrint(int[] arr, int[] searchArr) {
        for (int ukuran : searchArr) {
            int posisi = binarySearch(arr, ukuran);
            if (posisi != -1) {
                System.out.println("Ukuran sepatu " + ukuran + " ditemukan pada rak posisi " + posisi);
            } else {
                System.out.println("Ukuran sepatu " + ukuran + " tidak ditemukan dalam rak");
            }
        }
    }
    
}
