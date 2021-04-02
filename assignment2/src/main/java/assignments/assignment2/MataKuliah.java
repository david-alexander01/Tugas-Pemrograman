package assignments.assignment2;

public class MataKuliah {
    private final String kode;
    private final String nama;
    private final int sks;
    private final int kapasitas;
    private final Mahasiswa[] daftarMahasiswa;

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

    public void addMahasiswa(Mahasiswa mahasiswa) {
        this.daftarMahasiswa[findEmptyMahasiswa()] = mahasiswa;
    }

    public void dropMahasiswa(Mahasiswa mahasiswa) {
        this.daftarMahasiswa[findMahasiswaIndex(mahasiswa)] = null;
    }

    public boolean available() {
        return findEmptyMahasiswa() >= 0;
    }

    /**
     * @return name.
     */
    public String toString() {
        return this.nama;
    }

    public int getSKS() {
        return this.sks;
    }

    public String getKode() {
        return this.kode;
    }

    public String getJurusan() {
        return switch(this.kode){
            case "IK" -> "Ilmu Komputer";
            case "SI" -> "Sistem Informasi";
            default -> "Computer Science";
        };
    }
    public int getJumlahMahasiswa() {
        int count = 0;
        for (Mahasiswa mahasiswa : this.daftarMahasiswa) {
            if (mahasiswa != null)
                count++;
        }
        return count;
    }

    public int getKapasitas() {
        return this.kapasitas;
    }

    public Mahasiswa[] getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

}
