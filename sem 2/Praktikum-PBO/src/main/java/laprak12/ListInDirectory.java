package laprak12;

import java.io.File;

public class ListInDirectory {
    public static void main(String[] args) {
        String directoryPath = "D:\\NetBeans Projects\\Praktikum-PBO\\src\\main\\java\\laprak12";
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] filesList = directory.listFiles();
            
            if (filesList != null && filesList.length > 0) {
                System.out.println("Daftar file di direktori " + directoryPath + ":");
                for (File file : filesList) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("Direktori kosong atau tidak ada file di dalamnya.");
            }
        } else {
            System.out.println("Direktori tidak ditemukan atau bukan direktori.");
        }
    }
}

