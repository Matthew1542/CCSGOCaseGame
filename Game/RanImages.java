import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class RanImages{
     
     private int x;
     private int y;
     private int itemN;
     private boolean stat;
     
     public RanImages(int x, int y, int itemN, boolean stat){
          this.x = x;
          this.y = y;
          this.itemN = itemN;
          this.stat = stat;
     }
     public void moveX(int changeX){
          this.x -= changeX;      
     } 
     public int getX(){
          return x;
     }
     public int getY(){
          return y;
     }
     public int getItemN(){
          return itemN;
     }
     public boolean getStat(){
          return stat;    
     }
}