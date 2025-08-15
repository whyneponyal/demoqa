package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.*;

public class CheckBox {

    private final SelenideElement results = $("#result");
    private final SelenideElement expendDir = $("button.rct-option.rct-option-expand-all > svg");
    ElementsCollection elements = $$x("//div[@class='check-box-tree-wrapper']//ol//span[@class='rct-title']");


    public CheckBox(String url) {
        Selenide.open(url);
    }

    public void selectElement(String elementName) {
        expendDir.click();
        Optional<SelenideElement> selectedElement = elements.stream().filter(x -> x.getText().trim()
                .equals(elementName)).findFirst();
        selectedElement.get().click();
    }

    public List<String> selectElements(String... elementName) {
        for (String element : elementName) {
            selectElement(element);
        }
        return getResult();
    }

    public List<String> getResult() {
        return results.$$("span.text-success").texts();
    }

}
