package assignments.assignment2;

import java.util.Scanner;

public class SistemAkademik {
    private static final int ADD_MATKUL = 1;
    private static final int DROP_MATKUL = 2;
    private static final int RINGKASAN_MAHASISWA = 3;
    private static final int RINGKASAN_MATAKULIAH = 4;
    private static final int KELUAR = 5;
    private static final Mahasiswa[] daftarMahasiswa = new Mahasiswa[100];
    private static final MataKuliah[] daftarMataKuliah = new MataKuliah[100];

    private final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        SistemAkademik program = new SistemAkademik();
        program.run();
    }

    private Mahasiswa getMahasiswa(long npm) {
        // Returns Mahasiswa object from npm
        for (Mahasiswa element : daftarMahasiswa) {
            if (element.getNPM() == npm)
                return element;
        }
        return null;
    }

    private MataKuliah getMataKuliah(String namaMataKuliah) {
        // Return MataKuliah object from name
        for (MataKuliah element : daftarMataKuliah) {
            if (element.toString().equals(namaMataKuliah))
                return element;
        }
        return null;
    }

    private void addMatkul() {
        // Adds MataKuliah for Mahasiswa
        System.out.println("\n--------------------------ADD MATKUL--------------------------\n");

        System.out.print("Masukkan NPM Mahasiswa yang akan melakukan ADD MATKUL : ");
        long npm = Long.parseLong(input.nextLine());
        Mahasiswa mahasiswa = getMahasiswa(npm);

        System.out.print("Banyaknya Matkul yang Ditambah: ");
        int banyakMatkul = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan nama matkul yang ditambah");
        for (int i = 0; i < banyakMatkul; i++) {
            System.out.print("Nama matakuliah " + (i + 1) + " : ");
            String namaMataKuliah = input.nextLine();
            MataKuliah matKul = getMataKuliah(namaMataKuliah);
            assert mahasiswa != null;
            mahasiswa.addMatkul(matKul);

        }
        System.out.println("\nSilakan cek rekap untuk melihat hasil pengecekan IRS.\n");
    }

    private void dropMatkul() {
        // Drops MataKuliah from Mahasiswa
        System.out.println("\n--------------------------DROP MATKUL--------------------------\n");

        System.out.print("Masukkan NPM Mahasiswa yang akan melakukan DROP MATKUL : ");
        long npm = Long.parseLong(input.nextLine());
        Mahasiswa mahasiswa = getMahasiswa(npm);

        assert mahasiswa != null;
        MataKuliah[] daftarMataKuliah = mahasiswa.getMataKuliah();
        // Checks if Mahasiswa is currently taking a MataKuliah
        boolean empty = true;
        for (MataKuliah element : daftarMataKuliah) {
            if (element != null) {
                empty = false;
                break;
            }
        }

        if (!empty) {
            System.out.print("Banyaknya Matkul yang Di-drop: ");
            int banyakMatkul = Integer.parseInt(input.nextLine());
            System.out.println("Masukkan nama matkul yang di-drop:");
            for (int i = 0; i < banyakMatkul; i++) {
                System.out.print("Nama matakuliah " + (i + 1) + " : ");
                String namaMataKuliah = input.nextLine();
                MataKuliah matKul = getMataKuliah(namaMataKuliah);
                mahasiswa.dropMatkul(matKul);
            }
            System.out.println("\nSilakan cek rekap untuk melihat hasil pengecekan IRS.\n");
        } else
            System.out.println("[DITOLAK] Belum ada mata kuliah yang diambil.");
    }

    private void ringkasanMahasiswa() {
        // Prints information about a Mahasiswa based on npm
        System.out.print("Masukkan npm mahasiswa yang akan ditunjukkan ringkasannya : ");
        long npm = Long.parseLong(input.nextLine());
        Mahasiswa mahasiswa = getMahasiswa(npm);
        assert mahasiswa != null;

        System.out.println("\n--------------------------RINGKASAN--------------------------\n");
        System.out.println("Nama: " + mahasiswa);
        System.out.println("NPM: " + npm);
        System.out.println("Jurusan: " + mahasiswa.getJurusan());
        System.out.println("Daftar Mata Kuliah: ");

        MataKuliah[] daftarMataKuliah = mahasiswa.getMataKuliah();
        // Checks if Mahasiswa is currently taking a MataKuliah
        boolean empty = true;
        for (MataKuliah element : daftarMataKuliah) {
            if (element != null) {
                empty = false;
                break;
            }
        }

        int count = 1;
        if (!empty) {
            for (MataKuliah element : daftarMataKuliah) {
                if (element != null) {
                    System.out.println(count + ". " + element);
                    count++;
                }
            }
        } else
            System.out.println("Belum ada mata kuliah yang diambil.");

        System.out.println("Total SKS: " + mahasiswa.getSKS());

        System.out.println("Hasil Pengecekan IRS:");
        mahasiswa.cekIRS();
    }

    private void ringkasanMataKuliah() {
        // Prints information about a MataKuliah based on name
        System.out.print("Masukkan nama mata kuliah yang akan ditunjukkan ringkasannya : ");
        String namaMataKuliah = input.nextLine();
        MataKuliah mataKuliah = getMataKuliah(namaMataKuliah);
        assert mataKuliah != null;

        System.out.println("\n--------------------------RINGKASAN--------------------------\n");
        System.out.println("Nama mata kuliah: " + mataKuliah);
        System.out.println("Kode: " + mataKuliah.getKode());
        System.out.println("SKS: " + mataKuliah.getSKS());
        System.out.println("Jumlah mahasiswa: " + mataKuliah.getJumlahMahasiswa());
        System.out.println("Kapasitas: " + mataKuliah.getKapasitas());
        System.out.println("Daftar mahasiswa yang mengambil mata kuliah ini: ");
        Mahasiswa[] daftarMahasiswa = mataKuliah.getDaftarMahasiswa();
        int count = 1;
        boolean empty = true;
        for (Mahasiswa element : daftarMahasiswa) {
            if (element != null) {
                empty = false;
                break;
            }
        }
        if (!empty) {
            for (Mahasiswa element : daftarMahasiswa) {
                if (element != null) {
                    System.out.println(count + ". " + element);
                    count++;
                }
            }
        } else
            System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini.");
    }

    private void daftarMenu() {
        // Prints main menu and get user choice
        int pilihan;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----------------------------MENU------------------------------\n");
            System.out.println("Silakan pilih menu:");
            System.out.println("1. Add Matkul");
            System.out.println("2. Drop Matkul");
            System.out.println("3. Ringkasan Mahasiswa");
            System.out.println("4. Ringkasan Mata Kuliah");
            System.out.println("5. Keluar");
            System.out.print("\nPilih: ");
            try {
                pilihan = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            System.out.println();
            if (pilihan == ADD_MATKUL) {
                addMatkul();
            } else if (pilihan == DROP_MATKUL) {
                dropMatkul();
            } else if (pilihan == RINGKASAN_MAHASISWA) {
                ringkasanMahasiswa();
            } else if (pilihan == RINGKASAN_MATAKULIAH) {
                ringkasanMataKuliah();
            } else if (pilihan == KELUAR) {
                System.out.println("Sampai jumpa!");
                exit = true;
            }
        }
    }

    private void run() {
        // Gets initial information (MataKuliah and Mahasiswa)
        System.out.println("====================== Sistem Akademik =======================\n");
        System.out.println("Selamat datang di Sistem Akademik Fasilkom!");

        System.out.print("Banyaknya Matkul di Fasilkom: ");
        int banyakMatkul = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan matkul yang ditambah");
        System.out.println("format: [Kode Matkul] [Nama Matkul] [SKS] [Kapasitas]");

        for (int i = 0; i < banyakMatkul; i++) {
            String[] dataMatkul = input.nextLine().split(" ", 4);
            int sks = Integer.parseInt(dataMatkul[2]);
            int kapasitas = Integer.parseInt(dataMatkul[3]);
            MataKuliah newMataKuliah = new MataKuliah(dataMatkul[0], dataMatkul[1], sks, kapasitas);
            for (int j = 0; j < daftarMataKuliah.length; j++) {
                if (daftarMataKuliah[j] == null) {
                    daftarMataKuliah[j] = newMataKuliah;
                    break;
                }
            }
        }

        System.out.print("Banyaknya Mahasiswa di Fasilkom: ");
        int banyakMahasiswa = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan data mahasiswa");
        System.out.println("format: [Nama] [NPM]");

        for (int i = 0; i < banyakMahasiswa; i++) {
            String[] dataMahasiswa = input.nextLine().split(" ", 2);
            long npm = Long.parseLong(dataMahasiswa[1]);
            Mahasiswa newMahasiswa = new Mahasiswa(dataMahasiswa[0], npm);
            for (int j = 0; j < daftarMahasiswa.length; j++) {
                if (daftarMahasiswa[j] == null) {
                    daftarMahasiswa[j] = newMahasiswa;
                    break;
                }
            }
        }
        daftarMenu();
        input.close();
    }
}
