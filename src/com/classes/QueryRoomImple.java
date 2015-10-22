package classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;





import util.Constants;

public class QueryRoomImple implements QueryRoom {
	Connection connection;
	public QueryRoomImple(){
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
	public List<String> queryCityList(int serverNO) {
		// TODO Auto-generated method stub
		if(connection ==null){
			System.out.println("Connection is not established");
			return null;
		}else{
			List<String> string = new ArrayList<String>();
			String querystring = "select distinct citystr from hotel" + serverNO;
			try{
				PreparedStatement statement= connection.prepareStatement(querystring);
				ResultSet results =statement.executeQuery();
				while(results.next()){
					string.add(results.getString(1));
				}
				return string;
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}
		}
		
}

	@Override
	public List<String> queryByCityName(String cityname, int serverNO) {
		// TODO Auto-generated method stub
		if(connection == null){
			System.out.println("Connection is not established");
			return null;
		}else{
			List<String> string = new ArrayList<String>();
			String querystring = "select distinct bandstr from hotel"+serverNO + " where citystr=?";
			try{
				PreparedStatement statement = connection.prepareStatement(querystring);
				statement.setString(1, cityname);
				ResultSet results = statement.executeQuery();
				while(results.next()){
					string.add(results.getString(1));
				}
				return string;
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}
		
		}
	}

	@Override
	public List<Room> queryByHotel(String hotelID,
			String checkindate, String checkoutdate, int serverNO) {
		int id = Integer.parseInt(hotelID);
		if(connection == null){
			System.out.print("Connection is not established");
			return null;
		}else{
			List<Room> rooms = new ArrayList<Room>();
			TransactionInterImple tii = new TransactionInterImple();
			String querystring = "select * from room" +serverNO + " where hotelID = ? and (isavail=0 or (TO_DAYS(checkinDate) >= TO_DAYS(?) or "
					+ "TO_DAYS(checkoutDate) <= TO_DAYS(?)))"; 
			try{
				PreparedStatement statement = connection.prepareStatement(querystring);
				statement.setInt(1, id);
				statement.setString(2, checkoutdate);
				statement.setString(3,checkindate);
				ResultSet results = statement.executeQuery();
				while(results.next()){
					Room room = new Room();
					if(results.getInt(5)==0){
					room.setRoomID(results.getString(1));
					room.setCheckindate(results.getString(2));
					room.setCheckoutdate(results.getString(3));
					room.setHotelID(results.getString(4));
					room.setIsavail(0);
					rooms.add(room);
					}else{
						boolean bool = tii.queryTrans(hotelID, results.getString(1), checkindate, checkoutdate, serverNO);
						if(bool==true){
							continue;
						}else{
							room.setRoomID(results.getString(1));
							room.setCheckindate(results.getString(2));
							room.setCheckoutdate(results.getString(3));
							room.setHotelID(results.getString(4));
							room.setIsavail(0);
							rooms.add(room);
						}
					}
					
			}
				return rooms;
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}
			
			
		}
		// TODO Auto-generated method stub
	}


	@Override
	public String queryByHotel(String hotelID, int serverNO) {
		// TODO Auto-generated method stub
	     int id =Integer.parseInt(hotelID);     
	     if(connection == null){
			System.out.println("Connection is not established");
			return null;
		}else{
			String string = "";
			String querystring = "select roomrate from hotel"+serverNO + " where hotelID = ? ";
			try{
				PreparedStatement statement = connection.prepareStatement(querystring);
				statement.setInt(1, id);
				ResultSet results =statement.executeQuery();
				boolean flag=results.next();
				if(flag){
				string = results.getString(1);
				}
				return string;	
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}
			
			
		}
	}
	



	@Override
	public  boolean queryByID(String hotelID, String roomID, String checkinDate, String checkoutDate, int serverNO) {
		// TODO Auto-generated method stub
		int rid = Integer.parseInt(roomID);
		int hid = Integer.parseInt(hotelID);
		if(connection == null){
			System.out.println("Connection is not established");
			return false;
		}else{	
			TransactionInterImple tii = new TransactionInterImple();
			String querystring = "select roomID from room"+serverNO + " where roomID = ? and hotelID = ?";
			PreparedStatement statement;
			try{
				statement = connection.prepareStatement(querystring);
				statement.setInt(1, rid);
				statement.setInt(2, hid);
				ResultSet results = statement.executeQuery();
				boolean flag =results.next();
				if(flag){
					boolean bool = tii.queryTrans(hotelID, roomID, checkinDate, checkoutDate, serverNO);
					if(bool){
					return false;
					}else{
						return true;
					}
				}else{
					return false;
				}	
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
		}
	}
	
	@Override
	public boolean queryHR(String hotelID, String roomID, int serverNO) {
		// TODO Auto-generated method stub
		int rid = Integer.parseInt(roomID);
		int hid = Integer.parseInt(hotelID);
		if(connection == null){
			System.out.println("Connection is not established");
			return false;
		}else{	
			String querystring = "select roomID from room"+serverNO + " where roomID = ? and hotelID = ?";
			PreparedStatement statement;
			try{
				statement = connection.prepareStatement(querystring);
				statement.setInt(1, rid);
				statement.setInt(2, hid);
				ResultSet results = statement.executeQuery();
				boolean flag =results.next();
				if(flag){
					return true;
				}else{
					return false;
				}	
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
		}
	}
	
	@Override
	public List<Room> queryByServerno(String hotelID, int serverNO) {
		// TODO Auto-generated method stub
		int hid = Integer.parseInt(hotelID);
		if(connection == null){
			System.out.println("Connection is not established!");
			return null;
		}else{
			List<Room> rooms = new ArrayList<Room>();
			String querystring = "select * from room"+ serverNO +" where hotelID = ?";
			PreparedStatement statement;
			try{
				statement = connection.prepareStatement(querystring);
				statement.setInt(1, hid); 
				ResultSet results = statement.executeQuery();
				while(results.next()){
					Room room = new Room();
					room.setRoomID(results.getString(1));
					room.setCheckindate(results.getString(2));
					room.setCheckoutdate(results.getString(3));
					room.setHotelID(results.getString(4));
					room.setIsavail(results.getInt(5));
					rooms.add(room);
				}
				return rooms;
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
		//QueryRoomImple a = new QueryRoomImple();

		//boolean bool = a.queryByID("2", "25", "2014-01-08", "2014-01-09", 1);
	//	System.out.println(bool);
		
	    //System.out.println(t.size());
		//t=a.queryByHotel("26346702", "hotel1");
		//System.out.println(t);
		//List<Room> rooms = new ArrayList<Room>();
		//rooms = a.queryByHotel("26346702", "2014-03-25", "2014-05-22", 1);
		//System.out.println(rooms.get(0).getHotelID());
		//System.out.println(rooms.get(0).getRoomID());
	}

	

	


}
