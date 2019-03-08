package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DAO class specifies methods for interacting with the database.
 * 
 * @version 1.0
 * @author unidatavis
 *
 */
public class DAO {
	
	/**
	 * Gets the connection to the sqlite database.
	 * 
	 * @return Connection to the database
	 */
	private static Connection getConnection() {
		Connection c = null;
		try {
			// Sets the type of database.
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			// Sets the URL of the database and connects.
			String url = "jdbc:sqlite:database.db";
			c = DriverManager.getConnection(url);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
    }
	
	
	/**
	 * Method used to insert a user to the database.
	 * 
	 * @param User object to insert
	 * @return Boolean depending on if the user has been inserted or not.
	 * @throws SQLException
	 */
	public Boolean insertUser(String username, String password) throws SQLException {
		
		// Creates connection and prepared statement variables.
		Connection c = null;
		PreparedStatement ps = null;
		
		// Query to be run.
		String query = "INSERT INTO users (username, password) "
				+ "VALUES (?,?)";		
		
		try {
			// Sets the prepared statement and gets the connection.
			c = getConnection();
			System.out.println("Query: " + query);
			ps = c.prepareStatement(query);
			
			// Sets values for the prepared statement.
			ps.setString(1, username);
			ps.setString(2, password);
			
			// Prints the values for debugging purposes.
			System.out.println(username + " " + password);
			
			// Executes the update and returns true if there are any rows in the database (data has been inserted).
			int rs = ps.executeUpdate();
			if (rs != 0) {
				return true;
			}
			
		} catch(Exception e) {
			
			// Returns false if the data has not been inserted.
			System.out.println(e.getMessage());
			return false;
			
		} finally {
			
			// Close variables.
			if(ps != null) {
				ps.close();
			}
			if(c != null) {
				c.close();
			}
		}
			
		return true;
	}
	
	public String getUser(String uname, String password) throws SQLException {

		String userName = null;
		Connection getConnection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		String query = "SELECT * FROM users WHERE username = ? AND password = ?;";

		try {
			getConnection = getConnection();
			statement = getConnection.prepareStatement(query);
			statement.setString(1, uname);
			statement.setString(2, password);
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery();

			while (result.next()) {

				userName = result.getString("userName");
			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (getConnection != null) {
				getConnection.close();
			}
		}
		return userName;
	}


	/**
	 * Method returns all universities in the database with no conditions.
	 * 
	 * @return Array of all universities
	 * @throws SQLException to catch query failure
	 */
	public static ArrayList<University> getAllUniversities() throws SQLException {
		// Define connection, statement and result set variables to be used later.
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		// Query to be run. Printed for debugging.
		String query = "SELECT * FROM Universities ORDER BY \"University Name\" ASC";
		System.out.println(query);
		
		// Define array list to store all universities.
		ArrayList<University> universities = new ArrayList<University>();
		
		try {
			// Gets the connection and executes the query using the string above.
			c = getConnection();
			s = c.createStatement();
			rs = s.executeQuery(query);
			
			// Loops through all rows in the result set and creates a new university object with all the data.
			while(rs.next()) {	
				University uni = new University(rs.getInt("University ID"), rs.getInt("Rank"), rs.getString("University Name"),
						rs.getInt("Entry Standards"), rs.getFloat("Student Satisfaction"), rs.getFloat("Research Quality"), rs.getFloat("Research Intensity"),
						rs.getFloat("Graduate Prospects"), rs.getFloat("Student-Staff Ratio"), rs.getFloat("Academic Services Spend"),
						rs.getInt("Facilities Spend"), rs.getFloat("Good Honours"), rs.getFloat("Degree Completion"),
						rs.getInt("Overall Score"));
						
				// Adds the university to the array and prints the name for debugging.
				universities.add(uni);
				System.out.println(uni.getUniversity_name());
			}
		} catch (SQLException e) {
			e.printStackTrace();
				
		} finally {
			// Closes result set, statement and connection.
			if (rs != null) {
				rs.close();
			}
			if (s != null) {
				s.close();
			}
			if (c != null) {
				c.close();
			}
		}
		// Returns final list of university objects.
		return universities;
	 }
	
	/**
	 * Method for returning all universities using a university name as a condition.
	 * 
	 * @return Array of all universities that contain the search query.
	 * @throws SQLException to catch query failure
	 */
	public static ArrayList<University> searchUniversities(String universityName) throws SQLException {
		// Define connection, statement and result set variables to be used later.
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		// Query to be run. Printed for debugging.
		String query = "SELECT * FROM Universities WHERE \"University Name\" LIKE '%"+universityName+"%' ORDER BY \"University Name\" ASC";
		System.out.println(query);
		
		// Define array list to store all universities.
		ArrayList<University> universities = new ArrayList<University>();
		
		try {
			// Gets the connection and executes the query using the string above.
			c = getConnection();
			s = c.createStatement();
			rs = s.executeQuery(query);
			
			// Loops through all rows in the result set and creates a new university object with all the data.
			while(rs.next()) {	
				University uni = new University(rs.getInt("University ID"), rs.getInt("Rank"), rs.getString("University Name"),
						rs.getInt("Entry Standards"), rs.getFloat("Student Satisfaction"), rs.getFloat("Research Quality"), rs.getFloat("Research Intensity"),
						rs.getFloat("Graduate Prospects"), rs.getFloat("Student-Staff Ratio"), rs.getFloat("Academic Services Spend"),
						rs.getInt("Facilities Spend"), rs.getFloat("Good Honours"), rs.getFloat("Degree Completion"),
						rs.getInt("Overall Score"));
				
				// Adds the university to the array and prints the name for debugging.	
				universities.add(uni);
				System.out.println(uni.getUniversity_name());
			}
		} catch (SQLException e) {
			e.printStackTrace();
				
		} finally {
			// Closes result set, statement and connection.
			if (rs != null) {
				rs.close();
			}
			if (s != null) {
				s.close();
			}
			if (c != null) {
				c.close();
			}
		}

		// Returns final list of university objects.
		return universities;
	 }
	
	/**
	 * Method for returning all course names.
	 * 
	 * @return Array of all course names..
	 * @throws SQLException to catch query failure
	 */
	public static ArrayList<String> getAllCourseNames() throws SQLException {
		// Define connection, statement and result set variables to be used later.
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		// Query to be run. Printed for debugging.
		String query = "SELECT * FROM sqlite_master WHERE type='table' AND name !='Universities' AND name != 'sqlite_sequence'";
		System.out.println(query);
		
		ArrayList<String> courseNames = new ArrayList<String>();
				
		try {
			// Gets the connection and executes the query using the string above.
			c = getConnection();
			s = c.createStatement();
			rs = s.executeQuery(query);
			
			// Loops through all table names and adds them to the array.
			while(rs.next()) {	
				courseNames.add(rs.getString("name"));
				System.out.println(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
				
		} finally {
			// Closes result set, statement and connection.
			if (rs != null) {
				rs.close();
			}
			if (s != null) {
				s.close();
			}
			if (c != null) {
				c.close();
			}
		}

		// Prints final array.
		return courseNames;

	 }
	
	/**
	 * Method for returning all universities using a university name as a condition.
	 * 
	 * @return Array of all universities that contain the search query.
	 * @throws SQLException to catch query failure
	 */
	public static ArrayList<String> searchCourses(String courseName) throws SQLException {
		// Define connection, statement and result set variables to be used later.
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		// Query to be run. Printed for debugging.
		String query = "SELECT * FROM sqlite_master WHERE type='table' AND name LIKE '%"+courseName+"%' AND name !='Universities' AND name != 'sqlite_sequence'";
		System.out.println(query);
		
		// Define array list to store all universities.
		ArrayList<String> courseNames = new ArrayList<String>();
		
		try {
			// Gets the connection and executes the query using the string above.
			c = getConnection();
			s = c.createStatement();
			rs = s.executeQuery(query);
			
			// Loops through all rows in the result set and creates a new university object with all the data.
			while(rs.next()) {	
				courseNames.add(rs.getString("name"));
				System.out.println(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
				
		} finally {
			// Closes result set, statement and connection.
			if (rs != null) {
				rs.close();
			}
			if (s != null) {
				s.close();
			}
			if (c != null) {
				c.close();
			}
		}

		// Returns final list of university objects.
		return courseNames;
	 }
}
