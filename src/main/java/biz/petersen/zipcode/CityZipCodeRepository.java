package biz.petersen.zipcode;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * This repository is an interface and will allow you to perform various operations 
 * involving CityZipCodeEntity objects. It gets these operations by extending the 
 * CrudRepository interface defined in Spring Data Commons.
 * 
 * @author Jack Petersen
 *
 */

public interface CityZipCodeRepository extends CrudRepository<CityZipCodeEntity, Short> {
	CityZipCodeEntity findByCity(@Param("city") String city);
	CityZipCodeEntity findByCityZipCode(@Param("cityZipCode") String cityZipCode);
	List<CityZipCodeEntity> findAll();
}