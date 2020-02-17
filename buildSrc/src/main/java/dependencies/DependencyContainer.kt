package dependencies

interface DependencyContainer{
  val all: List<String>
  val kapt: String
    get() = ""
}