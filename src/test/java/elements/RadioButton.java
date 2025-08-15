package elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class RadioButton {
    public RadioButton(String url) {
        Selenide.open(url);
    }

    private final SelenideElement result = $("#app > div > div > div > div.col-12.mt-4.col-md-6 > div:nth-child(3) > p");

    public String getReply() {
        return result.getText();
    }

    public void pick(SelenideElement element) {
        element.click();
        getReply();
    }
}
