import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroTest {
    public static void main(String[]args){
        ShiroTest shiroTest = new ShiroTest();
        shiroTest.test();
//        someKey 的值：aValue
//        用户 zhangsan 登陆成功！
//        是否拥有 manager 角色：true
//        是否拥有 user:create 权限true
    }

    public void test() {

        // 读取 shiro.ini 文件内容
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        //将用户角色权限  密码放入工具类
        SecurityUtils.setSecurityManager(securityManager);

        //用工具类得到实体
        Subject currentUser = SecurityUtils.getSubject();
////////////////////////////////////////////////这一sessio跟下面的认证没有联系//////////////////////////////////////////////////////////////////////////////////////////////////
//        Session session = currentUser.getSession();
//        session.setAttribute("someKey", "aValue");
//        String value = (String) session.getAttribute("someKey");
//        if (value.equals("aValue")) {
//            System.out.println("someKey 的值：" + value);
//        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 登陆
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "zhangsan");
        token.setRememberMe(true);
        try {
            //使用实体登录
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            System.out.println("用户名不存在:" + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            System.out.println("账户密码 " + token.getPrincipal()  + " 不正确!");
        } catch (LockedAccountException lae) {
            System.out.println("用户名 " + token.getPrincipal() + " 被锁定 !");
        }

        // 认证成功后
        if (currentUser.isAuthenticated()) {

            System.out.println("用户 " + currentUser.getPrincipal() + " 登陆成功！");

            //测试角色
            System.out.println("是否拥有 manager 角色：" + currentUser.hasRole("manager"));

            //测试权限
            System.out.println("是否拥有 user:create 权限" + currentUser.isPermitted("user:create"));

            //退出
            currentUser.logout();
        }

    }



}
