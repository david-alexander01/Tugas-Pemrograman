package assignments.assignment2;

import java.util.Arrays;

public class MataKuliah {
    private String kode;
    private String nama;
    private int sks;
    private int kapasitas;
    private Mahasiswa[] daftarMahasiswa;

    public MataKuliah(String kode, String nama, int sks, int kapasitas) {
        // Initialize attributes
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.kapasitas = sks;
        this.daftarMahasiswa = new Mahasiswa[kapasitas];
    }

    // temporary test method
    // TODO: REMOVE THIS
    public static void main(String[] args) {
        MataKuliah a = new MataKuliah("IK", "POK", 3, 100);
        System.out.println("TEST SOUT " + a);
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        // Add new mahasiswa to list, keeps list sorted
        int key = -Arrays.binarySearch(daftarMahasiswa, mahasiswa) - 1;
        for (int i = daftarMahasiswa.length; i > key; i--) {
            daftarMahasiswa[i] = daftarMahasiswa[i - 1];
        }
        daftarMahasiswa[key] = mahasiswa;
    }

    public void dropMahasiswa(Mahasiswa mahasiswa) {
        // Remove mahasiswa from list, keeps list sorted
        int key = Arrays.binarySearch(daftarMahasiswa, mahasiswa);
        daftarMahasiswa[key] = null;
        for (int i = key; i < daftarMahasiswa.length; i++) {
            daftarMahasiswa[i] = daftarMahasiswa[i + 1];
        }
    }

    public boolean available() {
        return daftarMahasiswa[kapasitas - 1] == null;
    }

    public String toString() {
        /* TODO: implementasikan kode Anda di sini */
        return this.nama;
    }

    public int getSKS() {
        return this.sks;
    }

    public String getKode() {
        return this.kode;
    }
}
