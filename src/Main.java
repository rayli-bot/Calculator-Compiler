import java.util.Scanner;
import compile.Compiler;

public class Main {

    public static void main(String[] args) {
        System.out.println("========== Welcome to the Calculator! ==========");
        System.out.println("Type: '/?' for help ; 'exit' for exit");

        boolean running = true;
        while(running) {
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            // Trim
            input = input.replaceAll(" ", "");
            // Help Command
            if (input.length() == 0) {
                input = "/?";
            }
            if (input.matches("\\/\\?")) {
                System.out.println("Type: '/?' for help ; 'exit' for exit");
            }
            // Exit Command
            else if (input.matches("exit")) {
                running = false;
            }
            // Calculate
            else {
                Compiler compiler = new Compiler(input);
                String result = compiler.execute();
                System.out.println("= " + result);
            }
        }

        System.out.println("Bye!");
    }
}
