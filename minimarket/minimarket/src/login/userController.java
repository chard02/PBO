package login;

import login.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class userController implements Initializable {
    
    public class DatabaseConnection {
     public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/minimarket";
        String user = "root";
        String password = "";

        Connection connection = DriverManager.getConnection(url, user, password);

        if (connection != null) {
            System.out.println("Connected to the database!");
        } else {
            System.err.println("Failed to connect to the database.");
        }

        return connection;
    }
}
     @FXML
    private Label lblJmlQty;

    @FXML
    private TableView<?> tblList;

    @FXML
    private TextField txtDiskon;

    @FXML
    private TextField txtFaktur;

    @FXML
    private TextField txtHargaJual;

    @FXML
    private TextField txtHasilDiskon;

    @FXML
    private TextField txtHasilPPN;

    @FXML
    private TextField txtJumlahHarga;

    @FXML
    private TextField txtKodeBarang;

    @FXML
    private TextField txtNamaBarang;

    @FXML
    private TextField txtQTY;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtTotalHarga;

    @FXML
    private Button logout_btn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void logoutBtn() {

        try {
            {
                Parent root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
