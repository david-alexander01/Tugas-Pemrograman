package assignments.assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    /* TODO: Silahkan menambahkan visibility pada setiap method dan variabel apabila diperlukan */

    private static ArrayList<ElemenFasilkom> daftarElemenFasilkom = new ArrayList<>();
    private static ArrayList<MataKuliah> daftarMataKuliah = new ArrayList<>();
    private static int totalMataKuliah = 0;
    private static int totalElemenFasilkom = 0;

    static ElemenFasilkom getElemenFasilkom(String nama) {
        for (ElemenFasilkom elemen : daftarElemenFasilkom) {
            if (elemen.toString().equals(nama)) {
                return elemen;
            }
        }
        return null;
    }

    static MataKuliah getMataKuliah(String nama) {
        for (MataKuliah elemen : daftarMataKuliah) {
            if (elemen.toString().equals(nama)) {
                return elemen;
            }
        }
        return null;
    }

    static void addMahasiswa(String nama, long npm) {
        daftarElemenFasilkom.add(new Mahasiswa(nama, npm));
        totalElemenFasilkom++;
        System.out.printf("%s berhasil ditambahkan\n", nama);
    }

    static void addDosen(String nama) {
        daftarElemenFasilkom.add(new Dosen(nama));
        totalElemenFasilkom++;
        System.out.printf("%s berhasil ditambahkan\n", nama);
    }

    static void addElemenKantin(String nama) {
        daftarElemenFasilkom.add(new ElemenKantin(nama));
        totalElemenFasilkom++;
        System.out.printf("%s berhasil ditambahkan\n", nama);
    }

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

    static void addMakanan(String objek, String namaMakanan, long harga) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        assert elemen != null;
        if (elemen instanceof ElemenKantin){
            ((ElemenKantin) elemen).setMakanan(namaMakanan, harga);
        }
    }

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

    static void createMatkul(String nama, int kapasitas) {
        daftarMataKuliah.add(new MataKuliah(nama, kapasitas));
        totalMataKuliah++;
    }

    static void addMatkul(String objek, String namaMataKuliah) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Mahasiswa) {
            ((Mahasiswa) elemen).addMatkul(getMataKuliah(namaMataKuliah));
        } else {
            System.out.println("[DITOLAK] Hanya mahasiswa dapat menambahkan matkul");
        }
    }

    static void dropMatkul(String objek, String namaMataKuliah) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Mahasiswa) {
            ((Mahasiswa) elemen).dropMatkul(getMataKuliah(namaMataKuliah));
        } else {
            System.out.println("[DITOLAK] Hanya mahasiswa dapat drop matkul");
        }
    }

    static void mengajarMatkul(String objek, String namaMataKuliah) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Dosen) {
            ((Dosen) elemen).mengajarMataKuliah(getMataKuliah(namaMataKuliah));
        } else {
            System.out.println("[DITOLAK] Hanya dosen dapat mengajar matkul");
        }
    }

    static void berhentiMengajar(String objek) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Dosen) {
            ((Dosen) elemen).dropMataKuliah();
        } else {
            System.out.println("[DITOLAK] Hanya dosen dapat berhenti mengajar");
        }
    }

    static void ringkasanMahasiswa(String objek) {
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen instanceof Mahasiswa) {
            ((Mahasiswa) elemen).ringkasan();
        } else {
            System.out.printf("[DITOLAK] %s bukan merupakan seorang mahasiswa\n", elemen);
        }
    }

    static void ringkasanMataKuliah(String namaMataKuliah) {
        MataKuliah mataKuliah = getMataKuliah(namaMataKuliah);
        assert mataKuliah != null;
        mataKuliah.ringkasan();
    }

    static void nextDay() {
        double setengahElemen = (totalElemenFasilkom / 2.0) - 1;    // Not including one's self
        for (ElemenFasilkom elemen : daftarElemenFasilkom) {
            int tambahNilaiFriendship = 0;

            // Check first condition
            if (sapaLebihDariSetengahElemen(elemen, setengahElemen)) {
                tambahNilaiFriendship += 10;
            } else {
                tambahNilaiFriendship -= 5;
            }

            // Check second condition
            if (elemen instanceof Mahasiswa) {
                for (ElemenFasilkom sapa : elemen.getTelahMenyapa()) {
                    if (sapa instanceof Dosen
                            && ((Mahasiswa) elemen).getDaftarMataKuliah().contains(((Dosen) sapa).getMataKuliah())) {
                        tambahNilaiFriendship += 2;
                        sapa.setFriendship(sapa.getFriendship() + 2);
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

    static void friendshipRanking() {
        daftarElemenFasilkom.sort(Comparator.comparing(ElemenFasilkom::getFriendship).reversed().thenComparing(ElemenFasilkom::getNama));
        int count = 1;
        for (ElemenFasilkom elemen : daftarElemenFasilkom) {
            System.out.printf("%d. %s(%d)\n", count, elemen, elemen.getFriendship());
            count++;
        }
    }

    static void programEnd() {
        System.out.println("Program telah berakhir. Berikut nilai terakhir dari friendship pada Fasilkom :");
        friendshipRanking();
    }

    static boolean sapaLebihDariSetengahElemen(ElemenFasilkom elemen, double setengahElemen) {
        return elemen.getJumlahMenyapa() >= setengahElemen;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            String in = input.nextLine();
            if (in.split(" ")[0].equals("ADD_MAHASISWA")) {
                addMahasiswa(in.split(" ")[1], Long.parseLong(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_DOSEN")) {
                addDosen(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("ADD_ELEMEN_KANTIN")) {
                addElemenKantin(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("MENYAPA")) {
                menyapa(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("ADD_MAKANAN")) {
                addMakanan(in.split(" ")[1], in.split(" ")[2], Long.parseLong(in.split(" ")[3]));
            } else if (in.split(" ")[0].equals("MEMBELI_MAKANAN")) {
                membeliMakanan(in.split(" ")[1], in.split(" ")[2], in.split(" ")[3]);
            } else if (in.split(" ")[0].equals("CREATE_MATKUL")) {
                createMatkul(in.split(" ")[1], Integer.parseInt(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_MATKUL")) {
                addMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("DROP_MATKUL")) {
                dropMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("MENGAJAR_MATKUL")) {
                mengajarMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("BERHENTI_MENGAJAR")) {
                berhentiMengajar(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MAHASISWA")) {
                ringkasanMahasiswa(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MATKUL")) {
                ringkasanMataKuliah(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("NEXT_DAY")) {
                nextDay();
            } else if (in.split(" ")[0].equals("PROGRAM_END")) {
                programEnd();
                break;
            }
        }
        input.close();
    }
}