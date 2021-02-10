import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class SideMenu {
     
     public SideMenu(){
         
     }
     
     public static void tick(){
          if(Game.menuOpened){
               if(Game.moveOver > -200)
                    Game.moveOver -= 20;
               else
                    Game.moveOver = -200;
          }
          if(Game.menuClosing){
               if(Game.moveOver < 0)
                    Game.moveOver += 20;
               else{
                    Game.moveOver = 0;
                    Game.menuClosing = false;
               }
          }
     }
     
     static int trans = 0; 
     
     public static void render(Graphics g){
          if(Game.menuOpened){
               if(trans < 120)
                    trans += 12;
               else
                    trans = 127;
          }
          if(Game.menuClosing){
               if(trans > 0)
                    trans -= 12;
               else{
                    trans = 0;
                    Game.menuClosing = false;
               }
          }
          Graphics2D g2d = (Graphics2D) g;
          Color transBlack = new Color(0, 0, 0, trans);
          Font fnt0 = new Font("ariel", Font.BOLD, 40);
          Font fnt1 = new Font("ariel", Font.BOLD, 35);
          g.setFont(fnt0);
          g.setColor(transBlack);
          g2d.fillRect(0, 0, 640-185, 500);
          g.setColor(Color.white);
          g2d.drawRect(460, 39, 180, 1);
          g.drawString("Menu", 500, 35);
          g.setFont(fnt1);
          //Click
          g2d.drawRect(460, 50, 180, 50);
          g.drawString("Click", 510, 90);
          //Upgrades
          g2d.drawRect(460, 110, 180, 50);
          g.drawString("Upgrades", 470, 150);
          //Cases
          g2d.drawRect(460, 170, 180, 50);
          g.drawString("Cases", 500, 210);
          //Inventory
          g2d.drawRect(460, 230, 180, 50);
          g.drawString("Inventory", 470, 270);
          //Stats
          g2d.drawRect(460, 290, 180, 50);
          g.drawString("Statistics", 470, 330);
          //Options
          g2d.drawRect(460, 350, 180, 50);
          g.drawString("Options", 480, 390);
          //Quit Button
          g2d.drawRect(460, 410, 180, 50);
          g.drawString("Quit", 510, 450);
     }
     
     
}