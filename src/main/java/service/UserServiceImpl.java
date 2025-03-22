package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserMapper;
import entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean verifyUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        // 从数据库中获取用户信息
        User user2 = userMapper.getUser(username);

        // 如果用户不存在，返回 false
        if (user2 == null) {
            return false;
        }

        // 比较数据库中的密码与用户输入的密码是否相同
        if (password.equals(user2.getPassword())) {
            return true;
        }

        return false;
    }
}
