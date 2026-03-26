# Dino Adventure – Java Desktop Game

Dino Adventure is a Java desktop game inspired by the iconic **Chrome Dinosaur**, built with **Java Swing**. The objective is to control the dinosaur and dodge cacti, birds, and volcanic landscapes for as long as possible.

---

## Project Architecture

| Module | Link | Functionality |
|:------|:------|:---------------|
| **App** | [App.java](App.java) | Entry point and window setup |
| **Game Engine** | [ChromeDino.java](ChromeDino.java) | Core gameplay: rendering, physics, obstacle generation, collision detection, scoring, audio |

---

## Key Features

* Jump and duck mechanics to dodge obstacles at different heights
* Randomized obstacle generation — small cacti, big cacti, and birds at 3 altitudes
* Progressive difficulty — game speed increases every 10 seconds
* Animated backgrounds — erupting volcano and gas station scenery
* Real-time collision detection with hitbox padding for fairness
* Live score display and persistent high score per session
* Score flash effect — every 1000 points the score flashes red with a sound cue
* Sound effects — jump, point milestone, and death sounds
* Game Over screen with clickable restart button
* Automatic cleanup of off-screen obstacles to maintain performance

---

## Preview
```
┌─────────────────────────────────────────┐
│  300                    High Score: 450 │
│                                         │
│   🦕  ──────🌵──────🐦──────────🌵─── │
└─────────────────────────────────────────┘
```

---

## Gameplay

### Controls
Press **↑** to start the game and jump. Hold **↓** to duck under obstacles. Use the **Restart Button** after a game over.

### Obstacles
Cacti and birds spawn every 1.5 seconds and move from right to left. Birds appear at randomized heights, requiring either a jump or a duck to avoid.

### Speed
Every 10 seconds the track and obstacle velocity increases, making survival progressively harder.

### Scoring
Score increases every frame while the dinosaur is alive. Every 1000 points a sound plays and the score flashes red. The high score is kept for the duration of the session.

### Game Over
The game ends if the dinosaur collides with any obstacle. All animations stop, sounds halt, and the final score is displayed.

---

## Technologies

* Java OOP
* Java Swing (JPanel, JFrame)
* Java Sound API (Clip, AudioInputStream)
* Event-Driven Programming (KeyListener, ActionListener)
* Collision Detection (AABB with padding)
* Multithreaded Game Loop