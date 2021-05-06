package assignments.assignment3;

import java.util.ArrayList;

class MataKuliah {

    /* TODO: Silahkan menambahkan visibility pada setiap method dan variabel apabila diperlukan */

    private String nama;
    private int kapasitas;
    private Dosen dosen;
    private ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

    MataKuliah(String nama, int kapasitas) {
        this.nama = nama;
        this.kapasitas = kapasitas;
    }

    void addMahasiswa(Mahasiswa mahasiswa) {
        daftarMahasiswa.add(mahasiswa);
    }

    void dropMahasiswa(Mahasiswa mahasiswa) {
        daftarMahasiswa.remove(mahasiswa);
    }

    void addDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    void dropDosen() {
        this.dosen = null;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public String toString() {
        return nama;
    }

    public boolean available(){return daftarMahasiswa.size() < kapasitas;}

    public void ringkasan() {
        System.out.printf("""
                Nama mata kuliah: %s
                Jumlah mahasiswa: %d
                Kapasitas: %d
                Dosen pengajar: %s
                Daftar mahasiswa yang mengambil mata kuliah ini:
                """, this, daftarMahasiswa.size(), kapasitas, (dosen != null) ? dosen : "Belum ada");
        if (daftarMahasiswa.isEmpty()){
            System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini");
        } else{
            int count = 1;
            for (Mahasiswa mahasiswa: daftarMahasiswa){
                System.out.printf("%d. %s\n", count, mahasiswa);
                count++;
            }
        }
    }
}