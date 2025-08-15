package elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.Sleeper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class DynamicProperties {
    private final SelenideElement colorButton = $("#colorChange");
    private final SelenideElement afterDelay = $("#visibleAfter");

    public DynamicProperties(String url) {
        Selenide.open(url);
    }

    public String checkDisplay() {
        if (!afterDelay.exists()) {
            sleep(5000);
        }
        return afterDelay.getText();
    }

    public String getColorInfo() {
        return colorButton.getAttribute("class");
    }
}
