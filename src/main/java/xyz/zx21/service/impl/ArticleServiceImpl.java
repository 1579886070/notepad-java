package xyz.zx21.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.zx21.bean.Article;
import xyz.zx21.bean.User;
import xyz.zx21.common.Constants;
import xyz.zx21.dao.ArticleDao;
import xyz.zx21.dao.UserDao;
import xyz.zx21.service.ArticleService;
import xyz.zx21.utils.ReturnMsgUtil;
import xyz.zx21.vo.ArticleVo;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/3/5 10:26
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

    @Override
    public ReturnMsgUtil getList(int start, int limit) {
        if (start < 1) {
            start = 0;
        } else {
            start = (start - 1) * limit;
        }
        List<Article> list = articleDao.getList(start, limit);
        if (list.size() < 1) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "文章到底啦！");
        }
        return ReturnMsgUtil.success(list);
    }

    @Override
    public ReturnMsgUtil insert(ArticleVo articleVo) {
        logger.info("【文章发表，信息：[{}]】", articleVo.toString());
        if (StringUtils.isBlank(articleVo.getContent()) || StringUtils.isBlank(articleVo.getTitle())) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "参数填写不全！");
        }
        if (articleVo.getUserId() == null) {
            logger.error("用户未登录！");
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "用户未登录！");
        }
        User user = userDao.selectById(articleVo.getUserId());
        if (user == null) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "用户不存在！");
        }
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);
        if (StringUtils.isBlank(articleVo.getCategory())) {
            article.setCategory("未分类");
        }
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setStatus(Constants.STATUS_SHOW);
        int isSuccess = articleDao.insert(article);
        if (isSuccess < 1) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "系统异常！");
        }
        return ReturnMsgUtil.success(articleVo);
    }

    @Override
    public ReturnMsgUtil deleteById(Integer id) {
        if (id == null) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "参数填写不全！");
        }
        boolean isSuccess = articleDao.deleteById(id);
        if (!isSuccess) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "系统异常！");
        }
        return ReturnMsgUtil.success(isSuccess);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsgUtil updateById(ArticleVo articleVo) {
        logger.info("【文章修改，信息：[{}]】", articleVo.toString());
        Article entity = articleDao.selectById(articleVo.getId());
        if (entity == null) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "该文章不存在！");
        }
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);
        article.setUpdateTime(new Date());
        boolean isSuccess = articleDao.updateById(article);
        if (!isSuccess) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "系统异常！");
        }
        return ReturnMsgUtil.success(articleVo);
    }

    @Override
    public ReturnMsgUtil selectById(Integer id) {
        Article article = articleDao.selectById(id);
        if (article == null) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "该文章不存在！");
        }
        User user = userDao.selectById(article.getUserId());
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setNickName(user.getNickName());
        return ReturnMsgUtil.success(articleVo);
    }

    @Override
    public ReturnMsgUtil updateStatusById(Integer id, Integer status) {
        logger.info("【更改文章状态，id：[{}]，status：[{}]】", id, status);
        if ((id == null) || (status == null)) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "参数填写不全！");
        }
        boolean isSuccess = articleDao.updateStatusById(id, status);
        if (!isSuccess) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "系统异常！");
        }
        return ReturnMsgUtil.success(isSuccess);
    }

    @Override
    public ReturnMsgUtil getListByUserName(int start, int limit, Integer userName) {
        if (userName == null) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "参数填写不全！");
        }
        if (start < 1) {
            start = 0;
        } else {
            start = (start - 1) * limit;
        }
        List<Article> list = articleDao.getListByUserName(start, limit, userName);
        if (list.size() < 1) {
            return ReturnMsgUtil.fail(Constants.CODE_FAIL_OTHER, "还没有发表过文章哦");
        }
        return ReturnMsgUtil.success(list);
    }
}
