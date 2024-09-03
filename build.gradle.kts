import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.utils.addToStdlib.applyIf
import java.io.FileWriter

plugins {
    kotlin("jvm") version "1.9.21"
    `maven-publish`
//    application
}

group = "com.github.peco2282"
version = "1.0"
archivesName.set(rootProject.name)

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
    from(sourceSets.main.get().allSource) {
        exclude("**/**.java", "**/**.kt")
    }
}

tasks.getByName("compileKotlin", KotlinCompile::class) {
    destinationDirectory.set(file("$buildPath/ktSources"))
}
fun md5(path: String) = "$path.md5"
fun sha1(path: String) = "$path.sha1"
fun sha256(path: String) = "$path.sha256"
fun sha512(path: String) = "$path.sha512"

fun buildHref(path: String) = "<a href=\"./${path}\">${path}</a>\n" +
        "<a href=\"./${md5(path)}\">${md5(path)}</a>\n" +
        "<a href=\"./${sha1(path)}\">${sha1(path)}</a>\n" +
        "<a href=\"./${sha256(path)}\">${sha256(path)}</a>\n" +
        "<a href=\"./${sha512(path)}\">${sha512(path)}</a>\n"

tasks.register("genIndex", Task::class) {
    file("$buildPath/scm/com/github/peco2282/$archivesName/$version").apply {
        if (!exists()) mkdirs()
    }
    val file = File("$buildPath/scm/com/github/peco2282/$archivesName/$version/index.html")
    if (!file.exists()) {
        file.createNewFile()
    }

    val jarName = archivesName.get() + "-" + version + ".jar"
    val srcName = archivesName.get() + "-" + version + "-sources.jar"
    val docName = archivesName.get() + "-" + version + "-javadoc.jar"
    FileWriter(file).use {
        it.append(
            "<html lang=\"HTML5\">\n" +
                    "<link rel=\"stylesheet\" href=\"./../../../../style.css\">\n" +
                    "<head><title>Index of /com/github/peco2282/${version}</title></head>\n" +
                    "<body>\n" +
                    "<h1>Index of /com/github/peco2282/${version}</h1>\n" +
                    "<hr>\n" +
                    "<pre><a href=\"../index.html\">../</a>\n" +
                    buildHref(jarName) +
                    buildHref(srcName) +
                    buildHref(docName) +
                    "</pre>\n" +
                    "<hr>\n" +
                    "</body>\n" +
                    "</html>\n"
        )

        it.close()
    }
}

tasks.register("archiver") {
//    destinationDirectory.set(file("$buildPath/libs/$version"))
    dependsOn(
//        tasks.jar,
//        tasks.getByName("sourcesJar"),
//        tasks.getByName("javadocJar"),
        tasks.getByName("genIndex"),
        tasks.publish
    )
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(tasks.getByName("javadocJar"))
            artifact(tasks.getByName("sourcesJar"))

            // POMファイルの設定
            pom {
                name.set(archivesName.get())
                description.set("An lib for opengl")
                url.set("https://github.com/peco2282/pecolib")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        name.set("peco2282")
                        email.set("pecop2282@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/peco2282/pecolib.git")
                    developerConnection.set("scm:git:ssh://github.com:peco2282/pecolib.git")
                    url.set("https://github.com/peco2282/pecolib")
                }
            }
        }
    }
    repositories {
        maven {
            url = file("$buildPath/scm").toURI()
        }
    }
}


//kotlin {
//    jvmToolchain(8)
//}

//application {
//    mainClass.set("MainKt")
//}