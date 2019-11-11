package oraclejavasoapkubernetes;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
// ATP Library - 2019-08-07
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;
import oraclejavasoapkubernetes.*;
import oracle.jdbc.OracleConnection;
import java.sql.DatabaseMetaData;

@RestController
public class StudentController {

	public static String msg = System.getenv("MSG");

	final static String DB_URL = "jdbc:oracle:thin:@atp_tpurgent?TNS_ADMIN=/pipeline/source/target/classes/wallet_atp";
	final static String DB_USER = "admin";
	final static String DB_PASSWORD = "Oracle123456";

	public String template = msg + ", %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/newStudent")
	public Student newStudent(@RequestParam(value = "name", defaultValue = "World") String name) {

		Properties info = new Properties();
		info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
		info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
		info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");

		// Call to ATP
		try {
			OracleDataSource ods = new OracleDataSource();

			if (true) {
				ods.setURL(DB_URL);
				ods.setConnectionProperties(info);

				// With AutoCloseable, the connection is closed automatically.
				try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
					// Get the JDBC driver name and version
					DatabaseMetaData dbmd = connection.getMetaData();
					// Perform a database operation
					createStudent(connection, name);
					// createEmployees(connection);
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@RequestMapping("/findStudent")
	public Student findStudent(@RequestParam(value = "name", defaultValue = "World") String name) {

		Student student = new Student();
		if (true) {

			Properties info = new Properties();
			info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
			info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
			info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");

			// Call to ATP
			try {
				OracleDataSource ods = new OracleDataSource();

				if (true) {
					ods.setURL(DB_URL);
					ods.setConnectionProperties(info);

					// With AutoCloseable, the connection is closed automatically.
					try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
						// Get the JDBC driver name and version
						DatabaseMetaData dbmd = connection.getMetaData();
						// Perform a database operation
						student = printStudent(connection, name);
						// createEmployees(connection);
					}

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return student;
	}

	// ATP SOURCE-CODE
	public static Student printStudent(Connection connection, String studentName) throws SQLException {
		// Statement and ResultSet are AutoCloseable and closed automatically.
		Student student = new Student();
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery("select first_name, last_name from students where studentName = '" + studentName + "'")) {
				System.out.println("FIRST_NAME" + "  " + "LAST_NAME");
				System.out.println("---------------------");
				while (resultSet.next())
					System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " ");
					student.setName(resultSet.getString(1));
					student.setLastName(resultSet.getString(2));
					return student;
			}
		}
	}

	public static void createStudent(Connection connection, String name) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement
					.executeQuery("create table students (first_name varchar(255), last_name varchar(255))")) {
				System.out.println("table students created");
				System.out.println("---------------------");
			}
			try (ResultSet resultSet = statement
					.executeQuery("insert into students (first_name, last_name) values {'" + name + "')")) {
				System.out.println("student inserted");
				System.out.println("---------------------");
			}
		}
	}
}