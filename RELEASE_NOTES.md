# Release Notes - v1.0.0

**Release Date**: January 15, 2026

## 🎓 UIU CGPA Calculator - First Official Release

A modern, professional desktop application for calculating term GPA and updated CGPA for United International University (UIU) students.

---

## ✨ Key Features

- ✅ **Comprehensive GPA Calculation** - Calculate both term GPA and updated CGPA in a single step
- 🔄 **Retake Course Support** - Automatically handles grade replacement for retaken courses
- 📚 **Dynamic Course Management** - Add up to 15 courses with instant removal options
- 📊 **Integrated Grading Scale** - Built-in reference showing complete UIU grading scale (A to F)
- 📱 **Responsive Design** - Adaptive layout that shows grading panel when width exceeds 880px
- 🖥️ **Fullscreen Mode** - Press F11 or F to toggle fullscreen viewing for better focus
- ✔️ **Input Validation** - Prevents invalid entries with helpful, descriptive error messages
- 🎨 **Professional Results Display** - Clean dialog showing term credits, total credits, GPA, and CGPA
- 🔒 **Privacy-First** - No data storage, no internet connection required, completely offline

---

## 📦 Download & Installation

### Option 1: Windows Portable Executable (Recommended for Windows)

**Perfect for**: Users who want zero setup

- **File**: `UIU-CGPA-Calculator-Windows-Portable.zip` (45 MB)
- **Requirements**: None - fully self-contained
- **Installation**: 
  1. Download and extract the ZIP
  2. Run `UIU-CGPA-Calculator.exe`
  3. Done! No Java installation needed

### Option 2: Cross-Platform JAR (Windows/macOS/Linux)

**Perfect for**: Users who already have Java installed

- **File**: `UIU-CGPA-Calculator.jar` (13 KB)
- **Requirements**: Java Runtime Environment (JRE) 8+
- **Installation**: 
  1. Download the JAR file
  2. Double-click to run, or execute: `java -jar UIU-CGPA-Calculator.jar`

### Option 3: Build from Source

**Perfect for**: Developers who want to customize

```bash
git clone https://github.com/litch07/uiu-cgpa-calculator-swing-java.git
cd uiu-cgpa-calculator-swing-java
javac *.java
java Main
```

---

## 🚀 Quick Start Guide

1. **Launch** the application
2. **(Optional) Set Baseline Data**:
   - Enter completed credits from previous semesters
   - Enter your current CGPA
3. **Add Courses**: Click "Add Course" for each course you're taking
   - Select credit hours (1-4)
   - Select expected grade (A-F)
4. **Handle Retakes** (if applicable):
   - Check the "Retake" checkbox for retaken courses
   - Select the previous grade
5. **Click "Calculate Results"** to see:
   - Term GPA for this semester
   - Updated CGPA

---

## 📊 Grading Scale Reference

| Letter | Grade Point | Marks Range |
|--------|-------------|-------------|
| A      | 4.00        | 90-100      |
| A-     | 3.67        | 86-89       |
| B+     | 3.33        | 82-85       |
| B      | 3.00        | 78-81       |
| B-     | 2.67        | 74-77       |
| C+     | 2.33        | 70-73       |
| C      | 2.00        | 66-69       |
| C-     | 1.67        | 62-65       |
| D+     | 1.33        | 58-61       |
| D      | 1.00        | 55-57       |
| F      | 0.00        | Below 55    |

---

## ⌨️ Keyboard Shortcuts

| Shortcut | Action |
|----------|--------|
| **F11** or **F** | Toggle fullscreen mode |
| **ESC** | Exit fullscreen |
| **Enter** | Calculate results (when button focused) |

---

## 🛠️ Technical Specifications

- **Language**: Java
- **GUI Framework**: Swing
- **JDK Version**: 8+
- **Minimum Window Size**: 850×620 pixels
- **JAR Size**: 13 KB
- **Windows Portable Size**: 45 MB (includes bundled JRE)
- **Operating Systems Supported**: Windows 7+, macOS 10.10+, Linux

---

## 📋 What's Included

- ✅ Professional Swing-based GUI
- ✅ Robust input validation
- ✅ Accurate GPA/CGPA calculations
- ✅ Retake course handling
- ✅ Fullscreen mode support
- ✅ Responsive layout design
- ✅ Grading scale reference panel
- ✅ Results display dialog

---

## 🐛 Known Issues

None reported in v1.0.0

---

## 📝 System Requirements

**Minimum**:
- Windows 7, macOS 10.10, or any modern Linux distribution
- Display: 850×620 resolution
- RAM: 256 MB
- Disk: 50 MB (for portable version) or 13 KB (for JAR)

**Recommended**:
- Windows 10+, macOS 11+, or Ubuntu 20.04+
- Display: 1366×768 or higher
- RAM: 2 GB
- Processor: Modern multi-core CPU

---

## 📞 Support & Feedback

- **GitHub Issues**: [Report bugs or request features](https://github.com/litch07/uiu-cgpa-calculator-swing-java/issues)
- **Author**: SADID AHMED
- **GitHub Profile**: [@litch07](https://github.com/litch07)

---

## 📄 License

This project is licensed under the **MIT License** - see [LICENSE](LICENSE) file for details.

---

## 🎯 Future Roadmap

Potential features for future releases:
- Support for different grading scales
- Data persistence (save/load calculations)
- Export to PDF or Excel
- Support for more than 15 courses
- Modern UI themes
- Unit test suite
- GPA tracker across multiple semesters

---

<div align="center">

**⭐ If you find this project helpful, please give it a star on GitHub!**

Made with ❤️ for UIU Students

**v1.0.0** | January 15, 2026

</div>
