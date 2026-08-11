module dev.kameleonnn.onmark {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.logging;
    requires java.base;
    requires java.desktop;

    opens dev.kameleonnn.onmark to javafx.fxml;
    opens dev.kameleonnn.onmark.controller;
    exports dev.kameleonnn.onmark;
    exports dev.kameleonnn.onmark.controller;
}
