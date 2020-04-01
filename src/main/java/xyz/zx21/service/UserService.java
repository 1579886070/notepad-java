package xyz.zx21.service;

import xyz.zx21.utils.ReturnMsgUtil;
import xyz.zx21.vo.UserVo;

/**
 * @author Administrator
 * @date 2020/3/2 15:02
 */
public interface UserService {


    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @return
     */
    ReturnMsgUtil getList(int start, int limit);

    /**
     * 注册
     *
     * @param userVo
     * @return
     */
    ReturnMsgUtil register(UserVo userVo);

    /**
     * 修改密码
     *
     * @param userVo
     * @return
     */
    ReturnMsgUtil updateUser(UserVo userVo);

    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return
     */
    ReturnMsgUtil selectById(int id);

    /**
     * 通过用户名查询用户信息
     *
     * @param userName
     * @return
     */
    ReturnMsgUtil selectByUserName(String userName);

    /**
     * 登陆
     *
     * @param userVo
     * @return
     */
    ReturnMsgUtil login(UserVo userVo);

    /**
     * 修改用户显示状态
     *
     * @param id
     * @param status
     * @return
     */
    ReturnMsgUtil updateStatusById(Integer id, Integer status);
}
