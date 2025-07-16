import javax.swing.*;
import controller.Login_Controller;
import view.Login_View;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login_View loginView = new Login_View();
            new Login_Controller(loginView);
            loginView.setVisible(true); // On ne montre que la fenêtre login au début
        });
    }
}
