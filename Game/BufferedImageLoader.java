import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import javax.imageio.*;
import java.io.*;

public class BufferedImageLoader {  
     
    public BufferedImage loadImage(String path) throws IOException{
        return ImageIO.read(getClass().getResource(path));
    }
}