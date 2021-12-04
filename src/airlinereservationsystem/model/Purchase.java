package airlinereservationsystem.model;
import java.sql.Timestamp;

public class Purchase {
	
	private int prcID;
	private int tID;
	private int pId;
	private int price;
	private Timestamp purchaseDate;
	
	public int getPrcID() {
		return prcID;
	}
	
	public void setPrcID(int prcID) {
		this.prcID = prcID;
	}
	
	public int gettID() {
		return tID;
	}
	
	public void settID(int tID) {
		this.tID = tID;
	}
	
	public int getpId() {
		return pId;
	}
	
	public void setpId(int pId) {
		this.pId = pId;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}
	
	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	
	@Override
	public String toString() {
		return "Purchase [prcID=" + prcID + ", tID=" + tID + ", pId=" + pId + ", price=" + price + ", purchaseDate="
				+ purchaseDate + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pId;
		result = prime * result + prcID;
		result = prime * result + price;
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		result = prime * result + tID;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		if (pId != other.pId)
			return false;
		if (prcID != other.prcID)
			return false;
		if (price != other.price)
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		if (tID != other.tID)
			return false;
		return true;
	}

}
