package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class Buttons {
    public Buttons(String url) {
        Selenide.open(url);
    }

    private final SelenideElement doubleClick = $("#doubleClickBtn");
    private final SelenideElement rightClick = $("#rightClickBtn");
    private final SelenideElement oneClick = $x("//div[@class='mt-4'][2]");
    private final ElementsCollection result = $$x("//p[@id]");

    public void doubleClickCheck() {
        doubleClick.doubleClick();
    }

    public void rightClickCheck() {
        rightClick.contextClick();
    }

    public void oneCLickCheck() {
        oneClick.click();
    }

    public List<String> getResult() {
        return result.texts();
    }
}
