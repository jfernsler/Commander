/*
 * Jeremy Fernsler
 * jf575@drexel.edu
 * CS530:DUI, Term Project
 *
 */


// HelpStrings is a class which contains all of the strings for
// the tooltip help buttons on the UI.
public class HelpStrings
{

    // html formatting tags to create the dark bg, san-sarif fonts and the
    // proper font size. The color #E1E1E1 is used for all non-argument
    // based text. Nested font definitions bring additional colors
    String startFormat ="<html><body bgcolor=\"444444\"><br>"+
    "<font face=\"sans-serif\" size=\"2\" color=\"E1E1E1\">";
    String endFormat = "</font><br></body></html>";

    // Empty constructor
    public HelpStrings()
    {
        // intentionally empty.
    }
    
    // returns the help string for the input and output section
    public String getIOHelpString()
    {
        return startFormat +
        "The <font color=\"BFB23A\">input file path</font> is the last item to be appended<br>" +
        "to the command string and has no flags associated<br><br>" +
        "The output command consists of several flags: <ul>" +
        "<li><font color=\"2CB325\"><b>-fnc <i>file name commmand</i></b></font> is the format of the file name.</li>" +
        "<li><font color=\"2CB325\"><b>-of <i>output format</i></b></font> is the type of image file.</li>" +
        "<li><font color=\"2CB325\"><b>-pad <i>number padding</i></b></font> " +
        "how many digits the file numbering uses.</li>" +
        "<li><font color=\"2CB325\"><b>-im <i>image name</i></b></font> the name to be used." +
        "<li><font color=\"2CB325\"></b>-rd <i>render directory</i></b></font> " +
        "this is the path where the files will land.</li></ul>" +
        endFormat;
    }

    // returns the help string for the frame count section
    public String getFCHelpString()
    {
        return startFormat +
        "The frame count consists of 2 flags: <ul>" +
        "<li><font color=\"DB9496\"><b>-s <i>start frame</i></b></font> is the first frame of your sequence.</li>" +
        "<li><font color=\"DB9496\"><b>-e <i>end frame</i></b></font> is the final frame of your sequence.</li></ul>" +
        "the start and end frame will be the same for<br>" +
        "single frame renders.<br>" +
        endFormat;
    }
    
    // returns the help string for the presets section
    public String getPresetsHelpString()
    {
        return startFormat +
        "These presets are here to give you a quick start into<br>" +
        "tailoring your rendering. There are 3 presets:<ul>" +
        "<li><b>Draft</b> will give you fast rendering to judge rough<br>" +
        "aspect of lighting and motion at a low resolution.</li>" +
        "<li><b>Good</b> gets you closer to your final render size and <br>" +
        "renders cleaner for judgment of textures and finer<br>details.</li>" +
        "<li><b>Final</b> implements a full HD resolution render with<br>" +
        "fine anti-aliasing and sampling enabled.</li></ul>" +
        "The settings are propagated through the panel, watch their<br>" +
        "changes for insights on how to override them for your needs.<br>" +
        endFormat;
    }
    
    // returns the help string for the image size section
    public String getISHelpString()
    {
        return startFormat +
        "Adjust your output image size here for your needs.<br><br>" +
        "The <font color=\"AC9BD6\"><b>-res <i>\"X Y\" </i></b></font> flag handles setting the<br>" +
        "rendered image resolution. 3 choices have been provided for<br>" +
        "consistancy and ease:<ul>" +
        "<li><b>1920x1080</b> for full resolution broadcast size." +
        "<li><b>1280x720</b> is a good size for judging finer details<br>" +
        " before final render.</li>" +
        "<li><b>720x405</b> is a fairly low resolution for a early and fast pass<br>" +
        "at judging animation and lighting.</li></ul>" +
        endFormat;
    }
    
    // returns the help string for the override quality section
    public String getOQHelpString()
    {
        return startFormat +
        "Overrides some of the settings as necessary. after<br>" +
        "watching some change with presets, tweak to your needs.<br><br>" +
        "These overrides cover the following commands: <ul>" +
        "<li><font color=\"C57867\"><b>-setAttr ShadingRate <i>num</i></b></font> " +
        "- the higher the number, the faster,<br>" +
        "although grainer the render will be.</li>" +
        "<li><font color=\"C57867\"><b>-setAttr PixelSamples <i>\"X Y\"</i></b></font> " +
        "- a value of 1 in both X and Y will<br>" +
        "essentially turn of anti-alising, higher numbers produce smoother <br>" +
        "images edges and textures.</li>" +
        "<li><font color=\"C57867\"><b>-setAttr motionBlur <i>1 or 0</i></b></font> " +
        "- activates or deactivates motion blur.</li>" +
        endFormat;
    }
    
    // returns the help string for the worker section - this one is important
    // as it needs to further re-enforce the concept of what is happening
    public String getWKHelpString()
    {
        return startFormat +
        "You can speed up renders by spreading them across multiple computers. <br>" +
        "In this setup there are 2 options for utilizing up to 3 computers. First <br>" +
        "choose how many computers you will be using (1-3) and then choose the method<br>" +
        "of splitting frames between them:<ul>" +
        "<li><b>Straight</b> is easy to understand and gives equal sections of frames to each<br>" +
        "computer to render. This works well for sequences without much variation in the image.<br>" +
        "<ul><li>The <font color=\"C57867\"><b>-b <i>1</i></b></font> flag tells the renderer to advance one frame<br>" +
        "each time, giving each computer a straight sequence.</li></ul></li>" +
        "<li><b>Interlaced</b> is better for many sequences when there are parts which require<br>" +
        "more render time than others. This method will effectively share all computing needs<br>" +
        "equally and is typically the more ideal method for rendering." +
        "<ul><li>The <font color=\"C57867\"><b>-b <i>X</i></b></font> flag tells the skip <i>X</i> number of frames<br>" +
        "each time while the adjustement to the <font color=\"DB9496\"><b>-s <i>start frame</i></b></font> flag<br>" +
        "interlaces the entire sequence across multiple machines.</li></ul></li>" +
        endFormat;
    }
    
}