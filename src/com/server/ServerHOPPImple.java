package server;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import classes.*;

public class ServerHOPPImple implements ServerHOPP {

	private int serverNO;
	private QueryRoomImple qri=null;
	private TransactionInterImple tii=null; 
	private QueryHotelImple qhi=null;
	private BookerinfoImple bi=null;

	public ServerHOPPImple(int serverNO){
		qri= new QueryRoomImple();
		tii= new TransactionInterImple();
		qhi= new QueryHotelImple();
		bi= new BookerinfoImple();
		this.serverNO=serverNO;
	}
	
	@Override
	public List<String> queryCity() {
		// TODO Auto-generated method stub
		System.out.println("ServerHOPP is now working! You are now querying the CityList with the table hotel"+ this.serverNO);
		List<String> cities = new ArrayList<String>();
		cities = qri.queryCityList(this.serverNO);
		return cities;
	}

	@Override
	public List<String> queryByCityName(String cityname) {
		// TODO Auto-generated method stub
		System.out.println("ServerHOPP is now working! You are now querying the HotelName with the table hotel"+ this.serverNO);
		List<String> htl = new ArrayList<String>(); 
		htl = qri.queryByCityName(cityname, this.serverNO);
		if(htl.size()==0){
			System.out.println("This city doesn't have any hotels in our system!");
		}else{
		System.out.println("The hotles:");
		}
		return htl;
	}
	
	@Override
	public String insertBookers(String bookerID, String bookerName,
			String telePhone, String email, String passwd,int serverNO) {
		// TODO Auto-generated method stub
		System.out.println("ServerHOPP is now working! You are booking now !");
		boolean t = tii.insertBooker(bookerID, bookerName, telePhone, email, passwd, serverNO);
		if(t){
			System.out.println("Insert Successfully!");
			return Constants.BOOKERINSERT;
		}else{
			System.out.println("You have already registered!");
			return Constants.INSERTFAILED;
		}
	}
	
	@Override
	public synchronized String booking(String checkinDate, String checkoutDate,String hotelID,String roomID, 
			String bookerID, String creditNO, int serverNO) {
		// TODO Auto-generated method stub
		System.out.println("ServerHOPP is now working! You are booking now !");
		int result=checkinDate.compareTo(checkoutDate);
		if(result>0){
			System.out.println("The date is illegal!");
			return null;
		}else{
		String transacID = checkinDate + checkoutDate + hotelID + roomID + bookerID;
		boolean exit = tii.queryTransaction(transacID, serverNO);
		if(!exit){
			System.out.println("You are ready to book the room now!");
			String str = tii.bookingRoom(checkinDate, checkoutDate, hotelID, transacID, roomID, bookerID, creditNO,this.serverNO);
			return str;
		}else{
			System.out.println("You have already booked this room!");
			return Constants.DUPLICATEBOOKING;
		}	
		}
	}

	@Override
	public String queryRoomrates(String hotelID, int serverNO) {
		// TODO Auto-generated method stub
		System.out.println("ServerHOPP is now working! You are now querying the Room Rates with the table hotel"+ this.serverNO);
		System.out.println("The Room Rate:");
		String roomrate = qri.queryByHotel(hotelID, serverNO);
		return roomrate;
	}

	
	@Override
	public List<Room> queryVacantrooms(String hotelID, String checkindate,
			String checkoutdate, int serverNO) {
		// TODO Auto-generated method stub
		System.out.println("ServerHOPP is now working! You are now querying the Room which is vacant with the table room"+ this.serverNO);
		List<Room> rooms = new ArrayList<Room>();
		int result=checkindate.compareTo(checkoutdate);
		if(result>0){
			System.out.println("The date is illegal!");
			return rooms;
		}else{
			 rooms=qri.queryByHotel(hotelID, checkindate, checkoutdate, serverNO);
			if(rooms.size()==0){
				System.out.println("This hotel doesn't have the rooms you need!");
			}else{
			System.out.println("Yours Rooms:");
			}
			return rooms;	
		}
	}
	@Override
	public List<Hotel> queryHotel(String cityName, int serverNO) {
		// TODO Auto-generated method stub
		System.out.println("ServerHOPP is now working! You are now querying the hotel!");
		List<Hotel> hotels = new ArrayList<Hotel>(); 
		hotels = qhi.queryHotel(cityName, serverNO);
		if(hotels.size()==0){
			System.out.println("This city doesn't have the hotels you need!");
		}
		System.out.println("Yours Rooms:");
		return hotels;
	}
	
	@Override
	public List<Booker> queryBookerinfo(String bookerID, int serverNO) {
		// TODO Auto-generated method stub
		System.out.println("ServerHOPP is now working! You are now querying the Bookerinfo!");
		List<Booker> bookers = new ArrayList<Booker>(); 
		bookers = bi.queryBooker(bookerID, serverNO);
		if(bookers.size()==0){
			System.out.println("We don't have the information of this Booker!");
		}
		System.out.println("The Bookers: ");
		return bookers;
	}
	
	@Override
	public Transaction queryTransaction(String transactionID, int serverNO) {
		// TODO Auto-generated method stub
		System.out.println("ServerHOPP is now working! You are now querying the transaction");
		Transaction trans = new Transaction();
		trans = tii.queryTran(transactionID, serverNO);
		if(trans==null){
			System.out.println("This hotel doesn't have your transaction!");
		}
		System.out.println("Transaction:");
		return trans;
	}
	
	public int getSeverNO() {
		return serverNO;
	}

	public void setSeverNO(int serverNO) {
		this.serverNO = serverNO;
	}
	
	public void close() {
		System.out.println("ServerHOPP is now closing");
		qri.close();
		tii.close();
		qhi.close();
		bi.close();
	}
	
	public static void main(String[] args) {
		
		//ServerHOPPImple aa = new ServerHOPPImple(1);
	//	System.out.println(aa.insertBookers("5555555555555555555555", "dadsfasd", "dsfasdf", "dsfsdfs", "dfsdfds", 1));
		
	}
	
}
