package dao;

import commen.JavaImageServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create with Darcula IDEA
 * Description:
 *
 * @Author CJP
 * @Date 2020/6/10
 * @Time 21:30
 */
public class ImageDao {
    /**
     * 把 image 对象插入到数据库中
     * @param image
     */
    public boolean insert(Image image) {
        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2.创建并拼装 SQL 语句
        String sql = "insert into image_table values(null,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,image.getImageName());
            statement.setInt(2,image.getSize());
            statement.setString(3,image.getUploadTime());
            statement.setString(4,image.getContenType());
            statement.setString(5,image.getPath());
            statement.setString(6,image.getMd5());

            //3.执行 SQL 语句
            //返回值表示了影响了几行数据
            int ret = statement.executeUpdate();
            if(ret != 1){
                //程序出现问题，抛出一个异常
                throw new JavaImageServerException("插入数据库出错！");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JavaImageServerException e) {
            e.printStackTrace();
        }finally {
            //4.关闭连接和statement对象
            DBUtil.close((com.mysql.jdbc.Connection) connection,statement,null);
        }
        return false;
    }

    /**
     * 查找数据库中的所有图片的信息
     * @return
     */
    public List<Image> selectAll() {
        List<Image> images = new ArrayList<>();
        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2.构造 SQL 语句
        String sql = "select * from image_table";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //3.执行 SQL 语句
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            //4.处理结果集
            while(resultSet.next()){
                Image image = new Image();
                image.setImageId(resultSet.getInt("imageId"));
                image.setImageName((resultSet.getString("imageName")));
                image.setSize(resultSet.getInt("size"));
                image.setUploadTime(resultSet.getString("uploadTime"));
                image.setContenType((resultSet.getString("contentType")));
                image.setPath(resultSet.getString("path"));
                image.setMd5(resultSet.getString("md5"));
                images.add(image);
            }
            return images;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.关闭链接
            DBUtil.close((com.mysql.jdbc.Connection) connection,statement,resultSet);
        }
    return null;
    }

    /**
     * 根据 imageId 查找指定的图片信息
     * @param imageId
     * @return
     */
    public Image selectOne(int imageId) {
        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2.构造 SQL 语句
        String sql = "select * from image_table where imageId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //3.执行 SQL 语句
            statement = connection.prepareStatement(sql);
            statement.setInt(1,imageId);
            resultSet = statement.executeQuery();
            //4.处理结果集（结果只有一条或者没有）
            if(resultSet.next()) {
                Image image = new Image();
                image.setImageId(resultSet.getInt("imageId"));
                image.setImageName((resultSet.getString("imageName")));
                image.setSize(resultSet.getInt("size"));
                image.setUploadTime(resultSet.getString("uploadTime"));
                image.setContenType((resultSet.getString("contentType")));
                image.setPath(resultSet.getString("path"));
                image.setMd5(resultSet.getString("md5"));
                return image;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.关闭链接
            DBUtil.close((com.mysql.jdbc.Connection) connection,statement,resultSet);
        }
        return null;
    }

    /**
     * 根据 imageId 删除指定的图片
     * @param imageId
     */
    public boolean delete(int imageId) {
        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2.拼装 SQL 语句
        String sql = "delete  from image_table where imageId = ?";
        PreparedStatement statement = null;
        try {
            //3.执行 SQL 语句
            statement = connection.prepareStatement(sql);
            statement.setInt(1,imageId);
            int ret = statement.executeUpdate();
            //ret为数据库影响的行数
            while(ret != 1){
                throw new JavaImageServerException("删除数据库操作失败");
            }
            return true;
        } catch (SQLException | JavaImageServerException e) {
            e.printStackTrace();
        }finally {
            //4.关闭连接
            DBUtil.close((com.mysql.jdbc.Connection) connection,statement,null);
        }

        return false;
    }

    public static void main(String[] args) {
        //用于简单的测试
        //1.测试插入数据
        /*Image image = new Image();
        image.setImageName("1.png");
        image.setSize(100);
        image.setUploadTime("20200611");
        image.setContenType("image/png");
        image.setPath("./data/1.png");
        image.setMd5("11223344");
        ImageDao imageDao = new ImageDao();
        imageDao.insert(image);*/
        //2.测试查找所有图片信息
       /* ImageDao imageDao = new ImageDao();
        List<Image> images = imageDao.selectAll();
        System.out.println(images);*/
       //3.测试查找指定图片信息
       /* ImageDao imageDao = new ImageDao();
        Image image = imageDao.selectOne(1);
        System.out.println(image);*/
       //4.测试删除
        ImageDao imageDao = new ImageDao();
        imageDao.delete(1);
    }

    public static Image selectByMD5(String md5) {
        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2.构造 SQL 语句
        String sql = "select * from image_table where md5 = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //3.执行 SQL 语句
            statement = connection.prepareStatement(sql);
            statement.setString(1,md5);
            resultSet = statement.executeQuery();
            //4.处理结果集（结果只有一条或者没有）
            if(resultSet.next()) {
                Image image = new Image();
                image.setImageId(resultSet.getInt("imageId"));
                image.setImageName((resultSet.getString("imageName")));
                image.setSize(resultSet.getInt("size"));
                image.setUploadTime(resultSet.getString("uploadTime"));
                image.setContenType((resultSet.getString("contentType")));
                image.setPath(resultSet.getString("path"));
                image.setMd5(resultSet.getString("md5"));
                return image;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.关闭链接
            DBUtil.close((com.mysql.jdbc.Connection) connection,statement,resultSet);
        }
        return null;
    }

}
