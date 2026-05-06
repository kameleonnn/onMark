module dev.kameleonnn.onmark {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens dev.kameleonnn.onmark to javafx.fxml;
    exports dev.kameleonnn.onmark;
}
