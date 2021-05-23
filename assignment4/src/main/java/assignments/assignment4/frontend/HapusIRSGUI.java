package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

import static assignments.assignment4.frontend.SistemAkademikGUI.*;

public class HapusIRSGUI {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;

    public HapusIRSGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){

        this.frame = frame;
        this.daftarMahasiswa = daftarMahasiswa;
        this.daftarMataKuliah = daftarMataKuliah;

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 40))); // add top buffer

        initComponents();

        frame.getContentPane().add(panel);
        frame.revalidate();
    }

    private void initComponents() {
        addTitle(new JLabel("Hapus IRS"), panel);

        JComboBox<String> npmList = createNPMChooser(panel);

        JComboBox<String> namaList = createMatkulChooser(panel);

        JButton tambahkanButton = createGreenButton("Hapus");
        tambahkanButton.addActionListener(e -> {
            String npm = (String) npmList.getSelectedItem();
            String nama = (String) namaList.getSelectedItem();
            if (npm == null || nama == null){
                JOptionPane.showMessageDialog(frame, "Mohon isi seluruh Field");
            } else{
                String message = getMahasiswa(Long.parseLong(npm)).dropMatkul(getMataKuliah(nama));
                JOptionPane.showMessageDialog(frame, message);
            }
        });
        addComponent(tambahkanButton, panel);

        addComponent(createKembaliButton(frame), panel);
    }


    private MataKuliah getMataKuliah(String nama) {

        for (MataKuliah mataKuliah : daftarMataKuliah) {
            if (mataKuliah.getNama().equals(nama)){
                return mataKuliah;
            }
        }
        return null;
    }

    private Mahasiswa getMahasiswa(long npm) {

        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa.getNpm() == npm){
                return mahasiswa;
            }
        }
        return null;
    }

}
