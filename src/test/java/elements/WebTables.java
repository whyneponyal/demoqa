package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class WebTables {
    public WebTables(String url) {
        Selenide.open(url);
    }

    public WebTables() {
    }

    private final SelenideElement addButton = $("#addNewRecordButton");
    private final ElementsCollection tableRows = $$(".rt-tbody .rt-tr:not(.-padRow)");

    public ElementsCollection getTableRows() {
        return tableRows;
    }

    public int countFilledRows() {
        int count = 0;

        for (SelenideElement row : tableRows) {
            ElementsCollection cells = row.$$(".rt-td");

            boolean isFilled = cells.stream()
                    .anyMatch(cell ->
                            !cell.getText().isEmpty() &&
                                    !cell.getText().equals(" ")
                    );

            if (isFilled) {
                count++;
            }
        }
        return count;
    }

    public void addUser(FakeUserData user) {
        addButton.click();
        fillForm(user);
        clickSubmit();
    }

    private void fillForm(FakeUserData user) {
        $("#firstName").setValue(user.getName());
        $("#lastName").setValue(user.getLastName());
        $("#userEmail").setValue(user.getEmail());
        $("#age").setValue(user.getAge());
        $("#salary").setValue(user.getSalary());
        $("#department").setValue(user.getDepartment());
    }


    public void removeRows(String... items) {
        for (String item : items) {
            SelenideElement userRow = tableRows.findBy(text(item));
            SelenideElement deleteButton = userRow.$(".action-buttons span[title='Delete']");
            deleteButton.shouldBe(clickable);
            deleteButton.click();
        }
    }

    public WebTables editRow(String item) {
        SelenideElement element = tableRows.findBy(text(item));
        element.$(".action-buttons span[title='Edit']").click();
        return new WebTables();
    }

    public WebTables editName(String value) {
        SelenideElement name = $("#firstName").setValue(value);
        return new WebTables();
    }

    public WebTables editLastName(String value) {
        SelenideElement name = $("#lastName").setValue(value);
        return new WebTables();
    }

    public WebTables editAge(String value) {
        SelenideElement name = $("#age").setValue(value);
        return new WebTables();
    }

    public WebTables editEmail(String value) {
        SelenideElement name = $("#userEmail").setValue(value);
        return new WebTables();
    }

    public WebTables editSalary(String value) {
        SelenideElement name = $("#salary").setValue(value);
        return new WebTables();
    }

    public WebTables editDepartment(String value) {
        SelenideElement name = $("#department").setValue(value);
        return new WebTables();
    }

    public WebTables clickSubmit() {
        $("#submit").click();
        return new WebTables();
    }

    public List<String> getInfoByName(String name) {
        ElementsCollection info = $$("div.rt-tr-group");
        String stringInfo = info.findBy(text(name)).getText();
        List<String> list = Arrays.asList(stringInfo.split("\\s+"));
        return list;
    }

    public void fillTable(Integer quantityUsers, Integer quantityRows) {
        for (int i = 0; i < quantityUsers; i++) {
            addUser(new FakeUserData());
            if ((i + 1) % quantityRows == 0 && (i + 1) < quantityUsers) {
                addUser(new FakeUserData());
                clickOnNext();
            }
        }
    }

    public Integer selectQuantityRows(Integer quantityRows) {
        String str = String.valueOf(quantityRows);
        SelenideElement pageSizeOptions = $x("//span[@class='select-wrap -pageSizeOptions']//option[@value='" + quantityRows + "']");
        pageSizeOptions.click();
        return quantityRows;
    }

    public void clickOnNext() {
        SelenideElement next = $("div.-next > button").shouldBe(clickable).shouldBe(enabled);
        next.click();
    }

    public void clearTable() {
        while (countFilledRows() > 0) {
            tableRows.first()
                    .$(".action-buttons span[title='Delete']")
                    .shouldBe(visible, enabled)
                    .click();
        }
    }

    public void goToFirstPage() {
        SelenideElement prevButton = $("div.-previous > button").shouldBe(clickable).shouldBe(enabled);
        while (getCurrentPageNumber() != 1) {
            prevButton.click();
        }
    }

    public int getCurrentPageNumber() {
        String pageText = $("div.-pageInfo > span").getText();
        return Integer.parseInt(pageText.split(" ")[0]);
    }

    public void search(String item) {
        SelenideElement search = $("#searchBox");
        search.setValue(item);
    }
}