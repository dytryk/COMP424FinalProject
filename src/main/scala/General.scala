import scala.util.Random

@main
def general() = {
  val ten = new Array[Int](10)
  val thousand = new Array[Int](1000)
  val million = new Array[Int](1000000)
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

  mergeSort(ten)
  mergeSort(thousand)
  mergeSort(million)

  quickSort(ten)
  quickSort(thousand)
  quickSort(million)

  radixSort(ten)
  radixSort(thousand)
  radixSort(million)

  heapSort(ten)
  heapSort(thousand)
  heapSort(million)
}

def mergeSort(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}

def merge(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}

def quickSort(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}

def partition(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}

def radixSort(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}

def countingSort(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}

def heapSort(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}

def maxHeapify(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}