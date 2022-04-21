# Tank War Game

### 1. Introduction
There are two players (1P, 2P), and both of them start at the destinated location on the map. The player who makes life counts of another player to 0 will be the winner of the game. The screen is a split screen. The left side of the screen is for player1, and the right side of it is for player2. These screens move following each tank. There are two powerups in this game. One is adding life, and another is increasing the damage of bullets. Tanks can’t go through walls, but the tank can destroy the unbreakable walls by shooting bullets. Whenever tanks lose their life, they start again at the destinated location. The result of the game is popping up on each split screen.

### 2. Development Environment
IntelliJ IDEA 2019 3.3, JAVA 12.0.1

### 3. How to Run
Download the .jar file, Navigate to the jar folder in your terminal and Type

``` bash
java -jar tankgame.jar
```

### 4. How to Play
- Control

||Move Forwards|Move Backwards|Rotate Countclockwise|Roate Clockwise|Shoot|
|---|---|---|---|---|---|
|Player1|Up Arrow|Down Arrow|Left Arrow|Right Arrow|Enter|
|Player2|W|S|A|D|Space|

- Rule

There are two kinds of walls, which are breakable walls and unbreakable walls. You can destroy the unbreakable walls by shooting the walls. You can’t pass both walls. They will block you.

Also, there are two kinds of powerups. You can see these two icons on the map( - heart, -missile). If you go over these icons, you could get items. When you get a heart icon, 1 life is added to your tank’s life counts. When you get a missile icon, your tank’s bullet damage is increasing.

Each tank starts with having 3 lives, and 100 health. You can decrease other tank’s health by shooting bullets. If health becomes under 0, one life is disappeared. Then, the tank starts again at the place where they started first. The player who makes the other player’s life count to 0 becomes the winner of the game.


### 5. Play Screen
<img width="1287" alt="game screen" src="https://user-images.githubusercontent.com/60412023/148171638-399c5cf1-062a-4a13-ab14-e27dd045b479.png">

