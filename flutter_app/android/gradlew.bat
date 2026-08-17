@echo off
REM Minimal gradlew.bat placeholder. If the Gradle wrapper jar is not present, run 'gradle wrapper' locally or generate wrapper via Android Studio.
IF EXIST "%~dp0gradle\wrapper\gradle-wrapper.jar" (
  java -jar "%~dp0gradle\wrapper\gradle-wrapper.jar" %*
) ELSE (
  echo Gradle wrapper not found. Run "gradle wrapper" in this folder or use Android Studio to generate it.
  exit /b 1
)
