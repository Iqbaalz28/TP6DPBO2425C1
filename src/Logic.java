import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    int pipeVelocityX = -2;

    // === Variabel tambahan ===
    boolean gameOver = false;
    int score = 0;
    JLabel scoreLabel;

    // === Path suara ===
    String soundFlap = "src/sounds/flap.wav";
    String soundHit = "src/sounds/hit.wav";
    String soundPoint = "src/sounds/point.wav";

    public Logic() {
        // === Load gambar ===
        birdImage = new ImageIcon(getClass().getResource("/assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("/assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("/assets/upperPipe.png")).getImage();
        pipes = new ArrayList<>();

        // === Timer untuk spawn pipa ===
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!gameOver) {
                    placePipes();
                }
            }
        });
        pipesCooldown.start();

        // === Timer utama untuk update game ===
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void setView(View view) { this.view = view; }
    public void setScoreLabel(JLabel label) { this.scoreLabel = label; }
    public Player getPlayer() { return player; }
    public ArrayList<Pipe> getPipes() { return pipes; }

    // === Fungsi untuk menambah pipa baru ===
    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    // === Fungsi utama pergerakan ===
    public void move() {
        if (gameOver) return;

        // Gravitasi
        int newVelocity = player.getVelocityY() + gravity;
        player.setVelocityY(newVelocity);

        int newPosY = player.getPosY() + newVelocity;
        newPosY = Math.max(newPosY, 0);

        // Jatuh ke bawah = Game Over
        int floor = frameHeight - player.getHeight();
        if (newPosY >= floor) {
            newPosY = floor;
            player.setVelocityY(0);
            gameOver = true;
            playSound(soundHit);
        }
        player.setPosY(newPosY);

        // Pindahkan semua pipa
        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // Cek tabrakan
            Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerRect.intersects(pipeRect)) {
                gameOver = true;
                playSound(soundHit);
            }

            // Tambah skor
            if (!pipe.isPassed() && pipe.getImage() == upperPipeImage && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.setPassed(true);
                score++;
                playSound(soundPoint);
                if (scoreLabel != null)
                    scoreLabel.setText("Score: " + score);
            }
        }

        pipes.removeIf(p -> p.getPosX() + p.getWidth() < 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) view.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && !gameOver) {
            player.setVelocityY(-10);
            playSound(soundFlap); //
        }

        if (key == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    // === Fungsi restart ===
    public void restartGame() {
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        score = 0;
        gameOver = false;
        if (scoreLabel != null)
            scoreLabel.setText("Score: 0");
    }

    // === Fungsi untuk memainkan suara ===
    private void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}