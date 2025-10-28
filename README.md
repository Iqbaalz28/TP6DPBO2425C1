# TP6DPBO2425C1
A simple Java Swing implementation of the classic Flappy Bird game. This project demonstrates object-oriented programming principles such as encapsulation, event handling, and graphical rendering using JPanel and Timer

## 🙏 Agreement
My name **Iqbal Rizky Maulana**, with student ID number **2408622**, am completing **Practical Assignment 6** for the course **Object-Oriented Programming Design**. For His blessings, I will not commit any acts of cheating as specified.

## 🎮 Features
- 🐤 Playable Flappy Bird game with smooth gravity and pipe movement  
- 💥 Collision detection and game over system  
- 🔁 Restart game by pressing **R**  
- 🔊 Sound effects for flap, score, and hit events  
- 🌇 Background and assets rendered dynamically  
- 🧭 Main Menu GUI with **Play** and **Exit** options 

## 💬 Controls
| Key                   | Action                  |
| --------------------- | ----------------------- |
| **Space**             | Make the bird jump      |
| **R**                 | Restart after game over |
| **Esc / Exit Button** | Quit the game           |

## Class Descriptions

### 🟢 `App.java`
**Function:** The main entry point for running the program.  
Run `MainMenu` using `SwingUtilities.invokeLater()` so that the GUI runs on the Event Dispatch Thread (EDT).  

**Main method:**
- `main(String[] args)` → calls `new MainMenu()` to open the main menu form.

### 🟢 `MainMenu.java`
**Function:** Displays the **main menu form** before the game starts.  
It has two buttons:
- **Play Game** → opens the Flappy Bird main window  
- **Exit** → closes the program  

**Main attributes:**
- `JButton startButton` → button to start the game  
- `JButton exitButton` → button to exit

**Important methods:**
- `MainMenu()` constructor → creates a window with layout, labels, and buttons  
- `ActionListener startButton` → calls `Logic` and `View` to run the game  
- `ActionListener exitButton` → closes the program (`System.exit(0)`)

### 🟢 `Logic.java`
**Function:** Controls all **game logic mechanisms**, including player movement, pipes, scores, and sounds.  

**Main attributes:**
| Attribute | Type | Function |
|----------|------|--------|
| `frameWidth`, `frameHeight` | `int` | Game window size |
| `playerStartPosX`, `playerStartPosY` | `int` | Player's starting position |
| `playerWidth`, `playerHeight` | `int` | Player character size |
| `pipeStartPosX`, `pipeStartPosY` | `int` | Pipe's starting position |
| `gravity` | `int` | Determines the bird's falling acceleration |
| `pipeVelocityX` | `int` | Pipe movement speed to the left |
| `gameOver` | `boolean` | Game status (finished or not) |
| `score` | `int` | Stores the player's score |
| `scoreLabel` | `JLabel` | Score display label |
| `pipes` | `ArrayList<Pipe>` | List of pipe objects on the screen |
| `birdImage`, `upperPipeImage`, `lowerPipeImage` | `Image` | Sprite images for objects |
| `soundFlap`, `soundHit`, `soundPoint` | `String` | Sound file paths |

**Important methods:**
- `move()` → Controls the movement of birds and pipes and detects collisions  
- `placePipes()` → Adds new pipes randomly  
- `playSound(String filePath)` → Plays sound effects  
- `restartGame()` → Resets the game status after game over  
- `actionPerformed(ActionEvent e)` → Called every frame by `Timer` to update the game  
- `keyPressed(KeyEvent e)` → Controls SPACE (jump) and R (restart)

### 🟢 `View.java`
**Function:** Sets the **visual appearance** of the game (rendering).  
Uses `paintComponent(Graphics g)` to draw the background, player, pipes, and “Game Over” text.

**Main attributes:**
| Attribute | Type | Function |
|----------|------|--------|
| `width`, `height` | `int` | Game panel size |
| `logic` | `Logic` | Reference to game logic object |
| `backgroundImage` | `Image` | Game background image |

**Important methods:**
- Constructor `View(Logic logic)` → Connects the view with the logic  
- `paintComponent(Graphics g)` → Draws all game elements on the screen  
- `draw(Graphics g)` → Draws the player, pipes, background, and “Game Over” text

### 🟢 `Player.java`
**Function:** Representation of the **player bird** object in the game.  

**Main attributes:**
| Attribute | Type | Function |
|----------|------|--------|
| `posX`, `posY` | `int` | Player position on the screen |
| `width`, `height` | `int` | Bird size |
| `velocityY` | `int` | Player's vertical speed |
| `image` | `Image` | Bird image |

**Important methods:**
- Getter and Setter for all attributes
- Used by `Logic` to set position and speed

### 🟢 `Pipe.java`
**Function:** Representation of the **barrier pipe** object in the game.  

**Main attributes:**
| Attribute | Type | Function |
|----------|------|--------|
| `posX`, `posY` | `int` | Pipe position on screen |
| `width`, `height` | `int` | Pipe size |
| `image` | `Image` | Pipe image (top/bottom) |
| `velocityX` | `int` | Horizontal movement speed |
| `passed` | `boolean` | Whether the pipe has been passed by the player |

**Important methods:**
- Getter and Setter for each attribute  
- Used by `Logic` to update position and score

## 📸 Documentation

