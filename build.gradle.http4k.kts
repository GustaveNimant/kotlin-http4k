buildscript {
    extra["assertjVersion"] = "3.10.0"
    extra["gradleVersion"]  = "0.20.0"
    extra["jacocoVersion"]  = "0.8.1"
    extra["junitVersion"]   = "4.12"
    extra["kotlinVersion"]  = "1.3.61"
    extra["mockitoVersion"] = "2.12.0"
    extra["moshiVersion"]   = "1.4.0"
    extra["okhttpVersion"]  = "4.4.0"
    extra["okioVersion"]  = "2.4.3"
    extra["http4kVersion"]  = "3.239.0"
    
    repositories {
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "${extra["kotlinVersion"]}"))
	classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlinVersion"]}")
        classpath("com.github.ben-manes:gradle-versions-plugin:${extra["gradleVersion"]}")
    }
}

plugins {
    jacoco
    java
    kotlin("jvm") version "${extra["kotlinVersion"]}"
    application
    "com.github.ben-manes.versions"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

jacoco {
 toolVersion = "${extra["jacocoVersion"]}"
}

group = "minichain"

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${extra["kotlinVersion"]}")
    implementation("com.squareup.moshi:moshi:${extra["moshiVersion"]}")
    implementation("com.squareup.okio:okio:${extra["okioVersion"]}")
    implementation("com.squareup.okhttp3:okhttp:${extra["okhttpVersion"]}")
    implementation("org.http4k:http4k-core:${extra["http4kVersion"]}")
    implementation("org.http4k:http4k-server-jetty:${extra["http4kVersion"]}")
    implementation("org.http4k:http4k-client-apache:${extra["http4kVersion"]}")
    
    testImplementation("junit:junit:${extra["junitVersion"]}")
    testImplementation("org.mockito:mockito-core:${extra["mockitoVersion"]}")
    testImplementation("com.squareup.okhttp3:mockwebserver:${extra["okhttpVersion"]}")
    testImplementation("org.assertj:assertj-core:${extra["assertjVersion"]}")
}

application {
    mainClassName = "io.ipfs.kotlin.MainHttp4kKt"
}
