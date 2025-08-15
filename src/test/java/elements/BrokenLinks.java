package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.CssValue;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class BrokenLinks {
    ElementsCollection links = $$("div:nth-child(2) > a ");
    ElementsCollection images = $$("div:nth-child(2) > img ");

    public BrokenLinks(String url) {
        Selenide.open(url);
    }

    public List<String> getSrc() {
        return images.stream().map(x -> x.getAttribute("src")).toList();
    }

    public Integer checkImages() {
        List<String> brokenImages = new ArrayList<>();

        for (SelenideElement img : images) {
            try {
                img.shouldBe(Condition.visible);

                String src = img.getAttribute("src");

                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("HEAD");
                connection.setConnectTimeout(5000);

                int statusCode = connection.getResponseCode();
//                if (statusCode >= 200 || statusCode < 400) {
//                    brokenImages.add(src);
//                }

                int width = Integer.parseInt(img.getCssValue("width").replace("px", ""));
                int height = Integer.parseInt(img.getCssValue("height").replace("px", ""));
                if (width > 16 && height > 16) {
                    brokenImages.add(img.getAttribute("src"));
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return brokenImages.size();
    }

    public Integer checkLink() {
        List<String> validLink = new ArrayList<>();

        for (SelenideElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                try {
                    URL url = new URL(href);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("HEAD");
                    connection.connect();

                    Integer statusCode = connection.getResponseCode();
                    if (statusCode >= 200 && statusCode < 400) {
                        validLink.add(href);
                    }
                } catch (Exception e) {
                    System.err.println("Failed: " + href);
                }
            }
        }
        return validLink.size();
    }
}
