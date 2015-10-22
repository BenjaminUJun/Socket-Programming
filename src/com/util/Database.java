package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;




public class Database {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database base = new Database();
		base.createDatabase();
//		if(base.insertFunction("hilton")==false){
//			base.deleteFunction(1);
//		}
//		if(base.insertFunction("chevron")==false){
//			base.deleteFunction(2);
//		}
//		if(base.insertFunction("regent")==false){
//			base.deleteFunction(3);
//		}	
//		
	}
	
	Connection connection = null;
	private static final String[] CITY = { "NanJing", "Ningbo", "Suzhou", "Hangzhou", "Melbourne", "Sydney", "Brisbane", "Adelaide" };
	private static final String[] LOCATION = { "Jiangsu", "Zhejiang", "Jiangsu", "Zhejiang", "Victoria", "NSW", "Queensland", "SA" };
	private static final String[] HOTEL = { "HILTON", "Chevron", "Regent"};
	
	public Database(){
		getconnection();
	}
	
	public void getconnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
                  "jdbc:mysql://localhost/test", "fit5183a1", "");
		} catch (Exception e) {
			System.out.println("Fail to Connect");
		}
	}
	
	public boolean createDatabase(){
		if(connection == null){
			System.out.println("Connection is not established!");
			return false;
		}else{
			String createDatabase = "CREATE DATABASE id26346702";
			try{
				Statement stat = connection.createStatement();
				stat.executeUpdate(createDatabase);
				return true;
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		
	}
	
	public boolean insertFunction(String hotelName){
		String hotel = hotelName.trim().toLowerCase();
		if(connection == null){
			System.out.println("Connection is not established!");
			return false;
		}else{
			switch(hotel){
			case "hilton":
				String insertstr1 = "INSERT INTO hotel1 values(?,?,?,'hilton',10,235.99,10)";
				String insertstr2 = "INSERT INTO room1 values(?,?,?,?,0)";
				PreparedStatement statement1;
				PreparedStatement statement2;
				try{
					statement1 = connection.prepareStatement(insertstr1);
					statement2 = connection.prepareStatement(insertstr2);
					for(int i = 0; i < 8; ++i){
						statement1.setInt(1, i+1);
						statement1.setString(2, CITY[i]);
						statement1.setString(3, LOCATION[i]);
						statement1.executeUpdate();
						for(int j =0;j<10;++j){
							statement2.setInt(1, (i+1)*10+j);
							statement2.setString(2, "2015-03-"+(10+j));
							statement2.setString(3, "2015-03-"+(10+j));
							statement2.setInt(4, i+1);
							statement2.executeUpdate();
						}
					}
					return true;
				}catch(SQLException e){
					//e.printStackTrace();
				}
				
				break;
			case "chevron":
				String insertstr3 = "INSERT INTO hotel2 values(?,?,?,'chevron',20,100.01,20)";
				String insertstr4 = "INSERT INTO room2 values(?,?,?,?,0)";
				PreparedStatement statement3;
				PreparedStatement statement4;
				try{
					statement1 = connection.prepareStatement(insertstr3);
					statement2 = connection.prepareStatement(insertstr4);
					for(int i = 0; i < 8; ++i){
						statement1.setInt(1, i+1);
						statement1.setString(2, CITY[i]);
						statement1.setString(3, LOCATION[i]);
						statement1.executeUpdate();
						for(int j =0;j<10;++j){
							statement2.setInt(1, (i+1)*10+j);
							statement2.setString(2, "2015-03-"+(10+j));
							statement2.setString(3, "2015-03-"+(10+j));
							statement2.setInt(4, i+1);
							statement2.executeUpdate();
						}
					}
					return true;
				}catch(SQLException e){
					//e.printStackTrace();
				}
				
				break;
			case "regent":
				String insertstr5 = "INSERT INTO hotel3 values(?,?,?,'regent',20,150.98,20)";
				String insertstr6 = "INSERT INTO room3 values(?,?,?,?,0)";
				PreparedStatement statement5;
				PreparedStatement statement6;
				try{
					statement1 = connection.prepareStatement(insertstr5);
					statement2 = connection.prepareStatement(insertstr6);
					for(int i = 0; i < 8; ++i){
						statement1.setInt(1, i+1);
						statement1.setString(2, CITY[i]);
						statement1.setString(3, LOCATION[i]);
						statement1.executeUpdate();
						for(int j =0;j<10;++j){
							statement2.setInt(1, (i+1)*10+j);
							statement2.setString(2, "2015-03-"+(10+j));
							statement2.setString(3, "2015-03-"+(10+j));
							statement2.setInt(4, i+1);
							statement2.executeUpdate();
						}
					}
					return true;
				}catch(SQLException e){
					//e.printStackTrace();
				}

				break;
			default:
				break;
			}
		}
		return false;
		
	}
	public boolean deleteFunction(int num){
		if(connection == null){
			System.out.println("Connection is not established!");
			return false;
		}else{
			String delstr1 = "TRUNCATE TABLE transaction"+num;
			String delstr2 = "TRUNCATE TABLE booker"+num;
			String delstr3 = "delete from room"+num+" where hotelID = ?";
			String delstr4 = "delete from hotel"+num+" where hotelID = ?";
			try{
			PreparedStatement statement1 = connection.prepareStatement(delstr1);
			statement1.executeUpdate();
			PreparedStatement statement2 = connection.prepareStatement(delstr2);
			statement2.executeUpdate();
			PreparedStatement statement3 = connection.prepareStatement(delstr3);
			PreparedStatement statement4 = connection.prepareStatement(delstr4);
			for(int i=0;i < 8; ++i){
				statement3.setInt(1, i+1);
				statement3.executeUpdate();
				statement4.setInt(1, i+1);
				statement4.executeUpdate();
			}
			return true;
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}			
		}
		
	}
}
