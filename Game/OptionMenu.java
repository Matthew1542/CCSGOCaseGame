import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class OptionMenu {
     

     public static void refreshVariables(){

     }
     
     

     public OptionMenu(Game game){
          
     }
     
     
     public void tick(){
          if(Game.menuOpened || Game.menuClosing){
               SideMenu.tick();
               refreshVariables();
          }
          else{
               refreshVariables();
          }
     }
     
     public void render(Graphics g){
          if(Game.menuOpened){
               SideMenu.render(g);
          }
          Graphics2D g2d = (Graphics2D) g;
          g.drawImage(Game.background, 0 + Game.moveOver, 0, null);
          Color transBlack = new Color(0, 0, 0, 127);
          Font fnt0 = new Font("arial", Font.BOLD, 40);
          g.setFont(fnt0);
          g.setColor(Color.white);
          g2d.fillRect(605 + Game.moveOver, 15, 30, 5);
          g2d.fillRect(605 + Game.moveOver, 25, 30, 5);
          g2d.fillRect(605 + Game.moveOver, 35, 30, 5);
          Font fnt1 = new Font("ariel", Font.BOLD, 20);
 
           
          if(Game.menuOpened){
               g.setColor(transBlack);
               g2d.fillRect(0 + Game.moveOver, 0, 650, 500);
          }
     }
}