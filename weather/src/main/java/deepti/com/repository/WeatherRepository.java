package deepti.com.repository;

import deepti.com.model.WeatherStation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherStation, Integer> {

}
