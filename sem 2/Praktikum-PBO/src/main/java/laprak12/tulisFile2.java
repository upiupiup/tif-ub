package laprak12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class tulisFile2 {
    public static void main(String[] args) {
        var keyboard = new Scanner(System.in);
        var path = Paths.get("test.txt");
        System.out.print("Masukkan teks yang akan disimpan: ");

        var text = keyboard.nextLine();
        try {
            Files.writeString(path, text);
        } catch (IOException e) {
            System.err.println("Gagal menulis ke file");
        }
    }
}

