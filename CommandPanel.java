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
    
    String toolTipString = "<html>This is the helpful tooltip, yar!<br> and another line</html>";
    
    Font cmdFont = new Font("Sans Serif", Font.PLAIN, 10);
    
    public CommandPanel( String title, int xSize, int ySize )
    {
        
        setBackground(panelBGColor);
        setPreferredSize( new Dimension( xSize, ySize ));
        
        Border darkline = BorderFactory.createLineBorder(panelBorderColor);
        
        TitledBorder panelBorder = BorderFactory.createTitledBorder( darkline, title, TitledBorder.LEFT, TitledBorder.TOP, cmdFont);

        panelBorder.setTitleColor(panelTitleColor);
        
        ToolTipManager.sharedInstance().setInitialDelay(0);
        
        setBorder(panelBorder);
        
        setLayout( new BorderLayout() );
        
        JButton helpButton = new JButton();
        helpButton.setIcon(new ImageIcon("images/help_icon.png"));
        helpButton.setPreferredSize(new Dimension(16, 16));
        helpButton.setBackground( panelBGColor );
        helpButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        helpButton.setOpaque(true);
        helpButton.setToolTipText(toolTipString);

        
        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(panelBGColor);
        helpPanel.add( helpButton );
        helpPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        
        add( helpPanel, BorderLayout.LINE_END );
    }
    
}