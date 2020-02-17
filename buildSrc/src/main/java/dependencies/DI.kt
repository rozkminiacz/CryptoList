package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object DI: DependencyContainer{

  val DaggerVersion = "2.25.2"


  override val kapt = "com.google.dagger:dagger-compiler:${DaggerVersion}"
  private val dagger = "com.google.dagger:dagger:${DaggerVersion}"
  val annotations = "org.glassfish:javax.annotation:10.0-b28"

  val reflectKapt = "com.jakewharton.dagger:dagger-reflect:0.1.0-SNAPSHOT"

  override val all: List<String> = listOf(dagger)
}