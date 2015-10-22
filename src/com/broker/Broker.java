package broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;





import classes.Booker;
import classes.Hotel;
import classes.Room;
import classes.Transaction;
import util.Constants;

public class Broker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serversocket =null;
		try{
			serversocket = new ServerSocket(Constants.PORT);		
		}catch(IOException e){
			e.printStackTrace();
			return;
		}
		System.out.println("Get the broker server started!");
		while(true){
			Socket incoming = null;
			try{
				incoming = serversocket.accept();
			}catch(IOException e){
				//e.printStackTrace();
				return;
			}
			new ClientHandler(incoming).start();
		}

	}

}

class ClientHandler extends Thread {

	Socket incoming;
	BufferedReader reader;
	PrintStream writer;
	BrokerNHOPPServer bnhs;

	public ClientHandler(Socket incoming) {
		this.incoming = incoming;
		bnhs = new BrokerNHOPPServer();
	}

	public void run() {
		System.out.println("Broker: Broker is now running");
		String buffer="";
		try{
			reader = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			writer = new PrintStream(incoming.getOutputStream());
			
		}catch(IOException e){
			e.printStackTrace();
			bnhs.sendMsgBYE();
			return;		
		}
		while(true){
			try{
				buffer= reader.readLine();
				if(buffer.equals(Constants.BYE)){
					System.out.println("Broker: Client has sent a 'bye' request!");
					bnhs.sendMsgBYE();
					reader.close();
					writer.close();
					incoming.close();
					break;
				}
			}catch(IOException e){
				e.printStackTrace();
				bnhs.sendMsgBYE();
				return;
			}
			try{
				List<String> parameters = new ArrayList<String>();
				String tempstr="";
				switch(buffer){
				case "city":
					System.out.println("Broker: The Cities serviced by the system!");
					if(reader.readLine().equals(Constants.SOS)){
						List<String> cities = new ArrayList<String>();
						cities = bnhs.queryCity();
						if(cities.size()==0){
							System.out.println("Broker: The system don't supported any cities!");
							writer.print("NO"+Constants.CR_LF);
							break;
						}
						writer.print("YES"+Constants.CR_LF);
						System.out.println("Broker: Sending the formation!");
						for(String city:cities){
							System.out.println(city);
							writer.print(city+Constants.CR_LF);
						}
						writer.print(Constants.EOS+Constants.CR_LF);
					}
					break;
				case "hotelName":
					System.out.println("Broker: Client sends a query hotel request!");
					String cityName="";
					List<String> hotels = new ArrayList<String>();
					cityName = reader.readLine();
					hotels = bnhs.queryByCityName(cityName);
					if(hotels.size()==0){
						System.out.println("Broker: This city don't have any hotels!");
						writer.print("NO"+Constants.CR_LF);
						break;
					}
					writer.print("YES"+Constants.CR_LF);
					System.out.println("Broker: Sending the information!");
					for(String hotel:hotels){
						System.out.println(hotel);
						writer.print(hotel+Constants.CR_LF);
					}
					writer.print(Constants.EOS+Constants.CR_LF);
					break;
				case "roomrate":
					System.out.println("Broker: Client sends a query room rate request!");
					String roomrate=null;
					tempstr="";
					parameters.clear();
					while(true){
						tempstr=reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						parameters.add(tempstr);
					}
					
					if(parameters.size()==2){
						int t = Integer.parseInt(parameters.get(1));
						roomrate = bnhs.queryRoomrates(parameters.get(0), t);
					}
					System.out.println("Broker: Sending the information!");
					writer.print(roomrate+Constants.CR_LF);
					break;
				case "trans":
					System.out.println("Broker: The brokerHOPP has sent a transaction query!");
					parameters.clear();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
								break;
						}
						parameters.add(tempstr);
						}
					int m = Integer.parseInt(parameters.get(1)); 
					Transaction trans = this.bnhs.queryTransaction(parameters.get(0),m);		
					if(trans==null){
						System.out.println("Broker: NO Transaction!");
						writer.print("NO"+Constants.CR_LF);
					}else{
						System.out.println("Broker: Sending the information:");
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
					System.out.println("Broker: Client sends a query room vacancy!");
					tempstr = "";
					parameters.clear();
					List<Room> vacancies = new ArrayList<Room>();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						parameters.add(tempstr);
					}
					if(parameters.size()==4){
						int s = Integer.parseInt(parameters.get(3));
						vacancies = bnhs.queryVacantrooms(parameters.get(0), parameters.get(1), parameters.get(2), s);
					}
					if(vacancies.size()==0){
						System.out.println("Broker: NO Vacant Rooms!");
						writer.print("NO"+Constants.CR_LF);
						break;
					}
					writer.print("YES"+Constants.CR_LF);
					System.out.println("Broker: Sending the information!");
					for(Room vacancy:vacancies){
						writer.print(vacancy.getRoomID()+Constants.CR_LF);
						writer.print(vacancy.getCheckindate()+Constants.CR_LF);
						writer.print(vacancy.getCheckoutdate()+Constants.CR_LF);
						writer.print(vacancy.getHotelID()+Constants.CR_LF);
						writer.print(vacancy.getIsavail()+Constants.CR_LF);
					}
					writer.print(Constants.EOS+Constants.CR_LF);
					break;
				case "insertbks":
					System.out.println("Broker: Client sends a insert booker request!");
					tempstr = "";
					parameters.clear();
					String insertinfo=null;
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						parameters.add(tempstr);
					}
					if(parameters.size()==6){
						int k = Integer.parseInt(parameters.get(5));
						insertinfo = bnhs.insertBookers(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), 
								parameters.get(4), k);
					}
					writer.print("YES"+Constants.CR_LF);
					System.out.println("Broker: Sending the information: "+insertinfo);
					writer.print(insertinfo+Constants.CR_LF);			
					break;
				case "booking":
					System.out.println("Broker: Client sends a query booking request!");
					tempstr = "";
					parameters.clear();
					String bookinfo=null;
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						parameters.add(tempstr);
					}
					if(parameters.size()==7){
						int k = Integer.parseInt(parameters.get(6));
						bookinfo = bnhs.booking(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), 
								parameters.get(4), parameters.get(5),k);
					}
					writer.print("YES"+Constants.CR_LF);
					System.out.println("Broker: Sending the information: "+bookinfo);
					writer.print(bookinfo + Constants.CR_LF);			
					break;
				case "hotels":
					System.out.println("Broker: Client has query hotels request!");
					parameters.clear();
					List<Hotel> hotelss = new ArrayList<Hotel>();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						parameters.add(tempstr);
					}
					int f = Integer.parseInt(parameters.get(1));
					hotelss = this.bnhs.queryHotel(parameters.get(0), f);
					if(hotelss.size()==0){
						System.out.println("Broker: NO Hotels!");
						writer.print("NO"+Constants.CR_LF);
					}else{
						List<Room> roomss= new ArrayList<Room>();
						writer.print("YES"+Constants.CR_LF);
						for(Hotel hotel:hotelss){
							System.out.println("Broker: Hotel ID is: "+hotel.getHotelID());
							writer.print(hotel.getHotelID()+Constants.CR_LF);
							writer.print(hotel.getCitystr()+Constants.CR_LF);
							writer.print(hotel.getLocation()+Constants.CR_LF);
							writer.print(hotel.getBandstr()+Constants.CR_LF);
							writer.print(hotel.getVacantrooms()+Constants.CR_LF);
							writer.print(hotel.getRoomrate()+Constants.CR_LF);
							writer.print(hotel.getRoomNO()+Constants.CR_LF);	
							roomss = hotel.getRoom();
							if(roomss.size()==0){
								System.out.println("Broker: This hotel doesn't have any rooms!");
								writer.print("NO"+Constants.CR_LF);
							}else{
								writer.print("YES"+Constants.CR_LF); 
							for(Room room:roomss){
								System.out.println("Broker: Room ID is: "+room.getRoomID());
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
					parameters.clear();
					List<Booker> bookers = new ArrayList<Booker>();
					while(true){
						tempstr = reader.readLine();
						if(tempstr.equals(Constants.EOS)){
							break;
						}
						parameters.add(tempstr);
					}
					int i = Integer.parseInt(parameters.get(1));
					bookers = this.bnhs.queryBookerinfo(parameters.get(0), i);
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
			}catch(IOException e){
				e.printStackTrace();
				bnhs.sendMsgBYE();
				return;
			}
			
		}

	}
}
		