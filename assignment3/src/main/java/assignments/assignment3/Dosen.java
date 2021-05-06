package assignments.assignment3;

class Dosen extends ElemenFasilkom {

    /* TODO: Silahkan menambahkan visibility pada setiap method dan variabel apabila diperlukan */

    private MataKuliah mataKuliah;

    Dosen(String nama) {
        this.setNama(nama);
    }

    void mengajarMataKuliah(MataKuliah mataKuliah) {
        if (this.mataKuliah != null){
            System.out.printf("[DITOLAK] %s sudah mengajar mata kuliah %s\n",
                    this, this.mataKuliah);
        } else if (mataKuliah.getDosen() != null){
            System.out.printf("[DITOLAK] %s sudah memiliki dosen pengajar\n", mataKuliah);
        } else{
            this.mataKuliah = mataKuliah;
            mataKuliah.addDosen(this);
            System.out.printf("%s mengajar mata kuliah %s\n",
                    this.getNama(), mataKuliah);
        }
    }

    void dropMataKuliah() {
        if (mataKuliah != null){
            System.out.printf("%s berhenti mengajar %s\n",
                    this, mataKuliah);
            mataKuliah.dropDosen();
            mataKuliah = null;
        } else{
            System.out.printf("%s sedang tidak mengajar mata kuliah apapun\n",
                    this);
        }
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }
}