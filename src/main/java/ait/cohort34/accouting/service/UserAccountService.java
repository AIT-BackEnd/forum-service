package ait.cohort34.accouting.service;

import ait.cohort34.accouting.dto.RolesDto;
import ait.cohort34.accouting.dto.UserDto;
import ait.cohort34.accouting.dto.UserEditDto;
import ait.cohort34.accouting.dto.UserRegisterDto;


public interface UserAccountService {

    UserDto register(UserRegisterDto userRegisterDto);

    UserDto getUser(String login);//login и get user согластно Postman

    UserDto removeUser(String login);

    UserDto updateUser(String login, UserEditDto userEditDto);

    RolesDto changeRolesList(String login, String role, boolean isAddRole);

    void changePassword(String login, String newPassword);
}
