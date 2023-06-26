import java.util.*;
public class sem_4 {    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<User> users = new ArrayList<>();
    Map<Integer, List<Integer>> ageIndex = new TreeMap<>();
    Map<String, List<Integer>> genderAgeIndex = new TreeMap<>();

    while (true) {
        System.out.print("Введите ФИО (или \"выход\" для завершения): ");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("выход")) {
            break;
        }
        String[] parts = input.split("\\s+");
        if (parts.length < 3) {
            System.out.println("Неверный формат ввода!");
            continue;
        }
        String lastName = parts[0];
        String firstName = parts[1].substring(0, 1).toUpperCase() + ".";
        String middleName = parts[2].substring(0, 1).toUpperCase() + ".";
        int age;
        while (true) {
            System.out.print("Введите возраст: ");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода!");
            }
        }
        String gender;
        while (true) {
            System.out.print("Введите пол (м/ж): ");
            gender = scanner.nextLine().trim().toLowerCase();
            if (gender.equals("м") || gender.equals("ж")) {
                break;
            } else {
                System.out.println("Неверный формат ввода!");
            }
        }
        users.add(new User(lastName, firstName, middleName, age, gender));

        if (!ageIndex.containsKey(age)) {
            ageIndex.put(age, new ArrayList<>());
        }
        ageIndex.get(age).add(users.size() - 1);

        String genderAgeKey = gender + age;
        if (!genderAgeIndex.containsKey(genderAgeKey)) {
            genderAgeIndex.put(genderAgeKey, new ArrayList<>());
        }
        genderAgeIndex.get(genderAgeKey).add(users.size() - 1);
    }

    for (User user : users) {
        System.out.println(user.getFullName() + " " + user.getAge() + " " + user.getGender());
    }

    System.out.println("\nСписок пользователей, отсортированный по возрасту:");
    for (int age : ageIndex.keySet()) {
        for (int index : ageIndex.get(age)) {
            User user = users.get(index);
            System.out.println(user.getFullName() + " " + user.getAge() + " " + user.getGender());
        }
    }

    System.out.println("\nСписок пользователей, отсортированный по возрасту и полу:");
    for (String genderAgeKey : genderAgeIndex.keySet()) {
        for (int index : genderAgeIndex.get(genderAgeKey)) {
            User user = users.get(index);
            System.out.println(user.getFullName() + " " + user.getAge() + " " + user.getGender());
        }
    }
}
}

class User {
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final int age;
    private final String gender;

    public User(String lastName, String firstName, String middleName, int age, String gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.age = age;
        this.gender = gender;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
