package assignments.assignment4.frontend;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class HomeGUI {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;
    private JButton tambahMhs;
    private JButton tambahMatkul;
    private JButton tambahIRS;
    private JButton hapusIRS;
    private JButton ringkasanMhs;
    private JButton ringkasanMatkul;

    public HomeGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {
        this.frame = frame;
        this.daftarMahasiswa = daftarMahasiswa;
        this.daftarMataKuliah = daftarMataKuliah;

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Selamat datang di Sistem Akademik");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 40))); // add top buffer
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // spacer before buttons


        initButtons();

        frame.getContentPane().add(panel);
        frame.revalidate();
    }

    private void initButtons() {
        tambahMhs = createButton("Tambah Mahasiswa");
        tambahMatkul = createButton("Tambah Mata Kuliah");
        tambahIRS = createButton("Tambah IRS");
        hapusIRS = createButton("Hapus IRS");
        ringkasanMhs = createButton("Lihat Ringkasan Mahasiswa");
        ringkasanMatkul = createButton("Lihat Ringkasan Mata Kuliah");

        initButtons(new JButton[]{tambahMhs, tambahMatkul, tambahIRS,
                hapusIRS, ringkasanMhs, ringkasanMatkul});
    }


    /**
     * Creates JButton with predetermined format.
     * @param text String: Title of button.
     * @return JButton object.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(SistemAkademikGUI.green);
        button.setForeground(Color.white);
        button.setFont(SistemAkademikGUI.fontGeneral);

        return button;
    }

    /**
     * Adds action listener and insert button to panel.
     * @param buttons JButton[]: Array of button to insert.
     */
    private void initButtons(JButton[] buttons) {
        //Component spacer = Box.createRigidArea(new Dimension(0, 20));
        for (JButton button : buttons) {
            button.addActionListener(e -> {
                frame.getContentPane().removeAll();
                Object source = e.getSource();
                if (source == tambahMhs)
                    new TambahMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
                else if (source == tambahMatkul)
                    new TambahMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);
                else if (source == tambahIRS)
                    new TambahIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
                else if (source == hapusIRS)
                    new HapusIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
                else if (source == ringkasanMhs)
                    new RingkasanMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
                else
                    new RingkasanMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

}
