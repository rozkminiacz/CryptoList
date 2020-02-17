package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object UnitTests : DependencyContainer {

  val mockitoCoreVersion = "2.10.0"
  val kotlinMockitoVersion = "1.5.0"

  val junit = "junit:junit:4.12"

  val junit5 = "org.junit.platform:junit-platform-runner:1.0.0"

  val mockk = "io.mockk:mockk:1.9"

  val assertions = listOf(
      "io.strikt:strikt-core:0.16.0",
      "org.mockito:mockito-core:${mockitoCoreVersion}",
      "com.nhaarman:mockito-kotlin:${kotlinMockitoVersion}",
      mockk
  )

  val spekBdd = "com.github.rozkminiacz:SpekBddDsl:1.0"

  val spek = listOf(
      "org.jetbrains.spek:spek-api:1.1.5",
      "org.jetbrains.spek:spek-junit-platform-engine:1.1.5",
      spekBdd
  )

  val engine = spek + listOf(junit, junit5)

  override val all: List<String> = engine + assertions
}