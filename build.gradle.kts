plugins {
    kotlin("jvm") version "1.9.21"
//    application
}

group = "com.github.peco2282"
version = "1.1"

repositories {
    mavenCentral()
}

val LWJGL_VERSION = "3.3.3"

dependencies {
    implementation("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    testImplementation(kotlin("test"))
    implementation("org.lwjgl:lwjgl:$LWJGL_VERSION")
    implementation("org.lwjgl:lwjgl-opengl:$LWJGL_VERSION")
    implementation("org.lwjgl:lwjgl-glfw:$LWJGL_VERSION")
    implementation("org.lwjgl:lwjgl-stb:$LWJGL_VERSION")

    runtimeOnly("org.lwjgl:lwjgl:$LWJGL_VERSION:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-opengl:$LWJGL_VERSION:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-glfw:$LWJGL_VERSION:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-stb:$LWJGL_VERSION:natives-windows")

    // https://mvnrepository.com/artifact/com.google.code.findbugs/jsr305
    implementation("com.google.code.findbugs:jsr305:3.0.2")
//    // https://mvnrepository.com/artifact/com.google.code.findbugs/annotations
//    implementation("com.google.code.findbugs:annotations:3.0.1")




}

sourceSets {
    main {
        java.srcDirs("src/main/java")
        kotlin.srcDirs("src/main/kotlin")
    }
    test {
        java.srcDirs("src/test/java")
        kotlin.srcDirs("src/test/kotlin")
    }
}

tasks.test {
    useJUnitPlatform()
}

//kotlin {
//    jvmToolchain(8)
//}

//application {
//    mainClass.set("MainKt")
//}