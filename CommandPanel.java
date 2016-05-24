import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ToolTipManager;

public class CommandPanel extends JPanel
{
    Color panelBorderColor = new Color(41, 41, 41);
    Color panelTitleColor = new Color( 187, 187, 187);
    Color panelBGColor = new Color(68, 68, 68);
    
    Font cmdFont = new Font("Sans Serif", Font.PLAIN, 10);
    JPanel helpPanel = new JPanel();
    
    public CommandPanel( String title, int xSize, int ySize, String toolTipString )
    {
        
        setBackground(panelBGColor);
        setPreferredSize( new Dimension( xSize, ySize ));
        
        Border darkline = BorderFactory.createLineBorder(panelBorderColor);
        
        TitledBorder panelBorder = BorderFactory.createTitledBorder( darkline, title, TitledBorder.LEFT, TitledBorder.TOP, cmdFont);
        
        panelBorder.setTitleColor(panelTitleColor);
        
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        
        setBorder(panelBorder);
        
        setLayout( new BorderLayout() );
        
        JButton helpButton = new JButton();
        helpButton.setIcon(new ImageIcon("images/help_icon.png"));
        helpButton.setPreferredSize(new Dimension(16, 16));
        helpButton.setBackground( panelBGColor );
        helpButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        helpButton.setOpaque(true);
        helpButton.setToolTipText(toolTipString);
        
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
                                              "Maya ready terminal and paste the command to begin rendering\n\n" +
                                              "For additional information about the commands used, type:\n" +
                                              "\"render -help\" in a Maya command line.",
                                              "Commander Help",
                                              JOptionPane.PLAIN_MESSAGE );
            }
        });
        
        
        helpPanel.setBackground(panelBGColor);
        helpPanel.add( helpButton );
        helpPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        
        add( helpPanel, BorderLayout.LINE_END );
    }
    
}