plugins {
    id("java")
}

group = "ru.mamedov.graph"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    compileOnly("org.projectlombok:lombok:1.18.32")
}
