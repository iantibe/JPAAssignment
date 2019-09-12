package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.Table;

@Entity
@Table(name="coininfo")
public class CoinItem {
	
		//properties
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="ID")
		private int id;
		@Column(name="TYPE")
		private String type;
		@Column(name="PRICE")
		private double price;
						
		//constructor
		public CoinItem() {
			super();
		}
		
		public CoinItem(String type, double price) {
			this.type = type;
			this.price = price;
		}

		//getters and setters
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		//helper methods
		public String returnDetails() {
			return "Id Number: " + this.getId() + "\n" +
					"Coin Type: " + this.getType() + "\n" + 
					"Coin Price: " + this.getPrice(); 
					
					
		}
		
		public String returnDetailNoId() {
			return "Coin Type: " + this.getType() + "\n" + 
					"Coin Price: " + this.getPrice(); 
		}

}
