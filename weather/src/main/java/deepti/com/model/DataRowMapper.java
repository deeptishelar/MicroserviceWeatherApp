package deepti.com.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRowMapper implements RowMapper<Data> {
    @Override
    public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        Data data = new Data();
        List<Double> valuesList = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            if (metaData.getColumnName(i).equalsIgnoreCase("timestamp")) {
                data.setTimestamp(rs.getTimestamp(metaData.getColumnName(i)));
            } else {
                valuesList.add(rs.getDouble(i));
            }
        }
        data.setValues(valuesList);
        return data;
    }
}
