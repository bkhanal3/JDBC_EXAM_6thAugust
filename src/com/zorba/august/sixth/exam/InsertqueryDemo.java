/*  MCQ :
 * 1:D
 * 2:C
 * 3:B
 * 4:A
 * 5:D
 * 6:B
 * 7:C
 * 8:D
 * 9:B
 * 10:D
 * 
 */


package com.zorba.august.sixth.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsertqueryDemo {
	public static void main(String[] args) {

		Statement statement = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// Load the driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Create a Connection
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + "db_jdbc?useUnicode=true&useJDBCCompliantTimezoneShift="
							+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "Bisason12");

			statement = connection.createStatement();

			String query = "create table railway_reservastion(passenger_name varchar(25)	passenger_age int (15), choosen_seat varchar(25), reservation_type VARCHAR(25),is_senior_citizen boolean(false), amount_paid int(15))";
			statement.executeUpdate(query);
			System.out.println("Table Created Successfully.");

			// Execute Insert Query

			String insertQuery = "insert into railway_reservastion values(6, 'Dilli')";
			int count = statement.executeUpdate(insertQuery);
			System.out.println(count + " rows inserted!!");

			// fetch query
			ResultSet resultSet = statement.executeQuery("select * railway_reservastion");
			preparedStatement = connection.prepareStatement("select * from railway_reservastion");
			ResultSet resultSet1 = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String pName = resultSet.getString("passenger_name");
				System.out.println("Retrieved passange name :" + pName);

				int pAge = resultSet.getInt("passenger_age");
				System.out.println("Retrieved Student name : " + pAge);

				String chSeat = resultSet.getString("choosen_seat");
				System.out.println("Retrieved Student class : " + chSeat);

				String resType = resultSet.getString("reservation_type");
				System.out.println("Retrieved Student country : " + resType);

				boolean isSenior = resultSet.getBoolean("is_senior_citizen");
				System.out.println("Retrieved Student email : " + isSenior);
				int amtPaid = resultSet.getInt("amount_paid");
				System.out.println("Retrieved Student email : " + isSenior);
			}

			String insertQuery1 = "insert into railway_reservastion values(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(insertQuery1);

			int rowCount = preparedStatement.executeUpdate();
			System.out.println(rowCount + " rows Inserted!");

		} catch (Exception e) {
			System.err.println("Table not created Successfully...");
			System.err.println("Error Details : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				System.err.println("Error Details for closing connection::" + e.getMessage());
			}
		}
	}
}
