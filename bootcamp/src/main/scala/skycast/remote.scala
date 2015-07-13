package skycast

/**
 * Created by bala on 7/13/15.
 */
object remote {
  var currentChannel: Int = 0
  var prevChannel: Int = 0
  var blockedChannels: List[Int] = List(0)
  var minLimit: Int = 0
  var maxLimit: Int  = 0

  def numberButton(channel: Int): Unit={
    this.prevChannel = this.currentChannel
    this.currentChannel = channel
  }

  def backButton(): Unit = {
    val temp = this.currentChannel
    this.currentChannel = this.prevChannel
    this.prevChannel = temp
  }

  def upButton(): Unit = {
    if(!this.isBlocked(this.currentChannel+1)) {
      this.prevChannel = this.currentChannel
      this.currentChannel = if(this.currentChannel+1>this.maxLimit){
        this.minLimit
      }else{
        this.currentChannel+1
      }
    }else{
      this.upButton()
    }
  }

  def downButton(): Unit = {
    if(!this.isBlocked(this.currentChannel-1)){
      this.prevChannel = this.currentChannel
      this.currentChannel = if(this.currentChannel-1<this.minLimit){
        this.maxLimit
      }else{
        this.currentChannel-1
      }
    }else{
      //TODO: recursion explore possibilities of failure
      this.currentChannel -= 1;
      this.downButton()
    }


  }

  def getCurrentChannel(): Int = {
    this.currentChannel
  }

  def getPrevChannel(): Int = {
    this.prevChannel
  }

  def isBlocked(channel: Int): Boolean = {
    (blockedChannels.indexOf(channel)>=0)
  }

  def init(minLimit: Int, maxLimit: Int, blockedChannelList: List[Int])={
    this.blockedChannels = blockedChannelList
    this.maxLimit = maxLimit
    this.minLimit = minLimit
    this.currentChannel = this.minLimit
  }
}
