abstract class MovieAnswers {
  def howManyMovies: Int
  def mostVotes: RatingsRecord
  def highestRated: RatingsRecord
  def highestRatedMovie: RatingsRecord
  def moreThanMaximus: Int
  def longerThanLOTR: Int
  def theGreatestMenace: Double
  def blockbusterYear: Int
}

object Homework7  extends MovieAnswers {
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

/**
 * Looks up the title of a film based on the given tconst ID
 * @param tconst IMDb's unique ID assigned to the film
 * @return The title of the film if found, otherwise "UNKNOWN"
 */
def getTitle(tconst: String): String = IMDB.basicData.find(_.tconst==tconst) match
  case Some(value) => value.primaryTitle
  case None => "UNKNOWN"

/**
 * Returns both the answer and the clock time required to compute it for any function
 *
 * @param f the expression to be executed
 * @tparam A the type to which f evaluates
 * @return the time to compute and value of f
 */
def timeIt[A](f: => A): (Double, A) = {
  val startTime = System.currentTimeMillis()
  val result = f
  val endTime = System.currentTimeMillis()
  (endTime-startTime, result)
}