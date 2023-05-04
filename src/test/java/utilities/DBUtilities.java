package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtilities {

    private static final String url = Config.getValue("db.url");
    private static final String username = Config.getValue("db.username");
    private static final String password = Config.getValue("db.password");

    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static List<List<String>> getQueryResultAsListOfList(ResultSet resultSet) throws SQLException {
        List<List<String>> resultList = new ArrayList<>();
        while(resultSet.next()){
            List<String> rowList = new ArrayList<>();
            for(int i = 1; i < resultSet.getMetaData().getColumnCount(); i++){
                rowList.add(resultSet.getString(i));
            }
            resultList.add(rowList);
        }
        return resultList;
    }

}
