# SuperDuperBomberman
This project was developped together for the Ecole polytechnique de Bruxelles' Object Oriented Programming course (INFO-H200) with Gaetano RIPOLLINO and Robin CHARLIER. The aim of the project was to mimic the gameplay of the BOMBERMAN™ game series by Hudson Soft (now part of Konami Corp.) while showcasing some of the main characteristics of Object Oriented Programming.

The game is designed to be played on a QWERTY keyboard:
- Player 1 (White Bomberman): WASD for moving, Q for placing bombs.
- Player 2 (Black Bomberman): IJKL for moving, U for placing bombs.
- Player 3 (Blue Bomberman): Arrow keys for moving, Ctrl for placing bombs.
- Player 4 (Red Bomberman): Numpad 5123 for moving, Numpad 4 for placing bombs.

The game provides examples of several common Object Oriented Programming practices, such as:
- Model View Controller (MVC) design pattern.
- Getter and setter functions (all object attributes are private).
- Class inheritance (for powerup items).
- Classification of classes into packages.

The game contains the following items from classic BOMBERMAN™ games:
1. Fire Up, makes bombs reach one space further (2 spaces by default).
2. Bomb Up, adds one active bomb to the player's available bomb array (1 by default).
3. Skate, adds 1 to the player's speed value (1 by default) if it is lower than 4.
4. Heart (Revive), gives the player a single chance to resist an explosion (if not available yet).
5. Fire Down, makes bombs reach one space less, unless they already reach only 1 space.
6. Clog, substracts 1 to the player's speed value, unless it is already 1.
7. Danger Bomb, makes (only) the next bomb reach a large area, not bounded by obstacles.
8. Mole Bomb, makes (only) the next bomb dig a mole hole.

Aside for the usual features of a classic BOMBERMAN™ game, the game also contains some novel gameplay features:
- The dimensions of the game area can be manually modified.
- A new rare item, the Mole Bomb, creates temporary holes in the game area. Players can enter a hole, and jump out of it at any available space (by pressing the bomb button). If players spends too much time underground, they suffocate and pass out.
