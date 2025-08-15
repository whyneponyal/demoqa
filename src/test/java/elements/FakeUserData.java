package elements;

import com.github.javafaker.Faker;

public class FakeUserData {
    private final Faker faker = new Faker();
    private final String name;
    private final String lastName;
    private final String email;
    private final String age;
    private final String salary;
    private final String department;

    public FakeUserData() {
        this.name = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = faker.internet().emailAddress();
        this.age = String.valueOf(faker.number().numberBetween(18, 65));
        this.salary = String.valueOf(faker.number().numberBetween(1000, 10000));
        this.department = faker.job().position();
    }

    public Faker getFaker() {
        return faker;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }
}
