import scala.util.Random
import scala.collection.parallel.CollectionConverters.*

@main
def general() = {
  val ten = new Array[Int](10)
  val thousand = new Array[Int](1000)
  val million = new Array[Int](1000000)
  val hundredMillion = new Array[Int](100000000)
  val rand = new Random()

  for (i <- 0 until 10) {
    ten(i) = rand.nextInt(1000)
  }
  for (i <- 0 until 1000) {
    thousand(i) = rand.nextInt(1000)
  }
  for (i <- 0 until 1000000) {
    million(i) = rand.nextInt(1000)
  }
  for (i <- 0 until 100000000) {
    hundredMillion(i) = rand.nextInt(1000)
  }

  val (timeMergeSort10, _) = timeIt(mergeSort(ten))
  println(s"Merge Sort (10 elements): ${timeMergeSort10} ms")

  val (timeMergeSort1000, _) = timeIt(mergeSort(thousand))
  println(s"Merge Sort (1000 elements): ${timeMergeSort1000} ms")

  val (timeMergeSort1000000, _) = timeIt(mergeSort(million))
  println(s"Merge Sort (1000000 elements): ${timeMergeSort1000000} ms")

  val (timeQuickSort10, _) = timeIt(quickSort(ten))
  println(s"Quick Sort (10 elements): ${timeQuickSort10} ms")

  val (timeQuickSort1000, _) = timeIt(quickSort(thousand))
  println(s"Quick Sort (1000 elements): ${timeQuickSort1000} ms")

  val (timeQuickSort1000000, _) = timeIt(quickSort(million))
  println(s"Quick Sort (1000000 elements): ${timeQuickSort1000000} ms")

  val (timeRadixSort10, _) = timeIt(radixSort(ten))
  println(s"Radix Sort (10 elements): ${timeRadixSort10} ms")

  print(ten.mkString("Array(", ", ", ")") + "\n")
  print(radixSort(ten).mkString("Array(", ", ", ")") + "\n")

  val (timeRadixSort1000, _) = timeIt(radixSort(thousand))
  println(s"Radix Sort (1000 elements): ${timeRadixSort1000} ms")

//  print(thousand.mkString("Array(", ", ", ")") + "\n")
//  print(radixSort(thousand).mkString("Array(", ", ", ")") + "\n")

  val (timeRadixSort1000000, _) = timeIt(radixSort(million))
  println(s"Radix Sort (1000000 elements): ${timeRadixSort1000000} ms")

  val (timeRadixSort100000000, _) = timeIt(radixSort(hundredMillion))
  println(s"Radix Sort (100000000 elements): ${timeRadixSort100000000} ms")

  val (timeRadixSortPar10, _) = timeIt(radixSortPar(ten))
  println(s"Parallel Radix Sort (10 elements): ${timeRadixSortPar10} ms")

  print(ten.mkString("Parallel Array(", ", ", ")") + "\n")
  print(radixSort(ten).mkString("Parallel Array(", ", ", ")") + "\n")

  val (timeRadixSortPar1000, _) = timeIt(radixSortPar(thousand))
  println(s"Parallel Radix Sort (1000 elements): ${timeRadixSortPar1000} ms")

  //  print(thousand.mkString("Parallel Array(", ", ", ")") + "\n")
  //  print(radixSort(thousand).mkString("Parallel Array(", ", ", ")") + "\n")

  val (timeRadixSortPar1000000, _) = timeIt(radixSortPar(million))
  println(s"Parallel Radix Sort (1000000 elements): ${timeRadixSortPar1000000} ms")

  val (timeRadixSortPar100000000, _) = timeIt(radixSortPar(hundredMillion))
  println(s"Parallel Radix Sort (100000000 elements): ${timeRadixSortPar100000000} ms")

  val (timeHeapSort10, _) = timeIt(heapSort(ten))
  println(s"Heap Sort (10 elements): ${timeHeapSort10} ms")

  val (timeHeapSort1000, _) = timeIt(heapSort(thousand))
  println(s"Heap Sort (1000 elements): ${timeHeapSort1000} ms")

  val (timeHeapSort1000000, _) = timeIt(heapSort(million))
  println(s"Heap Sort (1000000 elements): ${timeHeapSort1000000} ms")
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
  val max = A.max
  var exp = 1
  var B = A

  while (max / exp > 0) {
    B = countingSort(B, exp)
    exp *= 10
  }

  B
}

def countingSort(A: Array[Int], exp: Int): Array[Int] = {
  val max: Int = A.max
  val C = Array.fill(max + 1)(0)
  val B = Array.fill(A.length)(0)

  for (num <- A) {C((num / exp) % 10) += 1}

  for (i <- 1 until C.length) {C(i) += C(i - 1)}

  for (i <- A.indices.reverse) {
    B(C((A(i) / exp) % 10) - 1) = A(i)
    C((A(i) / exp) % 10) -= 1
  }

  B
}

def radixSortPar(A: Array[Int]): Array[Int] = {
  val max = A.max
  var exp = 1
  var B = A

  while (max / exp > 0) {
    B = countingSortPar(B, exp)
    exp *= 10
  }

  B
}

def countingSortPar(A: Array[Int], exp: Int): Array[Int] = {
  val max: Int = A.max
  val C = Array.fill(max + 1)(0)
  val B = Array.fill(A.length)(0)

//  for (num <- A) {C((num / exp) % 10) += 1}

//  A.par.foreach(num => {
//    C((num / exp) % 10) += 1
//  })

  A.par.foreach(num => {
    synchronized {
      C((num / exp) % 10) += 1
    }
  })

  for (i <- 1 until C.length) {C(i) += C(i - 1)}

//  for (i <- A.indices.reverse) {
//    B(C((A(i) / exp) % 10) - 1) = A(i)
//    C((A(i) / exp) % 10) -= 1
//  }

  A.indices.reverse.foreach { i =>
    synchronized {
      B(C((A(i) / exp) % 10) - 1) = A(i)
      C((A(i) / exp) % 10) -= 1
    }
  }

  B
}

def heapSort(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}

def maxHeapify(A: Array[Int]): Array[Int] = {
  Array(1, 2, 3, 4, 5)
}

def timeIt[A](f: => A): (Double, A) = {
  val startTime = System.currentTimeMillis()
  val result = f
  val endTime = System.currentTimeMillis()
  (endTime-startTime, result)
}