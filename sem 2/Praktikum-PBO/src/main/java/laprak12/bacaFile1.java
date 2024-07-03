
package laprak12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class bacaFile1 {
    public static void main(String[] args) {
        var filename = "test.txt";
        var file = new File(filename);
        
        if (file.exists() && file.canRead()) {
            try (var reader = new FileReader(filename);
                 var buffer = new BufferedReader(reader)) {
                
                var line = buffer.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = buffer.readLine();
                }
            } catch (IOException e) {
                System.err.println("Gagal membaca file");
            }
        } else {
            System.out.println("File tidak ada atau tidak bisa dibaca");
        }
    }
}

