package elements;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;

public class UploadDownload {
    public UploadDownload(String url) {
        Selenide.open(url);
    }

    private final SelenideElement downloadButton = $("#downloadButton");
    private final SelenideElement uploadButton = $("#uploadFile");
    private final SelenideElement resultPath = $("#uploadedFilePath");

    public File downloadFile() {
        File fileDir = new File(Configuration.downloadsFolder);
        try {
            FileUtils.cleanDirectory(fileDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return downloadButton.download();
    }

    public void uploadFile() {
        File textFile = new File("src/main/resources/file.txt");
        uploadButton.uploadFile(textFile);
    }

    public String getResultPath() {
        return resultPath.getText();
    }
}
