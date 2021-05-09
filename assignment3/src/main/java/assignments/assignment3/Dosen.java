package assignments.assignment3;

class Dosen extends ElemenFasilkom {

    private MataKuliah mataKuliah;

    Dosen(String nama) {
        this.setNama(nama);
    }

    /**
     * Registers this object to a MataKuliah and updates this.mataKuliah if this is
     * currently not teaching a MataKuliah and MataKuliah object doesn't currently
     * have a Dosen.
     *
     * @param mataKuliah MataKuliah: MataKuliah to teach.
     */
    void mengajarMataKuliah(MataKuliah mataKuliah) {
        if (this.mataKuliah != null) {
            System.out.printf("[DITOLAK] %s sudah mengajar mata kuliah %s\n",
                    this, this.mataKuliah);
        } else if (mataKuliah.getDosen() != null) {
            System.out.printf("[DITOLAK] %s sudah memiliki dosen pengajar\n", mataKuliah);
        } else {
            this.mataKuliah = mataKuliah;
            mataKuliah.addDosen(this);
            System.out.printf("%s mengajar mata kuliah %s\n",
                    this.getNama(), mataKuliah);
        }
    }

    /**
     * Removes this object from MataKuliah and sets this.mataKuliah to null if this
     * is currently teaching a MataKuliah.
     */
    void dropMataKuliah() {
        if (mataKuliah != null) {
            System.out.printf("%s berhenti mengajar %s\n",
                    this, mataKuliah);
            mataKuliah.dropDosen();
            mataKuliah = null;
        } else {
            System.out.printf("[DITOLAK] %s sedang tidak mengajar mata kuliah apapun\n",
                    this);
        }
    }

    MataKuliah getMataKuliah() {
        return mataKuliah;
    }
}