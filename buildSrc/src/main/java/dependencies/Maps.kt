package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object Maps : DependencyContainer {

    val googleMaps = "com.google.android.gms:play-services-maps:16.0.0"

    override val all: List<String> = listOf(
        googleMaps
    )
}