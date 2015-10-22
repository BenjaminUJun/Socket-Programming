package client;

import java.util.List;

import classes.Booker;
import classes.Hotel;
import classes.Room;
import classes.Transaction;

public interface ClientHOPP {
	public List<String> queryCity();
	public List<String> queryByCityName(String cityname);
	public String queryRoomrates(String hotelID, int serverNO);
	public List<Room> queryVacantrooms(String hotelID, String checkindate, String checkoutdate, int serverNO);
	public String insertBookers(String bookerID, String bookerName, String telePhone, String email, String passwd,int serverNO);
	public String booking(String checkinDate, String checkoutDate, String hotelID, 
			String roomID, String bookerID,String creditNO, int serverNO);
	public List<Hotel> queryHotel(String cityName, int serverNO);
	public List<Booker> queryBookerinfo(String bookerID, int serverNO);
	public Transaction queryTransaction(String transactionID, int serverNO);
}
