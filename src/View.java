import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private Logic logic;
    private Image backgroundImage;

    // constructor
    public View(Logic logic) {
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);
        setFocusable(true);
        addKeyListener(logic);
        backgroundImage = new ImageIcon(getClass().getResource("/assets/background.png")).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Gambar background
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, width, height, null);
        }

        // Gambar Pemain
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        // Gambar Pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // === Tambahkan teks "Game Over" jika game berhenti ===
        if (logic.gameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("Poppins", Font.BOLD, 40));
            g.drawString("GAME OVER", width / 2 - 120, height / 2);
            g.setFont(new Font("Poppins", Font.PLAIN, 20));
            g.drawString("Press R to Restart", width / 2 - 100, height / 2 + 40);
        }
    }
}