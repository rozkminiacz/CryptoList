package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object Utils: DependencyContainer{

  val time = "joda-time:joda-time:2.9.9"
  val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.9.3"
  val timber = "com.jakewharton.timber:timber:4.7.0"
  val permissions = "com.github.florent37:runtime-permission:1.1.0"
  val qrCodeView = "com.dlazaro66.qrcodereaderview:qrcodereaderview:2.0.3"
  val lottieAnimations = "com.airbnb.android:lottie:2.8.0"

  override val all: List<String> = listOf(
      time, crashlytics, timber, permissions, qrCodeView, lottieAnimations
  )
}