/*
 * Jeremy Fernsler
 * jf575@drexel.edu
 * CS530:DUI, Term Project
 *
 */

import java.util.*;


// This is the base data structure for Commander
// Stores the base command, command flags, flag values,
//  input and output file paths and names.
// This also stores HTML formatting information such as colors
//  and font types and sizes for returning a formatted command
public class CLIconstruct
{
    
    String inputFile;
    String outputFile;
    String inputName;
    String outputName;
    int startFrame;
    int endFrame;
    int byFrame;
    int interlaced;
    int xSize;
    int ySize;
    int shadeRate;
    int pixSampX;
    int pixSampY;
    int motionblur;
    
    // base command string
    String renderCmd = "render -r rman";
    
    // command flags
    String inputFlag = " ";
    String outputFlag = " -fnc name.#.ext -of OpenEXR -pad 4 -im ";
    String outputPathFlag = " -rd ";
    String startFlag = " -s ";
    String endFlag = " -e ";
    String byFlag = " -b ";
    String resFlag = " -res ";
    String shadeRateFlag = " -setAttr ShadingRate ";
    String pixSampFlag = " -setAttr PixelSamples \"";
    String moblurFlag = " -setAttr motionBlur ";
    
    // font formatting strings and colors
    String fontFormat ="<font face=\"sans-serif\" size=\"2\" ";
    
    String inputFormat = "color=\"BFB23A\">";
    String outputFormat = "color=\"2CB325\">";
    String frameCountFormat = "color=\"DB9496\">";
    String imageSizeFormat = "color=\"AC9BD6\">";
    String imageQualityFormat = "color=\"C57867\">";
    String workerFormat = "color=\"528586\">";
    String cmdFormat = "color=\"E1E1E1\">";
    
    String endFontFormat = "</font>";
    
    // constructor
    public CLIconstruct(String in, String out, int start, int end, int by,
                        int x, int y, int shad, int pixX, int pixY, int moblur )
    {
        inputFile = in;
        outputFile = out;
        startFrame = start;
        endFrame = end;
        byFrame = by;
        xSize = x;
        ySize = y;
        shadeRate = shad;
        pixSampX = pixX;
        pixSampY = pixY;
        motionblur = moblur;
        interlaced = 0;
        
    }
    
    // method for setting all values
    public void setAll(String in, String out, int start, int end, int by,
                       int x, int y, int shad, int pixX, int pixY, int moblur )
    {
        inputFile = in;
        outputFile = out;
        startFrame = start;
        endFrame = end;
        byFrame = by;
        xSize = x;
        ySize = y;
        shadeRate = shad;
        pixSampX = pixX;
        pixSampY = pixY;
        motionblur = moblur;
        interlaced = 0;
        
    }
    
    // returns HTML formatted string for input, takes a prefix string
    // for an argument
    public String getFormattedInput(String pre){
        return fontFormat + inputFormat + pre +
        inputFlag + inputFile +
        endFontFormat;
    }
    
    // returns HTML formatted string for output, takes a prefix string
    // for an argument
    public String getFormattedOutput(String pre){
        return fontFormat + outputFormat + pre +
        outputFlag + outputName +
        outputPathFlag + outputFile +
        endFontFormat;
    }
    
    // returns HTML formatted string for frame count, takes a prefix string
    // for an argument
    public String getFormattedFrameCount(String pre){
        return fontFormat + frameCountFormat + pre +
        startFlag + Integer.toString(startFrame) +
        endFlag + Integer.toString(endFrame) +
        endFontFormat;
    }
    
    // returns HTML formatted string for image size, takes a prefix string
    // for an argument
    public String getFormattedImageSize(String pre){
        return fontFormat + imageSizeFormat + pre +
        resFlag + Integer.toString(xSize) + " " +
        Integer.toString(ySize) +
        endFontFormat;
        
    }
    
    // returns HTML formatted string for overrides, takes a prefix string
    // for an argument
    public String getFormattedImageQuality(String pre){
        return fontFormat + imageQualityFormat + pre +
        shadeRateFlag + Integer.toString(shadeRate) +
        pixSampFlag + Integer.toString(pixSampX) + " " +
        Integer.toString(pixSampY) + "\" " +
        moblurFlag + Integer.toString(motionblur) +
        endFontFormat;
    }
    
    // returns HTML formatted string for worker setup, takes a prefix string,
    // and which worker and how many workers their are.
    // - if it's worker 2 or 3 then the command string is different and 1 or 3
    public String getFormattedWorkers(String pre, int wkr, int ofWkr){
        
        int thisStart = startFrame;
        int thisEnd = endFrame;
        int frameCount = endFrame - startFrame;
        int workerHash = wkr * ofWkr;
        
        // check the method sharing work and set frame counts appropriately
        if (interlaced == 0)
        {
            switch (workerHash) {
                case 1:
                    break;
                    
                case 2:
                    thisStart = thisStart;
                    thisEnd = frameCount / 2;
                    break;
                case 4:
                    thisStart = frameCount / 2 + 1;
                    thisEnd = thisEnd;
                    break;
                case 3:
                    thisStart = thisStart;
                    thisEnd = frameCount / 3;
                    break;
                case 6:
                    thisStart = frameCount / 3 + 1;
                    thisEnd = thisEnd - frameCount / 3;
                    break;
                case 9:
                    thisStart = thisEnd - frameCount / 3 + 1;
                    thisEnd = thisEnd;
                    break;
            }
        } else {
            switch (workerHash) {
                case 1:
                    break;
                    
                case 2:
                    break;
                case 4:
                    thisStart += 1;
                    break;
                case 3:
                    break;
                case 6:
                    thisStart += 1;
                    break;
                case 9:
                    thisStart += 2;
                    break;
            }
        }
        
        return fontFormat + workerFormat + pre + endFontFormat +
        fontFormat + frameCountFormat +
        startFlag + Integer.toString(thisStart) +
        endFlag + Integer.toString(thisEnd) + endFontFormat +
        fontFormat + workerFormat +
        byFlag + Integer.toString(byFrame) +
        endFontFormat;
    }
    
    // returns the full, unformatted command string to be placed
    // in the clipboard. Takes the work and ofWorkers as arguments
    // reconstructs the full command in the return statement
    public String getCommand(int wkr, int ofWkr)
    {
        
        
        int thisStart = startFrame;
        int thisEnd = endFrame;
        int frameCount = thisEnd - thisStart;
        int workerHash = wkr * ofWkr;
        
        if (interlaced == 0)
        {
            switch (workerHash) {
                case 1:
                    break;
                    
                case 2:
                    thisStart = thisStart;
                    thisEnd = frameCount / 2;
                    break;
                case 4:
                    thisStart = frameCount / 2 + 1;
                    thisEnd = thisEnd;
                    break;
                case 3:
                    thisStart = thisStart;
                    thisEnd = frameCount / 3;
                    break;
                case 6:
                    thisStart = frameCount / 3 + 1;
                    thisEnd = thisEnd - frameCount / 3;
                    break;
                case 9:
                    thisStart = thisEnd - frameCount / 3 + 1;
                    thisEnd = thisEnd;
                    break;
            }
        } else {
            switch (workerHash) {
                case 1:
                    break;
                    
                case 2:
                    break;
                case 4:
                    thisStart += 1;
                    break;
                case 3:
                    break;
                case 6:
                    thisStart += 1;
                    break;
                case 9:
                    thisStart += 2;
                    break;
            }
        }
        
        return renderCmd +
        startFlag + Integer.toString(thisStart) +
        endFlag + Integer.toString(thisEnd) +
        byFlag + Integer.toString(byFrame) +
        resFlag + Integer.toString(xSize) + " " +
        Integer.toString(ySize) +
        shadeRateFlag + Integer.toString(shadeRate) +
        pixSampFlag + Integer.toString(pixSampX) + " " +
        Integer.toString(pixSampY) + "\" " +
        moblurFlag + Integer.toString(motionblur) +
        outputFlag + outputName +
        outputPathFlag + outputFile +
        inputFlag + inputFile;
    }
    
    // returns the full formatted command for display - leverages
    // all formatted command methods above - takes a prefix string
    // and worker / ofworker values.
    public String getFormattedCommand(String pre, int wkr, int ofWkr)
    {
        return fontFormat + cmdFormat + pre +
        renderCmd + endFontFormat +
        getFormattedWorkers("", wkr, ofWkr) +
        getFormattedImageSize("") +
        getFormattedImageQuality("") +
        getFormattedOutput("") +
        getFormattedInput("");
    }
    
    // Getters and Setters follow
    // set input
    public void setInput(String str){
        inputFile = str;
    }
    //get input file name
    public String getInputFile(){
        return "foobar.ma";
    }
    
    // set output
    public void setOutput(String str){
        outputFile = str;
    }
    
    // set start frame
    public void setStartFrame(int x){
        startFrame = x;
    }
    // get start frame
    public int getStartFrame(){
        return startFrame;
    }
    
    // set end frame
    public void setEndFrame(int x){
        endFrame = x;
    }
    // get end frame
    public int getEndFrame(){
        return endFrame;
    }
    
    // set by frame
    public void setByFrame(int x, int y){
        byFrame = x;
        interlaced = y;
    }
    // get by frame
    public int getByFrame(){
        return byFrame;
    }
    
    // set output x dimension
    public void setXSize(int x){
        xSize = x;
    }
    // get output x dimension
    public int getXSize(){
        return xSize;
    }
    
    // set output y dimension
    public void setYSize(int x){
        ySize = x;
    }
    // get output y dimension
    public int getYSize(){
        return ySize;
    }
    
    // shet shading rate
    public void setshadeRate(int x){
        shadeRate = x;
    }
    // get shading rate
    public int getshadeRate(){
        return shadeRate;
    }
    
    // set x pixel sample rate
    public void setPixSampX(int x){
        pixSampX = x;
    }
    // get x pixel sample rate
    public int getPixSampX(){
        return pixSampX;
    }
    
    // set y pixel sample rate
    public void setPixSampY(int x){
        pixSampY = x;
    }
    // get y pixel sample rate
    public int getPixSampY(){
        return pixSampY;
    }
    
    // set motion blur value
    public void setMotionblur(int x){
        motionblur = x;
    }
    // get motion blur value
    public int getMotionblur(){
        return motionblur;
    }
    
    // set input file name
    public void setInputName(String n)
    {
        inputName = n;
    }
    // get input file name
    public String getInputName()
    {
        return inputName;
    }
    
    // set output file name
    public void setOutputName(String n)
    {
        outputName = n;
    }
    
    // get output file name
    public String getOutputName()
    {
        return outputName;
    }
    
    
    // get the full output file name and extension
    public String getFullName()
    {
        return outputName + ".#.exr";
    }
    
    // set all values pertinent to a preset
    public void setPreset(int x, int y, int shad, int pixX, int pixY, int moblur )
    {
        xSize = x;
        ySize = y;
        shadeRate = shad;
        pixSampX = pixX;
        pixSampY = pixY;
        motionblur = moblur;
    }
    
}
