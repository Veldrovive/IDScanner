//Must compile and run this with:
//javac -classpath .:/Users/aidandempster/Desktop/RoboticsScanner/src/lib/mysql-connector-java-5.1.40-bin.jar Main.java and
//java -classpath .:/Users/aidandempster/Desktop/RoboticsScanner/src/lib/mysql-connector-java-5.1.40-bin.jar Main
//in order to get the MysqlDataSource

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import com.mysql.jdbc.Statement;
//import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

abstract public class Database{
  Connection conn;
  Statement stmt;
  ResultSet res;

  //serverN is the ip adress and databaseN is the schema for the database. The rest is self explanatory
  public Database(String serverN, int port, String databaseN, String user, String pass){
    MysqlDataSource dataSource = new MysqlDataSource();
    //System.out.println("Setting user to: "+user+", setting password to: "+pass+", setting server to: "+serverN+":"+port+", at database: "+databaseN);
    dataSource.setServerName(serverN);
    dataSource.setPortNumber(port);
    dataSource.setDatabaseName(databaseN);
    dataSource.setUser(user);
    dataSource.setPassword(pass);
    try{
      conn = dataSource.getConnection();
      System.out.println("Connected to Database");
    }catch(SQLException e){
      System.out.println("Sql error: "+e);
    }

  }

  public void exit(){
    System.out.println("Closing Connections");
    try{
      if(res != null){
        //System.out.println("Closing res");
        res.close();
      }
      if(stmt != null){
        //System.out.println("Closing stmt");
        stmt.close();
      }
      if(conn != null){
        //System.out.println("Closing conn");
        conn.close();
      }
    }catch(SQLException e){
      System.out.println("SQL Error: "+e);
    }
  }
}
