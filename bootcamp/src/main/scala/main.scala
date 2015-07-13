import rover.rover
import skycast.{remote, gaurav}

/**
 * Created by bala on 7/13/15.
 */
object main {
  def main(args: Array[String]): Unit = {
    println("test")
    //gaurav.init(1,20,List(2,18,19), Array(15,14,17,1,17));
    //println("Button count = " + gaurav.getButtonCount())

    rover.startRover(3,3,3,3,'E',"MMRMMRMRRM");
    rover.getPosition()
    rover.getHeading()

  }
}
