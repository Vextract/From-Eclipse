package root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class Operations {

    public static void main(String[] args) throws SQLException, IOException {
        SpringApplication.run(Operations.class, args);
    }
}
