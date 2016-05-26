/*
 * Jeremy Fernsler
 * jf575@drexel.edu
 * CS530:DUI, Term Project
 *
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


// This class provides a JTextPane with common settings for use in
// Commander
public class CMDTextPane extends JTextPane
{
    // set colors
    Color panelBGColor = new Color(68, 68, 68);
    Color fieldTextColor = new Color(225, 225, 225);
    
    // constructor - simple settings
    public CMDTextPane()
    {
        setContentType("text/html");
        setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        setBackground( panelBGColor );
        setForeground( fieldTextColor );
        setEditable(false);
    }
    
}
