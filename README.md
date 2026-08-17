# Streamlit WebUI Android Shell (Kotlin)

This repository contains a minimal Android app that acts as a fullscreen WebView shell for a Streamlit app.

Structure:
- app/src/main/java/com/streamlitwrapper/
  - MainActivity.kt        # WebView main UI
  - SettingsActivity.kt    # Settings page to enter Streamlit server URL
  - Constants.kt
- app/src/main/res/layout/
  - activity_main.xml
  - activity_settings.xml
- app/src/main/res/values/
  - strings.xml
- app/src/main/AndroidManifest.xml
- releases/                # Put built APK(s) here

How to open and build
1. Open Android Studio and choose "Open an existing Android Studio project", select the `e:\ai\ai\Streamlitapp` folder.
2. If necessary, create or update Gradle project files (Android Studio can prompt to update Gradle settings). This scaffold contains only the app source and resources; use Android Studio's project creation/import flow to finish Gradle configuration if missing.
3. Connect an Android device or start an emulator and run the app.
4. To build an APK: use Build > Build Bundle(s) / APK(s) > Build APK(s). Place the generated APK under `releases/` when done.

Note
- The WebView only loads the URL you set in Settings. Make sure your Streamlit server is accessible from the phone (same LAN or public URL with port 8501).
- WebSocket and CORS issues are handled by the Streamlit server; WebView is configured with JavaScript and DOM storage enabled for compatibility.

