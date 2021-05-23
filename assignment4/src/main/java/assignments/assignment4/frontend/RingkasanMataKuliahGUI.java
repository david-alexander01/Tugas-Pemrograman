package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

import static assignments.assignment4.frontend.SistemAkademikGUI.*;

public class RingkasanMataKuliahGUI {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;

    public RingkasanMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
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
        addTitle(new JLabel("Ringkasan Mata Kuliah"), panel);

        JComboBox<String> matkulList = createMatkulChooser(panel);

        JButton lihatButton = createGreenButton("Lihat");
        lihatButton.addActionListener(e -> {
            String nama = (String) matkulList.getSelectedItem();
            if (nama == null){
                JOptionPane.showMessageDialog(frame, "Mohon isi seluruh Field");
            } else{
                frame.getContentPane().removeAll();
                new DetailRingkasanMataKuliahGUI(frame, getMataKuliah(nama),
                        daftarMahasiswa, daftarMataKuliah);
            }
        });

        addComponent(lihatButton, panel);
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

}
