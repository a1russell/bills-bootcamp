object CenteredDiamond extends (Int => String) {
  def apply(size: Int): String = {
    if (size % 2 == 0) {
      throw new IllegalArgumentException("Size must be odd.")
    }
    "*"
  }
}