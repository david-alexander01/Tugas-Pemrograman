package assignments.assignment3;

import java.util.ArrayList;

class MataKuliah {

    private String nama;
    private int kapasitas;
    private Dosen dosen;
    private ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

    MataKuliah(String nama, int kapasitas) {
        this.nama = nama;
        this.kapasitas = kapasitas;
    }

    /**
     * Adds Mahasiswa object to daftarMahasiswa.
     *
     * @param mahasiswa Mahasiswa: mahasiswa to add.
     */
    void addMahasiswa(Mahasiswa mahasiswa) {
        daftarMahasiswa.add(mahasiswa);
    }

    /**
     * Removes Mahasiswa object from daftarMahasiswa.
     *
     * @param mahasiswa Mahasiswa: mahasiswa to remove.
     */
    void dropMahasiswa(Mahasiswa mahasiswa) {
        daftarMahasiswa.remove(mahasiswa);
    }

    /**
     * Sets this.dosen attribute.
     *
     * @param dosen Dosen: dosen to set.
     */
    void addDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    /**
     * Removes this.dosen attributes (sets it to null).
     */
    void dropDosen() {
        this.dosen = null;
    }

    Dosen getDosen() {
        return dosen;
    }

    /**
     * Checks if MataKuliah has empty slot for Mahasiswa.
     *
     * @return true if max capacity has been reached else false.
     */
    boolean available() {
        return daftarMahasiswa.size() < kapasitas;
    }

    /**
     * Print attributes of this instance.
     */
    void ringkasan() {
        System.out.printf("""
                Nama mata kuliah: %s
                Jumlah mahasiswa: %d
                Kapasitas: %d
                Dosen pengajar: %s
                Daftar mahasiswa yang mengambil mata kuliah ini:
                """, this, daftarMahasiswa.size(), kapasitas, (dosen != null) ? dosen : "Belum ada");
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini");
        } else {
            int count = 1;
            for (Mahasiswa mahasiswa : daftarMahasiswa) {
                System.out.printf("%d. %s\n", count, mahasiswa);
                count++;
            }
        }
    }

    @Override
    public String toString() {
        return nama;
    }

}
