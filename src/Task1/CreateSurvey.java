package Task1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class CreateSurvey {

    private final HashMap<String, Survey> surveys = new HashMap<>();


    public void start() {
        //instruction
        while (true) {
            System.out.println("\nCommands:\n" +
                    "create \"title\" - for creating new survey with title\n" +
                    "view \"title\"  show survey with title\n" +
                    "delete \"title\" delete survey with title\n" +
                    "list show all titles\n" +
                    "exit  close the program\n");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            String[] arrayString = command.split(" ");
            String commandName = arrayString[0];
            //check command
            String name = null;
            if (arrayString.length > 1) {
                name = command.substring(command.indexOf(' '));
            }

            if (Objects.equals(commandName, "create") && name != null) {
                create(name);
            } else if (Objects.equals(commandName, "view") && name != null) {
                view(name);

            } else if (Objects.equals(commandName, "delete") && name != null) {
                delete(name);

            } else if (Objects.equals(commandName, "list")) {
                list();

            } else if (Objects.equals(commandName, "exit")) {
                saveFile();
                break;
            } else {
                System.out.println("Error command");
            }
        }
    }

    //command methods
    private void create(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input question");
        String question = scanner.nextLine();
        System.out.println("Input amount answers");
        int amount = scanner.nextInt();
        String[] answers = new String[amount];

        for (int i = 0; i < amount; i++) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Input answer");
            answers[i] = scanner1.nextLine();
        }
        Survey survey = new Survey(amount, question, answers);
        surveys.put(name, survey);
    }

    private void view(String name) {
        System.out.println("Question: \n" + surveys.get(name).getQuestion());
        for (int i = 0; i < surveys.get(name).getAnswers().length; i++) {
            System.out.println((i + 1) + ") " + surveys.get(name).getAnswers()[i]);
        }
    }

    private void delete(String name) {
        surveys.remove(name);
    }

    private void list() {
        surveys.keySet().forEach(System.out::println);
    }

    //save surveys in txt file
    private void saveFile() {
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\user\\Desktop\\Task\\Surveys.txt")) {
            surveys.keySet().forEach(s -> {
                try {
                    fileWriter.write("\n" + s + "\n");
                    fileWriter.write(surveys.get(s).getQuestion() + "\n");
                    Arrays.stream(surveys.get(s).getAnswers()).forEach(a -> {
                        int count = 1;
                        try {
                            fileWriter.write((count++) + ") " + a + "\n");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
