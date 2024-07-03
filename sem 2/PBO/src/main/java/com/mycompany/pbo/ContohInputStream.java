/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo;

import java.util.*;
import java.io.*;

public class ContohInputStream {
    public static void main(String[] args) {
        String namaFile;
        byte[] bufferData = new byte[1024]; // Buffer untuk menyimpan data yang dibaca
        Scanner input = new Scanner(System.in);

        // Meminta user memasukkan nama file yang akan dibaca
        System.out.print("Masukkan nama file yang akan dibaca: ");
        namaFile = input.nextLine();

        // Membaca data dari file menggunakan FileInputStream
        try {
            FileInputStream fileInput = new FileInputStream(namaFile);
            int bytesRead = fileInput.read(bufferData); // Membaca data dari file ke buffer
            
            while (bytesRead != -1) {
                // Menampilkan data yang dibaca ke layar
                System.out.print(new String(bufferData, 0, bytesRead));
                bytesRead = fileInput.read(bufferData); // Membaca sisa data
            }
            
            fileInput.close(); // Menutup stream file
        } catch (FileNotFoundException e) {
            System.err.println("File tidak ditemukan: " + namaFile);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca file");
        }

        System.out.println("\nPembacaan file selesai\n");
    }
}

