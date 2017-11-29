package module;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;

/**
 * Created by Administrator on 2017/9/13.
 */
public class UserModule extends Module {
    private int age;
    private  String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getModuleName() {
        return null;
    }

    @Override
    public Version version() {
        return null;
    }

    @Override
    public void setupModule(SetupContext setupContext) {

    }
}
