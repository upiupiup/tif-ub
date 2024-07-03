package UAP;

import java.util.*;

// Interface Perawatan
interface Perawatan {
    void treatment();
}

// Parent Class Tanaman
abstract class Tanaman {
    protected int masaHidup;
    protected int lamaHidup;
    protected double prosesBerbuah;
    protected double perkembangan;
    protected int buah;
    protected int berbuah;

    public Tanaman(int masaHidup, int berbuah, double perkembangan) {
        this.masaHidup = masaHidup;
        this.berbuah = berbuah;
        this.perkembangan = perkembangan;
        this.lamaHidup = 0;
        this.prosesBerbuah = 0;
        this.buah = 0;
    }

    public abstract void berkembang();

    public String status() {
        if (lamaHidup >= masaHidup) {
            return "Mati";
        }
        return "Hidup";
    }

    @Override
    public String toString() {
        return "Masa hidup: " + masaHidup + " hari\n" +
               "Umur tanaman: " + lamaHidup + " hari\n" +
               "Menghasilkan: " + buah + " buah\n" +
               "Status: " + status();
    }
}

// Class Tomat
class Tomat extends Tanaman implements Perawatan {
    public Tomat() {
        super(100, 100, 0.25);
    }

    @Override
    public void berkembang() {
        lamaHidup++;
        if (lamaHidup <= masaHidup) {
            prosesBerbuah += perkembangan;
            while (prosesBerbuah >= berbuah) {
                buah++;
                prosesBerbuah -= berbuah;
            }
        }
    }

    @Override
    public void treatment() {
        perkembangan += 0.05;
    }

    @Override
    public String toString() {
        return "Tomat\n" + super.toString();
    }
}

// Class Stroberi
class Stroberi extends Tanaman implements Perawatan {
    public Stroberi() {
        super(60, 150, 0.35);
    }

    @Override
    public void berkembang() {
        lamaHidup++;
        if (lamaHidup <= masaHidup) {
            prosesBerbuah += perkembangan;
            while (prosesBerbuah >= berbuah) {
                buah++;
                prosesBerbuah -= berbuah;
            }
        }
    }

    @Override
    public void treatment() {
        perkembangan += 0.05;
    }

    @Override
    public String toString() {
        return "Stroberi\n" + super.toString();
    }
}

// Class Persik
class Persik extends Tanaman implements Perawatan {
    public Persik() {
        super(180, 250, );
    }

    @Override
    public void berkembang() {
        lamaHidup++;
        if (lamaHidup <= masaHidup) {
            prosesBerbuah += perkembangan;
            while (prosesBerbuah >= berbuah) {
                buah++;
                prosesBerbuah -= berbuah;
            }
        }
    }

    @Override
    public void treatment() {
        perkembangan += 0.025;
    }

    @Override
    public String toString() {
        return "Persik\n" + super.toString();
    }
}

// Class Data
class Data {
    private static ArrayList<Tanaman> tanamans = new ArrayList<>();
    private static ArrayList<Tanaman> semuaTanaman = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void mulai() {
        System.out.print("Masukkan masa tanam (dalam bulan): ");
        int masaTanam = scanner.nextInt() * 30;

        for (int i = 0; i < 3; i++) {
            menanam();
        }

        System.out.println("----------------");
        System.out.println("KRUK KRUK KRUK");
        System.out.println("Pak Andi sedang menanam");
        System.out.println("PROSES PERKEMBANGAN");
        System.out.println("----------------");

        for (int hari = 1; hari <= masaTanam; hari++) {
    ArrayList<Integer> lokasi = new ArrayList<>();

    for (int i = 0; i < tanamans.size(); i++) {
        Tanaman tanaman = tanamans.get(i);

        if (tanaman.status().equals("Hidup")) {
            tanaman.berkembang();
        } else {
            System.out.println("Tanaman " + tanaman.getClass().getSimpleName() + " telah mati.");
            lokasi.add(i);
        }
    }

    for (int index : lokasi) {
        tanamans.remove(index);
        Tanaman tanamanBaru = menanam();
        tanamanBaru.berkembang();
    }

    if (hari % 90 == 0) {
        System.out.println("----------------");
        System.out.println("DILAKUKAN PERAWATAN");
        System.out.println("----------------");
        for (Tanaman tanaman : tanamans) {
            if (tanaman.status().equals("Hidup")) {
                ((Perawatan) tanaman).treatment();
            }
        }
    }
}

    }

    public static Tanaman menanam() {
        System.out.println("Mau menanam apa?");
        System.out.println("1. Tomat");
        System.out.println("2. Stroberi");
        System.out.println("3. Persik");
        int pilihan = scanner.nextInt();

        Tanaman tanaman;
        switch (pilihan) {
            case 1:
                tanaman = new Tomat();
                break;
            case 2:
                tanaman = new Stroberi();
                break;
            case 3:
                tanaman = new Persik();
                break;
            default:
                System.out.println("Pilihan tidak valid, menanam Tomat secara default.");
                tanaman = new Tomat();
                break;
        }

        tanamans.add(tanaman);
        semuaTanaman.add(tanaman);
        System.out.println(tanaman.getClass().getSimpleName() + " berhasil ditanam.");
        return tanaman;
    }

    public static void info() {
        System.out.println("");
        System.out.println("------LAPORAN TANAMAN------");
        System.out.println("");
        for (int i = 0; i < semuaTanaman.size(); i++) {
            System.out.println("Tanaman buah ke-" + (i + 1) + ":");
            System.out.println(semuaTanaman.get(i).toString());
            System.out.println();
        }
    }
}

// Main Class
public class UAP {
    public static void main(String[] args) {
        
        // header
        System.out.println("SELAMAT DATANG DI PROGRAM TRACKING TANAMAN");
        System.out.println("-------------------------------------------------------");
        System.out.println("Halo Pak Andi, apa yang ingin Anda lakukan kali ini?");
        System.out.println("");
        Data.mulai();
        Data.info();
    }
}