package deepti.com.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRowMapper implements RowMapper<Data> {
    @Override
    public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        Map<String, String> variablesMap = new HashMap<>();
        Data d = new Data();
        StringBuilder variable = new StringBuilder();
        List<Double> valuesList = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            if (metaData.getColumnName(i).equalsIgnoreCase("timestamp")) {
                d.setTimestamp(rs.getTimestamp(metaData.getColumnName(i)));
            } else {
                valuesList.add(rs.getDouble(i));
            }
        }
        d.setValues(valuesList);
        return d;
    }
}
