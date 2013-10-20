object CenteredDiamond extends (Int => String) {
  def apply(size: Int): String = {
    if (size % 2 == 0) {
      throw new IllegalArgumentException("Size must be odd.")
    }
    apply(size, 1)
  }

  private def apply(size: Int, current: Int): String = {
    val paddingSize = (size - current) / 2
    val padding = " " * paddingSize
    val stars = "*" * current
    val currentLine = padding + stars + "\n"
    var result = ""
    result += currentLine
    if (current < size) {
      result += apply(size, current + 2)
      result += currentLine
    }
    result
  }
}