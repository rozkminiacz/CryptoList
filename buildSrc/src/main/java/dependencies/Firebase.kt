package dependencies

object Firebase : DependencyContainer {
  override val all = listOf(
      "com.google.firebase:firebase-messaging:17.3.4",
      "com.google.firebase:firebase-core:16.0.6"
  )
}