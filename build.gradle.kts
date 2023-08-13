import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.14"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "im.getmansky"
version = project.findProperty("projVersion") ?: "0.0.1-SNAPSHOT"

val testContainersVersion = "1.18.3"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// STARTERS
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// COMMON
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("jakarta.validation:jakarta.validation-api")
	implementation("me.paulschwarz:spring-dotenv:4.0.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")

	// DATABASE
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

	// TESTS
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
	testImplementation("com.h2database:h2")

	testImplementation("io.mockk:mockk:1.13.4")
	testImplementation("com.ninja-squad:springmockk:4.0.2")

	testImplementation("org.testcontainers:junit-jupiter:$testContainersVersion")
	testImplementation("org.testcontainers:testcontainers:$testContainersVersion")
	testImplementation("org.testcontainers:mariadb:$testContainersVersion")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
