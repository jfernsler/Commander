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
import javax.swing.ToolTipManager;

// CommandPanel extends JPanel in order to provide consistancy and a central
// location for changing all aspects of the UI Section boxes at one time.
//
// this class takes care of setting up the border and the ubiquitous help
// button with an instant tooltip.
//
// CommandPanel takes a title string for the titled border, a X and Y
// preferred size (this could potentially be removed) and a string
// for the tooltip.
public class CommandPanel extends JPanel
{
    // identify colors
    Color panelBorderColor = new Color(41, 41, 41);
    Color panelTitleColor = new Color( 187, 187, 187);
    Color panelBGColor = new Color(68, 68, 68);
    
    // create font
    Font cmdFont = new Font("Sans Serif", Font.PLAIN, 10);
    
    // setup a JPanel to hold the help button
    JPanel helpPanel = new JPanel();
    
    // constructor
    public CommandPanel( String title, int xSize, int ySize, String toolTipString )
    {
        // Setup panel with background, size, border and text justification
        setBackground(panelBGColor);
        setPreferredSize( new Dimension( xSize, ySize ));
        
        Border darkline = BorderFactory.createLineBorder(panelBorderColor);
        
        TitledBorder panelBorder = BorderFactory.createTitledBorder( darkline, title, TitledBorder.LEFT, TitledBorder.TOP, cmdFont);
        
        panelBorder.setTitleColor(panelTitleColor);
        
        
        // set the tooltip delay to zero so there's an instant activation
        // set the dismiss time to 10seconds to give time for the user to
        // read the text (this may need to go longer)
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        
        setBorder(panelBorder);
        
        setLayout( new BorderLayout() );
        
        // setup help button
        JButton helpButton = new JButton();
        helpButton.setIcon(new ImageIcon("images/help_icon.png"));
        helpButton.setPreferredSize(new Dimension(16, 16));
        helpButton.setBackground( panelBGColor );
        helpButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        helpButton.setOpaque(true);
        helpButton.setToolTipText(toolTipString);
        
        // Open a global help dialog box when clicked
        helpButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                JOptionPane.showMessageDialog( helpPanel,
                                              "Commander will help you build a CLI render command\n" +
                                              "for RenderMan in Maya. \n\n" +
                                              "Drag and Drop your Maya scene file on to the window\n" +
                                              "then work your way down the window configuring your\n" +
                                              "render settings.\n\n" +
                                              "Finally, click the Copy Command button to place the full\n" +
                                              "command into your clipboard. From there you can open a\n" +
                                              "Maya ready terminal and paste the command to begin rendering",
                                              "Commander Help",
                                              JOptionPane.PLAIN_MESSAGE );
            }
        });
        
        // set up colors and pop it in.
        helpPanel.setBackground(panelBGColor);
        helpPanel.add( helpButton );
        helpPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        
        add( helpPanel, BorderLayout.LINE_END );
    }
    
}