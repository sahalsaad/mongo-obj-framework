package org.smof.examples;

import org.smof.annnotations.SmofBuilder;
import org.smof.annnotations.SmofNumber;
import org.smof.annnotations.SmofParam;
import org.smof.annnotations.SmofString;
import org.smof.collection.Smof;
import org.smof.element.AbstractElement;

@SuppressWarnings("javadoc")
public final class BasicWriteExample {

	public static void main(String[] args) {
		//create the smof object with host, port and database name
		final Smof smof = Smof.create("localhost", 27017, "myDB");
		//create a new bottle
		final Bottle bottle = new Bottle("water", 1.0);
		//create a collection and map it to a type
		smof.createCollection("bottles", Bottle.class);
		//saves the bottle
		smof.insert(bottle);
		//fill the bottle
		bottle.fill(0.5);
		//update the object on the database
		smof.update(Bottle.class).fromElement(bottle);
		
		smof.close();
	}

	/**
	 * Represents a fillable bottle
	 * 
	 * @author Joao
	 *
	 */
	public static class Bottle extends AbstractElement {
		
		private static final String CAPACITY = "capacity";
		private static final String AMOUNT = "liquid_amount";
		private static final String LIQUID = "liquid";

		@SmofString(name = LIQUID)
		private String liquid;
		
		@SmofNumber(name = AMOUNT)
		private double amount;
		
		@SmofNumber(name = CAPACITY)
		private double capacity;
		
		/**
		 * Returns a new empty bottle
		 * 
		 * @param liquid liquid type
		 * @param capacity total capacity
		 */
		public Bottle(String liquid, double capacity) {
			this(liquid, capacity, 0.0);
		}
		
		/**
		 * General constructor
		 * 
		 * @param liquid liquid type
		 * @param capacity total capacity
		 * @param amount liquid amount
		 */
		@SmofBuilder
		public Bottle(@SmofParam(name=LIQUID) String liquid, 
				@SmofParam(name = CAPACITY) Double capacity, 
				@SmofParam(name = AMOUNT) Double amount) {
			this.liquid = liquid;
			this.capacity = capacity;
			this.amount = amount;
		}
		
		public boolean isFull() {
			return capacity == amount;
		}
		
		public double fill(Double amount) {
			final double left = capacity-amount; 
			if(left < amount) {
				this.amount = capacity;
				return amount-left;
			}
			this.amount += amount;
			return left-amount;
		}
	}
}