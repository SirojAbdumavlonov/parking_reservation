module parking {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens service to javafx.fxml;
    exports service;
    exports controller;
    opens controller to javafx.fxml;
    exports repo;
    opens repo to javafx.fxml;
    exports frame;
    opens frame to javafx.fxml;
    exports entity;
    opens entity to javafx.fxml;
}