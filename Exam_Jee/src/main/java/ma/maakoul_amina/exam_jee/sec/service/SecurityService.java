package ma.maakoul_amina.exam_jee.sec.service;


import ma.maakoul_amina.exam_jee.sec.entities.AppRole;
import ma.maakoul_amina.exam_jee.sec.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUserName(String username);

}
