package dto;

import java.sql.Date;
import java.sql.Timestamp;

public class PostDTO {
	private Long id;
	private String address;
	private int area;
	private String house_type;
	private int num_rooms;
	private int floor;
	private Long price;
	private String description;
	private Long year;
	private Timestamp creation_date;
	private String phone;
	private boolean archived;
	
	public PostDTO() {
		this.archived = false;
	}
	
	public PostDTO(String _address, int _area, String _house_type, int _num_rooms, int _floor, 
			Long _price, String _description, Long _year, Timestamp _creation_date, String _phone, boolean _archived) {
		this.address = _address;
		this.area = _area;
		this.house_type = _house_type;
		this.num_rooms = _num_rooms;
		this.floor = _floor;
		this.price = _price;
		this.description = _description;
		this.year = _year;
		this.creation_date = _creation_date;
		this.phone = _phone;
		this.archived = _archived;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHouse_type() {
		return house_type;
	}

	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getNum_rooms() {
		return num_rooms;
	}

	public void setNum_rooms(int num_rooms) {
		this.num_rooms = num_rooms;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Timestamp getCreationDate() {
		return creation_date;
	}

	public void setCreationDate(Timestamp creation_date) {
		this.creation_date = creation_date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	
}
