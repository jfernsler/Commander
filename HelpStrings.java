

public class HelpStrings
{

    String fontFormat ="<html><font face=\"sans-serif\" size=\"2\">";

    String inputFormat = "color=\"BFB23A\">";
    String outputFormat = "color=\"2CB325\">";
    String frameCountFormat = "color=\"DB9496\">";
    String imageSizeFormat = "color=\"AC9BD6\">";
    String imageQualityFormat = "color=\"C57867\">";
    String workerFormat = "color=\"528586\">";
    String cmdFormat = "color=\"E1E1E1\">";

    String endFontFormat = "</font></html>";

    public HelpStrings()
    {

    }

    public String getIOHelpString()
    {
        return fontFormat +
        "The input file path is the last item to be appended<br>" +
        "to the command string and has no flags associated<br><br>" +
        "The output command consists of several flags: <ul>" +
        "<li>-fnc <i>file name commmand</i> is the format of the file name</li>" +
        "<li>-of <i>output format</i> is the type of image file</li>" +
        "<li>-pad <i>number padding</i> how many digits the file numbering uses</li>" +
        "<li>-im <i>image name</i> the name to be used" +
        "<li>-rd <i>render directory</i> this is the path where the files will land</li></ul>" +
        endFontFormat;
    }

    public String getFCHelpString()
    {
        return fontFormat +
        "The frame count consists of 2 flags: <ul>" +
        "<li>-s <i>start frame</i> is the first frame of your sequence</li>" +
        "<li>-e <i>end frame</i> is the final frame of your sequence</li></ul>" +
        "the start and end frame will be the same for<br>" +
        "single frame renders." +
        endFontFormat;
    }
    
    public String getPresetsHelpString()
    {
        return fontFormat +
        "These presets are here to give you a quick start into<br>" +
        "tailoring your rendering. There are 3 presets:<ul>" +
        "<li><i>Draft</i> will give you fast rendering to judge rough<br>" +
        "aspect of lighting and motion at a low resolution</li>" +
        "<li><i>Good</i> gets you closer to your final render size and <br>" +
        "renders cleaner for judgment of textures and finer details</li>" +
        "<li><i>Final</i> implements a full HD resolution render with<br>" +
        "fine anti-aliasing and sampling enabled</li></ul>" +
        "The settings are propagated through the panel, watch their<br>" +
        "changes for insights on how to override them for your needs." +
        endFontFormat;
    }
    
    public String getISHelpString()
    {
        return fontFormat +
        "Adjust your output image size here for your needs<ul>" +
        "<li><i>1920x1080</i> for full resolution broadcast size." +
        "<li><i>1280x720</i> is a good size for judging finer details<br>" +
        " before final render.</li>" +
        "<li><i>720x405</i> is a fairly low resolution for a early and fast pass<br>" +
        "at judging animation and lighting</li></ul>" +
        endFontFormat;
    }
    
    public String getOQHelpString()
    {
        return fontFormat +
        "Overrides some of the settings as necessary. after<br>" +
        "watching some change with presets, tweak to your needs.<br><br>" +
        "These overrides cover the following commands: <ul>" +
        "<li>-setAttr ShadingRate <i>num</i> - the higher the number, the faster and<br>" +
        "grainer the render will be.</li>" +
        "<li>-setAttr PixelSamples <i>\"X Y\"</i> - a value of 1 in both X and Y will<br>" +
        "essentially turn of anti-alising, higher numbers produce smoother <br>" +
        "images edges and textures</li>" +
        "<li>-motionBlur <i>1 or 0</i> - activates or deactivates motion blur.</li>" +
        endFontFormat;
    }
    
    public String getWKHelpString()
    {
        return fontFormat +
        "You can speed up renders by spreading them across multiple computers. <br>" +
        "In this setup there are 2 options for utilizing up to 3 computers. First <br>" +
        "choose how many computers you will be using (1-3) and then choose the method<br>" +
        "of splitting frames between them<br><br>" +
        "<b>Straight</b> is easy to understand and gives equal sections of frames to each<br>" +
        "computer to render. This works well for sequences without much variation.<br><br>" +
        "<b>Interlaced</b> is better for many sequences when there are parts which require<br>" +
        "more render time than others. This method will effectively share all computing needs<br>" +
        "equally and is typically the more ideal method for rendering." +
        endFontFormat;
    }

}