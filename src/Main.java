import controllers.AuthController;
import database.PostgresDB;
import repositories.UserRepository;

public class Main {
    public static void main(String[] args) {
        String host = "jdbc:postgresql://localhost:5432";
        String username = "postgres";
        String password = "0000";
        String dbName = "quiz_app";

        PostgresDB db = new PostgresDB(host, username, password, dbName);

        UserRepository userRepo = new UserRepository(db);

        AuthController authController = new AuthController(userRepo);

        MyApplication app = new MyApplication(authController);
        app.start();

    }
}
