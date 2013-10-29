object centeredDiamond extends (Int => String) {

  def apply(size: Int): String = {

    def go(current: Int): String = {
      val paddingSize = (size - current) / 2
      val padding = " " * paddingSize
      val stars = "*" * current
      val currentLine = padding + stars + "\n"
      if (current < size) {
        currentLine + go(current + 2) + currentLine
      } else {
        currentLine
      }
    }

    require(size % 2 != 0, "Size must be odd.")
    go(1)
  }
}