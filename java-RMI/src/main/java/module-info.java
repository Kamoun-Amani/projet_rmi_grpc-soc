module com.rmi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.rmi;
    requires javafx.swing;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires eu.hansolo.fx.countries;
    requires eu.hansolo.fx.heatmap;
    requires eu.hansolo.toolboxfx;
    requires eu.hansolo.toolbox;

    opens com.client to javafx.fxml;
    opens com.server to javafx.fxml;
    opens com.common to javafx.fxml;
    exports com.client;
    exports com.server;
    exports com.common;
}