package biz.petersen.zipcode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * CityZipCodeEntity is a domainobject constructed to hold a zipcode and a cityname
 * There is also an id that is configured to be automatically generated so you 
 * don’t have to deal with that.
 * 
 * @author Jack Petersen
 * 		   http://wwww.petersen.biz
 * 		   https://github.com/Jack1972	
 */

@Entity
public class CityZipCodeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private short id; 

	private String cityZipCode;
	private String city;

	public CityZipCodeEntity(){
		
	}
	
	public short getId(){return id;}
	public CityZipCodeEntity(String zipcode, String city){
		this.cityZipCode = zipcode;
		this.city = city;
	}
	
	public String getCityZipCode() {
		return cityZipCode;
	}

	public void setCityZipCode(String cityZipCode) {
		this.cityZipCode = cityZipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
    public String toString() {
        return "CityZipCodeEntity[id=" + id + ", zipcode=" + cityZipCode + ", city=" + city + "]";
           
    } 
}