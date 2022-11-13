import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static int main(String args[]) throws FileNotFoundException {
        String fileName = args[0];
        SubString reader = new SubString();
        reader.subStr = args[1];
        File in = new File(fileName);
        Scanner scanner = new Scanner(in);
        int res = -1;

        while(scanner.hasNextLine()) {
            String text = scanner.nextLine();
            reader.str = text;
            res = reader.checkForSubString();
            if (res != -1) break;
        }
        return res;
    }
}
