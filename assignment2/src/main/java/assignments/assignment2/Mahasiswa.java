package assignments.assignment2;

public class Mahasiswa {
    private final MataKuliah[] mataKuliah = new MataKuliah[10];
    private final String nama;
    private final String jurusan;
    private final long npm;
    private int totalSKS;

    public Mahasiswa(String nama, long npm) {
        // Initialize attributes
        this.nama = nama;
        this.npm = npm;
        this.jurusan = this.getJurusan();
    }

    public String getJurusan() {
        // Returns string of jurusan else "invalid" if code is invalid.
        String jurusanCode = (npm + "").substring(2, 4);
        return switch (jurusanCode) {
            case "01" -> "Ilmu Komputer";
            case "02" -> "Sistem Informasi";
            default -> "invalid";
        };
    }

    public long getNPM() {
        return npm;
    }

    public MataKuliah[] getMataKuliah() {
        return mataKuliah;
    }

    private int findEmptyMataKuliah() {
        for (int i = 0; i < mataKuliah.length; i++) {
            if (mataKuliah[i] == null)
                return i;
        }
        return -1;
    }

    private int findMataKuliahIndex(MataKuliah mataKuliah) {
        for (int i = 0; i < this.mataKuliah.length; i++) {
            if (this.mataKuliah[i] == mataKuliah)
                return i;
        }
        return -1;
    }

    public void addMatkul(MataKuliah mataKuliah) {
        /* TODO: implementasikan kode Anda di sini */
        if (this.mataKuliah[9] != null)
            System.out.println("[DITOLAK] Maksimal mata kuliah yang diambil hanya 10.");
        else if (this.isTakingMataKuliah(mataKuliah))
            System.out.printf("[DITOLAK] %s telah diambil sebelumnya.", mataKuliah);
        else if (!mataKuliah.available())
            System.out.printf("[DITOLAK] %s telah penuh kapasitasnya.", mataKuliah);
        else {
            mataKuliah.addMahasiswa(this);
            this.mataKuliah[findEmptyMataKuliah()] = mataKuliah;
            totalSKS += mataKuliah.getSKS();
        }
    }

    public void dropMatkul(MataKuliah mataKuliah) {
        /* TODO: implementasikan kode Anda di sini */
        if (!this.isTakingMataKuliah(mataKuliah))
            System.out.printf("[DITOLAK] %s belum pernah diambil.", mataKuliah);
        else {
            mataKuliah.dropMahasiswa(this);
            this.mataKuliah[findMataKuliahIndex(mataKuliah)] = null;
            totalSKS -= mataKuliah.getSKS();
        }
    }


    public void cekIRS() {
        /* TODO: implementasikan kode Anda di sini */
        String[] masalahIRS = new String[10];
        int index = 0;
        for (MataKuliah mataKuliah : this.mataKuliah) {
            if (mataKuliah == null)
                break;
            if (!mataKuliah.getKode().equals("CS") || !mataKuliah.getKode().equals(this.jurusan)) {
                masalahIRS[index] = "Mata kuliah " + mataKuliah + " tidak dapat diambil jurusan " + this.jurusan;
                index += 1;
            }
        }
        if (this.totalSKS > 24)
            masalahIRS[index] = "SKS yang Anda ambil lebih dari 24";

        if (masalahIRS[0] != null) {
            for (String toPrint : masalahIRS) {
                if (toPrint != null) {
                    System.out.println(toPrint);
                }
            }
        } else
            System.out.println("IRS tidak bermasalah.");
    }


    public String toString() {
        /* TODO: implementasikan kode Anda di sini */
        return this.nama;
    }

    public int getSKS() {
        return this.totalSKS;
    }

    private boolean isTakingMataKuliah(MataKuliah mataKuliah) {
        for (MataKuliah element : this.mataKuliah) {
            if (element == mataKuliah) {
                return true;
            }
        }
        return false;
    }
}
