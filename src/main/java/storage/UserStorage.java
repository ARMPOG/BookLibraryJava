package storage;

import models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserStorage {

    private static UserStorage userStorage;

    private UserStorage() {
    }

    public synchronized static UserStorage getInstance() {
        synchronized (UserStorage.class) {
            if (userStorage == null) {
                userStorage = new UserStorage();
            }
            return userStorage;
        }
    }

    private List<UserModel> users = new ArrayList<>();

    public boolean saveUser(UserModel userModel) {
        boolean isNotExist = true;

//        if(!users.isEmpty() && users.stream().map(UserModel::getEmail).collect(Collectors.toList()).contains(userModel.getEmail())){
//        if(!users.isEmpty() && users.stream().map(UserModel::getEmail).anyMatch(s -> s.equalsIgnoreCase(userModel.getEmail()))){
        if (!users.isEmpty() && users.stream().anyMatch(s -> s.getEmail().equalsIgnoreCase(userModel.getEmail()))) {
            isNotExist = false;
        } else {
            users.add(userModel);
        }


       /* for (UserModel user : users) {
            if (user.getEmail().equalsIgnoreCase(userModel.getEmail())) {
                isNotExist = false;
                break;
            }
        }

        if (isNotExist) {
            users.add(userModel);
        }*/
        return isNotExist;
    }

    public boolean checkUser(String email, String password) {
        boolean isNotExist = true;
        if (!users.isEmpty() && users.stream().anyMatch(userModel -> userModel.getEmail().equalsIgnoreCase(email) &&
                userModel.getPassword().equals(password))) {
        } else {
            isNotExist = false;
        }

        return isNotExist;
    }
}
