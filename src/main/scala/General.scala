import scala.annotation.tailrec
import scala.util.Random
import scala.collection.parallel.CollectionConverters.*
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.*
import scala.util.{Failure, Success, Using, Random}

given ExecutionContext = ExecutionContext.global

val twenty = new Array[Int](40)
val thousand = new Array[Int](1000)
val million = new Array[Int](1000000)
val hundredMillion = new Array[Int](10000000)

@main
def general(): Unit = {
//  testMerge()
//  testQuick()
  testRadix()
//  testHeap()
}

def testMerge(): Unit = {
  println("---MERGE SORT TESTING---")
  println("Sequential:")

  print(mergeSort(scramble(twenty).toList).mkString("Proof of Concept: (", ", ", ")") + "\n")

  val (timeMergeSort1000, _) = timeIt(mergeSort(scramble(thousand).toList))
  println(s"Merge Sort (1000 elements): ${timeMergeSort1000} ms")

  //  val (timeMergeSort1000000, _) = timeIt(mergeSort(scramble(million).toList))
  //  println(s"Merge Sort (1000000 elements): ${timeMergeSort1000000} ms")

  //  val (timeMergeSort100000000, _) = timeIt(mergeSort(scramble(hundredMillion).toList))
  //  println(s"Merge Sort (100000000 elements): ${timeMergeSort100000000} ms")

  println("Parallel:")

  print(mergeSortPar(scramble(twenty).toList).mkString("Proof of Concept: (", ", ", ")") + "\n")

  val (timeMergeSortPar1000, _) = timeIt(mergeSortPar(scramble(thousand).toList))
  println(s"Merge Sort (1000 elements): ${timeMergeSortPar1000} ms")

  //  val (timeMergeSortPar1000000, _) = timeIt(mergeSortPar(scramble(million).toList))
  //  println(s"Merge Sort (1000000 elements): ${timeMergeSortPar1000000} ms")

  //  val (timeMergeSortPar100000000, _) = timeIt(mergeSortPar(scramble(hundredMillion).toList))
  //  println(s"Merge Sort (100000000 elements): ${timeMergeSortPar100000000} ms")

  println("Ratio of sequential to parallel is " + timeMergeSort1000 / timeMergeSortPar1000 + " (greater than zero indicates parallelization improved the efficiency)")
}

def testQuick(): Unit = {
  println("---QUICK SORT TESTING---")
  println("Sequential:")

  print(quickSort(scramble(twenty)).mkString("Proof of Concept: (", ", ", ")") + "\n")

  val (timeQuickSort1000, _) = timeIt(quickSort(scramble(thousand)))
  println(s"Quick Sort (1000 elements): ${timeQuickSort1000} ms")

  val (timeQuickSort1000000, _) = timeIt(quickSort(scramble(million)))
  println(s"Quick Sort (1000000 elements): ${timeQuickSort1000000} ms")

  val (timeQuickSort100000000, _) = timeIt(quickSort(scramble(hundredMillion)))
  println(s"Quick Sort (100000000 elements): ${timeQuickSort100000000} ms")

  println("Parallel:")

  print(quickSortPar(scramble(twenty)).mkString("Proof of Concept: (", ", ", ")") + "\n")

  val (timeQuickSortPar1000, _) = timeIt(quickSortPar(scramble(thousand)))
  println(s"Quick Sort (1000 elements): ${timeQuickSortPar1000} ms")

  val (timeQuickSortPar1000000, _) = timeIt(quickSortPar(scramble(million)))
  println(s"Quick Sort (1000000 elements): ${timeQuickSortPar1000000} ms")

  val (timeQuickSortPar100000000, _) = timeIt(quickSortPar(scramble(hundredMillion)))
  println(s"Quick Sort (100000000 elements): ${timeQuickSortPar100000000} ms")

  println("Ratio of sequential to parallel is " + timeQuickSort100000000 / timeQuickSortPar100000000 + " (greater than zero indicates parallelization improved the efficiency)")
}

def testRadix(): Unit = {
  println("---RADIX SORT TESTING---")
  println("Sequential:")

  print(radixSort(scramble(twenty)).mkString("Proof of Concept: (", ", ", ")") + "\n")

  val (timeRadixSort1000, _) = timeIt(radixSort(scramble(thousand)))
  println(s"Radix Sort (1000 elements): ${timeRadixSort1000} ms")

  val (timeRadixSort1000000, _) = timeIt(radixSort(scramble(million)))
  println(s"Radix Sort (1000000 elements): ${timeRadixSort1000000} ms")

  val (timeRadixSort100000000, _) = timeIt(radixSort(scramble(hundredMillion)))
  println(s"Radix Sort (100000000 elements): ${timeRadixSort100000000} ms")

  println("Parallel:")

  print(radixSortPar(scramble(twenty)).mkString("Proof of Concept: (", ", ", ")") + "\n")

  val (timeRadixSortPar1000, _) = timeIt(radixSortPar(scramble(thousand)))
  println(s"Radix Sort (1000 elements): ${timeRadixSortPar1000} ms")

  val (timeRadixSortPar1000000, _) = timeIt(radixSortPar(scramble(million)))
  println(s"Radix Sort (1000000 elements): ${timeRadixSortPar1000000} ms")

    val (timeRadixSortPar100000000, _) = timeIt(radixSortPar(scramble(hundredMillion)))
    println(s"Radix Sort (100000000 elements): ${timeRadixSortPar100000000} ms")

  println("Ratio of sequential to parallel is " + timeRadixSort100000000 / timeRadixSortPar100000000 + " (greater than zero indicates parallelization improved the efficiency)")
}

def testHeap(): Unit = {
  println("---HEAP SORT TESTING---")
  println("Sequential:")

  print(heapSort(scramble(twenty)).mkString("Proof of Concept: (", ", ", ")") + "\n")

  val (timeHeapSort1000, _) = timeIt(heapSort(scramble(thousand)))
  println(s"Heap Sort (1000 elements): ${timeHeapSort1000} ms")

  val (timeHeapSort1000000, _) = timeIt(heapSort(scramble(million)))
  println(s"Heap Sort (1000000 elements): ${timeHeapSort1000000} ms")

  val (timeHeapSort100000000, _) = timeIt(heapSort(scramble(hundredMillion)))
  println(s"Heap Sort (100000000 elements): ${timeHeapSort100000000} ms")

  println("Parallel:")

  print(heapSortPar(scramble(twenty)).mkString("Proof of Concept: (", ", ", ")") + "\n")

  val (timeHeapSortPar1000, _) = timeIt(heapSortPar(scramble(thousand)))
  println(s"Heap Sort (1000 elements): ${timeHeapSortPar1000} ms")

  val (timeHeapSortPar1000000, _) = timeIt(heapSortPar(scramble(million)))
  println(s"Heap Sort (1000000 elements): ${timeHeapSortPar1000000} ms")

  val (timeHeapSortPar100000000, _) = timeIt(heapSortPar(scramble(hundredMillion)))
  println(s"Heap Sort (100000000 elements): ${timeHeapSortPar100000000} ms")

  println("Ratio of sequential to parallel is " + timeHeapSort100000000 / timeHeapSortPar100000000 + " (greater than zero indicates parallelization improved the efficiency)")
}

/**----------------------------------------------------MERGE SORT-----------------------------------------------------*/

def mergeSort(seq: List[Int]): List[Int] = seq match {
  case Nil => Nil
  case xs::Nil => List(xs)
  case _ =>
    val (left, right) = seq splitAt seq.length/2
    merge(mergeSort(left), mergeSort(right))
}

@tailrec
def merge(seq1: List[Int], seq2: List[Int], accumulator: List[Int] = List()):List[Int] = (seq1, seq2) match {
  case (Nil, _) => accumulator ++ seq2
  case (_, Nil) => accumulator ++ seq1
  case (x::xs, y::ys) =>
    if(x<y) merge(xs,seq2, accumulator :+ x)
    else merge(seq1,ys, accumulator :+ y)
}

def mergeSortPar(seq: List[Int]): List[Int] = seq match {
  //TODO: FINISH THIS
  case Nil => Nil
  case xs::Nil => List(xs)
  case _ =>
    val (left, right) = seq splitAt seq.length/2
    merge(mergeSort(left), mergeSort(right))
}


/**----------------------------------------------------QUICK SORT-----------------------------------------------------*/

def quickSort(A: Array[Int]): Array[Int] = {
  //TODO: FINISH THIS

  Array(1, 2, 3, 4, 5)
}

def partition(A: Array[Int]): Array[Int] = {
  //TODO: FINISH THIS

  Array(1, 2, 3, 4, 5)
}

def quickSortPar(A: Array[Int]): Array[Int] = {
  //TODO: FINISH THIS

  Array(1, 2, 3, 4, 5)
}

def partitionPar(A: Array[Int]): Array[Int] = {
  //TODO: FINISH THIS

  Array(1, 2, 3, 4, 5)
}


/**----------------------------------------------------RADIX SORT-----------------------------------------------------*/


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
  //TODO: FINISH THIS

  val max: Int = A.max
  val C = Array.fill(max + 1)(0)
  val B = Array.fill(A.length)(0)

  A.par.foreach(num => {
    synchronized {
      C((num / exp) % 10) += 1
    }
  })

//  for (num <- A) {C((num / exp) % 10) += 1}

  for (i <- 1 until C.length) {C(i) += C(i - 1)}

  A.indices.reverse.foreach { i =>
      B(C((A(i) / exp) % 10) - 1) = A(i)
      C((A(i) / exp) % 10) -= 1
  }

  B
}


/**----------------------------------------------------HEAP SORT------------------------------------------------------*/

def heapSort(A: Array[Int]): Array[Int] = {
  val size = A.length
  buildMaxHeap(A, size - 1)
  for (i <- size - 1 to 1 by -1) {
    swap(A, 0, i)
    maxHeapify(A, 0, i - 1)
  }
  A
}

def buildMaxHeap(A: Array[Int], size: Int): Unit = {
  for (i <- size / 2 to 0 by -1) {
    maxHeapify(A, i, size)
  }
}

@tailrec
def maxHeapify(A: Array[Int], index: Int, size: Int): Unit = {
  val left = 2 * index + 1
  val right = 2 * index + 2
  var max = index

  if (left <= size && A(left) > A(max))
    max = left
  if (right <= size && A(right) > A(max))
    max = right

  if (max != index) {
    swap(A, index, max)
    maxHeapify(A, max, size)
  }
}

def swap(A: Array[Int], i: Int, j: Int): Unit = {
  val temp = A(i)
  A(i) = A(j)
  A(j) = temp
}

def heapSortPar(A: Array[Int]): Array[Int] = {
    val quarter = math.ceil(A.length/4.0).toInt

    val first: Future[Array[Int]] = heapSortParHelper(A.slice(0, quarter))
    val second: Future[Array[Int]] = heapSortParHelper(A.slice(quarter, 2*quarter))
    val third: Future[Array[Int]] = heapSortParHelper(A.slice(2*quarter, 3*quarter))
    val fourth: Future[Array[Int]] = heapSortParHelper(A.slice(3*quarter, A.length))

    val firstSorted = Await.ready(first, 100.seconds).value match
      case None => Array(0)
      case Some(result) => result match
        case Failure(exception) => throw exception
        case Success(value) => value

    val secondSorted = Await.ready(second, 100.seconds).value match
      case None => Array(0)
      case Some(result) => result match
        case Failure(exception) => throw exception
        case Success(value) => value

    val thirdSorted = Await.ready(third, 100.seconds).value match
      case None => Array(0)
      case Some(result) => result match
        case Failure(exception) => throw exception
        case Success(value) => value

    val fourthSorted = Await.ready(fourth, 100.seconds).value match
      case None => Array(0)
      case Some(result) => result match
        case Failure(exception) => throw exception
        case Success(value) => value

  val first2 = merge2Arrays(firstSorted, secondSorted)
  val second2 = merge2Arrays(thirdSorted, fourthSorted)
  merge2Arrays(first2, second2)
}

def heapSortParHelper(A: Array[Int]): Future[Array[Int]] = {
  val size = A.length
  buildMaxHeapPar(A, size - 1)
  for (i <- size - 1 to 1 by -1) {
    swap(A, 0, i)
    maxHeapify(A, 0, i - 1)
  }
  Future(A)
}

def buildMaxHeapPar(A: Array[Int], size: Int): Unit = {
  (size / 2 to 0 by -1).par.foreach(i => maxHeapify(A, i, size))
}


/**------------------------------------------------HELPER FUNCTION----------------------------------------------------*/

def scramble(A: Array[Int]): Array[Int] = {
  val rand = new Random()

  for (i <- A.indices) yield A(i) = rand.nextInt(1000)
  A
}

def merge2Arrays(arr1: Array[Int], arr2: Array[Int]): Array[Int] = {
  var j = 0
  var k = 0
  var i = 0
  val merged = new Array[Int](arr1.length + arr2.length)

  while (j < arr1.length && k < arr2.length) {
    if (arr1(j) < arr2(k)) {
      merged(i) = arr1(j)
      j += 1
    } else {
      merged(i) = arr2(k)
      k += 1
    }
    i += 1
  }

  while (j < arr1.length) {
    merged(i) = arr1(j)
    j += 1
    i += 1
  }

  while (k < arr2.length) {
    merged(i) = arr2(k)
    k += 1
    i += 1
  }

  merged
}

def timeIt[A](f: => A): (Double, A) = {
  val startTime = System.currentTimeMillis()
  val result = f
  val endTime = System.currentTimeMillis()
  (endTime-startTime, result)
}