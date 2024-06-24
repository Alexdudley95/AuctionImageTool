module com.example.auctionimagerenamer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.auctionimagerenamer to javafx.fxml;
    exports com.example.auctionimagerenamer;
}