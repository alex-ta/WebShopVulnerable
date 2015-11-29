package database.tables;

import java.util.Date;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import database.extension.DatabaseTable;

@Entity
public class Invoice extends DatabaseTable<Invoice>{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idinv")
	@SequenceGenerator(initialValue = 1, name = "idinv", sequenceName = "entityinv")
	private int id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orderid",referencedColumnName="id")
	private Order order;
	private Integer payload;
	private Date payed;
	private Date overtime;
	@Embedded
	private Address address;
	public Invoice (){
		super();
	}

	public Invoice(Order charge, int payload, Date payed, Date overtime,Address address) {
		this();
		this.order = charge;
		this.payload = payload;
		this.payed = payed;
		this.overtime = overtime;
		this.address = address;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		System.out.println("cannt be put in a map");
	}

	@Override
	public void update(Invoice instance) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initMapping() {
		typeMap.put("Address", "object");
		typeMap.put("payed", "number");
		typeMap.put("payload","number");
		typeMap.put("Charge", "object");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order charge) {
		if(this.checkObject(charge)){
			this.order = charge;
		}
	}

	public Integer getPayload() {
		return payload;
	}

	public void setPayload(int payload) {
		if(this.checkInteger(payload)){
			this.payload = payload;
		}
	}

	public Date getPayed() {
		return payed;
	}

	public void setPayed(Date payed) {
		if(this.checkObject(payed)){
			this.payed = payed;
		}
	}

	public Date getOvertime() {
		return overtime;
	}

	public void setOvertime(Date overtime) {
		if(this.checkObject(overtime)){
			this.overtime = overtime;
			}
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		if(this.checkObject(address)){
			this.address = address;
			}
	}

}
