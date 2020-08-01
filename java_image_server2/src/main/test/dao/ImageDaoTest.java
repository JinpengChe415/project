
package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLOutput;
import java.util.ArrayList;
//@RunWith()

/**
 * Create with Darcula IDEA
 * Description:
 *
 * @Author CJP
 * @Date 2020/7/25
 * @Time 10:26
 */

public class ImageDaoTest {
    @Before  //每一次方法之前都会执行
    public void before(){
        System.out.println("-----------before--------");
    }

    @After  //每一次方法之后都会执行
    public void after(){
        System.out.println("-------------after---------");
    }
//    @Ignore  //想要忽略那个单元测试用的标签
    @Test
    public void insert() {
        ImageDao imageDao = new ImageDao();
        //准备数据
        Image image = new Image();
        image.setImageName("8.png");
        image.setUploadTime("20200725");
        image.setPath(" ./image/a5fe527a41c2dfac4b65a166f919633d ");
        image.setContenType(" image/png");
        image.setMd5("a5fe527a41c2dfac4b65a166f919633d");
        image.setSize(1024);
        boolean flag = imageDao.insert(image);
        System.out.println("result = " + flag);

    }

    @Test
    public void selectAll() {
        ImageDao imageDao = new ImageDao();
        ArrayList<Image> lists = (ArrayList<Image>) imageDao.selectAll();
        System.out.println("lists:" + lists.size());
        System.out.println();
        for(Image image: lists){
            System.out.println("name:"+image.getImageName());
        }

    }

    @Test
    public void selectOne() {
        ImageDao imageDao = new ImageDao();
//        Image image = imageDao.selectOne(6);
        Image image = imageDao.selectOne(20);

        System.out.println(image.getImageName());
        System.out.println(image.getImageId());
        System.out.println(image.getMd5());
        System.out.println(image.getPath());
    }

    @Test
    public void delete() {
        ImageDao imageDao = new ImageDao();
        boolean flag = imageDao.delete(9);
        System.out.println("flag:"+ flag);
    }

    @Test
    public void selectByMD5() {
        ImageDao imageDao = new ImageDao();
        String md5 = "a5fe527a41c2dfac4b65a166f919633d";
        Image image =  imageDao.selectByMD5(md5);
        System.out.println("name:"+ image.getImageName());

    }
}

