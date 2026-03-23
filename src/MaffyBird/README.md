# MaffyBird – Java Desktop Game

MaffyBird is a Java desktop game inspired by the classic **Flappy Bird**, created to demonstrate **OOP principles**, real-time game mechanics, and GUI development using **Java Swing**. The objective of the game is to control the bird and navigate it through pipes without colliding with them.

---

## Project Architecture

| Module | Link | Functionality |
|:------|:------|:---------------|
| **Main App** | [App.java](App.java) | Application entry point & window initialization |
| **Game Engine** | [FlappyBird.java](FlappyBird.java) | Handles gameplay, rendering, pipe generation, gravity, collision detection, and scoring |

---

## Key Features

* Classic Flappy Bird gameplay
* Jump mechanics controlled by keyboard input
* Dynamic pipe generation
* Real-time collision detection
* Score tracking system
* Simple and responsive GUI using Java Swing

---

## Gameplay

### Player Control
Press **SPACE** to make the bird jump and avoid obstacles.

### Pipes
Pipes continuously move from right to left, creating gaps the player must fly through.

### Scoring
Each pipe successfully passed increases the player's score.

### Game Over
The game ends if the bird collides with a pipe or the ground.

---

## Technologies

* Java OOP
* Java Swing (GUI)
* Event-Driven Programming
* Basic Game Physics
* Collision Detection
* Real-time Game Loop