package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-27
 */
object Social : DependencyContainer {
  override val all: List<String> = listOf("com.facebook.android:facebook-login:[4,5)")
}