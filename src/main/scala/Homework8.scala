import scala.concurrent.{Await, Future}
import concurrent.duration.Duration
import concurrent.ExecutionContext.Implicits.global
import scala.collection.parallel.CollectionConverters.*

object Homework8 extends MovieAnswers {
  // How many movies are there? i.e. basic records with a titleType of "movie"
  def howManyMovies: Int = 0

  // What is the record with the most user votes?
  def mostVotes: RatingsRecord = RatingsRecord("", 0.0, 0)

  // What is the record with the most votes AND a perfect rating of 10.0?
  def highestRated: RatingsRecord = RatingsRecord("", 0.0, 0)

  // What is the record of the MOVIE with the most votes AND a perfect rating of 10.0?
  def highestRatedMovie: RatingsRecord = RatingsRecord("", 0.0, 0)

  // How many movies have more votes than Gladiator (2000) which has 1603296?
  def moreThanMaximus: Int = 0

  // How many movies released in 2003 or later are longer than The Return of the King (2003) at 3 hrs 21 mins
  def longerThanLOTR: Int = 0

  // What percentage [0.0,1.0] of records with at least 5000 votes have an average rating below that of The Phantom Menace (1999) at 6.5?
  def theGreatestMenace: Double = 0.0

  // In what year were the most movies released?
  def blockbusterYear: Int = 1776
}

@main def sample(): Unit = {
  // to avoid strange behavior, uncomment only one of these at a time
  timeTrivia(Homework7)
  //timeTrivia(Homework8)
}

def timeTrivia(homework: MovieAnswers): Unit = {
  // pre-access IMDB to force data loading and make first timings more accurate
  val numBasicRecords = IMDB.basicData.size
  val numRatingsRecords = IMDB.ratingsData.size

  val (time1,numMovies) = timeIt(homework.howManyMovies)
  print(s"[${time1} ms] ")
  println(s"A total of ${numMovies} movies are recorded in IMDb")

  val (time2,mostRated) = timeIt(homework.mostVotes)
  print(s"[${time2} ms] ")
  println(s"[The most voted on video is ${getTitle(mostRated.tconst)} with ${mostRated.numVotes} votes averaging ${mostRated.averageRating}")

  val (time3, highestRated) = timeIt(homework.highestRated)
  print(s"[${time3} ms] ")
  println(s"The highest rated video is ${getTitle(highestRated.tconst)} with ${highestRated.numVotes} votes averaging ${highestRated.averageRating}")

  val (time4, highestRatedMovie) = timeIt(homework.highestRatedMovie)
  print(s"[${time4} ms] ")
  println(s"The highest rated movie is ${getTitle(highestRatedMovie.tconst)} with ${highestRatedMovie.numVotes} votes averaging ${highestRatedMovie.averageRating}")

  val (time5, moreThanMaximus) = timeIt(homework.moreThanMaximus)
  print(s"[${time5} ms] ")
  println(s"${moreThanMaximus} movies have more user votes than Gladiator (2000)")

  val (time6, longerThanLOTR) = timeIt(homework.longerThanLOTR)
  print(s"[${time6} ms] ")
  println(s"${longerThanLOTR} movies released in 2003 or later are longer than The Return of the King")

  val (time7, theGreatestMenace) = timeIt(homework.theGreatestMenace)
  print(s"[${time7} ms] ")
  println(s"${(theGreatestMenace*100).round}% of movies with 5K+ votes are rated lower than Star Wars Episode 1")

  val (time8, blockbusterYear) = timeIt(homework.blockbusterYear)
  print(s"[${time8} ms] ")
  println(s"More movies were released in ${blockbusterYear} than in any other year")
}
