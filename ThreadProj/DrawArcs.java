package threadproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;


public class DrawArcs extends Pane {

    



  private void paintComponent() {
    int xCenter = (int)(getWidth() / 2);
    int yCenter = (int)(getHeight() / 2);
    int radius = (int)(Math.min(getWidth(), getHeight()) * 0.2);

    int x = xCenter ;
    int y = yCenter ;
    
    Arc arc1=new Arc(x,y,2 * radius, 2 * radius, 0, 30);
    arc1.setFill(Color.BLACK);
    Arc arc2=new Arc(x,y,2 * radius, 2 * radius, 90, 30);
    arc2.setFill(Color.BLACK);
    Arc arc3=new Arc(x,y,2 * radius, 2 * radius, 180, 30);
    arc3.setFill(Color.BLACK);
    Arc arc4=new Arc(x,y,2 * radius, 2 * radius, 270, 30);
    arc4.setFill(Color.BLACK);
    arc1.setType(ArcType.ROUND);
    arc2.setType(ArcType.ROUND);
    arc3.setType(ArcType.ROUND);
    arc4.setType(ArcType.ROUND);
    
    getChildren().clear(); // Clear the pane
    getChildren().addAll(arc1,arc2,arc3,arc4);
  }
    
  @Override
  public void setWidth(double width) {
    super.setWidth(width);
    paintComponent();
  }
  
  @Override
  public void setHeight(double height) {
    super.setHeight(height);
    paintComponent();
  }
}


