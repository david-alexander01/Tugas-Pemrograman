package assignments.assignment2;

import java.util.Arrays;

public class Mahasiswa {
    private MataKuliah[] mataKuliah = new MataKuliah[10];
    private String[] masalahIRS;
    private int totalSKS;
    private String nama;
    private String jurusan;
    private long npm;

    public Mahasiswa(String nama, long npm) {
        // Initialize attributes
        this.nama = nama;
        this.npm = npm;
        // TODO: find a way to import this.jurusan = getJurusan(npm);
    }

    public void addMatkul(MataKuliah mataKuliah) {
        /* TODO: implementasikan kode Anda di sini */
        if (this.mataKuliah[9] != null)
            System.out.println("[DITOLAK] Maksimal mata kuliah yang diambil hanya 10.");
        else if (Arrays.binarySearch(this.mataKuliah, mataKuliah) >= 0)
            System.out.printf("[DITOLAK] %s telah diambil sebelumnya.", mataKuliah);
        else if (!mataKuliah.available())
            System.out.printf("[DITOLAK] %s telah penuh kapasitasnya.", mataKuliah);
        else {
            mataKuliah.addMahasiswa(this);
            totalSKS += mataKuliah.getSKS();
        }
    }

    public void dropMatkul(MataKuliah mataKuliah) {
        /* TODO: implementasikan kode Anda di sini */
        if (Arrays.binarySearch(this.mataKuliah, mataKuliah) < 0)
            System.out.printf("[DITOLAK] %s belum pernah diambil.", mataKuliah);
        else {
            mataKuliah.dropMahasiswa(this);
            totalSKS -= mataKuliah.getSKS();
        }
    }


    public void cekIRS() {
        /* TODO: implementasikan kode Anda di sini */
        masalahIRS = new String[10];
        int index = 0;
        for (MataKuliah mataKuliah : this.mataKuliah) {
            if (!mataKuliah.getKode().equals("CS") && !mataKuliah.getKode().equals(this.jurusan)){
                masalahIRS[index] = "Mata kuliah " + mataKuliah + " tidak dapat diambil jurusan " + this.jurusan;
                index += 1;
            }
        }
        if (this.totalSKS > 24)
            masalahIRS[index] = "SKS yang Anda ambil lebih dari 24";
    }


    public String toString() {
        /* TODO: implementasikan kode Anda di sini */
        return this.nama;
    }

}
