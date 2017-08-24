import evgenii.Application;
import evgenii.controller.WeatherController;
import evgenii.dao.repositories.WeatherRepo;
import evgenii.property_holder.AppProperHolder;
import org.hamcrest.core.IsNot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zhenia on 10.07.17.
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private WeatherRepo weatherRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void databaseTest() throws Exception {
        mockMvc.perform(get("/getWeather?city=Kiev"));

        assertThat(weatherRepo.findWeatherHolderByCity("Kiev"), notNullValue());
    }

    @Test
    public void apiTest() throws Exception {
        mockMvc.perform(get("/getWeather?city=London")).andExpect(status().isOk());

        mockMvc.perform(get("/getWeather?city=Lwh123214diufhgiuhd")).andExpect(status().is(404));
    }
}
