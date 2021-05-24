# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/miguelcatalan/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

##---------------Begin: Language matters  ----------
# This is for Kotlin
-dontwarn kotlin.**

# This is for javax
-dontwarn javax.**

-keep public class * extends java.lang.Exception

# This only for JavaScript Target
-dontwarn org.w3c.dom.events.*

# Preserve the special static methods that are required in all enumeration classes.
-keepclassmembers enum * { *; }
##---------------End: Language matters  ----------

# Use unique member names to make stack trace reading easier
-useuniqueclassmembernames

##---------------Begin: Android optimization  ----------
-optimizationpasses 5
-allowaccessmodification
-optimizations !code/simplification/arithmetic,!code/simplification/arithmetic,!field/*,!class/merging/*
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-ignorewarnings
##---------------End: Android optimization  ----------

##---------------Begin: AndroidX  ----------

-dontwarn androidx.**
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

-keep class com.google.android.material.** { *; }

-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**

-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

##---------------End: AndroidX  ----------

##---------------Begin: proguard configuration for Google Services  ----------
-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault

-keepclassmembers class * {
  @com.google.api.client.util.Key <fields>;
}

-keep class com.google.** { *;}
-keep interface com.google.** { *;}
-keep public class com.google.android.gms.**

-dontwarn com.google.**
-dontwarn com.google.android.gms.**

# Needed by google-http-client-android when linking against an older platform version
-dontwarn com.google.api.client.extensions.android.**

# Needed by google-api-client-android when linking against an older platform version
-dontwarn com.google.api.client.googleapis.extensions.android.**

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
##---------------End: proguard configuration for Google Services  ----------

##---------------Begin: Dagger  ----------
-dontwarn dagger.internal.codegen.**
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}
-keep class dagger.* { *; }
-keep class javax.inject.* { *; }
-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection
-keep class * extends com.cabify.rider.injector.abstraction.activity.ActivityComponent {
    public void inject(*);
}
##---------------End: Dagger  ----------

##-------Begin: Hilt -----------

# Keep class names of Hilt injected ViewModels since their name are used as a multibinding map key.
-keepnames @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel

##---------------Begin: Glide  ----------
# https://github.com/bumptech/glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
##---------------End: Glide  ----------

##---------------Begin: Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
##---------------End: Gson  ----------

##---------------Begin: Retrofit 2.X  ----------
## https://square.github.io/retrofit/ ##
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions
##---------------Begin: Retrofit 2.X  ----------

##---------------Begin: OKHttp  ----------
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn retrofit2.**
-dontwarn okhttp3.**

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
##---------------End: OKHttp  ----------

##---------------Start: Crashlytics ----------

-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

##---------------End: Crashlytics ----------

##--------------- Start: GSON ----------
# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

##--------------- End: GSON ----------
