import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

public class ShiroTest注解 {

    @RequiresRoles( "manager" )      //# 角色校验
    public String save() {
        //...

        return "";
    }
    @RequiresPermissions("user:manage")//  # 权限检验
    public String delete() {
        //...
        return "";
    }

}
