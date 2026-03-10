module lk.ijse.nilmini_stitch_studio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens lk.ijse.nilmini_stitch_studio.controller to javafx.fxml;
    opens lk.ijse.nilmini_stitch_studio.dao to javafx.fxml;
    opens lk.ijse.nilmini_stitch_studio.entity to javafx.fxml;
    exports lk.ijse.nilmini_stitch_studio;
    exports lk.ijse.nilmini_stitch_studio.controller;
    exports lk.ijse.nilmini_stitch_studio.dto;
    exports lk.ijse.nilmini_stitch_studio.dao;
    exports lk.ijse.nilmini_stitch_studio.entity;
}
