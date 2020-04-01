package xyz.zx21.dao;

import org.apache.ibatis.annotations.Param;
import xyz.zx21.bean.User;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/3/2 15:27
 */
public interface UserDao {

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @return
     */
    List<User> getList(@Param("start") int start, @Param("limit") int limit);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 通过用户名查找用户
     *
     * @param userName
     * @return
     */
    User selectByUserName(@Param("userName") String userName);

    /**
     * 通过账号密码查找用户
     *
     * @param userName
     * @param password
     * @return
     */
    User selectByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 通过id查找用户
     *
     * @param id
     * @return
     */
    User selectById(@Param("id") int id);

    /**
     * 修改用户显示状态 0显示 1不显示
     *
     * @param id
     * @param status
     * @return
     */
    boolean updateStatusById(@Param("id") Integer id, @Param("status") Integer status);
}
