package test;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class EditImage {
  	/*
	InputStream inputStream = new ByteArrayInputStream(bytes);
		final BufferedImage image = ImageIO.read(inputStream);

	    Graphics g = image.getGraphics();
	    g.setFont(g.getFont().deriveFont(40f));
	    FontMetrics fm = g.getFontMetrics();
	    int x = 10;
	    int y = 10;
	    String s = "pippo";
	    for (int i = 0; i < s.length(); i++) {
	         char c = s.charAt(i);
	         int h = fm.getHeight();
	         int w = fm.charWidth(c);
	         
	         g.setColor(Color.black);
	         g.fillRect(x, y, w, h);
	         g.setColor(Color.white);
	         g.drawString(String.valueOf(c), x, y + h);
	         x = x + w;
	      }
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write( image, "png", baos );
		baos.flush();*/
}
