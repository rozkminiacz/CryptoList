package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object UnitTests : DependencyContainer {

    val mockitoCoreVersion = "2.10.0"
    val kotlinMockitoVersion = "2.2.0"

    val junit = "junit:junit:4.12"

    val junit5 = "org.junit.platform:junit-platform-runner:1.0.0"

    val kotlinTest = "io.kotlintest:kotlintest-runner-junit5:3.3.2"

    val mockk = "io.mockk:mockk:1.9"

    val assertions = listOf(
        "io.strikt:strikt-core:0.16.0",
        "org.mockito:mockito-core:${mockitoCoreVersion}",
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${kotlinMockitoVersion}",
        mockk
    )

    val coreLibraries = listOf(junit, junit5, kotlinTest)

    override val all: List<String> = coreLibraries + assertions
}