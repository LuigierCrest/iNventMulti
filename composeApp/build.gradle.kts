import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    
    jvm(){
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)

            // Kscan barcode reader
            implementation(libs.kscan)
        }
        commonMain.dependencies {

            implementation(project(":domain"))
            implementation(project(":data"))
            implementation(project(":presentation"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(projects.shared)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.ktor.clientCIO)

            implementation(libs.koin.compose.navigation3)
            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.jetbrains.navigation3.material3.adaptive)
            implementation(libs.jetbrains.lifecycle.viewmodel.nav3)
            implementation(libs.jetbrains.lifecycle.viewmodel)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.material.icons.extended)

            implementation(libs.kotlinx.datetime)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

android {
    namespace = "com.luigiercrest.inventmulti"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.luigiercrest.inventmulti"
        minSdk = 24
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.luigiercrest.inventmulti.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.luigiercrest.inventmulti"
            packageVersion = "1.0.0"
            linux {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/icon.png"))
            }
            windows {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/icon.ico"))
            }
        }

    }
}

// Fat JAR task para desktop (JVM)
tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Crea un fat JAR con todas las dependencias para desktop"

    archiveBaseName.set("iNventMulti-desktop")
    archiveVersion.set("1.0.0")
    archiveClassifier.set("all")

    // Punto de entrada
    manifest {
        attributes["Main-Class"] = "com.luigiercrest.inventmulti.MainKt"
    }

    // Incluir las clases compiladas del target jvm (desktop)
    val desktopCompilation = kotlin.jvm().compilations["main"]
    from(desktopCompilation.output.allOutputs)

    // Incluir todas las dependencias (excluyendo duplicados)
    from({
        desktopCompilation.runtimeDependencyFiles.filter {
            it.name.endsWith(".jar")
        }.map { zipTree(it) }
    })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    dependsOn("jvmMainClasses")
}
