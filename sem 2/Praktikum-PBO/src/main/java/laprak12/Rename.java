package laprak12;
import java.io.File;

public class Rename {

    public static void main(String[] args) {
        var file = new File("test.txt");
        var fileBaru = new File("test-baru.txt");

        if (file.exists() && !fileBaru.exists()) {
            var result = file.renameTo(fileBaru);
            System.out.println((result ? "Berhasil" : "Gagal") + " mengubah nama file");
        } else {
            System.out.println("File tidak ada atau file dengan nama yang baru sudah ada");
        }
    }
}

