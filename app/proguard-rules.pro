# ProGuard rules
-keep public class * {
    public protected *;
}
-keepclassmembers class * {
    public *;
}
-dontwarn androidx.**
-dontwarn com.google.**
