package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object Rx : DependencyContainer {

  val RxAndroidVersion = "2.0.1"
  val RxJavaVersion = "2.1.3"
  val RxKotlinVersion = "2.1.0"

  val jvm: List<String> = listOf(
      "io.reactivex.rxjava2:rxjava:${RxJavaVersion}",
      "io.reactivex.rxjava2:rxkotlin:${RxKotlinVersion}"
  )

  override val all: List<String> = jvm.plus(
      "io.reactivex.rxjava2:rxandroid:${RxAndroidVersion}"
  )

}