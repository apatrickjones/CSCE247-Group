/*
 * This is the class that handles the movie event
 * @Adampallante
 *
 */
public class Movie {

  public String showingType, director, rating;
  public int releaseYear;

  /*
  * These methods below are all getting and returning the attributes for the movie event
  * @returns the certain attribute that needs to be returned for the certain event 
  */
  public String getShowingType() {
    return showingType;
  }

  public String getDirector() {
    return director;
  }

  public String getRating() {
    return rating;
  }

  public int getReleaseYear() {
    return releaseYear;
  }

}
