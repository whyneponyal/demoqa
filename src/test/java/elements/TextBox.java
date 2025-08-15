package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class TextBox {
    private final SelenideElement nameField = $("#userName");
    private final SelenideElement emailField = $("#userEmail");
    private final SelenideElement currentAddressField = $("#currentAddress");
    private final SelenideElement permanentAddressField = $("#permanentAddress");

    private final ElementsCollection resultField = $$x("//div[@class='border col-md-12 col-sm-12']//p");

    public TextBox(String url) {
        Selenide.open(url);
    }

    public void fillFields(String name, String email, String currentAddress, String permanentAddress) {
        nameField.setValue(name);
        emailField.setValue(email);
        currentAddressField.setValue(currentAddress);
        permanentAddressField.setValue(permanentAddress);
        $("#submit").click();
    }

    public List<String> getInfo() {
        return resultField.texts();
    }

    public Map<String, String> splitList(List<String> list) {
        Map<String, String> user = new HashMap<>();
        for (String item : list) {
            String[] pairs = item.split(":", 2);
            String key = pairs[0].trim();
            String value = pairs[1].trim();
            user.put(key, value);
        }
        return user;
    }

    public static boolean validEmail(String email) {
        if (email.contains("@")) {
            if (email.endsWith(".ru") || email.endsWith(".com")) {
                return true;
            }
        }
        return false;
    }
}
