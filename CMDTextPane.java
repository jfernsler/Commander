import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class CMDTextPane extends JTextPane
{
    Color panelBGColor = new Color(68, 68, 68);
    Color fieldTextColor = new Color(225, 225, 225);
    
    public CMDTextPane()
    {
        setContentType("text/html");
        setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        setBackground( panelBGColor );
        setForeground( fieldTextColor );
        //setLineWrap(true);
        //setWrapStyleWord(true);
        setEditable(false);
    }
    
}