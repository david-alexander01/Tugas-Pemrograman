package assignments.assignment2;

public class Mahasiswa {
    private final MataKuliah[] mataKuliah = new MataKuliah[10];
    private final String nama;
    private final String jurusan;
    private final long npm;
    private int totalSKS;

    public Mahasiswa(String nama, long npm) {
        this.nama = nama;
        this.npm = npm;
        this.jurusan = this.getJurusan();
    }

    /**
     * @return empty index in mataKuliah array, -1 if full.
     */
    private int findEmptyMataKuliah() {
        for (int i = 0; i < this.mataKuliah.length; i++) {
            if (this.mataKuliah[i] == null)
                return i;
        }
        return -1;
    }

    /**
     * @param mataKuliah mataKuliah to find.
     * @return index of mataKuliah in array, -1 if not found.
     */
    private int findMataKuliahIndex(MataKuliah mataKuliah) {
        for (int i = 0; i < this.mataKuliah.length; i++) {
            if (this.mataKuliah[i] == mataKuliah)
                return i;
        }
        return -1;
    }

    /**
     * @param mataKuliah mataKuliah to check.
     * @return true if mataKuliah in mataKuliah array else false.
     */
    private boolean isTakingMataKuliah(MataKuliah mataKuliah) {
        for (MataKuliah element : this.mataKuliah) {
            if (element == mataKuliah) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true if mataKuliah array is empty else false.
     */
    public boolean isTakingMataKuliah() {
        boolean empty = true;
        for (MataKuliah element : this.mataKuliah) {
            if (element != null) {
                empty = false;
                break;
            }
        }
        return !empty;
    }

    /**
     * Adds mataKuliah to first available index of mataKuliah array if possible.
     *
     * @param mataKuliah mataKuliah to add.
     */
    public void addMatkul(MataKuliah mataKuliah) {
        if (this.isTakingMataKuliah(mataKuliah))
            System.out.printf("[DITOLAK] %s telah diambil sebelumnya.", mataKuliah);
        else if (!mataKuliah.available())
            System.out.printf("[DITOLAK] %s telah penuh kapasitasnya.", mataKuliah);
        else if (findEmptyMataKuliah() < 0)
            System.out.println("[DITOLAK] Maksimal mata kuliah yang diambil hanya 10.");
        else {
            mataKuliah.addMahasiswa(this);
            this.mataKuliah[findEmptyMataKuliah()] = mataKuliah;
            this.totalSKS += mataKuliah.getSKS();
        }
    }

    /**
     * Drops mataKuliah if possible.
     *
     * @param mataKuliah mataKuliah to drop.
     */
    public void dropMatkul(MataKuliah mataKuliah) {
        if (!this.isTakingMataKuliah(mataKuliah))
            System.out.printf("[DITOLAK] %s belum pernah diambil", mataKuliah);
        else {
            mataKuliah.dropMahasiswa(this);
            this.mataKuliah[findMataKuliahIndex(mataKuliah)] = null;
            this.totalSKS -= mataKuliah.getSKS();
        }
    }

    /**
     * Find and print problems in IRS. Possible problems: SKS > 24, mataKuliah code not according to jurusan.
     */
    public void cekIRS() {
        String[] masalahIRS = new String[20];
        int index = 0;

        if (this.totalSKS > 24) {
            masalahIRS[index] = "SKS yang Anda ambil lebih dari 24";
            index++;
        }

        for (MataKuliah mataKuliah : this.mataKuliah) {
            if (mataKuliah == null)
                continue;
            if (!mataKuliah.getKode().equals("CS") && !mataKuliah.getJurusan().equals(this.jurusan)) {
                masalahIRS[index] = "Mata kuliah " + mataKuliah + " tidak dapat diambil jurusan " + this.jurusan;
                index++;
            }
        }

        int count = 1;
        if (masalahIRS[0] != null) {
            for (String toPrint : masalahIRS) {
                if (toPrint != null) {
                    System.out.println(count + ". " + toPrint);
                    count++;
                }
            }
        } else
            System.out.println("IRS tidak bermasalah.");
    }

    /**
     * @return name.
     */
    public String toString() {
        return this.nama;
    }

    public int getSKS() {
        return this.totalSKS;
    }

    public long getNPM() {
        return this.npm;
    }

    public MataKuliah[] getMataKuliah() {
        return this.mataKuliah;
    }

    public String getJurusan() {
        String jurusanCode = (this.npm + "").substring(2, 4);
        return switch (jurusanCode) {
            case "01" -> "Ilmu Komputer";
            case "02" -> "Sistem Informasi";
            default -> "invalid";
        };
    }

}
