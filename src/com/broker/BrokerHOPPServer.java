package broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import classes.Booker;
import classes.Hotel;
import classes.Room;
import classes.Transaction;
import server.ServerHOPP;
import util.Constants;

public class BrokerHOPPServer implements ServerHOPP{

	private int serverNO;
	private int PORT;
	private InetAddress addr = null;
	private Socket socket = null;
	private BufferedReader reader = null;
	private PrintStream writer = null;
	
	public BrokerHOPPServer(int serverNO){
		this.serverNO=serverNO;
		this.PORT=Constants.PORT+serverNO;
		try{
			addr = InetAddress.getLocalHost();
			socket = new Socket(addr,this.PORT);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintStream(socket.getOutputStream());	
		}catch(UnknownHostException e){
			e.printStackTrace();
			System.out.println("BrokerHOPP: UnKnownHost!");
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("BrokerHOPP: UnKnownHost!");
			return;
		}
	}
	

	public List<String> queryCity() {
		List<String> cities = new ArrayList<String>();
		writer.print("city"+Constants.CR_LF);
		try{
			String buffer = reader.readLine();
			if(buffer.equals("NO")){
				return cities;
			}
			while(true){
				buffer=reader.readLine();
				if(buffer.equals(Constants.EOS)){
					break;
				}
				cities.add(buffer);
			}
			return cities;		
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
		
	}


	public List<String> queryByCityName(String cityname) {
		List<String> hotels = new ArrayList<String>();
		writer.print("hotelName"+ Constants.CR_LF);
		writer.print(cityname+Constants.CR_LF);		
		try{
			String buffer =reader.readLine();
			if(buffer.equals("NO")){
				return hotels;
			}
			while(true){
			buffer = reader.readLine();
			if(buffer.equals(Constants.EOS)){
				break;
			}
			hotels.add(buffer);	
		}
			return hotels;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}	
	
	}


	public String queryRoomrates(String hotelID, int serverNO) {
		String roomrate="";
		writer.print("roomrate"+Constants.CR_LF);
		writer.print(hotelID+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		try{
			String buffer = reader.readLine();
			roomrate = buffer; 	
			return roomrate;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
}
	
	public Transaction queryTransaction(String transactionID, int serverNO) {
		Transaction tran = new Transaction();
		writer.print("trans"+Constants.CR_LF);
		writer.print(transactionID+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		try{
			String buffer = reader.readLine();
			if(buffer.equals("NO")){
				return tran;
			}else if(buffer.equals("YES")){
				buffer = reader.readLine();
				tran.setTranID(buffer);
				buffer = reader.readLine();
				tran.setRoomID(buffer);
				buffer = reader.readLine();
				tran.setBookDT(buffer);
				buffer = reader.readLine();
				tran.setBookerID(buffer);
				buffer = reader.readLine();
				tran.setCreditNO(buffer);
				buffer = reader.readLine();
				tran.setHotelID(buffer);
			}
			return tran;
		}catch(IOException e){
			e.printStackTrace();
			return null;
			
		}
	}

	public List<Room> queryVacantrooms(String hotelID, String checkindate,
			String checkoutdate,int serverNO) {
		List<Room> rooms = new ArrayList<Room>();
		writer.print("vacancies"+Constants.CR_LF);
		writer.print(hotelID+Constants.CR_LF);
		writer.print(checkindate+Constants.CR_LF);
		writer.print(checkoutdate+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		try{
			String buffer = reader.readLine();
			if(buffer.equals("NO")){
				return rooms;
			}else if(buffer.equals("YES")){
				buffer = reader.readLine();
			while(!buffer.equals(Constants.EOS)){
				Room room = new Room();
				room.setRoomID(buffer);
				buffer = reader.readLine();
				room.setCheckindate(buffer);
				buffer = reader.readLine();
				room.setCheckoutdate(buffer);
				buffer = reader.readLine();
				room.setHotelID(buffer);
				buffer = reader.readLine();
				int is = Integer.parseInt(buffer);
				room.setIsavail(is);
				rooms.add(room);
				buffer =reader.readLine();
			}	
			}
			return rooms;	
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public String insertBookers(String bookerID, String bookerName,
			String telePhone, String email, String passwd,int serverNO) {
		String insertinfo = null;
		writer.print("insertbks"+Constants.CR_LF);
		writer.print(bookerID+Constants.CR_LF);
		writer.print(bookerName+Constants.CR_LF);
		writer.print(telePhone+Constants.CR_LF);
		writer.print(email+Constants.CR_LF);
		writer.print(passwd+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		try{
			String buffer= reader.readLine();
			insertinfo = buffer;
			return insertinfo;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	
	}

	public String booking(String checkinDate, String checkoutDate,String hotelID, 
			String roomID, String bookerID, String creditNO, int serverNO) {
		String bookinfo = null;
		writer.print("booking"+Constants.CR_LF);
		writer.print(checkinDate+Constants.CR_LF);
		writer.print(checkoutDate+Constants.CR_LF);
		writer.print(hotelID+Constants.CR_LF);
		writer.print(roomID+Constants.CR_LF);
		writer.print(bookerID+Constants.CR_LF);
		writer.print(creditNO+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		try{
			String buffer= reader.readLine();
			bookinfo = buffer;
			return bookinfo;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	
	}
	

	public List<Hotel> queryHotel(String cityName, int serverNO) {
		writer.print("hotels"+Constants.CR_LF);
		writer.print(cityName+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		List<Hotel> hotels = new ArrayList<Hotel>();
		List<Room> rooms = new ArrayList<Room>();
		String tempstr=null;
		try{
			tempstr = reader.readLine();
			if(tempstr.equals("NO")){
				return hotels;
			}else if(tempstr.equals("YES")){
				while(true){
				tempstr = reader.readLine();
				if(tempstr.equals(Constants.EOS)){
					break;
				}
				Hotel hotel = new Hotel();
				hotel.setHotelID(tempstr);
				tempstr = reader.readLine();
				hotel.setCitystr(tempstr);
				tempstr = reader.readLine();
				hotel.setLocation(tempstr);
				tempstr = reader.readLine();
				hotel.setBandstr(tempstr);
				tempstr = reader.readLine();
				hotel.setVacantrooms(tempstr);
				tempstr = reader.readLine();
				hotel.setRoomrate(tempstr);
				tempstr = reader.readLine();
				hotel.setRoomNO(tempstr);
				tempstr = reader.readLine();
				if(tempstr.equals("NO")){
					System.out.println("BrokerHOPP: NO ROOMS IN THIS HOTEL!");
				}else if(tempstr.equals("YES")){
				while(true){
				tempstr = reader.readLine();
				if(tempstr.equals(Constants.EOS)){
					break;
				}
				Room room = new Room();
				room.setRoomID(tempstr);
				tempstr = reader.readLine();
				room.setCheckindate(tempstr);
				tempstr = reader.readLine();
				room.setCheckoutdate(tempstr);
				tempstr = reader.readLine();
				room.setHotelID(tempstr);
				room.setIsavail(Integer.parseInt(reader.readLine()));
				rooms.add(room);
				}						
				}
				hotel.setRoom(rooms);
				rooms = new ArrayList<Room>();
				hotels.add(hotel);		
				}
			}
			return hotels;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Booker> queryBookerinfo(String bookerID, int serverNO){
		writer.print("bookers"+Constants.CR_LF);
		writer.print(bookerID+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		List<Booker> bookers = new ArrayList<Booker>();
		String tempstr=null;
		try{
			tempstr = reader.readLine();
			if(tempstr.equals("NO")){
				return bookers;
			}else if (tempstr.equals("YES")){
				while(true){
					tempstr = reader.readLine();
					if(tempstr.equals(Constants.EOS)){
						break;
					}
					Booker booker = new Booker();
					booker.setBookerID(tempstr);
					tempstr = reader.readLine();
					booker.setBookerName(tempstr);
					tempstr = reader.readLine();
					booker.setTelePhone(tempstr);
					tempstr = reader.readLine();
					booker.setEmail(tempstr);
					tempstr = reader.readLine();
					booker.setPasswd(tempstr);
					tempstr = reader.readLine();
					booker.setBookinfo1(tempstr);
					tempstr = reader.readLine();
					booker.setBookinfo2(tempstr);
					bookers.add(booker);
				}
			}
			return bookers;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void sendMsgBYE(){
		writer.print(Constants.BYE+Constants.CR_LF);
		try{
			reader.close();
			writer.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	BrokerHOPPServer aa = new BrokerHOPPServer(1);
		//System.out.println(aa.insertBookers("1222223", "dfasf", "dfadsf", "dfadsf", "dfasdf", 1));
		//List<Booker> bookers = new ArrayList<Booker>();
	//	bookers = aa.queryBookerinfo("26346702",1);
		//if(bookers.size()==0){
		//	System.out.print("null"); 
	//	}else{
		//for(Booker booker:bookers){
		//	System.out.println(booker.getBookerID()+booker.getBookinfo2());
	//	}
		//}
	//	aa.sendMsgBYE();
	}
}
