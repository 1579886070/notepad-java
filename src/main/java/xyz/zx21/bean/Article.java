package xyz.zx21.bean;

import java.util.Date;

/**
 * 文章实体类
 *
 * @author Administrator
 * @date 2020/3/5 10:14
 */
public class Article {

    public Article() {
    }

    public Article(Integer id, Integer userId, String title, String content, Integer praise, String category, Integer status, Integer pageView, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.praise = praise;
        this.category = category;
        this.status = status;
        this.pageView = pageView;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    private Integer id;

    /**
     * 用户主键id
     */
    private Integer userId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;
    /**
     * 点赞数
     */
    private Integer praise;
    /**
     * 类别
     */
    private String category;

    /**
     * 状态：0显示 1不显示
     */
    private Integer status;
    /**
     * 访问量
     */
    private Integer pageView;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", praise=" + praise +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", pageView=" + pageView +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
