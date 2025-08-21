package elements;

import forms.PracticeForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UiTest extends BaseTest {
    private final String TEXT_BOX_URL = "https://demoqa.com/text-box";
    private final String CHECK_BOX_URL = "https://demoqa.com/checkbox";
    private final String RADIO_BUTTON_URL = "https://demoqa.com/radio-button";
    private final String WEB_TABLE_URL = "https://demoqa.com/webtables";
    private final String BUTTONS_URL = "https://demoqa.com/buttons";
    private final String DOWNLOAD_UPLOAD_URL = "https://demoqa.com/upload-download";
    private final String DYNAMIC_PROPERTIES_URL = "https://demoqa.com/dynamic-properties";
    private final String PRACTICE_FORM_URL = "https://demoqa.com/automation-practice-form";

    @Test
    @DisplayName("Проверка валидности email адреса на странице ввода текста")
    void validEmailCheckTest() {
        var textBox = new TextBox(TEXT_BOX_URL);

        textBox.fillFields("Name", "fsda341@mail.com", "VRN", "MSK");
        List<String> userList = textBox.getInfo();
        Map<String, String> userMap = textBox.splitList(userList);

        assertTrue(textBox.validEmail(userMap.get("Email")), "Invalid Email address!");
    }

    @Test
    @DisplayName("Проверка выбора элементов на странице чекбоксов")
    void selectedCheckboxElementsTest() {
        var checkBoxPage = new CheckBox(CHECK_BOX_URL);

        checkBoxPage.selectElements("Notes");
        Assertions.assertTrue(checkBoxPage.getResult().contains("notes"));
    }

    @Test
    @DisplayName("Тест взаимодействия с радиокнопками")
    void radioButtonsTest() {
        var radioButton = new RadioButton(RADIO_BUTTON_URL);

        radioButton.pick($("div:nth-child(3) > label"));
        radioButton.pick($("div:nth-child(2) > label"));

        $("#noRadio").shouldBe(disabled);
        Assertions.assertTrue(radioButton.getReply().contains("Yes"));
    }


    @Test
    @DisplayName("Проверка работы таблицы")
    public void webTableTest() {
        WebTables webTables = new WebTables(WEB_TABLE_URL);

        webTables.clearTable();

        Integer quantityRows = webTables.selectQuantityRows(20);
        webTables.fillTable(10, quantityRows);
        Assertions.assertEquals(10, webTables.countFilledRows());

        webTables.editRow($x("//div[@class='rt-tr-group'][3]//div//div[1]")
                .getText()).editName("Yan").clickSubmit();
        webTables.search("Yan");

        Assertions.assertEquals("Yan", $x("//div[@class='rt-tr-group'][1]//div//div[1]").getText());
    }

    @Test
    @DisplayName("Тест кликов по кнопкам")
    public void clickTest() {
        Buttons buttons = new Buttons(BUTTONS_URL);
        buttons.doubleClickCheck();
        buttons.rightClickCheck();
        buttons.oneCLickCheck();
        buttons.oneCLickCheck();
        Assertions.assertEquals(2, buttons.getResult().size());
    }

    @Test
    @DisplayName("Тестирование ссылок")
    public void linksTest() {
        Links links = new Links("https://demoqa.com/links");

        Assertions.assertEquals(201, links.getStatusCode("Created"));

        for (String link : links.openLinks()) {
            Assertions.assertTrue(link.contains("https://demoqa.com/"));
        }
    }

    @Test
    @DisplayName("Проверка валдиных ссылок и картинок")
    public void checkLinksAndImgsTest() {
        BrokenLinks brokenLinks = new BrokenLinks("https://demoqa.com/broken");

        Assertions.assertEquals(1, brokenLinks.checkLink());

        Assertions.assertEquals(1, brokenLinks.checkImages());
    }

    @Test
    @DisplayName("Тест на скачивание и загрузку файла")
    public void downloadUploadTest() {
        UploadDownload uploadDownload = new UploadDownload(DOWNLOAD_UPLOAD_URL);

        File file = uploadDownload.downloadFile();
        Assertions.assertTrue(file.exists());
        Assertions.assertEquals("sampleFile.jpeg", file.getName());

        uploadDownload.uploadFile();
        Assertions.assertEquals("C:\\fakepath\\file.txt", uploadDownload.getResultPath());
    }

    @Test
    @DisplayName("Тест на ожидание отображения элементов")
    public void waitDisplayTest() {
        DynamicProperties dynamicProperties = new DynamicProperties(DYNAMIC_PROPERTIES_URL);

        String expectedStr = "Visible After 5 Seconds";
        Assertions.assertEquals(expectedStr, dynamicProperties.checkDisplay());
        Assertions.assertTrue(dynamicProperties.getColorInfo().contains("mt-4 text-danger btn btn-primary"));
    }

    @Test
    @DisplayName("Тест заполнения формы")
    public void fillFormTest(){
        PracticeForm practiceForm = new PracticeForm(PRACTICE_FORM_URL);
        List<String> subjects = new ArrayList<>();
        subjects.add("Math");
        subjects.add("Economics");
        subjects.add("English");
        subjects.add("Physics");

        practiceForm.fillForm("Male", "fs;hfldhglagh", subjects);
        int i = 0;
    }
}