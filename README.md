# Arkanoid Game

This project is an implementation of the classic **Arkanoid** game in Java, developed as part of a university assignment.

## 📁 Project Structure

```
.
├── src/                        # Java source files
├── bin/                        # Compiled class files (generated after build)
├── biuoop-1.4.jar              # External library (assumed required for drawing/input)
├── build.xml                   # Apache Ant build script
├── checkstyle-8.44-all.jar     # Optional: for code style checking
├── biuoop.xml                  # Optional: checkstyle configuration
└── README.md
```

## 🛠 Requirements

- Java JDK (version 8 or higher)
- [Apache Ant](https://ant.apache.org/) installed and available in your system PATH

## 🚀 How to Run

### 1. Compile the code

To compile all `.java` files in the `src` directory and output `.class` files to the `bin` directory, run:

```bash
ant compile
```

### 2. Run the game

To compile (if necessary) and run the game, execute:

```bash
ant run
```

> This runs the main class `Ass5Game`, assumed to be located in `src`.

### 3. Clean the build

To delete the compiled files and clean the project:

```bash
ant clean
```

### 4. Check code style (optional)

To run Checkstyle on your source code, make sure `checkstyle-8.44-all.jar` and `biuoop.xml` are present:

```bash
ant check
```

## 🎮 Controls & Gameplay

> (These are assumptions based on typical biuoop-based Arkanoid implementations.)

- Use **arrow keys** or **A/D keys** to move the paddle
- The objective is to break all the blocks without letting the ball fall
- Game may feature levels, lives, and scoring

## 📦 Notes

- The project uses `biuoop-1.4.jar`, a GUI/input library commonly used in academic settings
- The game's main class is `Ass5Game.java`, which should reside in the `src/` directory
- You can modify or extend the game by adding levels, power-ups, or improving the UI

## 📜 License

This project is intended for educational and personal learning purposes only.
