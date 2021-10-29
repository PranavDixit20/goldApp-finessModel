/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldapp;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 *
 * @author akshay
 */
public class SubtleSquareBorder implements Border
{
protected int m_w = 3;
protected int m_h = 3;
protected Color m_topColor = Color.blue;
protected Color m_bottomColor = Color.blue;
protected boolean roundc = false; // Do we want rounded corners on the border?
public SubtleSquareBorder(boolean round_corners)
{
                roundc = round_corners;
}
public Insets getBorderInsets(Component c)
{
return new Insets(m_h, m_w, m_h, m_w);
}
public boolean isBorderOpaque()
{
return true;
}
public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
{
w = w - 3;
h = h - 3;
x ++;
y ++;
// Rounded corners
if(roundc)
{
g.setColor(m_topColor);
g.drawLine(x, y + 2, x, y + h - 2);
g.drawLine(x + 2, y, x + w - 2, y);
g.drawLine(x, y + 2, x + 2, y); // Top left diagonal
g.drawLine(x, y + h - 2, x + 2, y + h); // Bottom left diagonal
g.setColor(m_bottomColor);
g.drawLine(x + w, y + 2, x + w, y + h - 2);
g.drawLine(x + 2, y + h, x + w -2, y + h);
g.drawLine(x + w - 2, y, x + w, y + 2); // Top right diagonal
g.drawLine(x + w, y + h - 2, x + w -2, y + h); // Bottom right diagonal
}
// Square corners
else
{
g.setColor(m_topColor);
g.drawLine(x, y, x, y + h);
g.drawLine(x, y, x + w, y);
g.setColor(m_bottomColor);
g.drawLine(x + w, y, x + w, y + h);
g.drawLine(x, y + h, x + w, y + h);
}
}
}
