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
  
  mergeSort(ten)
  mergeSort(thousand)
  mergeSort(million)
  mergeSort(billion)
  
  quickSort(ten)
  quickSort(thousand)
  quickSort(million)
  quickSort(billion)
  
  radixSort(ten)
  radixSort(thousand)
  radixSort(million)
  radixSort(billion)

  heapSort(ten)
  heapSort(thousand)
  heapSort(million)
  heapSort(billion)
}

def mergeSort(A: Array[Int]): List[Int] = {

}

def merge(A: Array[Int]): List[Int] = {

}

def quickSort(A: Array[Int]): List[Int] = {

}

def partition(A: Array[Int]): List[Int] = {

}

def radixSort(A: Array[Int]): List[Int] = {

}

def countingSort(A: Array[Int]): List[Int] = {

}

def heapSort(A: Array[Int]): List[Int] = {

}

def maxHeapify(A: Array[Int]): List[Int] = {

}