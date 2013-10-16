import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CenteredDiamondSpec extends Specification {

  "A centered diamond of size 1" should {
    "consist of 1 star" in {
      CenteredDiamond(1) must be_==("*")
    }
  }
}