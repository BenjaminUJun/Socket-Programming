package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import classes.*;
import util.Constants;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int temp = 0;
		String tempstr = args[0].toLowerCase();
		if(tempstr.equals("hilton")){
			temp = 1;
		}
		if(tempstr.equals("chevron")){
			temp = 2;
		}
		if(tempstr.equals("regent")){
			temp = 3;
		}
		if(temp==0){
			System.out.println("Server: Please choose the right Server Name!");
			System.out.println("Server: Hilton   Chevron   Regent");
		}else{
		int PORT = Constants.PORT + temp;
		ServerSocket serversocket = null;
		try{
			serversocket = new ServerSocket(PORT);
		}catch(IOException e){
			System.out.println(e);
		    return;
		}
		System.out.println("Server: The Server "+args[0]+" is now running!");
		while(true){
			Socket incoming = null;
			try{
				incoming = serversocket.accept();
			}catch(IOException e){
				 System.out.println(e);
			     continue;
			}
			new SocketHandler(incoming, temp).start();
			
		}
		
	}
}
}
class SocketHandler extends Thread {
	Socket incoming;
	ServerHOPPImple serverhoopimple;
	int serverNO;

	BufferedReader reader;
	PrintStream writer;

	public SocketHandler(Socket incoming, int serverNO) {
		// TODO Auto-generated constructor stub
		this.incoming = incoming;
		this.serverNO = serverNO;
		this.serverhoopimple = new ServerHOPPImple(serverNO);
		
	}
	public void run(){
		try{
			reader = new BufferedReader(new InputStreamReader(this.incoming.getInputStream()));
			writer = new PrintStream(this.incoming.getOutputStream());
			String tempstr="";
			List<String> temp = new ArrayList<String>();
			while(true){
				tempstr = reader.readLine();
				if(tempstr.trim().equalsIgnoreCase("bye")){
					reader.close();
					writer.close();
					this.incoming.close();
					System.out.println("Server: The broker has already sent the 'bye' to close!");
					System.out.println("Server: This Connection is going to close");
					this.serverhoopimple.close();
					break;
				}
				switch(tempstr){
				case "city":
					System.out.println("Server: The brokerHOPP has sent a query city request!");
					List<String> cities = new ArrayList<String>();
					cities = this.serverhoopimple.queryCity();
					if(cities.size()==0){
						System.out.println("don't have any cities in the server!");
						writer.print("NO"+Constants.CR_LF);
						break;
					}
					writer.print("YES"+Constants.CR_LF);
					System.out.println("Server: Cities you have requested!");
					for(String city:cities){
						System.out.println(city);
						writer.print(city + Constants.CR_LF);
					}
					writer.print(Constants.EOS+Constants.CR_LF);
					break;
				case "hotelName":
					System.out.println("Server: The brokerHOPP has sent a query hotelname request!");
					String q = reader.readLine();
					List<String> hotels = new ArrayList<String>();
					hotels = this.serverhoopimple.queryByCityName(q);	
					if(hotels.size()==0){
						System.out.println("Server: don't have any hotels in this city!");
						writer.print("NO"+Constants.CR_LF);
						break;
					}
					writer.print("YES"+Constants.CR_LF);
					System.out.println("Server: Hotels you have requested!");
					for(String hotel:hotels){
						System.out.println(hotel);
						writer.print(hotel+ Constants.CR_LF);
					}
					writer.print(Constants.EOS+Constants.CR_LF);
					break;
				case "roomrate":
					System.out.println("Server: The brokerHOPP has sent a query Room rate!");
					String c = reader.readLine();
					int g = Integer.parseInt(reader.readLine());
					String roomrate = this.serverhoopimple.queryRoomrates(c,g);
					System.out.println("Server: Room Rate you have requested!");
					System.out.println(roomrate);
					writer.print(roomrate+Constants.CR_LF);					
					break;
				case "trans":
					System.out.println("Server: The brokerHOPP has sent a transaction query!");
					temp.clear();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
								break;
						}
						temp.add(tempstr);
						}
					int m = Integer.parseInt(temp.get(1)); 
					Transaction trans = this.serverhoopimple.queryTransaction(temp.get(0),m);
					if(trans==null){
						System.out.println("Server: NO Transaction!");
						writer.print("NO"+Constants.CR_LF);
					}else{
						System.out.println("Server: The Transaction is:");
						writer.print("YES"+Constants.CR_LF);
						writer.print(trans.getTranID()+Constants.CR_LF);
						writer.print(trans.getRoomID()+Constants.CR_LF);
						writer.print(trans.getBookDT()+Constants.CR_LF);
						writer.print(trans.getBookerID()+Constants.CR_LF);
						writer.print(trans.getCreditNO()+Constants.CR_LF);
						writer.print(trans.getHotelID()+Constants.CR_LF);
					}
					break;
				case "vacancies":
					System.out.println("Server: The brokerHOPP has sent a searching vacant rooms query!");
					temp.clear();
					List<Room> rooms = new ArrayList<Room>();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						temp.add(tempstr);
					}
					int s =Integer.parseInt(temp.get(3));
					rooms = this.serverhoopimple.queryVacantrooms(temp.get(0), temp.get(1), temp.get(2),s);
					if(rooms.size()==0){
						System.out.println("Server: NO ROOMS!");
						writer.print("NO"+Constants.CR_LF);
					}else{
					System.out.println("Server: The vacant rooms are:");
					writer.print("YES"+Constants.CR_LF);
					for(Room room:rooms){
						System.out.println("Server: Room ID is: "+room.getRoomID());
						writer.print(room.getRoomID()+Constants.CR_LF);
						writer.print(room.getCheckindate()+Constants.CR_LF);
						writer.print(room.getCheckoutdate()+Constants.CR_LF);
						writer.print(room.getHotelID()+Constants.CR_LF);
						writer.print(room.getIsavail()+Constants.CR_LF);
						
					}
					writer.print(Constants.EOS+ Constants.CR_LF);
					}
					break;
				case "insertbks":
					System.out.println("Server: The brokerHOPP has a insert request!");
					temp.clear();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						temp.add(tempstr);
					}
					int b = Integer.parseInt(temp.get(5));
					String insertinfo = this.serverhoopimple.insertBookers(temp.get(0),temp.get(1) , temp.get(2), temp.get(3), temp.get(4),b); 
					System.out.println("Server: Insert Bookers:");
					System.out.println(insertinfo);
					writer.print(insertinfo+ Constants.CR_LF);
					break;
				case "booking":
					System.out.println("Server: The brokerHOPP has a booking request!");
					temp.clear();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						temp.add(tempstr);
					}
					int t = Integer.parseInt(temp.get(6));
					String booking = this.serverhoopimple.booking(temp.get(0),temp.get(1) , temp.get(2), temp.get(3), 
							temp.get(4), temp.get(5), t);
					System.out.println("Server: Booking:");
					System.out.println(booking);
					writer.print(booking+ Constants.CR_LF);
					break;
				case "hotels":
					System.out.println("Server: The brokerHOPP has query hotels request!");
					temp.clear();
					List<Hotel> hotelss = new ArrayList<Hotel>();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						temp.add(tempstr);
					}
					int f = Integer.parseInt(temp.get(1));
					hotelss = this.serverhoopimple.queryHotel(temp.get(0), f);
					if(hotelss.size()==0){
						System.out.println("Server: NO Hotels!");
						writer.print("NO"+Constants.CR_LF);
					}else{
						List<Room> roomss= new ArrayList<Room>();
						writer.print("YES"+Constants.CR_LF);
						for(Hotel hotel:hotelss){
							System.out.println("Server: Hotel ID is: "+hotel.getHotelID());
							writer.print(hotel.getHotelID()+Constants.CR_LF);
							writer.print(hotel.getCitystr()+Constants.CR_LF);
							writer.print(hotel.getLocation()+Constants.CR_LF);
							writer.print(hotel.getBandstr()+Constants.CR_LF);
							writer.print(hotel.getVacantrooms()+Constants.CR_LF);
							writer.print(hotel.getRoomrate()+Constants.CR_LF);
							writer.print(hotel.getRoomNO()+Constants.CR_LF);	
							roomss = hotel.getRoom();
							if(roomss.size()==0){
								System.out.println("Server: This hotel doesn't have any rooms!");
								writer.print("NO"+Constants.CR_LF);
							}else{
								writer.print("YES"+Constants.CR_LF); 
							for(Room room:roomss){
								System.out.println("Server: Room ID is: "+room.getRoomID());
								writer.print(room.getRoomID()+Constants.CR_LF);
								writer.print(room.getCheckindate()+Constants.CR_LF);
								writer.print(room.getCheckoutdate()+Constants.CR_LF);
								writer.print(room.getHotelID()+Constants.CR_LF);
								writer.print(room.getIsavail()+Constants.CR_LF);
								}
							writer.print(Constants.EOS+ Constants.CR_LF);
							}
							}
						writer.print(Constants.EOS+ Constants.CR_LF);
					}
					break;
				case "bookers":
					System.out.println("Server: The brokerHOPP has query bookers request!");
					temp.clear();
					List<Booker> bookers = new ArrayList<Booker>();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						temp.add(tempstr);
					}
					int i = Integer.parseInt(temp.get(1));
					bookers = this.serverhoopimple.queryBookerinfo(temp.get(0), i);
					if(bookers.size()==0){
						System.out.println("Server: NO Bookerinfo!");
						writer.print("NO"+Constants.CR_LF);
					}else{
						writer.print("YES"+Constants.CR_LF);
						for(Booker booker:bookers){
							System.out.println("Server: Booker ID is: "+booker.getBookerID());
							writer.print(booker.getBookerID()+Constants.CR_LF);
							writer.print(booker.getBookerName()+Constants.CR_LF);
							writer.print(booker.getTelePhone()+Constants.CR_LF);
							writer.print(booker.getEmail()+Constants.CR_LF);
							writer.print(booker.getPasswd()+Constants.CR_LF);
							writer.print(booker.getBookinfo1()+Constants.CR_LF);
							writer.print(booker.getBookinfo2()+Constants.CR_LF);
							}
						writer.print(Constants.EOS+ Constants.CR_LF);
					}
					break;
				default:
					break;
			}
			}
			}catch(IOException e){
			e.printStackTrace();
			return;
			}
		}
	}
	
			


