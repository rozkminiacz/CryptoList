package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object Images: DependencyContainer{

  val glideVersion = "4.1.1"
  val PhotoviewVersion = "2.1.3"

  val glide  = "com.github.bumptech.glide:glide:${glideVersion}"
  val glideKapt     = "com.github.bumptech.glide:compiler:${glideVersion}"
  val photoView = "com.github.chrisbanes:PhotoView:${PhotoviewVersion}"

  override val all: List<String> = listOf(glide, photoView)
  override val kapt: String = glideKapt
}