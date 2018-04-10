package dao;

import model.Login;

import model.User;

public interface UserDao {

  void register(User user);

  User validateUser(Login login);
  
  public boolean checkIfUsernameExists(String username);
  
  public boolean checkIfCompanyExists(String company);
  
}