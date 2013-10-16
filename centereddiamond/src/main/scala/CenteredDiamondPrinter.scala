object CenteredDiamondPrinter extends App {
  try {
    Console.println(CenteredDiamond(args(0).toInt))
  } catch {
    case e: ArrayIndexOutOfBoundsException => Console.println("Please provide a size for the centered diamond.")
    case e: NumberFormatException => Console.println("Provided size must be numeric.")
  }
}
