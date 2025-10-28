import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Jalankan tampilan menu utama
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu();
            }
        });
    }
}