import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2" // Добавляем плагин shadowJar
}

group = "org.deeplearning4j"
version = "1.0.0-SNAPSHOT"
application {
    mainClass.set("com.edu.ApplicationKt")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("xml-apis:xml-apis:1.0.b2")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-cio:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")

    implementation("org.postgresql:postgresql:42.5.4")
    implementation("com.zaxxer:HikariCP:5.0.1")

    //OpenAI
    implementation("com.aallam.openai:openai-client:3.2.5")
    //define dependencies without versions
    implementation ("com.aallam.openai:openai-client")
    runtimeOnly("io.ktor:ktor-client-okhttp")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-websockets-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-conditional-headers-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-cio-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    //AI
    //processing the text
    implementation("edu.stanford.nlp:stanford-corenlp:4.5.4")
    //Deeplearning4j
    implementation("org.deeplearning4j:deeplearning4j-core:0.9.1")
    implementation("org.deeplearning4j:deeplearning4j-nlp:0.9.1")
    implementation("org.deeplearning4j:deeplearning4j-nlp-parent:0.9.1")
    implementation("org.nd4j:nd4j-native-platform:1.0.0-beta6")

    //TODO: подсказка как исправлять импорты библиотек
// обычно градл импортирует криво - поэтому ищем подобную библиотеку в проекте mave
// и импортируем точно такую же бибилотеку как пример в Deeplearning4j
// то есть после названия библиотеки папку, если какие-то папки нам не доступны
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

// Добавляем настройку для shadowJar
tasks.getByName<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    manifest.attributes["Main-Class"] = "com.edu.ApplicationKt"
    // Другие настройки shadowJar, если необходимо
}
tasks.withType<Zip> {
    // Включаем расширение zip64
    isZip64 = true
}