package dependencies

object PlayServices: DependencyContainer {
  override val all = listOf(
      "com.google.android.gms:play-services-location:16.0.0"
  )
}