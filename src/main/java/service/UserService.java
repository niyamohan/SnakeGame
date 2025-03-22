package service;

import entity.User;

public interface UserService {
    Boolean verifyUser(User user); // 此方法不需要改变，还是验证用户
}
