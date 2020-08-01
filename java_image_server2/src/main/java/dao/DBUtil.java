package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Create with Darcula IDEA
 * Description:
 *
 * @Author CJP
 * @Date 2020/6/8
 * @Time 20:07
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java_image_server?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "peng0415";

    private static volatile DataSource dataSource = null;

    public static DataSource getDataSource() {
        //通过这个方法来创建DataSource的实例
        //注意线程安全
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    MysqlDataSource tmpDataSource = (MysqlDataSource) dataSource;
                    tmpDataSource.setURL(URL);
                    tmpDataSource.setUser(USERNAME);
                    tmpDataSource.setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    //获取连接
    public static java.sql.Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//关闭的顺序不能乱写，先创建的最后关闭。
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
