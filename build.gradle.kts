import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("org.jetbrains.intellij") version "1.8.0"
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = "com.hackerguild"
version = "1.0-SNAPSHOT"

sourceSets["main"].java.srcDirs("src/main/gen")

allprojects {
    apply {
        plugin("org.jetbrains.grammarkit")
    }
}

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2021.3.3")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

grammarKit {

}

val generateWexprLexer = task<GenerateLexerTask>("generateWexprLexer") {
    source.set("src/main/kotlin/com/hackerguild/intellijwexpr/Wexpr.flex")
    targetDir.set("src/main/gen/com/hackerguild/intellijwexpr")
    targetClass.set("WexprLexer")
    purgeOldFiles.set(true)
}

val generateWexprParser = task<GenerateParserTask>("generateWexprParser") {
    source.set("src/main/kotlin/com/hackerguild/intellijwexpr/Wexpr.bnf")
    targetRoot.set("src/main/gen")
    pathToParser.set("com/hackerguild/intellijwexpr/WexprParser.java")
    pathToPsiRoot.set("com/hackerguild/intellijwexpr/psi")
    purgeOldFiles.set(true)
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"

        dependsOn(
            generateWexprLexer, generateWexprParser
        )
    }

    patchPluginXml {
        sinceBuild.set("213")
        untilBuild.set("223.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
