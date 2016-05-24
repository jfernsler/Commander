import java.util.*;

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

    String renderCmd = "render -r rman";
    
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

    public String getFormattedInput(String pre){
        return fontFormat + inputFormat + pre +
        inputFlag + inputFile +
        endFontFormat;
    }

    public String getFormattedOutput(String pre){
        return fontFormat + outputFormat + pre +
        outputFlag + outputName +
        outputPathFlag + outputFile +
        endFontFormat;
    }

    public String getFormattedFrameCount(String pre){
        return fontFormat + frameCountFormat + pre +
        startFlag + Integer.toString(startFrame) +
        endFlag + Integer.toString(endFrame) +
        endFontFormat;
    }

    public String getFormattedImageSize(String pre){
        return fontFormat + imageSizeFormat + pre +
        resFlag + Integer.toString(xSize) + " " +
        Integer.toString(ySize) +
        endFontFormat;

    }

    public String getFormattedImageQuality(String pre){
        return fontFormat + imageQualityFormat + pre +
        shadeRateFlag + Integer.toString(shadeRate) +
        pixSampFlag + Integer.toString(pixSampX) + " " +
        Integer.toString(pixSampY) + "\" " +
        moblurFlag + Integer.toString(motionblur) +
        endFontFormat;
    }

    public String getFormattedWorkers(String pre, int wkr, int ofWkr){
        
        int thisStart = startFrame;
        int thisEnd = endFrame;
        int frameCount = endFrame - startFrame;
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
        
        return fontFormat + workerFormat + pre + endFontFormat +
        fontFormat + frameCountFormat +
        startFlag + Integer.toString(thisStart) +
        endFlag + Integer.toString(thisEnd) + endFontFormat +
        fontFormat + workerFormat +
        byFlag + Integer.toString(byFrame) +
        endFontFormat;
    }

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

    public void setByFrame(int x, int y){
        byFrame = x;
        interlaced = y;
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

    public void setshadeRate(int x){
        shadeRate = x;
    }

    public int getshadeRate(){
        return shadeRate;
    }

    public void setPixSampX(int x){
        pixSampX = x;
    }

    public int getPixSampX(){
        return pixSampX;
    }

    public void setPixSampY(int x){
        pixSampY = x;
    }

    public int getPixSampY(){
        return pixSampY;
    }

    public void setMotionblur(int x){
        motionblur = x;
    }

    public int getMotionblur(){
        return motionblur;
    }

    public void setInputName(String n)
    {
      inputName = n;
    }

    public String getInputName()
    {
      return inputName;
    }

    public void setOutputName(String n)
    {
      outputName = n;
    }

    public String getOutputName()
    {
      return outputName;
    }
    
    public String getFullName()
    {
        return outputName + ".#.exr";
    }

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
