import service.SodokuService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if ((args.length == 0) || (args[0] == null) || (args[0].isEmpty())) {
            throw new RuntimeException("Please pass file path as first argument");
        }

        SodokuService sodokuService = new SodokuService();

        System.out.println(sodokuService.validate(sodokuService.getPuzzleFromFile(args[0])));
    }
}
