module org.champlain.prog2.grandviewmovietheater {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;


    opens org.champlain.prog2.grandviewmovietheater to javafx.fxml;
    exports org.champlain.prog2.grandviewmovietheater;
}