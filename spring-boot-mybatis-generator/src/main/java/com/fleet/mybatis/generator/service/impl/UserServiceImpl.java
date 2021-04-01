//package com.fleet.mybatis.generator.service.impl;
//
//import com.fleet.mybatis.generator.dao.UserDynamicSqlSupport;
//import com.fleet.mybatis.generator.dao.UserMapper;
//import com.fleet.mybatis.generator.entity.User;
//import com.fleet.mybatis.generator.page.PageUtil;
//import com.fleet.mybatis.generator.page.entity.Page;
//import com.fleet.mybatis.generator.service.UserService;
//import org.mybatis.dynamic.sql.SqlBuilder;
//import org.mybatis.dynamic.sql.render.RenderingStrategies;
//import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
//import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Resource
//    private UserMapper userMapper;
//
//    @Override
//    public Boolean insert(User user) {
//        if (userMapper.insert(user) == 0) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public Boolean delete(Integer id) {
//        if (userMapper.deleteByPrimaryKey(id) == 0) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public Boolean update(User user) {
//        if (userMapper.updateByPrimaryKeySelective(user) == 0) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public User get(Integer id) {
//        Optional<User> user = userMapper.selectByPrimaryKey(id);
//        return user.orElse(null);
//    }
//
//    @Override
//    public List<User> list() {
//        // 使用 SqlBuilder 类构建 StatementProvider 查询
//        SelectStatementProvider selectStatementProvider = SqlBuilder.select(UserMapper.selectList)
//                .from(UserDynamicSqlSupport.user)
//                .where(UserDynamicSqlSupport.name, isLikeWhenPresent("%fleet%"))
//                .and(UserDynamicSqlSupport.id, isIn(1, 2, 3))
//                .orderBy(UserDynamicSqlSupport.id.descending())
//                .build()
//                .render(RenderingStrategies.MYBATIS3);
//        return userMapper.selectMany(selectStatementProvider);
//    }
//
//    @Override
//    public PageUtil<User> listPage(Page page) {
//        PageUtil<User> pageUtil = new PageUtil<>();
//        // Lambda条件查询
//        List<User> list = userMapper.select(c -> c.where(UserDynamicSqlSupport.name, isLikeWhenPresent("%fleet%"))
//                .and(UserDynamicSqlSupport.id, isIn(1, 2, 3))
//                .orderBy(UserDynamicSqlSupport.id.descending()));
//        pageUtil.setList(list);
//        return pageUtil;
//    }
//}
