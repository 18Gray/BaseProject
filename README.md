# baseproject

A new Flutter project.

## Getting Started

This project is a starting point for a Flutter application.

A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://flutter.dev/docs/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://flutter.dev/docs/cookbook)

For help getting started with Flutter, view our
[online documentation](https://flutter.dev/docs), which offers tutorials,
samples, guidance on mobile development, and a full API reference.




C:\Windows\Temp  删除
C:\Windows\Prefetch  删除
C:\Windows\SoftwareDistribution\Download  删除
C:\Windows\System32\LogFiles  删除

C:\Users\supaur\.android\avd 删除
C:\Users\supaur\.android\build-cache  只保留当前AS版本
C:\Users\supaur\.gradle\caches  不用的版本删除
C:\Users\supaur\.gradle\daemon  删除
C:\Users\supaur\.gradle\wrapper\dists  不用的版本删除
C:\Users\supaur\AppData 看着删除




flutter pub run environment_config:generate -e prod -r true
flutter pub run environment_config:generate -e sit -r false

flutter build apk --release
flutter build apk --release  --flavor pro
adb shell "dumpsys window | grep mCurrentFocus"

flutter build apk --target-platform android-arm,android-arm64,android-x64 --split-per-abi

flutter build apk --release  --flavor pro --obfuscate --split-debug-info=Obfuscate_Map --target-platform android-arm,android-arm64,android-x64 --split-per-abi

./gradlew assembleDebug --stacktrace