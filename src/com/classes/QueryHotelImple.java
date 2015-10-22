package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import util.Constants;

public class QueryHotelImple implements QueryHotel {
	Connection connection;
	public QueryHotelImple(){
		getconnection();
	}
	public void getconnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(Constants.CONNECTION_STRING,Constants.USERNAME,"");
		}catch(Exception e){
			System.out.println("Fail to Connect");
		}
	}
	@Override
	public List<Hotel> queryHotel(String cityName, int serverNO) {
		// TODO Auto-generated method stub
		if(connection == null){
			System.out.println("Connection is not established!");	
		return null;
		}else{
			List<Hotel> hotels = new ArrayList<Hotel>();
			List<Room> rooms = new ArrayList<Room>();
			String querystring = "select * from hotel"+serverNO+" where citystr = ?"; 
			try{
				QueryRoomImple qri = new QueryRoomImple();
				PreparedStatement statement = connection.prepareStatement(querystring);
				statement.setString(1, cityName);
				ResultSet results = statement.executeQuery();
				while(results.next()){
					Hotel temp = new Hotel();
					temp.setHotelID(results.getString(1));
					temp.setCitystr(results.getString(2));
					temp.setLocation(results.getString(3));
					temp.setBandstr(results.getString(4));
					temp.setVacantrooms(results.getString(5));
					temp.setRoomrate(results.getString(6));
					temp.setRoomNO(results.getString(7));
					rooms = new ArrayList<Room>();
					rooms = qri.queryByServerno(results.getString(1), serverNO);
					temp.setRoom(rooms);
					hotels.add(temp);		
				}
				return hotels;
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
