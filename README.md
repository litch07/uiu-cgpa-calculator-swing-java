# UIU CGPA Calculator

[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)
[![Swing](https://img.shields.io/badge/GUI-Swing-blue.svg)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A modern desktop application for calculating term GPA and updated CGPA for United International University (UIU) students. Built with Java Swing, this calculator features an intuitive interface with support for retake courses and a built-in grading scale reference.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Building and Running](#building-and-running)
- [Usage](#usage)
- [Grading Scale](#grading-scale)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Overview

The UIU CGPA Calculator is a comprehensive academic performance tracking tool designed specifically for UIU students. It automatically computes semester GPA and cumulative CGPA while handling complex scenarios such as retake courses, where previous grades are replaced in CGPA calculations while maintaining accurate term GPA reporting.

This lightweight desktop application requires no internet connection and stores no data, ensuring complete privacy for your academic records.

## Features

- ✅ **Comprehensive GPA Calculation**: Calculate both term GPA and updated CGPA in one step
- 🔄 **Retake Course Support**: Properly handles grade replacement for retaken courses
- 📚 **Dynamic Course Management**: Add up to 15 courses with easy removal options
- 📊 **Integrated Grading Scale**: Built-in reference showing letter grades, grade points (0.00-4.00), and mark ranges
- 📱 **Responsive Design**: Adaptive layout that shows the grading scale panel when window width exceeds 880px
- 🖥️ **Fullscreen Mode**: Press F11 or F to toggle fullscreen viewing
- ✔️ **Input Validation**: Prevents invalid entries and provides helpful error messages
- 🎨 **Professional Results Display**: Clean dialog showing calculated GPA and CGPA
- 🔒 **Privacy-First**: No data storage, no internet required

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- **Java Development Kit (JDK)**: Version 8 or higher
  - Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
  - Verify installation: `java -version` and `javac -version`
  
- **Operating System**: Windows 7+, macOS 10.10+, or Linux (any modern distribution)

- **Display**: Minimum screen resolution of 850×620 pixels (recommended: 900×700 or higher)

## Installation

### Option 1: Clone the Repository

```terminal
git clone https://github.com/litch07/uiu-cgpa-calculator-swing-java.git
cd uiu-cgpa-calculator-swing-java
```

### Option 2: Download ZIP

1. Click the green **Code** button at the top of this repository
2. Select **Download ZIP**
3. Extract the ZIP file to your desired location
4. Open a terminal/command prompt in the extracted folder

## Building and Running

### Compile the Application

```terminal
javac *.java
```

### Run the Application

```terminal
java Main
```

**Note**: Ensure you're in the project directory when running these commands.

**Note**: Ensure you're in the project directory when running these commands.

## Usage

### Quick Start Guide

1. Launch the application using `java Main`
2. The calculator window will open with a default window size
3. Start adding your courses and calculating your GPA/CGPA

### Basic Calculation

1. **Enter Baseline Data** (optional, but recommended):
   - **Completed Credits**: Total credits you've earned before this semester (e.g., 90)
   - **Current CGPA**: Your CGPA before this semester (e.g., 3.45)
   
   > Leave these fields empty if you're calculating your first semester GPA

2. **Add Courses**:
   - Click **"Add Course"** button to add course rows (maximum 15 courses)
   - For each course:
     - Select **credit hours** (1-4) from the dropdown
     - Select **letter grade** (A to F) from the dropdown
     - The corresponding **mark range** will be displayed automatically

3. **Calculate Results**:
   - Click **"Calculate Results"** button
   - A dialog will display your:
     - **Term GPA**: GPA for the current semester
     - **Updated CGPA**: Your new cumulative GPA

### Handling Retake Courses

If you're retaking a course to improve your grade:

1. Check the **"Retake"** checkbox for that course
2. Select the **previous grade** you received from the "Previous Grade" dropdown
3. The calculator will:
   - Include the new grade in your term GPA calculation
   - Replace the old grade with the new grade in your CGPA calculation
   - Remove the old grade's credit from your completed credits (preventing double counting)

**Important**: When using the retake feature, you **must** provide both:
- Completed Credits
- Current CGPA

### Keyboard Shortcuts

| Shortcut | Action |
|----------|--------|
| **F11** or **F** | Toggle fullscreen mode |
| **ESC** | Exit fullscreen or close the application |
| **Enter** | Calculate results (when button is focused) |

### Tips

- You can remove any course row by clicking the **"X"** button
- The grading scale panel automatically appears when the window width exceeds 880px
- All fields are validated to prevent calculation errors
- The application maintains minimum window dimensions for optimal usability

## Grading Scale

The UIU CGPA Calculator uses the standard United International University grading scale:

| Letter Grade | Grade Point | Marks Range (%) |
|--------------|-------------|-----------------|
| A            | 4.00        | 90-100          |
| A-           | 3.67        | 86-89           |
| B+           | 3.33        | 82-85           |
| B            | 3.00        | 78-81           |
| B-           | 2.67        | 74-77           |
| C+           | 2.33        | 70-73           |
| C            | 2.00        | 66-69           |
| C-           | 1.67        | 62-65           |
| D+           | 1.33        | 58-61           |
| D            | 1.00        | 55-57           |
| F            | 0.00        | Below 55        |

## Contributing

Contributions are welcome! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch:
   ```terminal
   git checkout -b feature/YourFeatureName
   ```
3. **Commit** your changes:
   ```terminal
   git commit -m "Add: Brief description of your changes"
   ```
4. **Push** to your branch:
   ```terminal
   git push origin feature/YourFeatureName
   ```
5. **Open** a Pull Request

### Ideas for Contributions

- Add support for different grading scales
- Implement data persistence (save/load calculations)
- Create export functionality (PDF, Excel)
- Add support for more than 15 courses
- Improve UI/UX with modern themes
- Add unit tests

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## Contact

**SADID AHMED**

- GitHub: [@litch07](https://github.com/litch07)
- Repository: [uiu-cgpa-calculator-swing-java](https://github.com/litch07/uiu-cgpa-calculator-swing-java)

---

<div align="center">

**⭐ If you find this project helpful, please consider giving it a star!**

Made with ❤️ for UIU Students

</div>
