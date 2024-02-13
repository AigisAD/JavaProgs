
package threadproject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;



public class StillClock	 extends Pane{
  private int hour;
  private int minute;
  private int second;

  /** Construct a default clock with the current time*/
  public StillClock() {
    setCurrentTime();
  }

  /** Construct a clock with specified hour, minute, and second */
  public StillClock(int hour, int minute, int second) {
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  /** Return hour */
  public int getHour() {
    return hour;
  }

  /** Set a new hour */
  public void setHour(int hour) {
    this.hour = hour;
    repaint();
  }

  /** Return minute */
  public int getMinute() {
    return minute;
  }

  /** Set a new minute */
  public void setMinute(int minute) {
    this.minute = minute;
    repaint();
  }

  /** Return second */
  public int getSecond() {
    return second;
  }

  /** Set a new second */
  public void setSecond(int second) {
    this.second = second;
    repaint();
  }

private void repaint() {
    

    // Initialize clock parameters
    int clockRadius =
      (int)(Math.min(getWidth(), getHeight()) * 0.8 * 0.5);
    int xCenter = (int) (getWidth() / 2);
    int yCenter = (int) (getHeight() / 2);

    // Draw circle
    Circle circle = new Circle(xCenter, yCenter, clockRadius);
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    String timeString = getHour() + ":" +getMinute() 
      + ":" + getSecond();
    
    Text t1 = new Text(xCenter - 5, yCenter - clockRadius + 12, "12");
    Text t2 = new Text(xCenter - clockRadius + 3, yCenter + 5, "9");
    Text t3 = new Text(xCenter + clockRadius - 10, yCenter + 3, "3");
    Text t4 = new Text(xCenter - 3, yCenter + clockRadius - 3, "6");
    Text t5 = new Text(xCenter-15,yCenter+clockRadius*.5,timeString);
    
    // Draw second hand
    double sLength = clockRadius * 0.8;
    double secondX = xCenter + sLength * 
      Math.sin(second * (2 * Math.PI / 60));
    double secondY = yCenter - sLength * 
      Math.cos(second * (2 * Math.PI / 60));
    Line sLine = new Line(xCenter, yCenter, secondX, secondY);
    sLine.setStroke(Color.RED);

    // Draw minute hand
    double mLength = clockRadius * 0.65;
    double xMinute = xCenter + mLength * 
      Math.sin(minute * (2 * Math.PI / 60));
    double minuteY = yCenter - mLength * 
      Math.cos(minute * (2 * Math.PI / 60));
    Line mLine = new Line(xCenter, yCenter, xMinute, minuteY);
    mLine.setStroke(Color.BLUE);
    
    // Draw hour hand
    double hLength = clockRadius * 0.5;
    double hourX = xCenter + hLength * 
      Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    double hourY = yCenter - hLength *
      Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    Line hLine = new Line(xCenter, yCenter, hourX, hourY);
    hLine.setStroke(Color.GREEN);
    
    //middle label
    
    getChildren().clear(); // Clear the pane
    getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine,t5);
  }

  public void setCurrentTime() {
    // Construct a calendar for the current date and time
    Calendar calendar = new GregorianCalendar();

    // Set current hour, minute and second
    this.hour = calendar.get(Calendar.HOUR_OF_DAY);
    this.minute = calendar.get(Calendar.MINUTE);
    this.second = calendar.get(Calendar.SECOND);
    repaint();
  }
    
  @Override
  public void setWidth(double width) {
    super.setWidth(width);
    repaint();
  }
  
  @Override
  public void setHeight(double height) {
    super.setHeight(height);
    repaint();
  }




  }


