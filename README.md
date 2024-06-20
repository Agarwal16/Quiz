# MyQuiz App

MyQuiz is a simple Android quiz application that presents multiple-choice questions to users, tracks their correct answers, and displays a score at the end. The app includes a start screen, a quiz screen with a countdown timer, and a result screen.

## Features

- Multiple-choice questions loaded from a JSON file.
- Countdown timer for the quiz.
- Tracking of correct answers.
- Display of results at the end of the quiz.
- Navigation between start, quiz, and result screens.

## Getting Started

### Prerequisites

- Android Studio
- Android device or emulator running Android API level 21 or higher

### Installation

1. Clone the repository:

```bash
git clone https://github.com/Agarwal16/Quiz.git
```

2. Open the project in Android Studio.

3. Build and run the app on an Android device or emulator.

### Project Structure

```
myquiz/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/myquiz/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── StartActivity.kt
│   │   │   │   ├── ResultActivity.kt
│   │   │   │   ├── Question.kt
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── question_background.xml
│   │   │   │   │   ├── question_background_with_shadow.xml
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_start.xml
│   │   │   │   │   ├── activity_result.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── styles.xml
│   │   │   │   ├── assets/
│   │   │   │   │   ├── questions.json
│   ├── build.gradle
├── build.gradle
├── README.md
```

### JSON File Format

The `questions.json` file should be placed in the `assets` directory. The format of the file should be as follows:

```json
[
  {
    "question": "What is the capital of France?",
    "choices": ["Paris", "London", "Berlin", "Madrid"],
    "answer": "Paris"
  },
  {
    "question": "What is 2 + 2?",
    "choices": ["3", "4", "5", "6"],
    "answer": "4"
  }
]
```

### Code Overview

#### `MainActivity.kt`

The main activity where the quiz takes place. It handles loading questions, displaying them, and tracking the user's answers.

#### `StartActivity.kt`

The start screen activity where users can begin the quiz.

#### `ResultActivity.kt`

The result screen activity that displays the user's score at the end of the quiz.

#### `Question.kt`

A data class representing a quiz question.

### Layout Files

#### `activity_main.xml`

The layout for the quiz screen, containing a question text view, a radio group for choices, a next button, and a timer text view.

#### `activity_start.xml`

The layout for the start screen, containing a button to start the quiz.

#### `activity_result.xml`

The layout for the result screen, displaying the user's score and a button to go back to the start screen.

### Drawable Resources

#### `question_background.xml`

Defines the shape and background color for the question text view.

#### `question_background_with_shadow.xml`

Defines the shape and shadow effect for the question text view.

### Colors

#### `colors.xml`

Defines the color resources used throughout the app.

```xml
<resources>
    <color name="background_color">#F5F5F5</color>
    <color name="primary_text">#212121</color>
    <color name="button_text">#FFFFFF</color>
    <color name="button_background">#6200EE</color>
</resources>
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Thanks to the Android developer community for providing tutorials and examples.
- This app uses the Gson library to parse JSON data.

## Contact

For any questions or feedback, please reach out to [agarwaltanmaay401@gmail.com](mailto:agarwaltanmay401@gmail.com).
