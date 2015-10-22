package client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import classes.Booker;
import classes.Hotel;
import classes.Room;
import classes.Transaction;
import util.Constants;
import util.RegularExpression;;

public class Client {
	
	protected BufferedReader console;
	protected ClientHOPPImple clientHOPP;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.loop();
	}
	
	public Client(){
		clientHOPP = null;
		try{
			clientHOPP = new ClientHOPPImple();
		}catch(Exception e){ 
			e.printStackTrace();
			System.exit(1);
		}
		console = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void loop(){	
		 System.out.println("*******************************************************************************");
		 System.out.println(Constants.CITY+"\t--Query the Cities Supported by the System!");
		 System.out.println(Constants.HOTEL+"\t--Input the City Name to get the hotels "
		 		+ "Supported this city!");
		 System.out.println(Constants.LISTHOTEL+"\t--Input the City Name to get the all the hotel information! "
			 		+ "Supported this city!");
		 System.out.println(Constants.ROOMRATE+"\t--Query the Room Rate of your requested Hotel!");
		 System.out.println(Constants.VACANT+ "\t--Input hotel ID, Check-in and Check-out Dates"
		 		+ "to get the vacant rooms in this hotel!");
		 System.out.println(Constants.BOOK+"\t--Input Check-in and Check-out Dates, hotel ID, "
		 		+ "room ID and you information to book the room you specify!");
		 System.out.println(Constants.USER+"\t--Input U(USER) to get information about the informatin about the User!");
		 System.out.println(Constants.QUIT+"\t--Input Q(QUIT) to exit the system!");
		 System.out.println("*******************************************************************************");
		 System.out.println("Please Use the following Commands to "
					+ "get the results you request! ");
		while(true){	
			String line = null;
			try{
				System.out.println();
				System.out.println();
				System.out.print("Enter Request: C | LH <cityname> | HI <cityname>"
						+ " | RR | V | B | U |Q: ");
				line = console.readLine().trim();
			 if (line.equalsIgnoreCase(Constants.CITY)) {
			      listCity();
			      } else if (line.toUpperCase().startsWith(Constants.HOTEL)) {
			        listHotel(losePrefix(line, Constants.HOTEL));
			      } else if (line.toUpperCase().startsWith(Constants.LISTHOTEL)){
			    	listHotelinfo(losePrefix(line, Constants.LISTHOTEL));	    	
			      } else if (line.equalsIgnoreCase(Constants.ROOMRATE)) {
			    	 int flag = 0;
			  		 int count = 0;
			    	 while(true){ 
			    	  System.out.print("Brand: ");
			    	  String brand =console.readLine().trim();
			    	  while((brand.equalsIgnoreCase("hilton")==false) && (brand.equalsIgnoreCase("chevron")==false) && (brand.equalsIgnoreCase("regent")==false)){
			    		    if(++count == 3){
			    			  System.out.println("You have tried 3 times!");
			    			  flag=1;
			    			  break;
			    		  }
			    		  System.out.print("NO This Brand! Try Again: ");
			    		  brand =console.readLine().trim();	
			    	  } 
			    	  if(flag==1){
			    		  break;
			    	  }
			    	  count=0;
			    	  System.out.print("Hotel NO.: ");
			    	  String hotelID = console.readLine().trim();
			    	  while(RegularExpression.isNumeric(hotelID)==false){  
			    		  if(++count == 3){
			    			  System.out.println("You have tried 3 times!");
			    			  flag=1;
			    			  break;
			    		  }
			    		  System.out.print("The Hotel NO. is illegal! Try Again: ");
			    		  hotelID = console.readLine().trim();	
			    	  }
			    	  if(flag==1){
			    		  break;
			    	  }
			    	  boolean room = getRoomrate(hotelID, brand);
			    	  if(room == false){
			    		 System.out.println("Go Back to Main Menu! ");
			    	  }
			    	  break;
			    	  }
			      } else if (line.equalsIgnoreCase(Constants.VACANT)){
			    	  int flag = 0;
			    	  int count = 0;
			    	 while(true){
			    	  System.out.print("Brand: ");
			    	  String brand = console.readLine().trim();
			    	  while((brand.equalsIgnoreCase("hilton")==false) && (brand.equalsIgnoreCase("chevron")==false) && (brand.equalsIgnoreCase("regent")==false)){
			    		  if(++count == 3){
			    			  System.out.println("You have tried 3 times!");
			    			  flag=1;
			    			  break;
			    		  }
			    		  System.out.print("NO This Brand! Try Again: ");
			    		  brand =console.readLine().trim();
			    	  } 
			    	  if(flag==1){
			    		  break;
			    	  }
			    	  count=0;
			    	  System.out.print("Hotel NO.: ");
			    	  String hotelid = console.readLine().trim();
			    	  while(RegularExpression.isNumeric(hotelid)==false){ 
			    		  if(++count == 3){
			    			  System.out.println("You have tried 3 times!");
			    			  flag=1;
			    			  break;
			    		  }
			    		  System.out.print("The Hotel NO. is illegal! Try Again: ");
			    		  hotelid = console.readLine().trim();		    		 
			    	  }
			    	  if(flag==1){
			    		  break;
			    	  }
			    	  count=0;
			    	  System.out.print("Check-in date: ");
			    	  String checkindate = console.readLine().trim();
			    	  System.out.print("Check-out date: ");
			    	  String checkoutdate = console.readLine().trim();
			    	  while(RegularExpression.checkDate(checkindate, checkoutdate)==false){
			    		  if(++count == 3){
			    			  System.out.println("You have tried 3 times!");
			    			  flag=1;
			    			  break;
			    		  }
			    		  System.out.println("The Date is illegal!");
			    		  System.out.print("Check-in date: ");
				    	  checkindate = console.readLine().trim();
				    	  System.out.print("Check-out date: ");
				    	  checkoutdate = console.readLine().trim();
				    	  System.out.println();
				    	 
			    	  }
			    	  if(flag==1){
			    		  break;
			    	  }
			    	  boolean vacant = listVacantRooms(hotelid,checkindate,checkoutdate,brand);
			    	  if(vacant == true){
			    		  System.out.println("These rooms are vacant, and You can select whatever you like!");
			    	  }else{
			    		  System.out.println("Go Back to Main Menu!");
			    	  }
			    	  break;
				    }
			      }else if (line.equalsIgnoreCase(Constants.USER)){
			    	  int count = 0;
			    	  int flag =  0;
				    	 while(true){
				    	  System.out.print("Brand: ");
				    	  String brand = console.readLine().trim();
				    	  while((brand.equalsIgnoreCase("hilton")==false) && (brand.equalsIgnoreCase("chevron")==false) && (brand.equalsIgnoreCase("regent")==false)){
				    		  if(++count == 3){
				    			  System.out.println("You have tried 3 times!");
				    			  flag=1;
				    			  break;
				    		  }
				    		  System.out.print("NO This Brand! Try Again: ");
				    		  brand =console.readLine().trim();
				    	  } 
				    	  if(flag==1){
				    		  break;
				    	  }
				    	  count=0;
				    	  System.out.print("Do You hava an  Account in this Hotel (YES/NO) ? ");
				    	  String temp = console.readLine();
				    	  if(temp.equalsIgnoreCase("NO")){
				    		  System.out.print("Input Your ID NO.: ");
				    		  String bookerid = console.readLine().trim();
					    	  while(RegularExpression.isNumeric(bookerid)==false){ 
					    		  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
					    		  System.out.print("Your ID NO. is illegal! Try Again: ");
					    		  bookerid = console.readLine().trim();		    		 
					    	  }
					    	  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  System.out.print("Your Name: ");
				    		 String bookername = console.readLine();
				    		  System.out.print("Input Your Password: ");
				    		 String passwd = console.readLine();
				    		 while(RegularExpression.checkPassword(passwd)==false){
				    			  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
				    			  System.out.println("Password is illegal! Try Again!");
				    			  System.out.print("Input Your Password: ");
					    		  passwd = console.readLine();
				    		  }
				    		  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  System.out.print("Confirm Your Password: ");
				    		  String passwd1 = console.readLine();
				    		  while(RegularExpression.checkPassword(passwd)==false){
				    			  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
				    			  System.out.println("Password is illegal! Try Again!");
				    			  System.out.print("Comfirm Your Password: ");
					    		  passwd1 = console.readLine();
				    		  }
				    		  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  while(passwd.equals(passwd1)==false){
				    			  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
				    			  System.out.println("Password is not equal! Try Again!");
				    			  System.out.print("Input Your Password: ");
					    		  passwd = console.readLine();
					    		  System.out.print("Confirm Your Password: ");
					    		  passwd1 = console.readLine();
				    		  }
				    		  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  System.out.print("Your Tele NO.: ");
				    		  String tele = console.readLine().trim();
				    		  while(RegularExpression.checkTel(tele)==false){  
					    		  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
					    		  System.out.print("You Tele NO. is illegal! Try Again: ");
					    		  tele = console.readLine().trim();		    		 
					    	  }
					    	  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  System.out.print("Your E-mail: ");
				    		  String mail = console.readLine().trim();
				    		  while(RegularExpression.checkEmai(mail)==false){ 
					    		  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
					    		  System.out.print("Your Email is illegal! Try Again: ");
					    		  mail = console.readLine().trim();		    		 
					    	  }
					    	  if(flag==1){
					    		  break;
					    	  }
					    	  boolean isinsert = insertBookers(bookerid, bookername, tele, mail, passwd, brand);
				    	  }
				    	  if((!temp.equalsIgnoreCase("YES"))&& (!temp.equalsIgnoreCase("NO"))){
				    		  System.out.println("Input illegal, exit!");
				    		  break;
				    	  }
				    	  count=0;
				    	  System.out.print("Your Account NO.: ");
				    	  String bookerid = console.readLine().trim();
				    	  while(RegularExpression.isNumeric(bookerid)==false){ 
				    		  if(++count == 3){
				    			  System.out.println("You have tried 3 times!");
				    			  flag=1;
				    			  break;
				    		  }
				    		  System.out.print("Your Account NO. is illegal! Try Again: ");
				    		  bookerid = console.readLine().trim();		    		 
				    	  }
				    	  if(flag==1){
				    		  break;
				    	  }
				    	  count=0;
				    	  System.out.print("Your PassWord: ");
				    	  String passwd = console.readLine();
				    	  while(login(bookerid,passwd,brand)==false){
				    		  if(++count == 3){
				    			  System.out.println("You have tried 3 times!");
				    			  flag=1;
				    			  break;
				    		  }
				    		  System.out.print("Your Password is not Correct or Your Account ID doesn't exit! Try Again: ");
				    		  passwd = console.readLine().trim();		    		 
				    	  }
				    	  if(flag==1){
				    		  break;
				    	  }
				    	  boolean booker = listBookerinfo(bookerid,brand);
				    	  if(booker == true){
				    		  System.out.println();
				    		  System.out.println("This is Your Personal information!");
				    	  }else{
				    			  System.out.println("Go Back to Main Menu!");
				    		  }
				    	  break;
				    	  }  
			      }else if (line.equalsIgnoreCase(Constants.BOOK)){
			    	    int count = 0;
			    	    int flag = 0;
				    	 while(true){ 
				    	  System.out.print("Brand: ");
				    	  String brand = console.readLine().trim();
				    	  while((brand.equalsIgnoreCase("hilton")==false) && (brand.equalsIgnoreCase("chevron")==false) && (brand.equalsIgnoreCase("regent")==false)){
				    		  if(++count == 3){
				    			  System.out.println("You have tried 3 times!");
				    			  flag=1;
				    			  break;
				    		  }
				    		  System.out.print("NO This Brand! Try Again: ");
				    		  brand =console.readLine().trim();
				    	  } 
				    	  if(flag==1){
				    		  break;
				    	  }
				    	  count=0;
				    	  System.out.print("Do You hava an  Account in this Hotel (YES/NO) ? ");
				    	  String temp = console.readLine();
				    	  if(temp.equalsIgnoreCase("NO")){
				    		  System.out.print("Input Your ID NO.: ");
				    		  String bookerid = console.readLine().trim();
					    	  while(RegularExpression.isNumeric(bookerid)==false){ 
					    		  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
					    		  System.out.print("Your ID NO. is illegal! Try Again: ");
					    		  bookerid = console.readLine().trim();		    		 
					    	  }
					    	  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  System.out.print("Your Name: ");
				    		 String bookername = console.readLine();
				    		  System.out.print("Input Your Password: ");
				    		 String passwd = console.readLine();
				    		 while(RegularExpression.checkPassword(passwd)==false){
				    			  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
				    			  System.out.println("Password is illegal! Try Again!");
				    			  System.out.print("Input Your Password: ");
					    		  passwd = console.readLine();
				    		  }
				    		  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  System.out.print("Confirm Your Password: ");
				    		  String passwd1 = console.readLine();
				    		  while(RegularExpression.checkPassword(passwd)==false){
				    			  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
				    			  System.out.println("Password is illegal! Try Again!");
				    			  System.out.print("Comfirm Your Password: ");
					    		  passwd1 = console.readLine();
				    		  }
				    		  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  while(passwd.equals(passwd1)==false){
				    			  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
				    			  System.out.println("Password is not equal! Try Again!");
				    			  System.out.print("Input Your Password: ");
					    		  passwd = console.readLine();
					    		  System.out.print("Confirm Your Password: ");
					    		  passwd1 = console.readLine();
				    		  }
				    		  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  System.out.print("Your Tele NO.: ");
				    		  String tele = console.readLine().trim();
				    		  while(RegularExpression.checkTel(tele)==false){  
					    		  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
					    		  System.out.print("You Tele NO. is illegal! Try Again: ");
					    		  tele = console.readLine().trim();		    		 
					    	  }
					    	  if(flag==1){
					    		  break;
					    	  }
					    	  count=0;
				    		  System.out.print("Your E-mail: ");
				    		  String mail = console.readLine().trim();
				    		  while(RegularExpression.checkEmai(mail)==false){ 
					    		  if(++count == 3){
					    			  System.out.println("You have tried 3 times!");
					    			  flag=1;
					    			  break;
					    		  }
					    		  System.out.print("Your Email is illegal! Try Again: ");
					    		  mail = console.readLine().trim();		    		 
					    	  }
					    	  if(flag==1){
					    		  break;
					    	  }
					    	  boolean isinsert = insertBookers(bookerid, bookername, tele, mail, passwd, brand);
				    	  }
				    	  if((!temp.equalsIgnoreCase("YES"))&& (!temp.equalsIgnoreCase("NO"))){
				    		  System.out.println("Input illegal, exit!");
				    		  break;
				    	  }
				    	  count=0;
				    	  System.out.print("Your Account NO.: ");
				    	  String bookerid = console.readLine().trim();
				    	  while(RegularExpression.isNumeric(bookerid)==false){ 
				    		  if(++count == 3){
				    			  System.out.println("You have tried 3 times!");
				    			  flag=1;
				    			  break;
				    		  }
				    		  System.out.print("Your Account NO. is illegal! Try Again: ");
				    		  bookerid = console.readLine().trim();		    		 
				    	  }
				    	  if(flag==1){
				    		  break;
				    	  }
				    	  count=0;
				    	  System.out.print("Your PassWord: ");
				    	  String passwd = console.readLine();
				    	  while(login(bookerid,passwd,brand)==false){
				    		  if(++count == 3){
				    			  System.out.println("You have tried 3 times!");
				    			  flag=1;
				    			  break;
				    		  }
				    		  System.out.print("Your Password is not Correct or Your Account ID doesn't exit! Try again: ");
				    		  passwd = console.readLine().trim();		    		 
				    	  }
				    	  if(flag==1){
				    		  break;
				    	  }
				    	  count=0;
				    	  System.out.print("Hotel NO.: ");
				    	  String hotelid = console.readLine().trim();
				    	  while(RegularExpression.isNumeric(hotelid)==false){ 
				    		  if(++count == 3){
				    			  System.out.println("You have tried 3 times!");
				    			  flag=1;
				    			  break;
				    		  }
				    		  System.out.print("The Hotel NO. is illegal! Try Again: ");
				    		  hotelid = console.readLine().trim();		    		 
				    	  }
				    	  if(flag==1){
				    		  break;
				    	  }
				    	  count=0;
				    	  System.out.print("Room NO.: ");
				    	  String roomid = console.readLine().trim();
				    	  while(RegularExpression.isNumeric(roomid)==false){ 
				    		  if(++count == 3){
				    			  System.out.println("You have tried 3 times!");
				    			  flag=1;
				    			  break;
				    		  }
				    		  System.out.print("The Room NO. is illegal! Try Again: ");
				    		  roomid = console.readLine().trim();		    		 
				    	  }
				    	  if(flag==1){
				    		  break;
				    	  }
				    	  count=0;
				    	  System.out.print("Check-in date: ");
				    	  String checkindate = console.readLine().trim();
				    	  System.out.print("Check-out date: ");
				    	  String checkoutdate = console.readLine().trim();
				    	  while(RegularExpression.checkDate(checkindate, checkoutdate)==false){
				    		  if(++count == 3){
				    			  System.out.println("You have tried 3 times!");
				    			  flag=1;
				    			  break;
				    		  }
				    		  System.out.println("The Date is illegal!");
				    		  System.out.print("Check-in date: ");
					    	  checkindate = console.readLine().trim();
					    	  System.out.print("Check-out date: ");
					    	  checkoutdate = console.readLine().trim();
					    	  System.out.println();
					    	 
				    	  }
				    	  if(flag==1){
				    		  break;
				    	  }
					     count=0;
				    	 System.out.print("Your credit NO.: ");
				    	 String credit = console.readLine().trim();
				    	 while(RegularExpression.checkCreditNO(credit)==false){
					    	if(++count == 3){
					    		System.out.println("You have tried 3 times!");
					    		flag=1;
					    		break;
					    	}
					    	System.out.print("Your Credit NO. is illegal! Try Again: ");
					    	credit = console.readLine().trim();		    		 
					    }
					    if(flag==1){
					    	break;
					    }
				    	boolean bookinfo = booking(checkindate, checkoutdate, hotelid, roomid, 
				    		               bookerid, credit, brand);
				    	if(bookinfo == true){
				    	}else{	   		
				    		  System.out.println("Booking Failed! Go Back to the Main Menu!");
				    	}
				    	 break;		   
					    }  
			      }else if(line.equalsIgnoreCase(Constants.QUIT)){
			    	  quit();
			    	  System.exit(0);
			      }else {
			    	  System.out.println("Unrecognised command");
			      }
			}catch(IOException e){
			     e.printStackTrace();
			     System.exit(1);
			}
			
		}
	}
	
	public String losePrefix(String str, String prefix) {
		    int index = prefix.length();
		    String ret = str.substring(index).trim();
		    return ret;
	 }
	
	public boolean login(String bookerID, String passwd, String brand){
		  int t= 0;
	  	  if(brand.equalsIgnoreCase("hilton")){
	  		  t = 1;
	  	  }else if(brand.equalsIgnoreCase("chevron")){
	  		  t = 2;
	  	  }else if(brand.equalsIgnoreCase("regent")){
	  		  t = 3;
	  	  }  
	  	  if(t==0){
	  		  System.out.println("NO this "+brand
	  				  +" brand! try again!");	
	  		return false;  
	  	 }else{
		List<Booker> bookers = new ArrayList<Booker>();
		bookers = this.clientHOPP.queryBookerinfo(bookerID, t);
		if(bookers.size()==0){
			return false;
		}else{
		for(Booker booker:bookers){
			if(booker.getPasswd().equals(passwd)){
				return true;
			}
		}
		return false;
		}
	  }
	}
	public void listCity(){
		List<String> cities = new ArrayList<String>();
		cities = this.clientHOPP.queryCity();
		int i=1;
		if(cities.size()==0){
			System.out.println("NO Cities int this System!");
		}else{
		for(String city:cities){
			System.out.println(i+". "+city);
			++i;
		}
		}
	}
	
	public void listHotel(String cityname){
		List<String> hotels = new ArrayList<String>();
		String cityName = cityname.toLowerCase();
		hotels = this.clientHOPP.queryByCityName(cityName);
		if(hotels.size()==0){
			System.out.println("NO Hotels in this City!");
		}else{
			System.out.println("The Hotels: ");
			int i = 1;
		for(String hotel:hotels){
			System.out.println(i+". "+hotel);
			++i;
		}
		}
	} 
	
	public void listHotelinfo(String cityname){	
		String cityName = cityname.toLowerCase();
		for(int i=0;i<3;++i){
			List<Hotel> hotels = new ArrayList<Hotel>();
			hotels = this.clientHOPP.queryHotel(cityName, i+1);		
				if(hotels.size()!=0){
					for(Hotel hotel:hotels){
						System.out.print("Brand: "+ hotel.getBandstr());
						System.out.print("\tHotel NO.: "+ hotel.getHotelID());
						System.out.print("\tCity Name: "+ hotel.getCitystr());
						System.out.print("\tLocation: "+ hotel.getLocation());
						System.out.print("\tRoom Rate: "+ hotel.getRoomrate()+" RMB");
						System.out.print("\tTotal Rooms Number: "+ hotel.getRoomNO());
						System.out.print("\tVacant Rooms Number: "+ hotel.getVacantrooms());
						System.out.println();
					}
				}
			}
		}
	
	public boolean listBookerinfo(String bookerID, String brand){
		if(RegularExpression.isNumeric(bookerID)==false){
			System.out.print("Booker NO. is illegal"); 
			return false;
		}
		  int t= 0;
	  	  if(brand.equalsIgnoreCase("hilton")){
	  		  t = 1;
	  	  }else if(brand.equalsIgnoreCase("chevron")){
	  		  t = 2;
	  	  }else if(brand.equalsIgnoreCase("regent")){
	  		  t = 3;
	  	  }  
	  	  if(t==0){
	  		  System.out.println("NO this "+brand
	  				  +" brand! try again!");	
	  		return false;  
	  	  }else{
	  		  List<Booker> bookers = new ArrayList<Booker>();
	  		  bookers = this.clientHOPP.queryBookerinfo(bookerID, t);
	  		  if(bookers.size()!=0){
	  			  int i=1;
	  			  for(Booker booker:bookers){
	  				 System.out.println(i+"."+" Booker ID: "+ booker.getBookerID()+"\tName: "+booker.getBookerName()+"\tTel.: "+
	  			  booker.getTelePhone() +"\tMail Box: "+booker.getEmail());
	  				 System.out.println("   Booking information: "+booker.getBookinfo1());
	  				 System.out.println("   " + booker.getBookinfo2());
	  				 System.out.println();
	  				 ++i;
	  			  }
	  			  return true;
	  		  }else{
	  			  System.out.println("NO this Booker information!");
	  			  return false;
	  		  }
	  	  }
		
	}
	public boolean getRoomrate(String hotelID, String brand){		 
	  int t= 0;
  	  if(brand.equalsIgnoreCase("hilton")){
  		  t = 1;
  	  }else if(brand.equalsIgnoreCase("chevron")){
  		  t = 2;
  	  }else if(brand.equalsIgnoreCase("regent")){
  		  t = 3;
  	  }  
  	  if(t!=0){		  
		String roomrate = this.clientHOPP.queryRoomrates(hotelID, t);
		if(roomrate.equals("")){
			System.out.println("This hotel doesn't exist!");
			return false;
		}else{
			System.out.println("The Room rate: " + roomrate+" RMB");
			return true;
		}
	}else{
		return false;
	}
	}
	
	public boolean listVacantRooms(String hotelID, String checkindate, 
			String checkoutdate, String brand){
		List<Room> rooms = new ArrayList<Room>();
		 int t= 0;
	  	  if(brand.equalsIgnoreCase("hilton")){
	  		  t = 1;
	  	  }else if(brand.equalsIgnoreCase("chevron")){
	  		  t = 2;
	  	  }else if(brand.equalsIgnoreCase("regent")){
	  		  t = 3;
	  	  }  
	  	  if(t==0){
	  		return false;  
	  	    }else{
		        rooms = this.clientHOPP.queryVacantrooms(hotelID, checkindate, checkoutdate, t);
	  	  }
		if(rooms.size()!=0){
		for(Room room:rooms){
			System.out.println("The Vacant Room NO.: "+room.getRoomID());
		}
		System.out.println();
		return true;
		}else{
			System.out.println("NO Vacant rooms or The hotel NO. you input doesn't exist!");
		}
		return false;
	}
	
	public boolean insertBookers(String bookerID, String bookerName, String telePhone, String email, String passwd,String brand){
		String insertinfo = null;  
		int t= 0;
	  	  if(brand.equalsIgnoreCase("hilton")){
	  		  t = 1;
	  	  }else if(brand.equalsIgnoreCase("chevron")){
	  		  t = 2;
	  	  }else if(brand.equalsIgnoreCase("regent")){
	  		  t = 3;
	  	  }  
	  	  if(t==0){
	  		  System.out.println("NO this "+brand
	  				  +" brand! try again!");	
	  		return false;  
	  	  }else{
	  		  insertinfo = this.clientHOPP.insertBookers(bookerID, bookerName, telePhone, email, passwd, t);
	  		  if(insertinfo.equals(Constants.BOOKERINSERT)){
	  			  System.out.println(insertinfo);
	  			  return true;
	  		  }else{
	  			 System.out.println(insertinfo);
	  			  return false;
	  		  }
		}
	  }
	
	
	public boolean booking(String checkinDate, String checkoutDate, String hotelID, String roomID, 
			String bookerID, String creditNO, String brand){
		String transacID = checkinDate + checkoutDate + hotelID + roomID + bookerID; 
		String book=null;
		int t = 0;
	  	  if(brand.trim().equalsIgnoreCase("hilton")){
	  		  t = 1;
	  	  }else if(brand.trim().equalsIgnoreCase("chevron")){
	  		  t = 2;
	  	  }else if(brand.trim().equalsIgnoreCase("regent")){
	  		  t = 3;
	  	  }  
	  	  if(t==0){
	  		  return false;
	  	  }else{
		        book = this.clientHOPP.booking(checkinDate, checkoutDate, hotelID, roomID, bookerID, creditNO, t);
		        if(book.equalsIgnoreCase(Constants.BOOKSUCCESSFUL)){
		        	System.out.println("Booking Successfully!");
		        	System.out.println();
					System.out.println("Transaction information:");
					System.out.print("Hotel:  "+brand);
		        	queryTrans(transacID, t);
		        	System.out.print("\tCheck-in Date: "+checkinDate+"\tCheck-out Date: "+checkoutDate);
		        	return true;
		        }else{
		        	System.out.println(book);
		        }
	  	  }
	  	  return false;
	}
	
	public void queryTrans(String transactionID, int serverNO){
		Transaction trans = new Transaction();
		trans = this.clientHOPP.queryTransaction(transactionID, serverNO);
		if(trans==null){
			System.out.println("Your Transaction infomation doesn't exit right now!");
		}else{
			System.out.println(" Booker ID: "+ trans.getBookerID()+"\tHotel NO.: "+trans.getHotelID()+
					"\tRoom NO.: "+trans.getRoomID()+"\tBook Date: "+trans.getBookDT());
		}
		
	}
	public void quit(){
		this.clientHOPP.sendMsgBYE();
	}
	}


