import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class SpriteSheet {
     
     private BufferedImage image;
     
     public SpriteSheet(BufferedImage image){  
          this.image = image; 
     }
     
     public BufferedImage grabImage(int col, int row, int width, int height){
          return image.getSubimage((col*width) - width, (row*height) - height, width, height);   
     }
     
}