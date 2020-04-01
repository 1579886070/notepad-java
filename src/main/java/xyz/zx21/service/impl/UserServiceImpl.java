package xyz.zx21.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.zx21.common.Constants;
import xyz.zx21.bean.User;
import xyz.zx21.dao.UserDao;
import xyz.zx21.service.UserService;
import xyz.zx21.utils.Md5Util;
import xyz.zx21.utils.ReturnMsgUtil;
import xyz.zx21.vo.UserVo;

import java.util.Date;
import java.util.List;

/**
 * 实现类
 *
 * @author Administrator
 * @date 2020/3/2 15:03
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public ReturnMsgUtil getList(int start, int limit) {
        if (start < 1) {
            start = 0;
        } else {
            start = (start - 1) * limit;
        }
        List<User> list = userDao.getList(start, limit);
        return ReturnMsgUtil.success(list);
    }

    @Override
    public ReturnMsgUtil register(UserVo userVo) {
        logger.info("【用户注册，注册信息：[{}]】", userVo.toString());
        if (StringUtils.isBlank(userVo.getUserName()) || StringUtils.isBlank(userVo.getPassword())) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "参数不能为空！");
        }
        //判断是否已经存在用户

        User entity = userDao.selectByUserName(userVo.getUserName());
        if (entity != null) {
            logger.error("【用户已经存在，用户名称：[{}]】", userVo.getUserName());
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "该用户账号已经存在！");
        }
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setNickName(userVo.getUserName());
        user.setPassword(Md5Util.md532(userVo.getPassword()));
        user.setStatus(Constants.STATUS_SHOW);

        int isSuccess = userDao.register(user);
        if (isSuccess < 1) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "系统异常！");
        }
        user.setPassword(null);
        return ReturnMsgUtil.success(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsgUtil updateUser(UserVo userVo) {
        logger.info("【用户修改，修改信息：[{}]】", userVo.toString());
        if (StringUtils.isBlank(userVo.getUserName())) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "参数不能为空！");
        }
        User user = new User();
        if(StringUtils.isNotBlank(userVo.getPassword()) && StringUtils.isNotBlank(userVo.getConfirmPassword())){
            user = userDao.selectByUserNameAndPassword(userVo.getUserName(), Md5Util.md532(userVo.getPassword()));
            if (user == null) {
                return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "该用户不存在！");
            }

        }
        BeanUtils.copyProperties(userVo, user);
        user.setUpdateTime(new Date());
        if(StringUtils.isNotBlank(userVo.getConfirmPassword())){
            user.setPassword(Md5Util.md532(userVo.getConfirmPassword()));
        }

        boolean isSuccess = userDao.updateUser(user);
        if (!isSuccess) {
            logger.error("【更新用户失败，用户信息：[{}]】", user.toString());
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "系统异常");
        }
        user.setPassword(null);
        return ReturnMsgUtil.success(user);
    }

    @Override
    public ReturnMsgUtil selectById(int id) {
        User user = userDao.selectById(id);
        return ReturnMsgUtil.success(user);
    }

    @Override
    public ReturnMsgUtil selectByUserName(String userName) {
        User user = userDao.selectByUserName(userName);
        return ReturnMsgUtil.success(user);
    }

    @Override
    public ReturnMsgUtil login(UserVo userVo) {
        User user = userDao.selectByUserNameAndPassword(userVo.getUserName(), Md5Util.md532(userVo.getPassword()));
        if (user == null) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "该用户不存在！");
        }
        return ReturnMsgUtil.success(user);
    }

    @Override
    public ReturnMsgUtil updateStatusById(Integer id, Integer status) {
        logger.info("【更改用户状态，id：[{}]，status：[{}]】", id, status);
        if ((id == null) || (status == null)) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "参数不能为空！");
        }
        boolean isSuccess = userDao.updateStatusById(id, status);
        if (!isSuccess) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "系统异常");
        }
        return ReturnMsgUtil.success(isSuccess);
    }
}
