# Tic Tac Toe – Java Desktop Game

Tic Tac Toe is a Java desktop game designed to demonstrate **OOP principles**, event-driven programming, and GUI development using Java Swing. The application offers multiple game modes, including player vs player, player vs bot, and an innovative **Infinite Mode**.

---

## Project Architecture

| Module | Link | Functionality |
|:------|:------|:---------------|
| **Main App** | [MainFrame.java](MainFrame.java) | Application entry point & screen navigation |
| **GUI Panels** | [MenuPanel.java](MenuPanel.java), [BackgroundPanel.java](BackgroundPanel.java) | User interface & layout rendering |
| **Game Logic** | [GamePanel.java](GamePanel.java) | Handles gameplay, win detection, scoring, and AI moves |
| **Audio** | [SoundManager.java](SoundManager.java) | MIDI-based sound effects for moves, win, and tie |

---

## Key Features

* Classic Tic Tac Toe gameplay (3x3 grid)
* Single Player mode vs intelligent bot with three difficulty levels
* Two Player local mode
* Infinite Mode (continuous play with dynamic board updates)
* Advanced AI — Easy (random), Medium (strategic blocking), Hard (unbeatable Minimax)
* Animated piece placement with a bounce-in scale effect
* Winning cells flash and highlight with a color animation
* Oldest-piece removal in Infinite Mode plays a flash-and-fade animation
* Modern GUI with custom background and styled components
* Score tracking and winner highlighting
* Switchable Dark / Light theme during gameplay
* Native dark title bar on Windows (via JNA)
* Distinct MIDI sound effects for X moves, O moves, wins, and ties

---

## Game Modes

### Two Players
Play locally with a friend by entering both player names.

### One Player (vs Bot)
Play against a randomly named bot (Greg, Marc, Roger, Bob, or Jack). Before starting, choose a difficulty:

| Difficulty | Strategy |
|:-----------|:---------|
| **Easy** | Plays a random empty cell every turn |
| **Medium** | Blocks your winning moves and completes its own (default) |
| **Hard** | Uses the Minimax algorithm — plays a perfect, unbeatable game |

### Infinite Mode
A unique variation where the oldest piece is removed after 6 moves, allowing endless and dynamic gameplay.

---

## Technologies

* Java OOP
* Java Swing (GUI)
* Event-Driven Programming
* AI Logic — strategic heuristics (Medium) & Minimax algorithm (Hard)
* Modular Architecture
* Java Sound API (MIDI)
* JNA – Java Native Access (Windows dark title bar)