import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    // Constructor utama
    public MainMenu() {
        // --- Atur properti dasar window ---
        setTitle("Main Menu - Flappy Bird");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // pusat layar
        setResizable(false);
        setLayout(new BorderLayout());

        // --- Panel utama untuk menampung komponen ---
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.cyan); // warna latar belakang
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // susun vertikal
        add(mainPanel, BorderLayout.CENTER);

        // --- Label judul game ---
        JLabel titleLabel = new JLabel("FLAPPY BIRD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 50, 0)); // jarak atas-bawah
        mainPanel.add(titleLabel);

        // --- Tombol mulai ---
        JButton startButton = new JButton("Play Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setFocusPainted(false);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setMaximumSize(new Dimension(200, 50));
        startButton.setBackground(Color.green);
        startButton.setForeground(Color.black);
        mainPanel.add(startButton);

        // Tambahkan jarak antar tombol
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // --- Tombol keluar ---
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setFocusPainted(false);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(new Dimension(200, 50));
        exitButton.setBackground(Color.red);
        exitButton.setForeground(Color.white);
        mainPanel.add(exitButton);

        // --- Action tombol "Play Game" ---
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Tutup menu utama
                dispose();

                // === Inisialisasi jendela game ===
                JFrame frame = new JFrame("Flappy Bird");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(360, 640);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                // === Inisialisasi logika dan tampilan ===
                Logic logika = new Logic();
                View tampilan = new View(logika);
                logika.setView(tampilan);
                tampilan.requestFocus();

                // === Tambahkan label skor di bagian atas JFrame ===
                JLabel labelSkor = new JLabel("Score: 0");
                labelSkor.setHorizontalAlignment(SwingConstants.CENTER);
                labelSkor.setFont(new Font("Arial", Font.BOLD, 18));
                labelSkor.setForeground(Color.BLACK);

                // === Hubungkan labelSkor dengan class Logic ===
                logika.setScoreLabel(labelSkor);

                // === Tambahkan komponen ke frame ===
                frame.add(labelSkor, BorderLayout.NORTH); // label skor di atas
                frame.add(tampilan, BorderLayout.CENTER); // game di tengah
                frame.pack();
                frame.setVisible(true);
            }
        });

        // --- Action tombol "Exit" ---
        exitButton.addActionListener(e -> System.exit(0));

        // --- Tampilkan form ---
        setVisible(true);
    }
}