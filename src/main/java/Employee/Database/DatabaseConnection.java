package Employee.Database;
import java.sql.*;
public class DatabaseConnection {

    public Connection getConnectionInfo() {
        String url = "jdbc:mysql://localhost:3306/employeeInfo";
        String username = "root";
        String password = "Java@2022";

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("connection Established");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public String getMaxEmpId() {
        String maxEmployeeId=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnectionInfo();
            boolean ifTableExists= checkDatabaseExist(connection);
            if(ifTableExists) {
                statement = connection.createStatement();
                String query = "select max(employee_id) from EmployeeSystem";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    maxEmployeeId = resultSet.getString(1);
                }
            }
        }  catch (
                SQLException e) {
            System.err.println("Sql exception::" + e.getMessage());
        } catch (Exception e2) {
            System.err.println("exception::" + e2.getMessage());
            e2.printStackTrace();
        } finally {
            {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                    System.out.println("connection closed!!!");
                } catch (SQLException e) {
                    System.err.println("Error::" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return maxEmployeeId;
    }

    public  boolean checkDatabaseExist(Connection connection) throws SQLException {
        PreparedStatement preparedStatement=null;
        String checkDbTablesExists = "select count(*) from information_schema.tables where table_name =? ";
        preparedStatement = connection.prepareStatement(checkDbTablesExists);
        preparedStatement.setString(1, "EmployeeSystem");
        boolean ifTableExists = false;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getInt(1) != 0) {
                ifTableExists = true;
            }
        }
        System.out.println("if table exists::" + ifTableExists);
        return ifTableExists ;
    }



    public void storeEmployeeInfo (basics.Employee employee){

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnectionInfo();
            //to check if table exists
            statement = connection.createStatement();
            boolean ifTableExists= checkDatabaseExist(connection);
            if (!ifTableExists) {
                String createQuery = "create table EmployeeSystem (employee_id varchar(60) ,employee_name varchar(50),employee_address varchar(60),employee_salary decimal(10,2))";

                int numberOfRows = statement.executeUpdate(createQuery);
                System.out.println("Created  Number of Rows :: " + numberOfRows);
            }
            String insertQuery = "insert into EmployeeSystem values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, employee.getEmpId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setFloat(4, employee.getSalary());

            int noOfRows = preparedStatement.executeUpdate();
            System.out.println("inserted rows" + noOfRows);


        }  catch (
                SQLException e) {
            System.err.println("Sql exception::" + e.getMessage());
        } catch (Exception e2) {
            System.err.println("exception::" + e2.getMessage());
            e2.printStackTrace();
        } finally {
            {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                    System.out.println("connection closed!!!");
                } catch (SQLException e) {
                    System.err.println("Error::" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}

