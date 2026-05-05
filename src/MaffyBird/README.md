# MaffyBird – Java Desktop Game

MaffyBird is a Java desktop game inspired by the classic **Flappy Bird**, built with **Java Swing**. The objective is to control the bird and navigate it through pipes and meteors without colliding with them.

---

## Project Architecture

| Module | Link | Functionality |
|:------|:------|:---------------|
| **App** | [App.java](App.java) | Entry point, window setup, and screen navigation via `CardLayout` |
| **Menu** | [Menu.java](Menu.java) | Main menu screen with high score display |
| **Game Engine** | [MaffyBird.java](MaffyBird.java) | Core gameplay: rendering, physics, pipe & meteor generation, collision detection, scoring, and sound |

---

## Key Features

* Smooth bird animation with three flight frames (up, mid, down)
* Gravity and jump physics via velocity system
* Dynamic pipe generation with randomized heights
* Meteor obstacle system that activates after reaching a score of 5
* Shield power-up — collectible golden ★ orb that makes the bird immune to meteors for 3 seconds
* Visual shield effect with a blue glow ring and a countdown bar displayed on screen
* Real-time collision detection for pipes, meteors, and power-ups
* "★ NEW BEST!" golden banner on the game over screen when a new high score is set
* Sound effects for jumping, scoring, and game over
* Persistent high score saved locally to the user's home directory
* Menu screen with high score display
* Back button available during gameplay to return to the main menu

---

## Gameplay

### Controls
Press **SPACE** to make the bird jump. Press **R** to restart after a game over.

### Pipes
Pipes spawn every 1.5 seconds and move from right to left with randomized gap positions.

### Meteors
Once the player reaches a score of 5, meteors begin spawning every 3 seconds. They move diagonally, adding an extra layer of difficulty.

### Power-Ups
Every 10 seconds a golden ★ orb spawns at a random height. Collecting it activates a shield that renders the bird immune to meteor collisions for 3 seconds. A shield bar at the top of the screen shows the remaining duration.

### Scoring
Each pipe successfully passed increases the score by 1. The high score is saved between sessions. If a new high score is reached, a "★ NEW BEST!" banner appears on the game over screen.

### Game Over
The game ends if the bird collides with a pipe, a meteor (without an active shield), or the ground.

---

## Technologies

* Java OOP
* Java Swing (GUI & CardLayout navigation)
* Event-Driven Programming
* Game Physics (gravity & velocity)
* Collision Detection (AABB)
* Multithreaded Game Loop
* Java Sound API