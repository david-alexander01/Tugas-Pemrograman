package assignments.assignment2;

public class MataKuliah {
    private final String kode;
    private final String nama;
    private final int sks;
    private final int kapasitas;
    private final Mahasiswa[] daftarMahasiswa;

    public MataKuliah(String kode, String nama, int sks, int kapasitas) {
        // Initialize attributes
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.kapasitas = kapasitas;
        this.daftarMahasiswa = new Mahasiswa[kapasitas];
    }

    private int findEmptyMahasiswa() {
        // Find empty index in daftarMahasiswa, returns -1 if full
        for (int i = 0; i < daftarMahasiswa.length; i++) {
            if (daftarMahasiswa[i] == null)
                return i;
        }
        return -1;
    }

    private int findMahasiswaIndex(Mahasiswa mahasiswa) {
        // Find index for a given mahasiswa
        for (int i = 0; i < daftarMahasiswa.length; i++) {
            if (daftarMahasiswa[i] == mahasiswa)
                return i;
        }
        return -1;
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        // Adds mahasiswa to array
        daftarMahasiswa[findEmptyMahasiswa()] = mahasiswa;
    }

    public void dropMahasiswa(Mahasiswa mahasiswa) {
        // Removes mahasiswa from array
        daftarMahasiswa[findMahasiswaIndex(mahasiswa)] = null;
    }

    public boolean available() {
        // Checks if array is full
        return findEmptyMahasiswa() < 0;
    }

    public String toString() {
        // Returns name
        return this.nama;
    }

    public int getSKS() {
        // Returns sks
        return this.sks;
    }

    public String getKode() {
        // Returns code
        return this.kode;
    }

    public int getJumlahMahasiswa() {
        // Returns mahasiswa count
        int count = 0;
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa != null)
                count++;
        }
        return count;
    }

    public int getKapasitas() {
        // Returns capacity
        return kapasitas;
    }

    public Mahasiswa[] getDaftarMahasiswa() {
        // returns daftarMahasiswa array
        return daftarMahasiswa;
    }

}
