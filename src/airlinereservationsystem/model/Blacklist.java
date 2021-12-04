package airlinereservationsystem.model;

import java.sql.Timestamp;

public class Blacklist {
	private int pID;
	private int aID;
	private String reason;
	private Timestamp creationDate;
	private Timestamp updateTime;
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public int getaID() {
		return aID;
	}
	public void setaID(int aID) {
		this.aID = aID;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aID;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + pID;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
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
		Blacklist other = (Blacklist) obj;
		if (aID != other.aID)
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (pID != other.pID)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Blacklist [pID=" + pID + ", aID=" + aID + ", reason=" + reason + ", creationDate=" + creationDate
				+ ", updateTime=" + updateTime + "]";
	}
	
}
