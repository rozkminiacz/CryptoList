package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object InstrumentationTest : DependencyContainer {
  private const val espressoVersion = "3.1.1"
  private const val annotationsSupportVersion = "26.1.0"

  val espressoDependencies = listOf(
      "androidx.test:core:1.0.0",
      "androidx.test:runner:1.1.0",
      "androidx.test:rules:1.1.0",
      // Espresso dependencies
      "androidx.test.espresso:espresso-core:3.1.0",
      "androidx.test.espresso:espresso-contrib:3.1.0",
      "androidx.test.espresso:espresso-intents:3.1.0",
      "androidx.test.espresso:espresso-accessibility:3.1.0",
      "androidx.test.espresso:espresso-web:3.1.0",
      "androidx.test.espresso.idling:idling-concurrent:3.1.0",
      "androidx.test.ext:junit:1.0.0",
      "androidx.test.ext:truth:1.0.0",
      "com.google.truth:truth:0.42"
  )

  // AndroidJUnitRunner and JUnit Rules

  val orchestrator = "androidx.test:orchestrator:1.1.1"

  val uiAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"

  override val all: List<String> = espressoDependencies + uiAutomator

}