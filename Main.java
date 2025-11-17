import java.io.*;
import java.util.*;

public class FileSplit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nama file input: ");
        String inputFile = sc.nextLine();

        System.out.print("Jumlah file output: ");
        int jumlahBagian = sc.nextInt();

        Queue<String> antrianTeks = new LinkedList<>();

        // Membaca file dan memasukkannya ke dalam Queue
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String baris;
            while ((baris = br.readLine()) != null) {
                antrianTeks.add(baris);
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan membaca file!");
            return;
        }

        int totalBaris = antrianTeks.size();
        int barisPerFile = (int) Math.ceil((double) totalBaris / jumlahBagian);

        System.out.println("\nMemproses pemotongan file...\n");

        // Memotong Queue ke beberapa file output
        for (int i = 1; i <= jumlahBagian; i++) {
            String outputName = "output_" + i + ".txt";

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputName))) {
                for (int j = 0; j < barisPerFile && !antrianTeks.isEmpty(); j++) {
                    bw.write(antrianTeks.poll());
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Gagal menulis ke file " + outputName);
            }

            System.out.println("Bagian " + i + ": " + outputName + " selesai dibuat");
        }

        sc.close();
    }
}
