package assignments.assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static ArrayList<ElemenFasilkom> daftarElemenFasilkom = new ArrayList<>();
    private static ArrayList<MataKuliah> daftarMataKuliah = new ArrayList<>();
    private static int totalMataKuliah = 0;
    private static int totalElemenFasilkom = 0;

    /**
     * Gets an ElemenFasilkom object from its name.
     *
     * @param nama String: name of ElemenFasilkom to get.
     * @return ElemenFasilkom: ElemenFasilkom object if found else null.
     */
    static ElemenFasilkom getElemenFasilkom(String nama) {
        for (ElemenFasilkom elemen : daftarElemenFasilkom) {
            if (elemen.toString().equals(nama)) {
                return elemen;
            }
        }
        return null;
    }

    /**
     * Gets a MataKuliah object from its name.
     *
     * @param nama String: name of MataKuliah to get.
     * @return MataKuliah MataKuliah object if found else null.
     */
    static MataKuliah getMataKuliah(String nama) {
        for (MataKuliah elemen : daftarMataKuliah) {
            if (elemen.toString().equals(nama)) {
                return elemen;
            }
        }
        return null;
    }

    /**
     * Adds Mahasiswa object to daftarElemenFasilkom ArrayList and updates totalElemenFasilkom.
     *
     * @param nama String: name of Mahasiswa to add.
     * @param npm  long: npm of Mahasiswa to add.
     */
    static void addMahasiswa(String nama, long npm) {
        daftarElemenFasilkom.add(new Mahasiswa(nama, npm));
        totalElemenFasilkom++;
        System.out.printf("%s berhasil ditambahkan\n", nama);
    }

    /**
     * Adds Dosen object to daftarElemenFasilkom ArrayList and updates totalElemenFasilkom.
     *
     * @param nama String: name of Dosen to add.
     */
    static void addDosen(String nama) {
        daftarElemenFasilkom.add(new Dosen(nama));
        totalElemenFasilkom++;
        System.out.printf("%s berhasil ditambahkan\n", nama);
    }

    /**
     * Adds ElemenKantin object to daftarElemenFasilkom Arraylist and updates totalElemenFasilkom.
     *
     * @param nama String: name of ElemenKantin to add.
     */
    static void addElemenKantin(String nama) {
        daftarElemenFasilkom.add(new ElemenKantin(nama));
        totalElemenFasilkom++;
        System.out.printf("%s berhasil ditambahkan\n", nama);
    }

    /**
     * Have to ElemenFasilkom greet each other.
     *
     * @param objek1 String: name of first ElemenFasilkom.
     * @param objek2 String: name of second ElemenFasilkom.
     */
    static void menyapa(String objek1, String objek2) {
        if (objek1.equals(objek2)) {
            System.out.println("[DITOLAK] Objek yang sama tidak bisa saling menyapa");
        } else {
            ElemenFasilkom elemen1 = getElemenFasilkom(objek1);
            ElemenFasilkom elemen2 = getElemenFasilkom(objek2);
            assert elemen1 != null;
            assert elemen2 != null;
            elemen1.menyapa(elemen2);
        }
    }

    /**
     * Adds Makanan to daftarMakanan Arraylist of ElemenKantin.
     *
     * @param objek       String: name of ElemenFasilkom. If ElemenFasilkom found is not
     *                    ElemenKantin, cancel the addition of Makanan.
     * @param namaMakanan String: name of Makanan to add.
     * @param harga       long: price of Makanan to add.
     */
    static void addMakanan(String objek, String namaMakanan, long harga) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        assert elemen != null;
        if (elemen instanceof ElemenKantin) {
            ((ElemenKantin) elemen).setMakanan(namaMakanan, harga);
        } else {
            System.out.printf("[DITOLAK] %s bukan merupakan elemen kantin\n", objek);
        }
    }

    /**
     * Action: Object1 buys food from Object2. For this method to succeed, Object2
     * must be ElemenKantin.
     *
     * @param objek1      String: name of buyer (ElemenFasilkom).
     * @param objek2      String: name of seller (ElemenKantin).
     * @param namaMakanan String: name of food to buy.
     */
    static void membeliMakanan(String objek1, String objek2, String namaMakanan) {
        ElemenFasilkom elemen1 = getElemenFasilkom(objek1);
        ElemenFasilkom elemen2 = getElemenFasilkom(objek2);
        if (!(elemen2 instanceof ElemenKantin)) {
            System.out.println("[DITOLAK] Hanya elemen kantin yang dapat menjual makanan");
        } else if (elemen1 == elemen2) {
            System.out.println("[DITOLAK] Elemen kantin tidak bisa membeli makanan sendiri");
        } else {
            ElemenFasilkom.membeliMakanan(elemen1, (ElemenKantin) elemen2, namaMakanan);
        }
    }

    /**
     * Create new MataKuliah object and adds it to daftarMataKuliah ArrayList of Main.
     *
     * @param nama      String: name of MataKuliah to add.
     * @param kapasitas int: capacity of MataKuliah to add.
     */
    static void createMatkul(String nama, int kapasitas) {
        daftarMataKuliah.add(new MataKuliah(nama, kapasitas));
        totalMataKuliah++;
        System.out.printf("%s berhasil ditambahkan dengan kapasitas %d\n", nama, kapasitas);
    }

    /**
     * Adds MataKuliah if the given object is Mahasiswa.
     *
     * @param objek          String: name of ElemenFasilkom to try to add MataKuliah.
     * @param namaMataKuliah String: name of MataKuliah to add.
     */
    static void addMatkul(String objek, String namaMataKuliah) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Mahasiswa) {
            ((Mahasiswa) elemen).addMatkul(getMataKuliah(namaMataKuliah));
        } else {
            System.out.println("[DITOLAK] Hanya mahasiswa yang dapat menambahkan matkul");
        }
    }

    /**
     * Drops MataKuliah if the given object is Mahasiswa.
     *
     * @param objek          String: name of ElemenFasilkom to try to add MataKuliah.
     * @param namaMataKuliah String: name of MataKuliah to add.
     */
    static void dropMatkul(String objek, String namaMataKuliah) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Mahasiswa) {
            ((Mahasiswa) elemen).dropMatkul(getMataKuliah(namaMataKuliah));
        } else {
            System.out.println("[DITOLAK] Hanya mahasiswa yang dapat drop matkul");
        }
    }

    /**
     * Sets dosen of MataKuliah to given object if object is Dosen.
     *
     * @param objek          String: name of ElemenFasilkom (Dosen)
     * @param namaMataKuliah String: name of MataKuliah.
     */
    static void mengajarMatkul(String objek, String namaMataKuliah) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Dosen) {
            ((Dosen) elemen).mengajarMataKuliah(getMataKuliah(namaMataKuliah));
        } else {
            System.out.println("[DITOLAK] Hanya dosen yang dapat mengajar matkul");
        }
    }

    /**
     * Stops teaching a MataKuliah if object is Dosen.
     *
     * @param objek String: name of ElemenFasilkom (Dosen).
     */
    static void berhentiMengajar(String objek) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Dosen) {
            ((Dosen) elemen).dropMataKuliah();
        } else {
            System.out.println("[DITOLAK] Hanya dosen yang dapat berhenti mengajar");
        }
    }

    /**
     * Prints attribute of Mahasiswa if given object is Mahasiswa.
     *
     * @param objek String: name of ElemenFasilkom (Mahasiswa) to print ringkasan.
     */
    static void ringkasanMahasiswa(String objek) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Mahasiswa) {
            ((Mahasiswa) elemen).ringkasan();
        } else {
            System.out.printf("[DITOLAK] %s bukan merupakan seorang mahasiswa\n", elemen);
        }
    }

    /**
     * Prints attribute of MataKuliah.
     *
     * @param namaMataKuliah String: name of MataKuliah to print ringkasan.
     */
    static void ringkasanMataKuliah(String namaMataKuliah) {
        MataKuliah mataKuliah = getMataKuliah(namaMataKuliah);
        assert mataKuliah != null;
        mataKuliah.ringkasan();
    }

    /**
     * Calculate friendship score of each ElemenFasilkom and resets telahMenyapa and
     * beliMakanan ArrayList. Prints out friendshipRanking. Conditions of friendship score:
     * <br>1. +10 if an element has greeted greater than or equal to half of total ElemenFasilkom (not including
     * one's self), else -5.
     * <br>2. +2 to both Mahasiswa and Dosen if a Mahasiswa greets a Dosen who teaches a MataKuliah the
     * Mahasiswa is currently taking.
     * <br>3. +1 to both ElemenFasilkom and ElemenKantin if an ElemenFasilkom bought food from the
     * ElemenKantin.
     */
    static void nextDay() {
        double setengahElemen = (totalElemenFasilkom - 1) / 2.0;    // Not including one's self
        for (ElemenFasilkom elemen : daftarElemenFasilkom) {
            int tambahNilaiFriendship = 0;

            // Check first condition
            if (sapaLebihDariSamaDengan(elemen, setengahElemen)) {
                tambahNilaiFriendship += 10;
            } else {
                tambahNilaiFriendship -= 5;
            }

            // Check second condition
            if (elemen instanceof Mahasiswa) {
                for (ElemenFasilkom orangYangDisapa : elemen.getTelahMenyapaArrayList()) {
                    if (orangYangDisapa instanceof Dosen
                            && ((Mahasiswa) elemen).getDaftarMataKuliah().contains(((Dosen) orangYangDisapa).getMataKuliah())) {
                        tambahNilaiFriendship += 2;
                        orangYangDisapa.setFriendship(orangYangDisapa.getFriendship() + 2);
                    }
                }
            }

            // Check third condition
            for (ElemenFasilkom penjual : daftarElemenFasilkom) {
                if (penjual instanceof ElemenKantin) {
                    for (Makanan makanan : elemen.getBeliMakanan()) {
                        if (((ElemenKantin) penjual).getDaftarMakanan().contains(makanan)) {
                            tambahNilaiFriendship += 1;
                            penjual.setFriendship(penjual.getFriendship() + 1);
                        }
                    }
                }
            }

            elemen.setFriendship(elemen.getFriendship() + tambahNilaiFriendship);
            elemen.resetMenyapa();
            elemen.resetBeliMakanan();
        }
        System.out.println("Hari telah berakhir dan nilai friendship telah diupdate");
        friendshipRanking();
    }

    /**
     * Prints each element of daftarElemenFasilkom sorted by friendship score (highest first), then
     * alphabetically.
     */
    static void friendshipRanking() {
        daftarElemenFasilkom.sort(Comparator.comparing(ElemenFasilkom::getFriendship).reversed().thenComparing(ElemenFasilkom::getNama));
        int count = 1;
        for (ElemenFasilkom elemen : daftarElemenFasilkom) {
            System.out.printf("%d. %s(%d)\n", count, elemen, elemen.getFriendship());
            count++;
        }
    }

    /**
     * Terminates program and prints final friendshipRanking.
     */
    static void programEnd() {
        System.out.println("Program telah berakhir. Berikut nilai terakhir dari friendship pada Fasilkom :");
        friendshipRanking();
    }

    /**
     * Checks if an ElemenFasilkom has greeted greater than or equal to an amount of ElemenFasilkom.
     *
     * @param elemen ElemenFasilkom: ElemenFasilkom to check.
     * @param jumlah double: amount of ElemenFasilkom.
     * @return true if condition is met else false.
     */
    static boolean sapaLebihDariSamaDengan(ElemenFasilkom elemen, double jumlah) {
        return elemen.getJumlahMenyapa() >= jumlah;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();

        while (!in.equals("PROGRAM_END")) {
            switch (in.split(" ")[0]) {
                case "ADD_MAHASISWA" -> addMahasiswa(in.split(" ")[1], Long.parseLong(in.split(" ")[2]));
                case "ADD_DOSEN" -> addDosen(in.split(" ")[1]);
                case "ADD_ELEMEN_KANTIN" -> addElemenKantin(in.split(" ")[1]);
                case "MENYAPA" -> menyapa(in.split(" ")[1], in.split(" ")[2]);
                case "ADD_MAKANAN" -> addMakanan(in.split(" ")[1], in.split(" ")[2], Long.parseLong(in.split(" ")[3]));
                case "MEMBELI_MAKANAN" -> membeliMakanan(in.split(" ")[1], in.split(" ")[2], in.split(" ")[3]);
                case "CREATE_MATKUL" -> createMatkul(in.split(" ")[1], Integer.parseInt(in.split(" ")[2]));
                case "ADD_MATKUL" -> addMatkul(in.split(" ")[1], in.split(" ")[2]);
                case "DROP_MATKUL" -> dropMatkul(in.split(" ")[1], in.split(" ")[2]);
                case "MENGAJAR_MATKUL" -> mengajarMatkul(in.split(" ")[1], in.split(" ")[2]);
                case "BERHENTI_MENGAJAR" -> berhentiMengajar(in.split(" ")[1]);
                case "RINGKASAN_MAHASISWA" -> ringkasanMahasiswa(in.split(" ")[1]);
                case "RINGKASAN_MATKUL" -> ringkasanMataKuliah(in.split(" ")[1]);
                case "NEXT_DAY" -> nextDay();
            }
            in = input.nextLine();
        }
        programEnd();
        input.close();
    }
}
