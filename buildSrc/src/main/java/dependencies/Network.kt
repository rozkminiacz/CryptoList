package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object Network : DependencyContainer {

  val RetrofitVersion = "2.3.0"
  val OkHttpVersion = "3.9.0"

  val retrofit = listOf<String>(
      "com.squareup.retrofit2:retrofit:${RetrofitVersion}",
      "com.squareup.retrofit2:converter-gson:${RetrofitVersion}",
      "com.squareup.retrofit2:adapter-rxjava2:${RetrofitVersion}"
  )

  val okhttp = listOf(
      "com.squareup.okhttp3:okhttp:${OkHttpVersion}",
      "com.squareup.okhttp3:logging-interceptor:${OkHttpVersion}"
  )

  override val all: List<String> = retrofit + okhttp
}