package assignments.assignment3;

import java.util.ArrayList;

class Mahasiswa extends ElemenFasilkom {

    /* TODO: Silahkan menambahkan visibility pada setiap method dan variabel apabila diperlukan */

    private ArrayList<MataKuliah> daftarMataKuliah = new ArrayList<>();
    private long npm;
    private String tanggalLahir;
    private String jurusan;

    Mahasiswa(String nama, long npm) {
        this.setNama(nama);
        this.npm = npm;
        this.jurusan = extractJurusan(npm);
        this.tanggalLahir = extractTanggalLahir(npm);
    }

    void addMatkul(MataKuliah mataKuliah) {
        if (daftarMataKuliah.contains(mataKuliah)) {
            System.out.printf("[DITOLAK] %s telah diambil sebelumnya\n", mataKuliah);
        } else if (!mataKuliah.available()) {
            System.out.printf("[DITOLAK] %s telah penuh kapasitasnya\n", mataKuliah);
        } else {
            daftarMataKuliah.add(mataKuliah);
            mataKuliah.addMahasiswa(this);
            System.out.printf("%s berhasil menambahkan mata kuliah %s\n",
                    this, mataKuliah);
        }
    }

    void dropMatkul(MataKuliah mataKuliah) {
        if (daftarMataKuliah.contains(mataKuliah)) {
            daftarMataKuliah.remove(mataKuliah);
            mataKuliah.dropMahasiswa(this);
            System.out.printf("%s berhasil drop mata kuliah %s\n",
                    this, mataKuliah);
        } else {
            System.out.printf("[DITOLAK] %s belum pernah diambil\n", mataKuliah);
        }
    }

    public String extractJurusan(long npm) {
        String jurusanCode = (npm + "").substring(2, 4);
        return switch (jurusanCode) {
            case "01" -> "Ilmu Komputer";
            case "02" -> "Sistem Informasi";
            default -> "invalid";
        };
    }

    public String extractTanggalLahir(long npm) {
        String birthDate = (npm + "").substring(4, 12);
        String date = birthDate.substring(0, 2).replaceFirst("^0", "");
        String month = birthDate.substring(2, 4).replaceFirst("^0", "");
        String year = birthDate.substring(4);
        return String.format("%s-%s-%s", date, month, year);
    }

    public void ringkasan() {
        System.out.printf("""
                        Nama: %s
                        Tanggal lahir: %s
                        Jurusan: %s
                        Daftar Mata Kuliah:
                        """,
                getNama(), tanggalLahir, jurusan);
        if (daftarMataKuliah.isEmpty()) {
            System.out.println("Belum ada mata kuliah yang diambil");
        } else {
            int count = 1;
            for (MataKuliah elemen : daftarMataKuliah) {
                System.out.printf("%d. %s\n", count, elemen);
                count++;
            }
        }
    }

    public ArrayList<MataKuliah> getDaftarMataKuliah() {
        return daftarMataKuliah;
    }
}