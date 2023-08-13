import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static Boolean getRandom(int a){
        Random random = new Random();
        int number = (random.nextInt(0,101));        //0% - 100%
        if (number <= a){
            return true;
        }else {
            return false;
        }
    }

    public static String replace(String json, int a) {
        Pattern pattern = Pattern.compile("\\b(true|false)\\b");
        Matcher matcher = pattern.matcher(json);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String replacement = getRandom(a).toString();
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);

        return result.toString();
    }
    public static String readFileToString(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

    public static void main(String[] args) {
        try {
            String json = readFileToString( "src/Salon/salon.json");
                json = replace(json,80);
                System.out.println(json);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Salon/salon.json"))) {
                writer.write(json);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}