import java.io.File;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class MangaProjectGen {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MangaProjectGen() {
    }

    /**
     * Write the header fo the html file to index.
     *
     * @param file
     */
    private static void outputHeader(SimpleWriter file) {
        file.println("<html>");
        file.println("<head>");
        file.println("    <link rel=\"stylesheet\" href=\"style.css\">");
        file.println(
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        file.println("</head>");
        file.println("<header>");
        file.println("    <a class=\"kanji\"></a>");
        file.println("</header>");

    }

    /**
     *
     * @param file
     * @param fileNames
     */
    private static void outputBody(SimpleWriter file, Stack<String> fileNames) {
        //start body
        file.println("<body>");
        file.println("    <div class=\"content\">");
        file.println("        <div class=\"images\">");
        //cycle through and print all files
        while (fileNames.length() > 0) {
            //get name to use in spots
            String name = "panels/" + fileNames.pop();
            file.println("            <a href = \"" + name + "\">");
            file.println("                <img src = \"" + name + "\">");
            file.println("            </a>");
        }

        //output closings and scripts
        file.println("        </div>");
        file.println("    </div>");
    }

    /**
     *
     * @param file
     */
    private static void outputRemainder(SimpleWriter file) {
        file.println("<div class=\"clearfix\"></div>");
        file.println("<!--scripts -->");
        file.println(
                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js\"></script>\r\n");
        file.println("<script>");
        file.println("    //Random bg color");
        file.println("    var hue = Math.floor(Math.random() * 360);");
        file.println("    var pastel = 'hsl(' + hue + ', 100%, 87.5%)';");
        file.println("    $('html').css('background', pastel);");
        file.println("</script>");
        file.println("</body>");
        file.println("</html>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //create file name
        String fileDestination = "F:\\Code\\New Manga Project\\mangaproject\\";
        String fileName = "index.html";
        SimpleWriter file = new SimpleWriter1L(fileDestination + fileName);
        //output header

        //File stuff
        File directory = new File(
                "F:\\Code\\New Manga Project\\mangaproject\\panels");
        int fileCount = directory.list().length;

        out.println("File Count: " + fileCount);
        File[] files = directory.listFiles();
        Stack<String> fileNames = new Stack1L<>();

        for (File f : files) {
            fileNames.push(f.getName());
            //out.println(f.getName());
        }

        outputHeader(file);
        outputBody(file, fileNames);
        outputRemainder(file);

        in.close();
        out.close();
    }

}
