package laprak12;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class tulisFile3 {
    public static void main(String[] args) {
        var keyboard = new Scanner(System.in);
        System.out.print("Masukkan teks yang akan disimpan: ");

        var text = keyboard.nextLine();
        try (var writer = new PrintWriter("test.txt")) {
            writer.println(text);
        } catch (IOException e) {
            System.err.println("Gagal menulis ke file");
        }
    }
}

