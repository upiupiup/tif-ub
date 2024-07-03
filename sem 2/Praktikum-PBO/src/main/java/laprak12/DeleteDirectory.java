package laprak12;
import java.io.File;

public class DeleteDirectory {
    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\Aufii\\Documents\\folder";
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] filesList = directory.listFiles();
            
            if (filesList != null && filesList.length > 0) {
                for (File file : filesList) {
                    if (file.isFile()) {
                        if (file.delete()) {
                            System.out.println("File " + file.getName() + " berhasil dihapus.");
                        } else {
                            System.out.println("File " + file.getName() + " gagal dihapus.");
                        }
                    }
                }
            }
            
            if (directory.delete()) {
                System.out.println("Direktori " + directoryPath + " berhasil dihapus.");
            } else {
                System.out.println("Direktori " + directoryPath + " gagal dihapus.");
            }
        } else {
            System.out.println("Direktori tidak ditemukan atau bukan direktori.");
        }
    }
}

