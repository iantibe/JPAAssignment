package model;

public class CoinItem {
	
		//properties
		private int id;
		private String type;
		private double price;
		private String location;
		private String condition;
		
		//constructor
		public CoinItem() {
			super();
		}
		
		public CoinItem(int id, String type, double price, String location, String condition) {
			this.id = id;
			this.type = type;
			this.price = price;
			this.location = location;
			this.condition = condition;
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

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getCondition() {
			return condition;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}
		
		//helper methods
		public String returnDetails() {
			return "Id Number: " + this.getId() + "\n" +
					"Coin Type: " + this.getType() + "\n" + 
					"Coin Price: " + this.getPrice() + "\n" + 
					"Coin Location in inventory: " + this.getLocation() + "\n" +
					"Coin Condition: " + this.getCondition();
		}

}
