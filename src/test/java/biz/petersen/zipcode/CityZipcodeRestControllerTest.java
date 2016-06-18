package biz.petersen.zipcode;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * This class is testing the CityZipcodeRestController
 * basically its testing the rest service operations by calling them
 * at test the result from each call.  
 * 
 * @author Jack Petersen
 * 		   http://wwww.petersen.biz
 * 		   https://github.com/Jack1972	
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZipcodeService.class)
@WebAppConfiguration
public class CityZipcodeRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CityZipCodeRepository cityZipCodeRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.cityZipCodeRepository.deleteAllInBatch();
       
        cityZipCodeRepository.save(new CityZipCodeEntity("4000", "Roskilde"));
        cityZipCodeRepository.save(new CityZipCodeEntity("4296", "Nyrup"));
        cityZipCodeRepository.save(new CityZipCodeEntity("4320", "Lejre"));
        cityZipCodeRepository.save(new CityZipCodeEntity("4450", "Jyderup"));
        cityZipCodeRepository.save(new CityZipCodeEntity("4460", "Snertinge"));
        
    }

    @Test
    public void readSingleByZipcode() throws Exception {
        mockMvc.perform(get("/zipcode/{zipcode}", "4000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))            
                .andExpect(jsonPath("$.city", is("Roskilde")));
        
    }
    
    @Test
    public void readSingleByCity() throws Exception {
        mockMvc.perform(get("/city/{cityname}", "Roskilde"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))            
                .andExpect(jsonPath("$.cityZipCode", is("4000")));
        
    }

    @Test
    public void readAllJson() throws Exception {
        mockMvc.perform(get("/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))            
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[2].cityZipCode", is("4320")))
        		.andExpect(jsonPath("$[2].city", is("Lejre")));
    }


    @Test
    public void readAllXml() throws Exception {
        mockMvc.perform(get("/citiesasxml"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE));                           
    }
}