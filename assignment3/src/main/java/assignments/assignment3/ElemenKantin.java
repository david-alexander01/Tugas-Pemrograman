package assignments.assignment3;

import java.util.ArrayList;

class ElemenKantin extends ElemenFasilkom {
    
    /* TODO: Silahkan menambahkan visibility pada setiap method dan variabel apabila diperlukan */

    ArrayList<Makanan> daftarMakanan = new ArrayList<>();

    ElemenKantin(String nama) {
        this.setNama(nama);
    }

    void setMakanan(String nama, long harga) {
        if (getMakanan(nama) == null){
            Makanan makanan = new Makanan(nama, harga);
            daftarMakanan.add(makanan);
            System.out.printf("%s telah mendaftarkan makanan %s dengan harga %d\n",
                    this, nama, harga);
        } else{
            System.out.printf("[DITOLAK] %s sudah pernah terdaftar\n", nama);
        }

    }

    Makanan getMakanan(String namaMakanan){
        for (Makanan element : daftarMakanan){
            if (element.toString().equals(namaMakanan)){
                return element;
            }
        }
        return null;
    }

    public ArrayList<Makanan> getDaftarMakanan() {
        return daftarMakanan;
    }
}