package classes;

import java.sql.Date;


public interface TransactionInter {
	public boolean insertBooker(String bookerID,String bookerName, String telePhone, String email, String passwd, int serverNO);
	public boolean insertTransaction(String transactionID, String roomID, String bookDT, String bookerID,
			String creditNO, String hotelID, int serverNO);
	public boolean queryTransaction(String transactionID, int serverNO);
	public boolean queryBooker(String bookerID, int serverNO);
	public String bookingRoom(String checkinDate, String checkoutDate, String hotelID,String transactionID, String roomID, 
			String bookerID,String creditNO, int serverNO);
	public Transaction queryTran(String transactionID, int serverNO);

}
