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
* Dynamic day/night cycle — background transitions smoothly between day and night every 700 points
* Animated backgrounds — erupting volcano and gas station scenery
* Death bounce animation — the dinosaur bounces before the game over screen appears
* Real-time collision detection with hitbox padding for fairness
* Live score display and persistent high score per session
* Score flash effect — every 1000 points the score flashes red with a sound cue
* "★ NEW BEST!" golden indicator on the game over screen when a new high score is set
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

### Night Mode
Every 700 points the scene transitions smoothly between day and night. The background darkens gradually and the score text switches from black to white for readability.

### Scoring
Score increases every frame while the dinosaur is alive. Every 1000 points a sound plays and the score flashes red. The high score is kept for the duration of the session. A "★ NEW BEST!" indicator appears on the game over panel if the previous best is beaten.

### Game Over
When the dinosaur hits an obstacle it bounces briefly before the game over screen appears. All timers stop and the final score is displayed alongside the session best.

---

## Technologies

* Java OOP
* Java Swing (JPanel, JFrame)
* Java Sound API (Clip, AudioInputStream)
* Event-Driven Programming (KeyListener, ActionListener)
* Collision Detection (AABB with padding)
* Multithreaded Game Loop