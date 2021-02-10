import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class UpgradeMenu {
     
     public static int moveValue = 0;
     public static int sliderHeight = 371;
     static Rectangle slider = new Rectangle(535 + Game.moveOver, 55 + moveValue, 15, sliderHeight);

     public static void refreshVariables(){
          
          slider = new Rectangle(535 + Game.moveOver, 55 + moveValue, 15, sliderHeight);
          
     }
     
     

     public UpgradeMenu(Game game){
          
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
          g.setFont(fnt1);
          g.drawString("" + Game.moneyString, 10 + Game.moveOver, 25);
           
          g.drawRect(105, 40, 445, 400);
          g.drawRect(535, 40, 15, 400);
          g.drawRect(535, 55, 15, 370);
          g2d.fill(slider);
          
          for(int i = 0; i < 4; i++){
               g.fillRect(115, 55 + i * (15+85) + moveValue*-2 , 410, 85);
          }
          
          
          
          if(Game.menuOpened){
               g.setColor(transBlack);
               g2d.fillRect(0 + Game.moveOver, 0, 650, 500);
          }
     }
}