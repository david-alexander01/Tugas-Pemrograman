package assignments.assignment3;

import java.util.ArrayList;

class ElemenKantin extends ElemenFasilkom {

    ArrayList<Makanan> daftarMakanan = new ArrayList<>();

    ElemenKantin(String nama) {
        this.setNama(nama);
    }

    /**
     * Creates a Makanan object and adds it to the daftarMakanan ArrayList.
     *
     * @param nama  String: name of Makanan to add.
     * @param harga long: price of Makanan to add.
     */
    void setMakanan(String nama, long harga) {
        if (getMakanan(nama) == null) {
            Makanan makanan = new Makanan(nama, harga);
            daftarMakanan.add(makanan);
            System.out.printf("%s telah mendaftarkan makanan %s dengan harga %d\n",
                    this, nama, harga);
        } else {
            System.out.printf("[DITOLAK] %s sudah pernah terdaftar\n", nama);
        }

    }

    /**
     * Returns Makanan object according to its name.
     *
     * @param namaMakanan String: name of Makanan to get.
     * @return Makanan: Makanan object if found else null.
     */
    Makanan getMakanan(String namaMakanan) {
        for (Makanan element : daftarMakanan) {
            if (element.toString().equals(namaMakanan)) {
                return element;
            }
        }
        return null;
    }

    public ArrayList<Makanan> getDaftarMakanan() {
        return daftarMakanan;
    }
}