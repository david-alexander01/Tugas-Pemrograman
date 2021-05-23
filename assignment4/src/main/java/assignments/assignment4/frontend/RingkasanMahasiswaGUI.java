package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

import static assignments.assignment4.frontend.SistemAkademikGUI.*;

public class RingkasanMahasiswaGUI {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;

    public RingkasanMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
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
        addTitle(new JLabel("Ringkasan Mahasiswa"), panel);

        JComboBox<String> npmList = createNPMChooser(panel);

        JButton lihatButton = createGreenButton("Lihat");
        lihatButton.addActionListener(e -> {
            String npm = (String) npmList.getSelectedItem();
            if (npm == null){
                JOptionPane.showMessageDialog(frame, "Mohon isi seluruh Field");
            } else{
                frame.getContentPane().removeAll();
                new DetailRingkasanMahasiswaGUI(frame, getMahasiswa(Long.parseLong(npm)),
                        daftarMahasiswa, daftarMataKuliah);
            }
        });

        addComponent(lihatButton, panel);
        addComponent(createKembaliButton(frame), panel);
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
