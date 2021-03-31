package assignments.assignment2;

public class MataKuliah {
    private final String kode;
    private final String nama;
    private final int sks;
    private final int kapasitas;
    private final Mahasiswa[] daftarMahasiswa;

    /**
     * Initialize attributes
     */
    public MataKuliah(String kode, String nama, int sks, int kapasitas) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.kapasitas = kapasitas;
        this.daftarMahasiswa = new Mahasiswa[kapasitas];
    }

    /**
     * @return empty index in daftarMahasiswa, -1 if full.
     */
    private int findEmptyMahasiswa() {
        for (int i = 0; i < this.daftarMahasiswa.length; i++) {
            if (this.daftarMahasiswa[i] == null)
                return i;
        }
        return -1;
    }

    /**
     * @param mahasiswa mahasiswa to find.
     * @return index for a given mahasiswa.
     */
    private int findMahasiswaIndex(Mahasiswa mahasiswa) {
        for (int i = 0; i < this.daftarMahasiswa.length; i++) {
            if (this.daftarMahasiswa[i] == mahasiswa)
                return i;
        }
        return -1;
    }

    /**
     * @param mahasiswa mahasiswa to add to array.
     */
    public void addMahasiswa(Mahasiswa mahasiswa) {
        this.daftarMahasiswa[findEmptyMahasiswa()] = mahasiswa;
    }

    /**
     * @param mahasiswa mahasiswa to remove from array.
     */
    public void dropMahasiswa(Mahasiswa mahasiswa) {
        this.daftarMahasiswa[findMahasiswaIndex(mahasiswa)] = null;
    }

    /**
     * @return true if array is full else false.
     */
    public boolean available() {
        return findEmptyMahasiswa() >= 0;
    }

    /**
     * @return name.
     */
    public String toString() {
        return this.nama;
    }

    /**
     * @return sks.
     */
    public int getSKS() {
        return this.sks;
    }

    /**
     * @return code.
     */
    public String getKode() {
        return this.kode;
    }

    /**
     * @return mahasiswa count.
     */
    public int getJumlahMahasiswa() {
        int count = 0;
        for (Mahasiswa mahasiswa : this.daftarMahasiswa) {
            if (mahasiswa != null)
                count++;
        }
        return count;
    }

    /**
     * @return capacity.
     */
    public int getKapasitas() {
        return this.kapasitas;
    }

    /**
     * @return daftarMahasiswa array.
     */
    public Mahasiswa[] getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

}
