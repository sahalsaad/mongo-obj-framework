package org.smof.test.dataModel;

import java.time.LocalDate;
import java.util.List;

import org.smof.annnotations.SmofBuilder;
import org.smof.annnotations.SmofParam;
import org.smof.element.Element;

@SuppressWarnings("javadoc")
public interface Brand extends Element{

	String NAME = "name";
	String OWNERS = "owners";
	String FOUNDING = "founding";
	String CAPITAL = "capital";
	String LOCATION = "location";
	
	@SmofBuilder
	static Brand create(
			@SmofParam(name = NAME) String name,
			@SmofParam(name = LOCATION) Location headQuarters,
			@SmofParam(name = OWNERS) List<String> owners) {
		return new BrandImpl(name, headQuarters, owners);
	}
	
	String getName();
	
	List<String> getOwners();
	
	LocalDate getFoundingDate();
	
	long getCapital();
	
	void setCapital(long value);
	
	void increaseCapital(long value);
	
	Location getLocation();
}
