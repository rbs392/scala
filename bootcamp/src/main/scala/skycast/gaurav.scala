package skycast

/**
 * Created by bala on 7/13/15.
 */
object gaurav {
  var maxChannelLimit: Int = 0;
  var minChannelLimit: Int = 0;

  //TODO: find a workaround for List(0)

  var blockedChannelList: List[Int] = List(0)
  var navigateChannelList: Array[Int] = Array(0)


  var buttonCount: Int = 0

  def navAlgorithm(channel: Int)={
    val prevChannel = remote.getPrevChannel()
    val currentChannel =remote.getCurrentChannel()

    def isNavThroughUpDownButtons(channel: Int,currentChannel: Int): Boolean = {
      if(channel == currentChannel+1 || channel == currentChannel-1){
        return true
      }else{
        //TODO: refactor for better logic

        val prevBlocked = remote.isBlocked(currentChannel-1)
        val nextBlocked = remote.isBlocked(currentChannel+1)
        if(prevBlocked || nextBlocked){
          if(prevBlocked){
            isNavThroughUpDownButtons(channel,currentChannel-1)
          }else{
            isNavThroughUpDownButtons(channel,currentChannel+1)
          }
        }else{
          return false
        }
      }

    }


    if(channel == prevChannel || isNavThroughUpDownButtons(channel,currentChannel)){
      if(channel == prevChannel){
        remote.backButton()
        this.updateCount()
      }else if(channel == currentChannel+1 ){
        remote.upButton()
        this.updateCount()
      }else if(channel == currentChannel-1){
        remote.downButton()
        this.updateCount()
      }
    }else{
      var i = channel
      remote.numberButton(channel)
      while(i>0){
        this.updateCount()
        i = i/10
      }
    }



  }

  def updateCount(): Unit = {
    this.buttonCount += 1
  }

  def surfChannels(navChannelList: Array[Int]): Unit = {

    //TODO: explore possibilities of divide and conquer/ actor

    navChannelList.foreach{
        channel: Int=>{
          println(channel)
          navAlgorithm(channel)
        }
    }
  }


  def init(minChannelLt: Int, maxChannelLt: Int, blockedChannelList: List[Int], channelList: Array[Int]): Unit = {
    this.maxChannelLimit = maxChannelLt
    this.minChannelLimit = minChannelLt

    this.blockedChannelList = blockedChannelList
    this.navigateChannelList = channelList

    remote.init(this.minChannelLimit,this.maxChannelLimit,this.blockedChannelList)
    this.surfChannels(this.navigateChannelList)
  }

  def getButtonCount(): Int ={
    this.buttonCount
  }

}

