package test

object Loader {
  
  def load(clazz: String) {
    Class.forName(clazz)
  }
}
