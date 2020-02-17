package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object ArchitectureComponents : DependencyContainer {


    val design = "com.google.android.material:material:1.0.0"
    val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
    val appCompat = "androidx.appcompat:appcompat:1.1.0"
    val constraint = "androidx.constraintlayout:constraintlayout:1.1.3"
    val browser = "androidx.browser:browser:1.0.0"

    override val all: List<String> = listOf(
        design,
        recyclerView,
        appCompat,
        constraint,
        browser
    )

    override val kapt: String = "com.android.databinding:compiler:3.5.0"

}
