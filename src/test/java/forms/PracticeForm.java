package forms;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {
    Faker faker = new Faker();
    private final SelenideElement name = $("#firstName");
    private final SelenideElement lastName = $("#lastName");
    private final SelenideElement email = $("#userEmail");
    private final ElementsCollection genders = $$("#genterWrapper > div.col-md-9.col-sm-12");
    private final SelenideElement phoneNumber = $("#userNumber");
    private final SelenideElement dateOfBirth = $("#dateOfBirthInput");
    private final SelenideElement subjectsField = $("#subjectsInput");
    private final ElementsCollection hobbies = $$("#hobbiesWrapper > div.col-md-9.col-sm-12");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddress = $("#currentAddress");

    public PracticeForm(String url) {
        Selenide.open(url);
    }

    public void fillForm(String target, String text, List<String> subjectsList){
        name.setValue(faker.name().firstName());
        lastName.setValue(faker.name().lastName());
        email.setValue(faker.internet().emailAddress());
        $("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(1) > label").click();
        phoneNumber.setValue(String.valueOf(faker.phoneNumber()));
        dateOfBirth.setValue("19 11 2000");
        selectSubjects(String.valueOf(subjectsList));
    }

    public void selectSubjects(String ...subjects){
        for(String subject : subjects){
            subjectsField.setValue(subject).sendKeys(Keys.ENTER);
        }
    }
}
