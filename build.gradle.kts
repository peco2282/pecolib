import org.jetbrains.kotlin.codegen.optimization.fixStack.removeAlwaysFalseIfeq
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

val buildPath = projectDir.path + "/build"

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

tasks.register("sourcesJar", Jar::class.java) {
    archiveClassifier.set("sources")
    destinationDirectory.set(file("$buildPath/libs/$version"))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().allSource)
}

tasks.register("javadocJar", Jar::class.java) {
    archiveClassifier.set("javadoc")
    destinationDirectory.set(file("$buildPath/libs/$version"))

    from(tasks.javadoc.get().destinationDir)
}

tasks.javadoc {
    include("src/main/java")
}

tasks.withType(JavaCompile::class.java).configureEach {
    options.encoding = "UTF-8"
//    destinationDirectory.set(file("$buildPath/libs/$version"))
}

tasks.getByName("jar", Jar::class) {
    destinationDirectory.set(file("$buildPath/libs/$version"))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().allSource){
        exclude("**/**.java", "**/**/**.kt")
    }
}

tasks.getByName("compileKotlin", KotlinCompile::class) {
    destinationDirectory.set(file("$buildDir/ktSources"))
}

tasks.register("archiver") {
//    destinationDirectory.set(file("$buildPath/libs/$version"))
    dependsOn(
        tasks.jar,
        tasks.getByName("sourcesJar"),
        tasks.getByName("javadocJar")
    )
}



//kotlin {
//    jvmToolchain(8)
//}

//application {
//    mainClass.set("MainKt")
//}