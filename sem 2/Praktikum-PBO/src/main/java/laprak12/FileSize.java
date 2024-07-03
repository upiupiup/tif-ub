package laprak12;
import java.io.File;

public class FileSize {
    public static void main(String[] args) {
        String filePath = "test.txt";
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            long fileSizeInBytes = file.length();

            if (fileSizeInBytes < 1024 * 1024) {
                double fileSizeInKB = fileSizeInBytes / 1024.0;
                System.out.printf("Ukuran file: %.2f KB%n", fileSizeInKB);
            } else {
                double fileSizeInMB = fileSizeInBytes / (1024.0 * 1024.0);
                System.out.printf("Ukuran file: %.2f MB%n", fileSizeInMB);
            }
        } else {
            System.out.println("File tidak ditemukan atau bukan file biasa.");
        }
    }
}

