package xyz.zx21.service;

import xyz.zx21.utils.ReturnMsgUtil;
import xyz.zx21.vo.ArticleVo;

/**
 * @author Administrator
 * @date 2020/3/5 10:25
 */
public interface ArticleService {
    /**
     * 获取文章分页
     *
     * @param start
     * @param limit
     * @return
     */
    ReturnMsgUtil getList(int start, int limit);

    /**
     * 文章插入
     *
     * @param articleVo
     * @return
     */
    ReturnMsgUtil insert(ArticleVo articleVo);

    /**
     * 根据主键id删除文章
     *
     * @param id
     * @return
     */
    ReturnMsgUtil deleteById(Integer id);

    /**
     * 根据主键id修改文章
     *
     * @param articleVo
     * @return
     */
    ReturnMsgUtil updateById(ArticleVo articleVo);

    /**
     * 根据主键id查找文章
     *
     * @param id
     * @return
     */
    ReturnMsgUtil selectById(Integer id);

    /**
     * 根据状态修改文章是否显示
     *
     * @param id
     * @param status
     * @return
     */
    ReturnMsgUtil updateStatusById(Integer id, Integer status);

    /**
     * 查询用户发表的文章
     *
     * @param start
     * @param limit
     * @param userName
     * @return
     */
    ReturnMsgUtil getListByUserName(int start, int limit, Integer userName);
}
