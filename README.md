# imad5112wa1

# Hera's Meal Helper (IMAD5112wA1)

An Android app written in **Kotlin** that suggests meals based on the **time of day**. Built with **Android Studio**, version controlled on **GitHub**, and automatically built with **GitHub Actions**.

## 🎯 Features
- Text input for time of day (e.g., *Morning, Mid-morning, Afternoon, Afternoon snack, Dinner, After dinner*).
- **If/else** suggestion logic mapped to predefined meals.
- **Reset** button clears input and suggestion.
- **Error handling** with user-friendly Toast messages for invalid input.
- **Logging** (`Log.d/i/w`) for manual testing and troubleshooting.
- Saves suggestion on rotation (**state persistence**).

## 📸 Screenshots
_Add 2–4 images here (home screen, valid suggestion, error message, reset)._

## 🧪 Testing & CI
- Manual tests performed on Android Emulator (API XX).
- Logs observable via **Logcat** (tags: `MealHelper`).
- **GitHub Actions** workflow builds the project and runs `lint`.
  - See `.github/workflows/build.yml`.

## 🏗️ Architecture / Design Choices
- Single-Activity simple UI (XML) for clarity.
- Input normalization (lowercase, trim) to be forgiving.
- `when` expression for readable branching.
- Material 3 theme for accessibility & contrast.

## 🚀 Getting Started
1. Clone:
   ```bash
   git clone https://github.com/CourtneyBritney/imad5112wa1.git
