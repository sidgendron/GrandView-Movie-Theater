module org.champlain.prog2.grandviewmovietheater {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.champlain.prog2.grandviewmovietheater to javafx.fxml;
    exports org.champlain.prog2.grandviewmovietheater;
}