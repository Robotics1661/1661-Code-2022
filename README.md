<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&height=140&color=0:aa0000,50:d40000,100:ff1a1a&section=header" alt="" width="100%" />

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://team1661.com/assets/images/1661-logo-white.png">
  <img src="https://team1661.com/assets/images/1661-logo-black.png" alt="Team 1661 logo" width="120">
</picture>

# 🤖 1661-Code-2022

**TimedRobot code for the 2021-22 season**

[![FRC: Team 1661](https://img.shields.io/badge/FRC-Team%201661-d40000?style=for-the-badge&logo=firstinspires&logoColor=white)](https://team1661.com)
![Java: 11](https://img.shields.io/badge/Java-11-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
[![WPILib: 2022](https://img.shields.io/badge/WPILib-2022-003366?style=for-the-badge)](https://docs.wpilib.org/)
[![License](https://img.shields.io/github/license/Robotics1661/1661-Code-2022?style=for-the-badge&color=d40000)](LICENSE)

</div>

---

Java robot code for the 2021–22 season from [Griffitrons 1661](https://team1661.com), the FIRST Robotics Competition team at The Buckley School.

This version is a `TimedRobot` that implements a shared `Robot_Framework` interface (controllers, motors and
sensors) and has a class per mechanism: drive, auto drive, shooter, intake, climb, vertical and horizontal
agitators, Limelight and ultrasonic sensor.
[PathWeaver](https://docs.wpilib.org/en/stable/docs/software/pathplanning/pathweaver/index.html) paths for
autonomous are in `PathWeaver/`.

The command-based version of the same robot lives in
[1661-Command-Code-2021](https://github.com/Robotics1661/1661-Command-Code-2021).

Vendor libraries: CTRE Phoenix and NavX (see `vendordeps/`).

## 🛠️ Building and running

You'll need [WPILib 2022](https://docs.wpilib.org/) (it bundles the JDK and Gradle). Then, from the repo root:

```bash
./gradlew build          # compile and run checks
./gradlew simulateJava   # run in the WPILib simulator
./gradlew deploy         # deploy to a roboRIO (must be connected to the robot)
```

On Windows use `gradlew.bat` instead of `./gradlew`. The team number (1661) lives in
`.wpilib/wpilib_preferences.json`.

## 📜 License

Team-written code is released under the [MIT License](LICENSE). Files generated from the WPILib
project template keep their original headers, and WPILib itself is covered by the BSD-style license in
[WPILib-License.md](WPILib-License.md). Vendor libraries in `vendordeps/` are covered by their own licenses.

---

<div align="center">

<sub>Built by students of <a href="https://team1661.com">Griffitrons 1661</a> at The Buckley School 🛠️</sub>

<img src="https://capsule-render.vercel.app/api?type=waving&height=90&color=0:aa0000,50:d40000,100:ff1a1a&section=footer" alt="" width="100%" />

</div>
