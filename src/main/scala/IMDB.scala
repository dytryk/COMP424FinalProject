import java.io.FileInputStream
import java.util.zip.GZIPInputStream
import scala.collection.mutable
import scala.io.BufferedSource
import scala.io.Source.fromInputStream
import scala.util.{Failure, Success, Using}

object IMDB {
  val basicData: List[BasicRecord] = {
    Using(readGZFile("src/main/resources/title.basics.tsv.gz"))(parseTSVList(BasicRecord.parseItem)) match
      case Failure(exception) => throw exception
      case Success(value) => value
  }

  val ratingsData: List[RatingsRecord] = {
    Using(readGZFile("src/main/resources/title.ratings.tsv.gz"))(parseTSVList(RatingsRecord.parseItem)) match
      case Failure(exception) => throw exception
      case Success(value) => value
  }

  private def parseTSVList[A](f: String => A)(fin: BufferedSource): List[A] = fin.getLines().toList.tail.map(f)

  private def parseTSVMap[K <: Comparable[K],V](f: String => (K,V))(fin: BufferedSource): Map[K,V] = {
    val out = mutable.Map[K, V]()
    val it = fin.getLines()
    // return an empty map on an empty file otherwise burn up the header line
    if it.isEmpty then return Map[K,V]() else it.next()
    // loop through all the lines adding the pairs to the output map
    while it.hasNext do {
      val (key, value) = f(it.next())
      out(key) = value
    }
    out.toMap
  }

  private def readGZFile(path: String): BufferedSource = fromInputStream(new GZIPInputStream(new FileInputStream(path)))
}

case class BasicRecord(tconst: String, titleType: String, primaryTitle: String, startYear: Option[Int], endYear: Option[Int],
                       runtimeMinutes: Option[Int], genres: List[String]) extends Record

object BasicRecord {
  def parseItem(s: String): BasicRecord = {
    val entries = s.trim.split("\\t")
    if entries.length != 7 then throw new IllegalArgumentException(s"Could not parse $s as a BasicRecord")
    new BasicRecord(
      entries(0), // tconst
      entries(1), // titleType
      entries(2), // primaryTitle
      if entries(3) != "\\N" then Some(entries(3).toInt) else None,
      if entries(4) != "\\N" then Some(entries(4).toInt) else None,
      if entries(5) != "\\N" then Some(entries(5).toInt) else None,
      entries(6).split(",").toList
    )
  }
  def parsePair(s: String): (String, BasicRecord) = {
    val item = parseItem(s)
    (item.tconst, item)
  }
}

case class RatingsRecord(tconst: String, averageRating: Double, numVotes: Int)

object RatingsRecord {
  def parseItem(s: String): RatingsRecord = {
    val entries = s.trim.split("\\t")
    if entries.length != 3 then throw new IllegalArgumentException(s"Could not parse $s as a RatingsRecord")
    new RatingsRecord(
      entries(0), // tconst
      entries(1).toDouble, // averageRating
      entries(2).toInt // numVotes
    )
  }
  def parsePair(s: String): (String, RatingsRecord) = {
    val item = parseItem(s)
    (item.tconst, item)
  }
}

case class EpisodeRecord(tconst: String, parentTconst: String, seasonNumber: Option[Int], episodeNumber: Option[Int])

object EpisodeRecord {
  def parseItem(s: String): EpisodeRecord = {
    val entries = s.trim.split("\\t")
    if entries.length != 4 then throw new IllegalArgumentException(s"Could not parse $s as an EpisodeRecord")
    new EpisodeRecord(
      entries(0), // tconst
      entries(1), // parentTconst
      if entries(2) != "\\N" then Some(entries(2).toInt) else None, // seasonNumber
      if entries(3) != "\\N" then Some(entries(3).toInt) else None // episodeNumber
    )
  }
  def parsePair(s: String): (String, EpisodeRecord) = {
    val item = parseItem(s)
    (item.tconst, item)
  }
}