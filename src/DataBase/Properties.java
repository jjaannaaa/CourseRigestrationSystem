package DataBase;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
    public static DataBaseInfo getDataBaseInfo(){
        DataBaseInfo dbi = new DataBaseInfo();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/DataBase/config.properties");
            java.util.Properties prop = new java.util.Properties();
            prop.load(fis);
            dbi.setHost(prop.getProperty("host"));
            dbi.setUsername(prop.getProperty("username"));
            dbi.setPassword(prop.getProperty("password"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (fis!=null)
                    fis.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        return dbi;
    }
}
