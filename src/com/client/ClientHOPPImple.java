package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import classes.Booker;
import classes.Hotel;
import classes.Room;
import classes.Transaction;

public class ClientHOPPImple implements ClientHOPP {

	
	private Socket clientSocket;
	private InetAddress addr;
	private BufferedReader reader;
	private PrintStream writer;
	
	
	public ClientHOPPImple() {
		try {
			addr = InetAddress.getLocalHost();
			clientSocket = new Socket(addr, Constants.PORT);
			reader = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			writer = new PrintStream(clientSocket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@Override
	public List<String> queryCity() {
		writer.print("city"+Constants.CR_LF);
		writer.print(Constants.SOS+Constants.CR_LF);
		List<String> cities = new ArrayList<String>();
		String tempstr="";
		try{
			tempstr = reader.readLine();
			if(tempstr.equals("NO")){
				return cities;
			}
			while(true){		
			tempstr = reader.readLine();
			if(tempstr.equals(Constants.EOS)){
				break;
			}
			cities.add(tempstr);
			}
			return cities;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	
		
}

	@Override
	public List<String> queryByCityName(String cityname) {
		writer.print("hotelName"+Constants.CR_LF);
		writer.print(cityname+Constants.CR_LF);
		String tempstr="";
		List<String> hotels = new ArrayList<String>();
			try{	
				tempstr = reader.readLine();
				if(tempstr.equals("NO")){
					return hotels;
				}
				while(true){
				tempstr = reader.readLine();
				if(tempstr.equals(Constants.EOS)){
					break;
				}
				hotels.add(tempstr);
				}
				return hotels;
			}catch(IOException e){
				e.printStackTrace();
				return null;
			}
		
	}
	

	@Override
	public String queryRoomrates(String hotelID, int serverNO) {
		writer.print("roomrate"+Constants.CR_LF);
		writer.print(hotelID+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		String tempstr="";
		String roomrate="";
			try{
				tempstr = reader.readLine();
				roomrate = tempstr;
				return roomrate;
				}catch(IOException e){
					e.printStackTrace();
					return null;
				}		
	}
	
	@Override
	public List<Room> queryVacantrooms(String hotelID, String checkindate,
			String checkoutdate, int serverNO) {
		writer.print("vacancies"+Constants.CR_LF);
		writer.print(hotelID+Constants.CR_LF);
		writer.print(checkindate+Constants.CR_LF);
		writer.print(checkoutdate+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		List<Room> vacancies = new ArrayList<Room>();
		String tempstr = "";	
		int temp;
		
			try{
				tempstr = reader.readLine();
				if(tempstr.equals("NO")){
					return vacancies;
				}
				while(true){
				Room vacancy = new Room();
				tempstr = reader.readLine();
				if(tempstr.equals(Constants.EOS)){
					break;
				}
				vacancy.setRoomID(tempstr);
				tempstr = reader.readLine();
				vacancy.setCheckindate(tempstr);
				tempstr = reader.readLine();
				vacancy.setCheckoutdate(tempstr);
				tempstr = reader.readLine();
				vacancy.setHotelID(tempstr);
				tempstr = reader.readLine();
				temp = Integer.parseInt(tempstr);
				vacancy.setIsavail(temp);
				vacancies.add(vacancy);
				}
				return vacancies;
			}catch(IOException e){
				e.printStackTrace();
				return null;
			}
	}
	
	@Override
	public String insertBookers(String bookerID, String bookerName,
			String telePhone, String email, String passwd, int serverNO) {
		// TODO Auto-generated method stub
		writer.print("insertbks"+Constants.CR_LF);
		writer.print(bookerID+Constants.CR_LF);
		writer.print(bookerName+Constants.CR_LF);
		writer.print(telePhone+Constants.CR_LF);
		writer.print(email+Constants.CR_LF);
		writer.print(passwd+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		String buffer="";
		try {
			buffer = reader.readLine();
			if (buffer.equals("YES")) {
				buffer = reader.readLine();
			} else {
				buffer = "";
			}
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return "Connection lost!";
		}	
	}
	@Override
	public String booking(String checkinDate, String checkoutDate, String hotelID,
			String roomID, String bookerID,String creditNO, int serverNO) {
		writer.print("booking"+Constants.CR_LF);
		writer.print(checkinDate+Constants.CR_LF);
		writer.print(checkoutDate+Constants.CR_LF);
		writer.print(hotelID+Constants.CR_LF);
		writer.print(roomID+Constants.CR_LF);
		writer.print(bookerID+Constants.CR_LF);
		writer.print(creditNO+Constants.CR_LF);
		writer.print(serverNO+Constants.CR_LF);
		writer.print(Constants.EOS+Constants.CR_LF);
		String buffer="";
		try {
			buffer = reader.readLine();
			if (buffer.equals("YES")) {
				buffer = reader.readLine();
			} else {
				buffer = "";
			}
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return "Connection lost!";
		}		
	}
	
    @Override
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
					System.out.println("ClientHOPP: NO ROOMS IN THIS HOTEL!");
				}else{
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
	
	@Override
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
	@Override
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
	
	public void sendMsgBYE() {
		writer.print(Constants.BYE + Constants.CR_LF);
		try{
			reader.close();
			writer.close();
			clientSocket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientHOPPImple a = new ClientHOPPImple();
		List<Booker> bookers = new ArrayList<Booker>();
		bookers= a.queryBookerinfo("123456", 1);
		for(Booker booker:bookers){
			System.out.println(booker.getBookerName());
		}
		//System.out.println(result);
	}
		
}
