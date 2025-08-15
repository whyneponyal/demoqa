package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Links {
    public Links(String url) {
        Selenide.open(url);
    }

    private ElementsCollection links = $$x("//p/a[@target]");
    private ElementsCollection apiLinks = $$x("//p/a");

    public List<String> openLinks() {
        return links.stream().map(x -> x.getAttribute("href")).toList();
    }

    public String getResponse(String linkName) {
        apiLinks.findBy(text(linkName)).click();
        return $("#linkResponse").text();
    }

    public Integer getStatusCode(String link) {
        apiLinks.findBy(text(link)).click();
        return Integer.valueOf($x("//p/b[1]").text());
    }

    public String getStatusText(String link) {
        apiLinks.findBy(text(link)).click();
        return $x("//p/b[2]").text();
    }

    public String getInfo(String link) {
        return String.format("Response: " + getResponse(link) + "\n"
                + "Status code: " + getStatusCode(link) + "\n"
                + "Status text: " + getStatusText(link));
    }
}
