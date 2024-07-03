package LC12;
import java.util.Scanner;

class HashTable {
    private String[] table;
    private int size;
    private boolean[] dihapus;

    public HashTable(int size) {
        this.size = size;
        this.table = new String[size];
        this.dihapus = new boolean[size];
    }

    private int fungsiHash(int nis) {
        return nis % size;
    }

    private int searchIndex(int nis) {
        int indeks;
        int indeksAsli;
        
        indeks = fungsiHash(nis);
        indeksAsli= indeks;
        while (table[indeks] != null || dihapus[indeks]) {
            if (table[indeks] != null) {
                String[] bagian = table[indeks].split(" ", 2);
                int nisTersimpan;
                nisTersimpan = Integer.parseInt(bagian[0]);
                if (nisTersimpan == nis) {
                    return indeks;
                }
            }
            indeks = (indeks + 1) % size;
            if (indeks == indeksAsli) break;
        }
        return -1;
    }

    public void insert(int nis, String nama) {
        int indeks;
        int indeksAsli;
        
        indeks = fungsiHash(nis);
        indeksAsli= indeks;
        while (table[indeks] != null && !dihapus[indeks]) {
            indeks = (indeks + 1) % size;
            if (indeks == indeksAsli) {
                return;
            }
        }
        table[indeks] = nis + " " + nama;
        dihapus[indeks] = false;
    }

    public void search(int nis) {
        int indeks;
        
        indeks = searchIndex(nis);
        if (indeks != -1) {
            String[] bagian = table[indeks].split(" ", 2);
            System.out.println("NIS " + nis + " adalah " + bagian[1] + ", ditemukan pada indeks " + indeks);
        } else {
            System.out.println("Data siswa dengan NIS " + nis + " tidak ditemukan");
        }
    }

    public void delete(int nis) {
        int indeks = searchIndex(nis);
        if (indeks != -1) {
            table[indeks] = null;
            dihapus[indeks] = true;
            System.out.println("Data siswa dengan NIS " + nis + " berhasil dihapus");
        } else {
            System.out.println("Data siswa dengan NIS " + nis + " tidak ditemukan");
        }
    }
}

public class LC12_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ukuranTabel;
        int jumlahPerintah;
        
        ukuranTabel = scanner.nextInt();
        jumlahPerintah = scanner.nextInt();
        scanner.nextLine();

        HashTable hashTable = new HashTable(ukuranTabel);

        for (int i = 0; i < jumlahPerintah; i++) {
            String perintah = scanner.nextLine().trim();
            if (perintah.equals("tambah")) {
                String data;
                data = scanner.nextLine().trim();
                if (data.isEmpty()) {
                    System.out.println("Berhasil memasukkan 0 data siswa");
                } else {
                    String[] siswa = data.split(";");
                    for (String siswaPer : siswa) {
                        String[] bagian = siswaPer.trim().split(" ", 2);
                        int nis;
                        nis = Integer.parseInt(bagian[0].trim());
                        String nama = bagian[1].trim();
                        hashTable.insert(nis, nama);
                    }
                    System.out.println("Berhasil memasukkan " + siswa.length + " data siswa");
                }
            } else if (perintah.equals("cari")) {
                int nis;
                nis = Integer.parseInt(scanner.nextLine().trim());
                hashTable.search(nis);
            } else if (perintah.equals("hapus")) {
                int nis;
                nis = Integer.parseInt(scanner.nextLine().trim());
                hashTable.delete(nis);
            }
        }

        scanner.close();
    }
}
