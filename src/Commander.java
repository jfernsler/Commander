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
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import javax.swing.JFormattedTextField.AbstractFormatter;


public class Commander
{
    // Create a command object to be used throughout
    //
    // CLIconstruct(String in, String out, int start, int end, int by,
    //             int x, int y, int shadeRate, int pixSampX, int pixSampY, int moblur )
    static CLIconstruct CMD1 = new CLIconstruct("/foo.ma", "/foo.####.exr",
                                                1, 200, 1, 1920, 1080, 5, 3, 3, 1 );
    
    // create an object to address the help strings
    static HelpStrings hs = new HelpStrings();
    
    //----------
    // setup the Preset values
    //
    // Draft preset
    static int draftXsize = 720;
    static int draftYsize = 405;
    static int draftShadeRate = 15;
    static int draftPixSampX = 1;
    static int draftPixSampY = 1;
    static int draftMotionblur = 0;
    //
    // Good preset
    static int goodXsize = 1280;
    static int goodYsize = 720;
    static int goodShadeRate = 5;
    static int goodPixSampX = 3;
    static int goodPixSampY = 3;
    static int goodMotionblur = 1;
    //
    // Final preset
    static int finalXsize = 1920;
    static int finalYsize = 1080;
    static int finalShadeRate = 1;
    static int finalPixSampX = 8;
    static int finalPixSampY = 8;
    static int finalMotionBlur = 1;
    
    // Setup common colors for the dark theme
    static Color panelBGColor = new Color(68, 68, 68);
    static Color darkBGColor = new Color(54, 54, 54);
    static Color buttonTextColor = new Color(187, 187, 187);
    static Color fieldTextColor = new Color(225, 225, 225);
    
    // estabilish our 
    static JFrame frame = new JFrame ("Commander");
    static JPanel uber_panel = new JPanel();
    
    static String dropString = "<html><font face=\"sans-serif\" size=\"3\" color=\"E1E1E1\"><center>Drop A Maya Scene File On Window To Begin</html>";
    static String waitString = "<html><font face=\"sans-serif\" size=\"2\" color=\"E1E1E1\">Awaiting file info...</html>";
    
    // -- all command holding text panes
    static CMDTextPane inCMD_text = new CMDTextPane();
    static CMDTextPane outCMD_text = new CMDTextPane();
    static CMDTextPane fcCMD_text = new CMDTextPane();
    static CMDTextPane isCMD_text = new CMDTextPane();
    static CMDTextPane oqCMD_text = new CMDTextPane();
    static CMDTextPane wkCMD_text = new CMDTextPane();
    static CMDTextPane cmd1_text = new CMDTextPane();
    
    
    // -- set up input/output icons
    static ImageIcon inputIcon = new ImageIcon("images/io_inputFile.png");
    static ImageIcon computerIcon = new ImageIcon("images/io_computer.png");
    static ImageIcon dropIcon = new ImageIcon("images/dropIcon.png");
    static ImageIcon outputIcon = new ImageIcon("images/io_outputFile.png");
    // -- input/output buttons
    static JButton inputButton = new JButton("InputFile.ma", inputIcon);
    static JButton outputButton = new JButton("OutputFile.####.exr", outputIcon);
    static JLabel ioComputer = new JLabel();
    
    // -- frame count controls
    static JRadioButton stillButton = new JRadioButton("Still Frame");
    static JRadioButton animButton = new JRadioButton("Animation");
    static JFormattedTextField singleFrameField = new JFormattedTextField();
    static JFormattedTextField endFrameField = new JFormattedTextField();
    static JFormattedTextField startFrameField = new JFormattedTextField();
    
    // -- preset icons
    static ImageIcon draftIcon = new ImageIcon("images/pre_draft_unsel.png");
    static ImageIcon goodIcon = new ImageIcon("images/pre_good_unsel.png");
    static ImageIcon finalIcon = new ImageIcon("images/pre_final_unsel.png");
    // -- preset buttons
    static JButton draftPreButton = new JButton(draftIcon);
    static JButton midPreButton = new JButton(goodIcon);
    static JButton finalPreButton = new JButton(finalIcon);
    
    // -- image size icons
    static ImageIcon finalSizeImage = new ImageIcon("images/size_final.png");
    static ImageIcon goodSizeImage = new ImageIcon("images/size_good.png");
    static ImageIcon draftSizeImage = new ImageIcon("images/size_draft.png");
    static ImageIcon sizeDisabled = new ImageIcon("images/size_disabled.png");
    // -- image size controls
    static JRadioButton finalButton = new JRadioButton( "1920x1080" );
    static JRadioButton goodButton = new JRadioButton( "1280x720" );
    static JRadioButton draftButton = new JRadioButton( "720x405" );
    static JLabel isDisplay = new JLabel();
    
    // -- override panel controls
    static JCheckBox overrideBox = new JCheckBox( "Override" );
    static JCheckBox moblurBox = new JCheckBox( "Motion Blur" );
    static JFormattedTextField shadeRateField = new JFormattedTextField();
    static JFormattedTextField pixSampXField = new JFormattedTextField();
    static JFormattedTextField pixSampYField = new JFormattedTextField();
    
    // -- worker panel icons
    static ImageIcon workerStraight1 = new ImageIcon("images/work_straight1.png");
    static ImageIcon workerStraight2 = new ImageIcon("images/work_straight2.png");
    static ImageIcon workerInter2 = new ImageIcon("images/work_inter2.png");
    static ImageIcon workerStraight3 = new ImageIcon("images/work_straight3.png");
    static ImageIcon workerInter3 = new ImageIcon("images/work_inter3.png");
    static ImageIcon workerDisabled = new ImageIcon("images/work_disabled.png");
    // -- worker panel controls
    static String[] numStrings = {"1 Computer", "2 Computers", "3 Computers"};
    static JComboBox wkNumList = new JComboBox( numStrings );
    static JRadioButton wkStraightButton = new JRadioButton( "Straight" );
    static JRadioButton wkInterButton = new JRadioButton( "Interlaced" );
    static JLabel wkDisplay = new JLabel();
    
    // -- final command section
    static String[] cmdStrings = {"Command 1:"};
    static JComboBox cmdList = new JComboBox( cmdStrings );
    static JButton cpybutton = new JButton("To Clipboard");
    static JButton gResetButton = new JButton( "Reset" );
    static JButton gQuitButton = new JButton( "Quit" );
    static JLabel copyStatus = new JLabel();
    
    // -- Drop Panel section
    static JButton dropButton = new JButton("InputFile.ma", inputIcon);
    
    static int strInter = 0;
    static int allEnabled = 1;
    static int stillFrame = 0;
    static int fullReset = 1;
    static int workerNum = 1;
    static int ofWorkers = 1;
    
    
    // Grab formatted text from the CLIconstruct data structure and
    // display it in the lower panel text fields
    public static void setTextFields()
    {
        inCMD_text.setText(CMD1.getFormattedInput("input:"));
        outCMD_text.setText(CMD1.getFormattedOutput("output:"));
        fcCMD_text.setText(CMD1.getFormattedFrameCount("frame count:"));
        isCMD_text.setText(CMD1.getFormattedImageSize("image sizing:"));
        oqCMD_text.setText(CMD1.getFormattedImageQuality("image quality:"));
        wkCMD_text.setText(CMD1.getFormattedWorkers("adjusted frame count:", workerNum, ofWorkers));
        cmd1_text.setText(CMD1.getFormattedCommand("", workerNum, ofWorkers));
        copyStatus.setText("");
    }
    
    // Clear data from the lower panel text field - used for a reset
    public static void clearTextFields()
    {
        inCMD_text.setText(dropString);
        outCMD_text.setText("");
        fcCMD_text.setText(waitString);
        isCMD_text.setText(waitString);
        oqCMD_text.setText(waitString);
        wkCMD_text.setText(waitString);
        cmd1_text.setText(waitString);
        copyStatus.setText("");
    }
    
    // walk through all text fields and set them to the CLIconstruct
    // stored data
    public static void setInputFields()
    {
        inputButton.setText( CMD1.getInputName() );
        outputButton.setText( CMD1.getFullName() );
        singleFrameField.setValue( CMD1.getStartFrame() );
        startFrameField.setValue( CMD1.getStartFrame() );
        endFrameField.setValue( CMD1.getEndFrame() );
        shadeRateField.setValue( CMD1.getshadeRate() );
        pixSampXField.setValue( CMD1.getPixSampX() );
        pixSampYField.setValue( CMD1.getPixSampY() );
        
        if (CMD1.getXSize() == 1920 ) {
            finalSizeSelected();
        } else if (CMD1.getXSize() == 1280 ) {
            goodSizeSelected();
        } else {
            draftSizeSelected();
        }
        
        if (CMD1.getMotionblur() == 1) {
            moblurBox.setSelected(true);
        } else{
            moblurBox.setSelected(false);
        }
        
        // if any change is made, the copy message is cleared
        copyStatus.setText("");
    }
    
    
    // check which set of fields is selected, disable the others
    // and grab the relevant values. Then set the UI to those values
    public static void setFrameCount(){
        if (stillFrame == 0){
            startFrameField.setEnabled(true);
            endFrameField.setEnabled(true);
            singleFrameField.setEnabled(false);
            CMD1.setEndFrame( (int) singleFrameField.getValue() );
            setInputFields();
            setTextFields();
        } else {
            startFrameField.setEnabled(false);
            endFrameField.setEnabled(false);
            singleFrameField.setEnabled(true);
            CMD1.setEndFrame( (int) singleFrameField.getValue() );
            setInputFields();
            setTextFields();
        }
        
    }
    
    // If motionblur is checked, run this
    public static void setMotionBlur()
    {
        if (moblurBox.isSelected())
            CMD1.setMotionblur(1);
        else
            CMD1.setMotionblur(0);
        
        setTextFields();
    }
    
    
    // setEnabledAll does a mass enable/disable of all components
    // used for resets and fresh sets
    public static void setEnabledAll(boolean set)
    {
        inputButton.setEnabled(set);
        outputButton.setEnabled(set);
        stillButton.setEnabled(set);
        animButton.setEnabled(set);
        endFrameField.setEnabled(set);
        startFrameField.setEnabled(set);
        
        draftPreButton.setEnabled(set);
        midPreButton.setEnabled(set);
        finalPreButton.setEnabled(set);
        
        finalButton.setEnabled(set);
        goodButton.setEnabled(set);
        draftButton.setEnabled(set);
        
        overrideBox.setEnabled(set);
        
        // don't re-enable these - disabled by default
        if (!set)
        {
            singleFrameField.setEnabled(set);
            moblurBox.setEnabled(set);
            shadeRateField.setEnabled(set);
            pixSampXField.setEnabled(set);
            pixSampYField.setEnabled(set);
        }
        
        wkNumList.setEnabled(set);
        wkStraightButton.setEnabled(set);
        wkInterButton.setEnabled(set);
        
        cmdList.setEnabled(set);
        cpybutton.setEnabled(set);
        gResetButton.setEnabled(set);
        
        // need to swap out icons and images to
        // disabled states or "drop file" states
        if (set){
            ioComputer.setIcon(computerIcon);
            wkDisplay.setIcon( workerStraight1 );
            allEnabled = 1;
        } else {
            ioComputer.setIcon(dropIcon);
            wkDisplay.setIcon( workerDisabled );
            isDisplay.setIcon( sizeDisabled );
            allEnabled = 0;
        }
    }
    
    // when a file is dropped, run through all settings
    public static void setCommander()
    {
        setGood();
        setOverridesEnabled(false);
        setTextFields();
        setInputFields();
        setEnabledAll(true);
    }
    
    // clear out everything, including the CLI
    public static void resetCommander()
    {
        CMD1.setAll( "", "", 0, 0, 0, 1920, 1080, 0, 0, 0, 0 );
        CMD1.setInputName("scene.ma");
        CMD1.setOutputName("output.#.exr");
        stillFrame = 0;
        setInputFields();
        clearTextFields();
        setOverridesEnabled(false);
        setEnabledAll(false);
    }
    
    // push draft preset and refresh the UI
    public static void setDraft()
    {
        CMD1.setPreset(draftXsize, draftYsize, draftShadeRate, draftPixSampX,
                       draftPixSampY, draftMotionblur );
        setInputFields();
        draftSizeSelected();
        setOverridesEnabled(false);
    }
    
    // push good preset and refresh the UI
    public static void setGood()
    {
        CMD1.setPreset(goodXsize, goodYsize, goodShadeRate, goodPixSampX,
                       goodPixSampY, goodMotionblur );
        setInputFields();
        goodSizeSelected();
        setOverridesEnabled(false);
    }
    
    // push final preset and refresh the UI
    public static void setFinal()
    {
        CMD1.setPreset(finalXsize, finalYsize, finalShadeRate, finalPixSampX,
                       finalPixSampY, finalMotionBlur );
        setInputFields();
        finalSizeSelected();
        setOverridesEnabled(false);
    }
    
    // enable / disable the fields for overriding preset settings
    public static void setOverridesEnabled(boolean set)
    {
        moblurBox.setEnabled(set);
        shadeRateField.setEnabled(set);
        pixSampXField.setEnabled(set);
        pixSampYField.setEnabled(set);
    }
    
    // if the Final image size radio button is selected
    public static void finalSizeSelected()
    {
        finalButton.setSelected( true );
        isDisplay.setIcon( finalSizeImage );
        CMD1.setXSize(1920);
        CMD1.setYSize(1080);
        setTextFields();
    }
    
    // if the good image size radio button is selected
    public static void goodSizeSelected()
    {
        goodButton.setSelected( true );
        isDisplay.setIcon( goodSizeImage );
        CMD1.setXSize(1280);
        CMD1.setYSize(720);
        setTextFields();
    }
    
    // if the draft image size radio button is selected
    public static void draftSizeSelected()
    {
        draftButton.setSelected( true );
        isDisplay.setIcon( draftSizeImage );
        CMD1.setXSize(720);
        CMD1.setYSize(405);
        setTextFields();
    }
    
    // make adjustments to the CLIconstruct and the worker image
    // based on the worker radio buttons and dropdown menu
    public static void checkWorkerStatus()
    {
        int num = wkNumList.getSelectedIndex();
        
        if ( num == 0 ){ // one worker
            CMD1.setByFrame(1, 0);
            wkDisplay.setIcon( workerStraight1 );
            wkStraightButton.setSelected( true );
            wkInterButton.setEnabled( false );
            ofWorkers = 1;
            if (cmdList.getItemCount() != 1)
            {
                cmdList.removeAllItems();
                cmdList.insertItemAt("Command 1:", 0);
                cmdList.setSelectedIndex(0);
            }
            
        } else if (num == 1 && strInter == 0){ // two workers straight
            CMD1.setByFrame(1, 0);
            wkDisplay.setIcon( workerStraight2 );
            wkInterButton.setEnabled( true );
            ofWorkers = 2;
            
            if (cmdList.getItemCount() != 2)
            {
                cmdList.removeAllItems();
                cmdList.insertItemAt("Command 1:", 0);
                cmdList.insertItemAt("Command 2:", 1);
                cmdList.setSelectedIndex(0);
            }
            
        } else if (num == 1 && strInter == 1){ // two workers interlaced
            CMD1.setByFrame(2, 1);
            wkDisplay.setIcon( workerInter2 );
            wkInterButton.setEnabled( true );
            ofWorkers = 2;
            
            if (cmdList.getItemCount() != 2)
            {
                cmdList.removeAllItems();
                cmdList.insertItemAt("Command 1:", 0);
                cmdList.insertItemAt("Command 2:", 1);
                cmdList.setSelectedIndex(0);
            }
            
        } else if (num == 2 && strInter == 0){ // three workers straight
            CMD1.setByFrame(1, 0);
            wkDisplay.setIcon( workerStraight3 );
            wkInterButton.setEnabled( true );
            ofWorkers = 3;
            
            if (cmdList.getItemCount() != 3)
            {
                cmdList.removeAllItems();
                cmdList.insertItemAt("Command 1:", 0);
                cmdList.insertItemAt("Command 2:", 1);
                cmdList.insertItemAt("Command 3:", 2);
                cmdList.setSelectedIndex(0);
            }
            
        } else if (num == 2 && strInter == 1){ // three workers interlaced
            CMD1.setByFrame(3, 1);
            wkDisplay.setIcon( workerInter3 );
            wkInterButton.setEnabled( true );
            ofWorkers = 3;
            
            if (cmdList.getItemCount() != 3)
            {
                cmdList.removeAllItems();
                cmdList.insertItemAt("Command 1:", 0);
                cmdList.insertItemAt("Command 2:", 1);
                cmdList.insertItemAt("Command 3:", 2);
                cmdList.setSelectedIndex(0);
            }
        }
        
        // update text fields
        setTextFields();
    }
    
    public static void addActions()
    {
        // -- image size control actions
        finalButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                finalSizeSelected();
            }
        });
        
        goodButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                goodSizeSelected();
            }
        });
        
        draftButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                draftSizeSelected();
            }
        });
        
        
        // -- frame count button actions
        stillButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                stillFrame = 1;
                setFrameCount();
            }
        });
        
        animButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                stillFrame = 0;
                setFrameCount();
            }
        });
        
        // -- worker control actions
        wkNumList.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                checkWorkerStatus();
            }
        });
        
        wkStraightButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                strInter = 0;
                checkWorkerStatus();
            }
        });
        
        wkInterButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                strInter = 1;
                checkWorkerStatus();
            }
        });
        
        // -- reset & quit actions
        gResetButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                fullReset = 1;
                resetCommander();
            }
        });
        
        gQuitButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                System.exit(0);
            }
        });
        
        
        // -- preset button actions
        draftPreButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                setDraft();
            }
        });
        
        midPreButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                setGood();
            }
        });
        
        finalPreButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                setFinal();
            }
        });
        
        // -- enable / disable overrides
        overrideBox.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                if (overrideBox.isSelected() ){
                    setOverridesEnabled(true);
                } else {
                    setOverridesEnabled(false);
                }
            }
        });
        
        // -- enable / disable motionblur
        moblurBox.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                setMotionBlur();
            }
        });
        
        // -- command number combobox actions (swap out full command)
        cmdList.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                workerNum = cmdList.getSelectedIndex() + 1;
                setTextFields();
            }
        });
        
        // -- copy to clipboard action
        cpybutton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                StringSelection stringSelection = new StringSelection( CMD1.getCommand(workerNum, ofWorkers) );
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
                copyStatus.setText("Worker " + workerNum + " Command Placed in Clipboard");
            }
        });
        
        // -- drag and drop on the window
        new FileDrop( frame, new FileDrop.Listener() {
            public void filesDropped( java.io.File[] files )
            {
                // grab the path of the file, derive the name
                // and then set the output file name and location based on that.
                String scenePath = files[0].getPath();
                String sceneName = files[0].getName();
                String imageName = sceneName.replace(".ma", "");
                
                
                // in a proper project rendered images go in the /images/ directory
                // back out past the first dir and append images
                int slashIndex = scenePath.lastIndexOf( "/");
                String imagePath =  scenePath.substring(0, slashIndex);
                
                slashIndex = imagePath.lastIndexOf( "/");
                imagePath =  imagePath.substring(0, slashIndex) + "/images/";
                
                
                // set the vars and then run through commander to set all fields
                CMD1.setInput( scenePath );
                CMD1.setInputName( sceneName );
                CMD1.setOutput( imagePath );
                CMD1.setOutputName( imageName );
                
                setCommander();
                
            }
        });
        
        
        // frame action (quit on close)
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) { System.exit(0); }
        });
        
        // field actions:
        // ActionListeners for hitting the enter key
        // FocusListeners for tabbing in and out
        //  - tab in action is to select all, because these are
        //      JFomattedTextFields the selection behavior needs
        //      to be overriddent
        //  - tab out set the value to the CLIconstruct
        singleFrameField.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                CMD1.setStartFrame( (int) singleFrameField.getValue() );
                CMD1.setEndFrame( (int) singleFrameField.getValue() );
                setInputFields();
                setTextFields();
            }
        });
        
        singleFrameField.addFocusListener(new FocusListener () {
            public void focusLost (FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CMD1.setStartFrame( (int) singleFrameField.getValue() );
                        CMD1.setEndFrame( (int) singleFrameField.getValue() );
                        setInputFields();
                        setTextFields();
                    }
                });
            }
            
            public void focusGained( FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        singleFrameField.selectAll();
                    }
                });
            }
        });
        
        startFrameField.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                CMD1.setStartFrame( (int) startFrameField.getValue() );
                setTextFields();
            }
        });
        
        startFrameField.addFocusListener(new FocusListener () {
            public void focusLost (FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CMD1.setStartFrame( (int) startFrameField.getValue() );
                        setTextFields();
                    }
                });
            }
            
            public void focusGained( FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        startFrameField.selectAll();
                    }
                });
            }
        });
        
        endFrameField.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                CMD1.setEndFrame( (int) endFrameField.getValue() );
                setTextFields();
            }
        });
        
        endFrameField.addFocusListener(new FocusListener () {
            public void focusLost (FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CMD1.setEndFrame( (int) endFrameField.getValue() );
                        setTextFields();
                    }
                });
            }
            
            public void focusGained( FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        endFrameField.selectAll();
                    }
                });
            }
        });
        
        shadeRateField.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                CMD1.setshadeRate( (int) shadeRateField.getValue() );
                setTextFields();
            }
        });
        
        shadeRateField.addFocusListener(new FocusListener () {
            public void focusLost (FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CMD1.setshadeRate( (int) shadeRateField.getValue() );
                        setTextFields();
                    }
                });
            }
            
            public void focusGained( FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        shadeRateField.selectAll();
                    }
                });
            }
        });
        
        pixSampXField.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                CMD1.setPixSampX( (int) pixSampXField.getValue() );
                setTextFields();
            }
        });
        
        pixSampXField.addFocusListener(new FocusListener () {
            public void focusLost (FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CMD1.setPixSampX( (int) pixSampXField.getValue() );
                        setTextFields();
                    }
                });
            }
            
            public void focusGained( FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        pixSampXField.selectAll();
                    }
                });
            }
        });
        
        pixSampYField.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                CMD1.setPixSampY( (int) pixSampYField.getValue() );
                setTextFields();
            }
        });
        
        pixSampYField.addFocusListener(new FocusListener () {
            public void focusLost (FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CMD1.setPixSampY( (int) pixSampYField.getValue() );
                        setTextFields();
                    }
                });
            }
            
            public void focusGained( FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        pixSampYField.selectAll();
                    }
                });
            }
        });
    }
    
    
    // this builds out the full UI
    public static void buildFullUI()
    {
        
        //------------------//
        // Input / Output Panel
        //================================================
        JPanel io_panel = new CommandPanel ( "Input / Output", 100, 135, hs.getIOHelpString() );
        
        JPanel ioIconPanel = new JPanel( );
        ioIconPanel.setBackground( panelBGColor );
        ioIconPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        inputButton.setPreferredSize( new Dimension( 120, 80));
        inputButton.setBackground( panelBGColor );
        inputButton.setForeground( fieldTextColor );
        inputButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inputButton.setHorizontalTextPosition(SwingConstants.CENTER);
        inputButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        inputButton.setOpaque(true);
        
        ioComputer.setBackground( panelBGColor );
        ioComputer.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        outputButton.setPreferredSize( new Dimension( 120, 80));
        outputButton.setBackground( panelBGColor );
        outputButton.setForeground( fieldTextColor );
        outputButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        outputButton.setHorizontalTextPosition(SwingConstants.CENTER);
        outputButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        outputButton.setOpaque(true);
        
        ioIconPanel.add(inputButton);
        ioIconPanel.add(Box.createRigidArea (new Dimension (18,18)));
        ioIconPanel.add(ioComputer);
        ioIconPanel.add(Box.createRigidArea (new Dimension (18,18)));
        ioIconPanel.add(outputButton);
        
        JPanel ioCMD_panel = new JPanel( new BorderLayout() );
        
        ioCMD_panel.add( inCMD_text, BorderLayout.PAGE_START);
        ioCMD_panel.add( outCMD_text, BorderLayout.PAGE_END );
        
        io_panel.add( ioIconPanel, BorderLayout.CENTER );
        io_panel.add( ioCMD_panel, BorderLayout.PAGE_END );
        
        
        //------------------//
        // Frame Count Panel
        //================================================
        JPanel fc_panel = new CommandPanel( "Frame Count", 100, 110, hs.getFCHelpString() );
        
        stillButton.setForeground( buttonTextColor );
        
        animButton.setForeground( buttonTextColor );
        animButton.setSelected( true );
        
        ButtonGroup fcButtonGRP = new ButtonGroup();
        fcButtonGRP.add( stillButton );
        fcButtonGRP.add( animButton );
        
        JLabel singleFrameLabel = new JLabel("Frame:", JLabel.RIGHT);
        singleFrameLabel.setForeground( buttonTextColor );
        singleFrameLabel.setBackground( panelBGColor );
        
        JLabel startFrameLabel = new JLabel("Start:", JLabel.RIGHT);
        startFrameLabel.setForeground( buttonTextColor );
        startFrameLabel.setBackground( panelBGColor );
        
        JLabel endFrameLabel = new JLabel("End:", JLabel.RIGHT);
        endFrameLabel.setForeground( buttonTextColor );
        endFrameLabel.setBackground( panelBGColor );
        
        singleFrameField.setColumns(4);
        singleFrameField.setHorizontalAlignment( JTextField.CENTER );
        singleFrameField.setForeground( fieldTextColor );
        singleFrameField.setBackground( darkBGColor );
        singleFrameField.setEnabled(false);
        
        endFrameField.setColumns(4);
        endFrameField.setHorizontalAlignment( JTextField.CENTER );
        endFrameField.setForeground( fieldTextColor );
        endFrameField.setBackground( darkBGColor );
        
        startFrameField.setColumns(4);
        startFrameField.setHorizontalAlignment( JTextField.CENTER );
        startFrameField.setForeground( fieldTextColor );
        startFrameField.setBackground( darkBGColor );
        
        JPanel fcStillPanel = new JPanel( new FlowLayout(FlowLayout.LEADING) );
        fcStillPanel.setBackground(panelBGColor);
        
        fcStillPanel.add( stillButton );
        fcStillPanel.add(Box.createRigidArea (new Dimension (8,8)));
        fcStillPanel.add( singleFrameLabel );
        fcStillPanel.add( singleFrameField );
        
        JPanel fcAnimPanel = new JPanel( new FlowLayout(FlowLayout.LEADING) );
        fcAnimPanel.setBackground(panelBGColor);
        
        fcAnimPanel.add( animButton );
        fcAnimPanel.add(Box.createRigidArea (new Dimension (18,18)));
        fcAnimPanel.add( startFrameLabel );
        fcAnimPanel.add( startFrameField );
        fcAnimPanel.add( endFrameLabel );
        fcAnimPanel.add( endFrameField );
        
        JPanel fcRadioPanel = new JPanel(new GridLayout(2,0));
        fcRadioPanel.setBackground(panelBGColor);
        fcRadioPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        fcRadioPanel.add( fcStillPanel );
        fcRadioPanel.add( fcAnimPanel );
        
        fc_panel.add( fcRadioPanel, BorderLayout.LINE_START  );
        fc_panel.add( fcCMD_text, BorderLayout.PAGE_END  );
        
        
        //------------------//
        // Quality Presets Panel
        //================================================
        CommandPanel qp_panel = new CommandPanel( "Quality Presets - Shortcut buttons to quality levels", 100, 100, hs.getPresetsHelpString() );
        
        
        JPanel presetPanel = new JPanel( );
        presetPanel.setBackground( panelBGColor );
        
        Dimension buttonDim = new Dimension( 64,64 );
        
        
        draftPreButton.setPreferredSize(buttonDim);
        draftPreButton.setBackground( panelBGColor );
        draftPreButton.setForeground( fieldTextColor );
        draftPreButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        draftPreButton.setOpaque(true);
        
        
        midPreButton.setPreferredSize(buttonDim);
        midPreButton.setBackground( panelBGColor );
        midPreButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        midPreButton.setOpaque(true);
        
        
        finalPreButton.setPreferredSize(buttonDim);
        finalPreButton.setBackground( panelBGColor );
        finalPreButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        finalPreButton.setOpaque(true);
        
        presetPanel.add(draftPreButton);
        presetPanel.add(Box.createRigidArea (new Dimension (50,18)));
        presetPanel.add(midPreButton);
        presetPanel.add(Box.createRigidArea (new Dimension (50,18)));
        presetPanel.add(finalPreButton);
        presetPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        qp_panel.add( presetPanel, BorderLayout.CENTER );
        
        
        //------------------//
        // Image Size Panel
        //================================================
        CommandPanel is_panel = new CommandPanel( "Image Size - Change this if you want a larger or smaller image", 100, 130, hs.getISHelpString() );
        
        finalButton.setForeground( buttonTextColor );
        finalButton.setSelected( true );
        goodButton.setForeground( buttonTextColor );
        draftButton.setForeground( buttonTextColor );
        
        ButtonGroup isButtonGRP = new ButtonGroup();
        isButtonGRP.add( finalButton );
        isButtonGRP.add( goodButton );
        isButtonGRP.add( draftButton );
        
        JPanel isRadioPanel = new JPanel( new GridLayout(0,1));
        isRadioPanel.setBackground(panelBGColor);
        isRadioPanel.add( finalButton );
        isRadioPanel.add( goodButton );
        isRadioPanel.add( draftButton );
        
        JPanel isContentPanel = new JPanel();
        isContentPanel.setBackground( panelBGColor );
        isContentPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        
        isDisplay.setIcon( finalSizeImage );
        isDisplay.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        isContentPanel.add(isRadioPanel);
        isContentPanel.add(Box.createRigidArea (new Dimension (45,1)));
        isContentPanel.add(isDisplay);
        
        is_panel.add( isContentPanel, BorderLayout.LINE_START );
        is_panel.add( isCMD_text, BorderLayout.PAGE_END );
        
        //------------------//
        // Override Image Quality Panel
        //================================================
        CommandPanel oq_panel = new CommandPanel( "Override Image Quality - Fine tune a preset for your needs", 100, 150, hs.getOQHelpString() );
        
        
        overrideBox.setForeground( buttonTextColor );
        
        moblurBox.setForeground( buttonTextColor );
        moblurBox.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        JLabel shadeRateLabel = new JLabel("Shading Rate: ", JLabel.RIGHT);
        shadeRateLabel.setForeground( buttonTextColor );
        shadeRateLabel.setBackground( panelBGColor );
        
        JLabel pixSampXLabel = new JLabel("Pixel Samples X: ", JLabel.RIGHT);
        pixSampXLabel.setForeground( buttonTextColor );
        pixSampXLabel.setBackground( panelBGColor );
        
        JLabel pixSampYLabel = new JLabel("Pixel Samples Y: ", JLabel.RIGHT);
        pixSampYLabel.setForeground( buttonTextColor );
        pixSampYLabel.setBackground( panelBGColor );
        pixSampYLabel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
        
        shadeRateField.setColumns(5);
        shadeRateField.setHorizontalAlignment( JTextField.CENTER );
        shadeRateField.setForeground( fieldTextColor );
        shadeRateField.setBackground( darkBGColor );
        
        pixSampXField.setColumns(5);
        pixSampXField.setHorizontalAlignment( JTextField.CENTER );
        pixSampXField.setForeground( fieldTextColor );
        pixSampXField.setBackground( darkBGColor );
        
        pixSampYField.setColumns(5);
        pixSampYField.setHorizontalAlignment( JTextField.CENTER );
        pixSampYField.setForeground( fieldTextColor );
        pixSampYField.setBackground( darkBGColor );
        
        JPanel qLeftPanel = new JPanel( new GridLayout(3,0));
        qLeftPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        qLeftPanel.setBackground( panelBGColor );
        
        qLeftPanel.add( overrideBox );
        qLeftPanel.add( Box.createRigidArea (new Dimension (18,18)) );
        qLeftPanel.add( Box.createRigidArea (new Dimension (18,18)) );
        
        JPanel qLabelPanel = new JPanel( new GridLayout(3,0));
        qLabelPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        qLabelPanel.setBackground( panelBGColor );
        
        qLabelPanel.add( shadeRateLabel );
        qLabelPanel.add( pixSampXLabel );
        qLabelPanel.add( pixSampYLabel );
        
        JPanel qFieldPanel = new JPanel( new GridLayout(3,0));
        qFieldPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        qFieldPanel.setBackground( panelBGColor );
        
        qFieldPanel.add( shadeRateField );
        qFieldPanel.add( pixSampXField );
        qFieldPanel.add( pixSampYField );
        
        JPanel qRightPanel = new JPanel( new GridLayout(3,0));
        qRightPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        qRightPanel.setBackground( panelBGColor );
        
        qRightPanel.add( moblurBox );
        qRightPanel.add( Box.createRigidArea (new Dimension (20,25)) );
        qRightPanel.add( Box.createRigidArea (new Dimension (18,25)) );
        
        JPanel qSettingsPanel = new JPanel();
        qSettingsPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        qSettingsPanel.setBackground( panelBGColor );
        
        qSettingsPanel.add( qLeftPanel );
        qSettingsPanel.add( Box.createRigidArea (new Dimension (5,5)) );
        qSettingsPanel.add( qLabelPanel );
        qSettingsPanel.add( qFieldPanel );
        qSettingsPanel.add( Box.createRigidArea (new Dimension (5,5)) );
        qSettingsPanel.add( qRightPanel );
        
        oq_panel.add( qSettingsPanel, BorderLayout.LINE_START );
        oq_panel.add( oqCMD_text, BorderLayout.PAGE_END );
        
        setOverridesEnabled(false);
        
        //------------------//
        // Worker Panel
        //================================================
        CommandPanel wk_panel = new CommandPanel( "Workers - How many computers will be used", 100, 140, hs.getWKHelpString());
        
        wkStraightButton.setForeground( buttonTextColor );
        wkStraightButton.setSelected( true );
        
        wkInterButton.setForeground( buttonTextColor );
        
        ButtonGroup wkButtonGRP = new ButtonGroup();
        wkButtonGRP.add( wkStraightButton );
        wkButtonGRP.add( wkInterButton );
        
        wkNumList.setSelectedIndex(0);
        
        JPanel wkRadioPanel = new JPanel( new GridLayout(0,1));
        wkRadioPanel.setBackground(panelBGColor);
        
        wkRadioPanel.add( wkNumList );
        wkRadioPanel.add( wkStraightButton );
        wkRadioPanel.add( wkInterButton );
        wkRadioPanel.setBorder(BorderFactory.createEmptyBorder(8,8,16,8));
        
        
        JPanel wkImagePanel = new JPanel();
        wkImagePanel.setBackground( panelBGColor );
        wkImagePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        wkDisplay.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        
        wkImagePanel.add(wkRadioPanel);
        wkImagePanel.add(wkDisplay);
        
        
        wk_panel.add(wkImagePanel, BorderLayout.LINE_START);
        wk_panel.add(wkCMD_text, BorderLayout.PAGE_END );
        
        //------------------//
        // Final Command Panel
        //================================================
        JPanel cmd1_panel = new JPanel( new GridLayout(0,1));
        cmd1_panel.setBackground( panelBGColor );
        
        cpybutton.setBackground( panelBGColor );
        cpybutton.setOpaque(true);
        
        cmd1_panel.add(cmdList);
        cmd1_panel.add(cpybutton);
        
        JPanel cmd_panel = new JPanel( new BorderLayout() );
        cmd_panel.setBackground( panelBGColor );
        
        cmd1_text.setPreferredSize( new Dimension( 360, 40 ));
        cmd1_text.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        cmd1_text.setBackground( darkBGColor );
        cmd1_text.setForeground( fieldTextColor );
        
        JScrollPane jsp = new JScrollPane(cmd1_text);
        jsp.setPreferredSize( new Dimension( 380, 40 ));
        jsp.setMinimumSize( new Dimension( 380, 40));
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        JPanel globalButtonsPanel = new JPanel( new BorderLayout() );
        globalButtonsPanel.setBackground( panelBGColor );
        
        JPanel gButtons = new JPanel( new GridLayout(1, 2) );
        gButtons.setBackground( panelBGColor );
        gButtons.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        
        gButtons.add( gResetButton );
        gButtons.add( gQuitButton );
        
        copyStatus.setForeground( buttonTextColor );
        copyStatus.setBackground( panelBGColor );
        
        globalButtonsPanel.add( gButtons, BorderLayout.LINE_END );
        globalButtonsPanel.add( copyStatus, BorderLayout.LINE_START );
        
        cmd_panel.add( cmd1_panel, BorderLayout.LINE_START );
        cmd_panel.add( jsp, BorderLayout.LINE_END );
        cmd_panel.add( globalButtonsPanel, BorderLayout.PAGE_END );
        cmd_panel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        
        
        //------------------//
        // UBER Panel
        //================================================
        uber_panel.setLayout( new BoxLayout(uber_panel, BoxLayout.PAGE_AXIS) );
        
        uber_panel.setBackground(new Color(68, 68, 68));
        uber_panel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        
        uber_panel.add( io_panel );
        uber_panel.add( fc_panel );
        uber_panel.add( qp_panel );
        uber_panel.add( is_panel );
        uber_panel.add( oq_panel );
        uber_panel.add( wk_panel );
        uber_panel.add( cmd_panel );
        
        frame.getContentPane().add (uber_panel);
        
        frame.setResizable(false );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack ();
    }
    
    // main method - set up the actions, build out the UI and do
    // a full reset before displaying
    public static void main (String[] args)
    {
        addActions();
        buildFullUI();
        resetCommander();
        frame.setVisible(true);
    }
}
