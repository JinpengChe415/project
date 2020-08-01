import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * Create with Darcula IDEA
 * Description:
 *
 * @Author CJP
 * @Date 2020/6/7
 * @Time 22:46
 */
class Hero {
    public String name;
    public String skill1;
    public String skill2;
    public String skill3;
    public String skill4;
}
public class TestGson {
    public static void main(String[] args) {
        /*HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("name","曹操");
        hashMap.put("技能1","剑气");
        hashMap.put("技能2","三段跳");
        hashMap.put("技能3","加攻击并吸血");
        hashMap.put("技能4","加攻速");
*/
        Hero hero = new Hero();
        hero.name = "曹操";
        hero.skill1 = "剑气";
        hero.skill2 = "三段跳";
        hero.skill3 = "加攻击并吸血";
//        hero.skill4 = "加攻速";
        //通过map转成 JSON 结构的字符串
        //1.创建一个gson对象
        Gson gson = new GsonBuilder().create();
        //2.使用 toJson 方法把键值对结构转成 JSON 字符串
//        String str = gson.toJson(hashMap);
        String str = gson.toJson(hero);
        System.out.println(str);
    }
}
