@echo off
setlocal enabledelayedexpansion

echo =============================================
echo Build and copy Flutter APK (debug) - streamlit_wrapper
echo =============================================
:: Ensure running from this script's foldercd /d "%~dp0"
:: Optionally set SDK path via local.properties if not set:: If local.properties doesn't have sdk.dir, user must edit android\local.properties manually.
:: Check flutter availabilitywhere flutter >nul 2>nulif errorlevel 1 (    echo Flutter not found in PATH. Please install Flutter and add it to PATH.    pause    exit /b 1)echo Running flutter pub get...flutter pub getif errorlevel 1 (    echo flutter pub get failed. Check network and Flutter installation.    pause    exit /b 1)echo Building debug APK...flutter build apk --debugif errorlevel 1 (    echo flutter build apk failed. Ensure Android SDK is installed and configured (local.properties or ANDROID_SDK_ROOT).    pause    exit /b 1)set "APK_PATH=build\app\outputs\flutter-apk\app-debug.apk"if not exist "%APK_PATH%" (    echo APK not found at %APK_PATH%    pause    exit /b 1)if not exist "..\releases" mkdir "..\releases"copy /Y "%APK_PATH%" "..\releases\streamlit-wrapper-debug.apk" >nulif errorlevel 1 (    echo Failed to copy APK to ..\releases\    pause    exit /b 1)
echo APK built and copied to: ..\releases\streamlit-wrapper-debug.apk
echo If you want to install to a connected device now, run: adb install -r "..\releases\streamlit-wrapper-debug.apk"
echo Done.pause
