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

    // temporary test method
    // TODO: REMOVE THIS
    public static void main(String[] args) {
        MataKuliah a = new MataKuliah("IK", "POK", 3, 3);
        System.out.println("TEST SOUT " + a);
        System.out.println(a.available());
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        daftarMahasiswa[findEmptyMahasiswa()] = mahasiswa;
    }

    public void dropMahasiswa(Mahasiswa mahasiswa) {
        daftarMahasiswa[findMahasiswaIndex(mahasiswa)] = null;
    }

    private int findEmptyMahasiswa() {
        for (int i = 0; i < daftarMahasiswa.length; i++) {
            if (daftarMahasiswa[i] == null)
                return i;
        }
        return -1;
    }

    private int findMahasiswaIndex(Mahasiswa mahasiswa) {
        for (int i = 0; i < daftarMahasiswa.length; i++) {
            if (daftarMahasiswa[i] == mahasiswa)
                return i;
        }
        return -1;
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

    public int getJumlahMahasiswa() {
        int count = 0;
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa != null)
                count++;
            else
                break;
        }
        return count;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public Mahasiswa[] getDaftarMahasiswa() {
        return daftarMahasiswa;
    }
}
