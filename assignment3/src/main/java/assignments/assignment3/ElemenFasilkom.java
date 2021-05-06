package assignments.assignment3;

import java.util.ArrayList;

abstract class ElemenFasilkom{
    /* TODO: Silahkan menambahkan visibility pada setiap method dan variabel apabila diperlukan */

    private String nama;
    private int friendship;
    private ArrayList<ElemenFasilkom> telahMenyapa = new ArrayList<>();
    private ArrayList<Makanan> beliMakanan = new ArrayList<>();

    void menyapa(ElemenFasilkom elemenFasilkom) {
        if (telahMenyapa.contains(elemenFasilkom)) {
            System.out.printf("[DITOLAK] %s telah menyapa %s hari ini\n",
                    this, elemenFasilkom);
        } else {
            telahMenyapa.add(elemenFasilkom);
            elemenFasilkom.telahMenyapa.add(this);
            System.out.printf("%s menyapa dengan %s\n",
                    this, elemenFasilkom);
        }
    }

    void resetMenyapa() {
        telahMenyapa.clear();
    }

    void resetBeliMakanan(){beliMakanan.clear();}

    static void membeliMakanan(ElemenFasilkom pembeli, ElemenKantin penjual, String namaMakanan) {
        Makanan makanan = penjual.getMakanan(namaMakanan);
        if (makanan != null) {
            pembeli.beliMakanan.add(makanan);
            System.out.printf("%s berhasil membeli %s seharga %s\n",
                    pembeli, namaMakanan, makanan.getHarga());
        } else {
            System.out.printf("[DITOLAK] %s tidak menjual %s\n",
                    penjual, namaMakanan);
        }

    }

    public ArrayList<Makanan> getBeliMakanan() {
        return beliMakanan;
    }

    public int getJumlahMenyapa(){
        return telahMenyapa.size();
    }

    public ArrayList<ElemenFasilkom> getTelahMenyapa() {
        return telahMenyapa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getFriendship() {
        return friendship;
    }

    public void setFriendship(int friendship) {
        this.friendship = (friendship > 100) ? 100 :
                Math.max(friendship, 0);
    }

    public String toString() {
        return nama;
    }
}