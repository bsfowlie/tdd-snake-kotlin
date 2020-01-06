object Deps {
    data class Versions(val kotlin: String = "1.3.61")

    private val versions = Versions()

    val kotlin_stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${versions.kotlin}"
}

object TestingDeps {
    data class Versions(
        val assertj:String = "3.14.0",
        val junit5:String = "5.5.2",
        val junit4:String = "4.12",
        val mockk:String = "1.8.9"
    )

    private val versions = Versions()

    val assertj = "org.assertj:assertj-core:${versions.assertj}"

    val junit_vintage = "junit:junit:${versions.junit4}"
    val junit_jupiter = "org.junit.jupiter:junit-jupiter-api:${versions.junit5}"
    val junit_jupiter_params = "org.junit.jupiter:junit-jupiter-params:${versions.junit5}"

    val junit_vintage_runtime = "org.junit.vintage:junit-vintage-engine:${versions.junit5}"
    val junit_jupiter_runtime = "org.junit.jupiter:junit-jupiter-engine:${versions.junit5}"

    val mockk = "io.mockk:mockk:${versions.mockk}"
}
