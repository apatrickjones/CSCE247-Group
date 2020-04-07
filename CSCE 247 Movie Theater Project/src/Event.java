/*
 * This class is used to return the properties of a certain event
 * @author Adampallante
 *
 */
import java.util.Scanner;


public class Event {
  public static final int COMMENT_LIMIT = 100;


  public String theater, title, genre, description, showing;
  public double price, rating;
  boolean[] seating = new boolean[50];
  String[] comments = new String[COMMENT_LIMIT];

  /*
  * All of the below methods are all getting the properties of the event
  * @returns the certain variable the user is wanting to view
  */
  public String getTheater() {
    return theater;
  }

  public String getTitle() {
    return title;
  }

  public String getGenre() {
    return genre;
  }

  public String getDescription() {
    return description;
  }

  public boolean getSeating() {
    return seating;
  }

  public String getShowing() {
    return showing;
  }

  public double getRating() {
    return rating;
  }

  public double getPrice() {
    return price;
  }

  public String[] getComments() {
    return comments;
  }
}
