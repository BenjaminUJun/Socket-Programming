package broker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import classes.Booker;
import classes.Hotel;
import classes.Room;
import classes.Transaction;
import client.ClientHOPP;

public class BrokerNHOPPServer implements ClientHOPP{
	private BrokerHOPPServer[] brokerhopps;
	
	public BrokerNHOPPServer(){
		brokerhopps = new BrokerHOPPServer[3];
		for(int i =0;i<3;++i){
			brokerhopps[i] = new BrokerHOPPServer(i+1);
		}
	}

	public List<String> queryCity() {
		// TODO Auto-generated method stub
		List<String> cities = new ArrayList<String>(); 
		for(int i = 0;i<3;++i){
			cities.addAll(brokerhopps[i].queryCity());
		}
		HashSet<String> hash = new HashSet<String>(cities);
		cities.clear();
		cities.addAll(hash);
		return cities;
	}

	

	public List<String> queryByCityName(String cityname) {
		// TODO Auto-generated method stub
		List<String> hotels = new ArrayList<String>();
		for(int i = 0;i < 3; ++i){
			hotels.addAll(brokerhopps[i].queryByCityName(cityname));
		}
		HashSet<String> hash = new HashSet<String>(hotels);
		hotels.clear();
		hotels.addAll(hash);
		return hotels;
	}


	public String queryRoomrates(String hotelID, int serverNO) {
		// TODO Auto-generated method stub
		String roomrate = brokerhopps[serverNO-1].queryRoomrates(hotelID, serverNO);	
		return roomrate;
	}
	
	public Transaction queryTransaction(String transactionID, int serverNO) {
		// TODO Auto-generated method stub
		Transaction trans = new Transaction();
		trans = brokerhopps[serverNO-1].queryTransaction(transactionID, serverNO);
		return trans;
	}
	

	public List<Room> queryVacantrooms(String hotelID, String checkindate,
			String checkoutdate, int serverNO) {
		// TODO Auto-generated method stub
		List<Room> rooms = new ArrayList<Room>();
		rooms = brokerhopps[serverNO-1].queryVacantrooms(hotelID, checkindate, checkoutdate, serverNO);
		return rooms;
	}
	public String insertBookers(String bookerID, String bookerName,
			String telePhone, String email, String passwd,int serverNO) {
		// TODO Auto-generated method stub
		String insertinfo = brokerhopps[serverNO-1].insertBookers(bookerID, bookerName, telePhone, email, passwd, serverNO);
		return insertinfo;
	}

	public String booking(String checkinDate, String checkoutDate,String hotelID, 
			String roomID, String bookerID, String creditNO, int serverNO) {
		// TODO Auto-generated method stub
		String bookings = brokerhopps[serverNO-1].booking(checkinDate, checkoutDate, hotelID, roomID, bookerID, creditNO, serverNO);
		return bookings;
	}
	

	public List<Hotel> queryHotel(String cityName, int serverNO) {
		// TODO Auto-generated method stub
		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels = brokerhopps[serverNO-1].queryHotel(cityName, serverNO);
		return hotels;
	}
	
	public List<Booker> queryBookerinfo(String bookerID, int serverNO){
		List<Booker> bookers = new ArrayList<Booker>();
		bookers = brokerhopps[serverNO-1].queryBookerinfo(bookerID, serverNO);
		return bookers;
	}
	
	public void sendMsgBYE() {
		for (int i = 0; i < 3; i++) {
			brokerhopps[i].sendMsgBYE();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BrokerNHOPPServer brk = new BrokerNHOPPServer();
		System.out.println(brk.insertBookers("12222253", "dfasf", "dfadsf", "dfadsf", "dfasdf", 1));
		//List<Booker> bookers = new ArrayList<Booker>();
		//bookers = brk.queryBookerinfo("26346702",1);
		//if(bookers.size()==0){
		//	System.out.print("null"); 
	//	}else{
		//for(Booker booker:bookers){
		//	System.out.println(booker.getBookerID()+booker.getBookinfo2());
	//	}
	//	}
	//	brk.sendMsgBYE();
	}
	

}
