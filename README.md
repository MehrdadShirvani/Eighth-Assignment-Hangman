# Eighth Assignment Hangman 🪢
## Introduction 👋
Welcome to your last assignment before the Final project. 🤠🤠  
I hope you all are still energetic and ready to continue your excellent work. In this project, you will build a Hangman game using JavaFX and store its data in a chosen database. 💻
## Prerequisites ✅
1) Make sure you have installed these apps before starting your project:

- SceneBuilder
- Mongodb/PostgreSQL
- Java 21
- Git
- Maven as a package manager 

## Objectives ✏️

By completing this assignment, you will:

- Learn about databases and how to work with them.
- Gain experience with JavaFX and UI development.


## Assignment Overview 🔎

Your task is to develop a Hangman game. Hangman is a game where the player must guess the word by suggesting letters within a limited number of guesses. Incorrect guesses result in a part of a stick figure being drawn, and the game is lost if the figure is completed before the word is guessed. Note that you must design a Graphical User Interface for this project using JavaFX.

## How to Play Hangman

1. **Setup**:
   - The host (the computer, in this case) chooses a word and draws a series of blank spaces representing each letter of the word. For example, if the word is "APPLE", 5 blank spaces must be shown to the player.
     ```
     _ _ _ _ _
     ```

2. **Gameplay**:
   - The player (the guesser) suggests letters one at a time.
   - If the guessed letter is in the word, the host fills in the corresponding blanks with that letter, and the letter will vanish from the keyboard.
   - If the guessed letter is not in the word, the host draws one part of a hangman figure. You can draw this figure however you like in your GUI, but the typical order of drawing is:
     1. Head
     2. Body
     3. Left arm
     4. Right arm
     5. Left leg
     6. Right leg

3. **Winning and Losing**:
   - The guesser wins the game if they correctly guess all the letters in the word before the hangman figure is fully drawn.
   - The guesser loses the game if the hangman figure is completed before the word is guessed.

### Example of a game

#### Step 1: Initial Setup
- The host thinks of the word "JAVA" and draws the blanks:
  ```
  _ _ _ _
  ```

#### Step 2: First Guess
- The guesser suggests the letter "A".
- The host fills in the blanks:
  ```
  _ A _ A
  ```
  No part of the hangman is drawn since the guess was correct.
  Letter A will vanish from the keyboard.

#### Step 3: Second Guess
- The guesser suggests the letter "E".
- "E" is not in the word, so the host draws the head of the hangman.
  ```
  Drawing:
   O
  ```
  Word:
  ```
  _ A _ A
  ```

#### Step 4: Third Guess
- The guesser suggests the letter "J".
- The host fills in the blanks:
  ```
  J A _ A
  ```
  No additional part of the hangman is drawn.

#### Step 5: Fourth Guess
- The guesser suggests the letter "V".
- The host fills in the blanks:
  ```
  J A V A
  ```
  The word is fully guessed, and the guesser wins!

#### Final Drawing if the Guesser Loses:
If the guesser makes enough incorrect guesses, the hangman figure is fully drawn, and the word is not guessed:
```
   ____
  |    |
  O    |
 /|\   |
 / \   |
       |
  =====
```

For more information, we recommend playing online hangman, available in the [resource section](#Resources-).




## Tasks 📝

1. Fork this repository and clone it to your local machine.
2. Create a new branch named `develop` and switch to it. All development should be done in this branch.
3. Implement your Hangman game code. The following sections detail what needs to be done.
4. Record a video showcasing your project while explaining its functionality. You can either submit this video to your mentor or push it to your repository. Your video should be between 1 to 2 minutes long.




## Database Features ⛁
You can implement your database with either SQL or MongoDB. There are no preferences.
This project will utilize 2 Tables:

1) Implement a Table titled: `UserInfo`
   When a user wants to start the game,
   they must add their information. 
   After signing up their information should be saved in the database.
   
    <pre>
    --Name--|--Username--|--Password--
            |            |
                 </pre>
    ```NOTE``` : Make sure each username is different from others.
   </pre>
   
2) You should create a table titled: `GameInfo`,
Store the details of **each** played game in this table.

   <pre>
   --GameID--|--Username--|--Word--|--WrongGuesses--|--Time--|--Win--
             |            |        |                |        |
   </pre>

    `GameID`: A unique value identifying the game.
   
    `Username`: The username of the player.
   
    `Word`: The word that was chosen for that specific game.

    `WrongGuesses`: Count the number of wrong letters guessed during the game.

    `Time`:  The amount of time (in seconds) spent in one game.

    `Win`:  A boolean to store whether or not the game was won.

**Important: Ensure to specify the primary key when creating your Database. You are allowed to add additional columns and tables if necessary.**

## UI + Front Features 🎨
 * You must first design the Login, Signup, and main menu pages.
 * After logging in, the player should be able to access 3 buttons on the main page:
   1) Start the game.
   2) View the player's previous game details. Use the data stored in the `GameInfo` table to display a summary of every game played by the current user.
   3) View the leaderboard. Display every player's name alongside their total number of wins. The leaderboard should be sorted from the highest number of wins to the lowest.

While playing the game, note that:
 - For each wrong letter, you must display the next part of the stick figure.
 - You must measure the amount of time the player has spent on each game and store it in the database.
 - Data (words) should be randomly generated by using [Animals API](https://api-ninjas.com/api/animals). First, you can test your code with a fixed word and then from the list API gives you, generate random words for your game. 


## Evaluation 📃

Your submission will be evaluated based on the following criteria:

- Error-free compilation and execution of your code.
- Clean and visually appealing UI.
- Correct use and design of the database.


**Note: Usage of ChatGPT or any other AI generative model to complete any part of this assignment is strictly prohibited. Violation will result in a score of 0 without warnings.**

## Bonus Objectives ✒️
- Add different difficulty levels to your game, with longer words or a smaller number of guesses.
- Display a timer on the screen as the user is playing the game. The timer should stop once the game is finished.
- Implement a competitive mode with two players playing simultaneously.
- Enhance your leaderboard by adding filters that allow users to search the data by username, duration, number of wins, and number of incorrect guesses.

## Submission ⌛
The deadline for submitting your code is **Friday, June 7 (18th of Khordad)**. Good luck!

## Resources 📚
🔗 [Online Hangman game](https://www.gamestolearnenglish.com/hangman/)

🔗 [Install postgresql](https://www.postgresql.org/download/)

🔗 [Install Mongodb](https://www.mongodb.com/try/download/community
)

🔗 [Install scenebuilder]( https://gluonhq.com/products/scene-builder
)


