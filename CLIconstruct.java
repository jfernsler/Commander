import java.util.*;

public class CLIconstruct
{

    
    String inputFile;
    String outputFile;
    int startFrame;
    int endFrame;
    int byFrame;
    int xSize;
    int ySize;
    int minSamples;
    int maxSamples;
    double threshold;
    int motionblur;
    
    String inputFlag = " ";
    String outputFlag = " -im ";
    String startFlag = " -s ";
    String endFlag = " -e ";
    String byFlag = " -b ";
    String xSizeFlag = " -x ";
    String ySizeFlag = " -y ";
    String minSampFlag = " -minSamp ";
    String maxSampFlag = " -maxSamp ";
    String threshFlag = " -thresh ";
    String moblurFlag = " -motionbur ";
    
    String fontFormat ="<font face=\"sans-serif\" size=\"2\" ";
    
    String inputFormat = "color=\"BFB23A\">";
    String outputFormat = "color=\"2CB325\">";
    String frameCountFormat = "color=\"DB9496\">";
    String imageSizeFormat = "color=\"AC9BD6\">";
    String imageQualityFormat = "color=\"C57867\">";
    String workerFormat = "color=\"528586\">";
    String cmdFormat = "color=\"E1E1E1\">";
    
    String endFontFormat = "</font>";
    
    
    public CLIconstruct(String in, String out, int start, int end, int by,
                        int x, int y, int min, int max, double thresh, int moblur )
    {
        inputFile = in;
        outputFile = out;
        startFrame = start;
        endFrame = end;
        byFrame = by;
        xSize = x;
        ySize = y;
        minSamples = min;
        maxSamples = max;
        threshold = thresh;
        motionblur = moblur;
        
    }


    public String getFormattedInput(String pre){
        return fontFormat + inputFormat + pre +
        inputFlag + inputFile + endFontFormat;
    }
    
    public String getFormattedOutput(String pre){
        return fontFormat + outputFormat + pre +
        outputFlag + outputFile + endFontFormat;
    }
    
    public String getFormattedFrameCount(String pre){
        return fontFormat + frameCountFormat + pre +
        startFlag + Integer.toString(startFrame) +
        endFlag + Integer.toString(endFrame) +
        endFontFormat;
    }
    
    public String getFormattedImageSize(String pre){
        return fontFormat + imageSizeFormat + pre +
        xSizeFlag + Integer.toString(xSize) +
        ySizeFlag + Integer.toString(ySize) +
        endFontFormat;
        
    }
    
    public String getFormattedImageQuality(String pre){
        return fontFormat + imageQualityFormat + pre +
        minSampFlag + Integer.toString(minSamples) +
        maxSampFlag + Integer.toString(maxSamples) +
        threshFlag + Double.toString(threshold) +
        endFontFormat;
    }
    
    public String getFormattedWorkers(String pre){
        return fontFormat + workerFormat + pre + endFontFormat +
        fontFormat + frameCountFormat +
        startFlag + Integer.toString(startFrame) +
        endFlag + Integer.toString(endFrame) + endFontFormat +
        fontFormat + workerFormat +
        byFlag + Integer.toString(byFrame) +
        endFontFormat;
    }
    
    public String getCommand()
    {
        return "foo";
    }
    
    public String getFormattedCommand(String pre)
    {
        return fontFormat + cmdFormat + pre +
        "render -r mentalray" + endFontFormat +
        getFormattedWorkers("") +
        getFormattedImageSize("") +
        getFormattedImageQuality("") +
        getFormattedOutput("") +
        getFormattedInput("");
    }
    
    public void setInput(String str){
        inputFile = str;
    }

    public String getInputFile(){
        return "foobar.ma";
    }
    
    public void setOutput(String str){
        outputFile = str;
    }
    
    public void setStartFrame(int x){
        startFrame = x;
    }
    
    public int getStartFrame(){
        return startFrame;
    }
    
    public void setEndFrame(int x){
        endFrame = x;
    }
    
    public int getEndFrame(){
        return endFrame;
    }
    
    public void setByFrame(int x){
        byFrame = x;
    }
    
    public int getByFrame(){
        return byFrame;
    }

    public void setXSize(int x){
        xSize = x;
    }
    
    public int getXSize(){
        return xSize;
    }
    
    public void setYSize(int x){
        ySize = x;
    }
    
    public int getYSize(){
        return ySize;
    }
    
    public void setMinSamples(int x){
        minSamples = x;
    }
    
    public int getMinSamples(){
        return minSamples;
    }
    
    public void setMaxSamples(int x){
        maxSamples = x;
    }
    
    public int getMaxSamples(){
        return maxSamples;
    }
    
    public void setThreshold(double x){
        threshold = x;
    }
    
    public double getThreshold(){
        return threshold;
    }
    
    public void setMotionblur(int x){
        motionblur = x;
    }
    
    public int getMotionblur(){
        return motionblur;
    }

    
    
}