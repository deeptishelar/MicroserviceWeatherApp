package deepti.com.repository;

import deepti.com.model.Data;
import deepti.com.model.DataRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DataRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Data> getData(String sql) {
        return jdbcTemplate.query(sql,new DataRowMapper())
                .stream()
                .findFirst();
    }
}
