package assignments.assignment3;

import java.util.ArrayList;

abstract class ElemenFasilkom {

    private String nama;
    private int friendship;
    private ArrayList<ElemenFasilkom> telahMenyapa = new ArrayList<>();
    private ArrayList<Makanan> beliMakanan = new ArrayList<>();

    /**
     * Action: buy food.
     *
     * @param pembeli     ElemenFasilkom: Object that buys the food.
     * @param penjual     ElemenKantin: Object that sells the food.
     * @param namaMakanan String: Name of food to buy.
     */
    static void membeliMakanan(ElemenFasilkom pembeli, ElemenKantin penjual, String namaMakanan) {
        Makanan makanan = penjual.getMakanan(namaMakanan);

        // checks if the seller has the food the buyer wants
        if (makanan != null) {
            pembeli.beliMakanan.add(makanan);
            System.out.printf("%s berhasil membeli %s seharga %s\n",
                    pembeli, namaMakanan, makanan.getHarga());
        } else {
            System.out.printf("[DITOLAK] %s tidak menjual %s\n",
                    penjual, namaMakanan);
        }

    }

    /**
     * Action: Greets another ElemenFasilkom if they haven't greeted. Menyapa goes both ways,
     * which means o1 greets o2 executes o2 greets o1.
     *
     * @param elemenFasilkom ElemenFasilkom: element to greet.
     */
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

    int getFriendship() {
        return friendship;
    }

    /**
     * Sets friendship score. 0 <= friendship <= 100
     *
     * @param friendship int: new friendship score.
     */
    void setFriendship(int friendship) {
        this.friendship = (friendship > 100) ? 100 :
                Math.max(friendship, 0);
    }

    ArrayList<Makanan> getBeliMakanan() {
        return beliMakanan;
    }

    void resetBeliMakanan() {
        beliMakanan.clear();
    }

    void resetMenyapa() {
        telahMenyapa.clear();
    }

    int getJumlahMenyapa() {
        return telahMenyapa.size();
    }

    ArrayList<ElemenFasilkom> getTelahMenyapaArrayList() {
        return telahMenyapa;
    }

    String getNama() {
        return nama;
    }

    void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return nama;
    }
}