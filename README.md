# MoneyMaker Host V1 — clean starter project

This is a clean Android Studio/Gradle project for the MoneyMaker Host prototype. It uses Java (no Kotlin plugin), Android Gradle Plugin 8.7.3, Gradle 8.9, and Java 17. GitHub Actions builds a debug APK on every push to `main` and on manual workflow runs.

## Important
This version is a visual starter app only. It does not connect to MetaTrader 5, does not read broker accounts, and does not place trades. Those require a separately designed and secured integration/backend.

## Project files
- `build.gradle.kts` — root plugin versions only; no `android {}` block.
- `settings.gradle.kts` — plugin and dependency repositories plus `:app` inclusion.
- `app/build.gradle.kts` — Android app configuration.
- `.github/workflows/build-apk.yml` — GitHub Actions build workflow.

## Build on GitHub
1. Create a new empty repository named `MoneyMakerHostV1-Clean` (or another name), with no README/license/gitignore added by GitHub.
2. Upload the extracted project files and folders so `.github`, `app`, `build.gradle.kts`, `settings.gradle.kts`, and `gradle.properties` are at the repository root. Do not upload the ZIP as the only file.
3. Commit to the `main` branch.
4. Open the repository's **Actions** tab and select **Build MoneyMaker Host APK**.
5. When the run is green, open the run and download the artifact named `MoneyMakerHostV1-debug-apk`.
6. Extract the downloaded artifact ZIP; it contains `app-debug.apk`. Open that APK on your Android phone to install. You may need to allow installs from the app used to open it.

## Build configuration
- `compileSdk = 35`, `minSdk = 24`, `targetSdk = 35`
- Android Gradle Plugin `8.7.3`
- Gradle `8.9`
- Java `17`
