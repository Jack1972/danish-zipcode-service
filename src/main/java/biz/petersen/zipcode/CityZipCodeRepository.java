package biz.petersen.zipcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * This repository is an interface and will allow you to perform various operations 
 * involving CityZipCodeEntity objects. It gets these operations by extending the 
 * CrudRepository interface defined in Spring Data Commons.
 * 
 * @author Jack Petersen
 * 		   http://wwww.petersen.biz
 * 		   https://github.com/Jack1972	
 * 
 */

public interface CityZipCodeRepository extends JpaRepository<CityZipCodeEntity, Short>	{

CityZipCodeEntity findByCity(@Param("city") String city);
	CityZipCodeEntity findByCityZipCode(@Param("cityZipCode") String cityZipCode);
	List<CityZipCodeEntity> findAll();
}