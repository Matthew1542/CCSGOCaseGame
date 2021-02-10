import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class FadeClick {

     private int x;
     private int y;
     private int trans;
     private String symbol;
     public FadeClick(int x, int y, String symbol){
          this.x = x;
          this.y = y;
          this.symbol = symbol;
          trans = 260;
     }
     public int getTrans(){
          trans-=5;
          return trans;
     }
     public int getX(){
          return x;    
     }
     public int getY(){
          return y--;
     }
     public String getSymbol(){
          return symbol;
     }
     
}