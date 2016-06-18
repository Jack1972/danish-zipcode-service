package biz.petersen.zipcode;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 /**
 * Forklar lidt om rest controlleren
 * 
 * @author Jack Petersen
 * 		   http://wwww.petersen.biz
 * 		   https://github.com/Jack1972	
 * 
 */

@RestController
@RequestMapping("/")
public class CityZipcodeRestController {

	@Autowired
	CityZipCodeRepository repository;
	
	@Autowired
	CityResourceAssembler cityResourceAssembler;
	
	@RequestMapping(value = "/city/{cityname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<CityZipCodeEntity> readByCity(@PathVariable String cityname) {
		String tmp = cityname.toLowerCase();
		tmp =  (tmp.substring(0, 1).toUpperCase() + tmp.substring(1, tmp.length()));
	    CityZipCodeEntity entity = this.repository.findByCity(tmp);
		
		if(entity == null){
			throw new CityNotFoundException(cityname);
		}
		
		return this.cityResourceAssembler.toResource(entity);
	}
	
	@RequestMapping(value = "/zipcode/{zipcode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<CityZipCodeEntity> readByZipCode(@PathVariable String zipcode) {
		CityZipCodeEntity entity = this.repository.findByCityZipCode(zipcode);
		
		if(entity == null){
			throw new ZipCodeNotFoundException(zipcode);
		}

		return this.cityResourceAssembler.toResource(entity);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cities" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Resource<CityZipCodeEntity>> readZipCode(){
		List<CityZipCodeEntity> entityList = this.repository.findAll();
		 List<Resource<CityZipCodeEntity>> resourceList = new ArrayList<Resource<CityZipCodeEntity>>();
		 
		for(CityZipCodeEntity en : entityList){
			resourceList.add(this.cityResourceAssembler.toResource(en));
		}
				
		return resourceList;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/citiesasxml" , produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<CityZipCodeEntity> readZipCodeAsXml(){		
		return this.repository.findAll();
	}
}

/**
 * forklar lidt om CityResourceAssembler
 * 
 * @author jack
 *
 */
@Component
final class CityResourceAssembler implements ResourceAssembler<CityZipCodeEntity, Resource<CityZipCodeEntity>> {

	@Override
	public Resource<CityZipCodeEntity> toResource(CityZipCodeEntity cityZipcode) {
		Resource<CityZipCodeEntity> resource = new Resource<CityZipCodeEntity>(cityZipcode);
		
		resource.add(linkTo(CityZipcodeRestController.class).slash("cities").withRel("cities"));
		resource.add(linkTo(CityZipcodeRestController.class).slash("city").slash(cityZipcode.getCity()).withSelfRel());
		resource.add(linkTo(CityZipcodeRestController.class).slash("zipcode").slash(cityZipcode.getCityZipCode()).withSelfRel());
	
		return resource;
	}
}


