module com.example.tcrs_group8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.tcrs_group8 to javafx.fxml;
    exports com.example.tcrs_group8;
    exports com.example.tcrs_group8.Services;
    opens com.example.tcrs_group8.Services to javafx.fxml;
    opens com.example.tcrs_group8.Contollers to javafx.fxml;
    exports com.example.tcrs_group8.Contollers;
}