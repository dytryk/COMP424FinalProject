import scala.util.Random

@main
def general() = {
  val ten = new Array[Int](10)
  val thousand = new Array[Int](1000)
  val million = new Array[Int](1000000)
  val billion = new Array[Int](1000000000)
  val rand = new Random()
  
  for (i <- 0 until 10) {
    ten(i) = rand.nextInt(101)
  }
  for (i <- 0 until 1000) {
    thousand(i) = rand.nextInt(101) 
  }
  for (i <- 0 until 1000000) {
    million(i) = rand.nextInt(101)
  }
  for (i <- 0 until 1000000000) {
    billion(i) = rand.nextInt(101)
  }
  
  merge(ten)
  merge(thousand)
  merge(million)
  merge(billion)

}

def merge(A: Array[Int]): List[Int] = {

}