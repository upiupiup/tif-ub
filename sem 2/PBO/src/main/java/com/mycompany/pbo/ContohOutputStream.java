/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo;

import java.io.*;
import java.util.Scanner;

public class ContohOutputStream {
    public static void main(String[] args) {
        String namaFile, data;
        byte[] bufferData = new byte[1024]; // Buffer untuk menyimpan data input
        Scanner input = new Scanner(System.in);

        // Meminta user memasukkan nama file yang akan dibuat
        System.out.print("Masukkan nama file yang akan dibuat: ");
        namaFile = input.nextLine();

        // Meminta user memasukkan data teks
        System.out.print("\nMasukkan data teks: ");
        try {
            System.in.read(bufferData); // Membaca data dari input user ke buffer
        } catch (IOException e) {
            System.err.println("Data tak terbaca");
        }

        // Menulis data ke file menggunakan FileOutputStream
        try {
            FileOutputStream fileOutput = new FileOutputStream(namaFile);
            fileOutput.write(bufferData, 0, bufferData.length); // Menulis data dari buffer ke file
            fileOutput.close(); // Menutup stream file
        } catch (IOException e) {
            System.err.println("Maaf, file gagal dibuat & ditulis");
            System.exit(1); // Keluar dengan status error
        }

        System.out.println("\nFile berhasil dibuka dan diisi\n");
    }
}
