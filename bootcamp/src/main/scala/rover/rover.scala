package rover

/**
 * Created by bala on 7/13/15.
 */
object rover {
  var xPos: Int = 0
  var yPos: Int = 0

  var xMax: Int = 0
  var yMax: Int = 0

  var heading: Char = 'N'

  def move(heading: Char): Unit = {
    heading match {
      case 'N' =>
        this.yPos += 1
      case 'S' =>
        this.yPos -= 1
      case 'E' =>
        this.xPos += 1
      case 'W' =>
        this.xPos -= 1

    }
  }

  def rotate(direction: Char, heading: Char)={
    var finalHeading: Char = 'N'
    heading match {
      case 'N' =>
        if(direction == 'L'){
          finalHeading = 'W'
        }else{
          finalHeading = 'E'
        }

      case 'S' =>
        if(direction == 'L'){
          finalHeading = 'E'
        }else{
          finalHeading = 'W'
        }
      case 'E' =>
        if(direction == 'L'){
          finalHeading = 'N'
        }else{
          finalHeading = 'S'
        }
      case 'W' =>
        if(direction == 'L'){
          finalHeading = 'S'
        }else{
          finalHeading = 'N'
        }
    }
    finalHeading
  }

  def startRover(xMax: Int, yMax: Int,xPos: Int, yPos: Int,heading: Char, command: String)= {
    this.xMax = xMax
    this.yMax = yMax
    this.xPos = xPos
    this.yPos = yPos
    this.heading = heading
    for(instruction <- command){
      instruction match {
        case 'M'=>this.move(this.heading)
          //TODO: handle default instead of l/R
        case 'L' => this.heading = this.rotate('L',this.heading)
        case 'R' => this.heading = this.rotate('R',this.heading)
      }
    }

  }

  def getPosition(): Unit ={
    println("xPos = "+ this.xPos);
    println("yPos = "+ this.yPos);
  }

  def getHeading(): Unit ={
    println("heading : "+ this.heading)
  }


}
