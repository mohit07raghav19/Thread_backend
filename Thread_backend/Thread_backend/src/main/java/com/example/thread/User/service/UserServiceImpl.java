package com.example.thread.User.service;

import java.util.List;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.thread.Post.service.PostServiceImpl;
import com.example.thread.Roles.model.Role;
import com.example.thread.Roles.repo.RoleRepo;
import com.example.thread.User.model.User;
import com.example.thread.User.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepo userRepo;

        @Autowired
        private RoleRepo roleRepo;

        @Autowired
        private PasswordEncoder passwordEncoder;

        public void initRoleAndUser() {
                if (userRepo.findAll().size() == 0) {
                        Role adminRole = new Role();
                        adminRole.setRoleName("Admin");
                        adminRole.setRoleDescription("Admin role");
                        roleRepo.save(adminRole);

                        Role userRole = new Role();
                        userRole.setRoleName("User");
                        userRole.setRoleDescription("Default role for newly created record");
                        roleRepo.save(userRole);

                        Set<Role> adminRoles = new HashSet<>();
                        adminRoles.add(adminRole);

                        Set<Role> userRoles = new HashSet<>();
                        userRoles.add(userRole);

                        User adminUser = new User("admin", "Admin", null, "admin@gmail.com", "Lucknow", "Male",
                                        "01/01/2000",
                                        "logo.jpg");
                        adminUser.setUserPassword(getEncodedPassword("Admin@1234"));
                        adminUser.setRole(adminRoles);

                        User adminUser1 = new User("Mohit_lcs32", "Mohit Raghav", null, "lcs2022032@iiitl.ac.in",
                                        "Lucknow",
                                        "Male", "04/03/2004",
                                        "mohit.jpeg");
                        adminUser1.setUserPassword(getEncodedPassword("Mohit@1234"));
                        adminUser1.setRole(adminRoles);
                        User adminUser2 = new User("Yash_lcs57", "Yash Aggarwal", null, "lcs2022057@iiitl.ac.in",
                                        "Lucknow",
                                        "Male", "01/01/2004",
                                        "yash.jpeg");
                        adminUser2.setUserPassword(getEncodedPassword("Yash@1234"));
                        adminUser2.setRole(adminRoles);
                        User adminUser3 = new User("Umesh_lcs52", "Umesh Singh Verma", null, "lcs2022052@iiitl.ac.in",
                                        "Lucknow",
                                        "Male", "01/01/2004",
                                        "umesh.jpeg");
                        adminUser3.setUserPassword(getEncodedPassword("Umesh@1234"));
                        adminUser3.setRole(adminRoles);
                        User adminUser4 = new User("Harshit_lcs21", "Harshit Chordiya", null, "lcs2022021@iiitl.ac.in",
                                        "Lucknow",
                                        "Male", "01/01/2004",
                                        "harshit.jpeg");
                        adminUser4.setUserPassword(getEncodedPassword("Harshit@1234"));
                        adminUser4.setRole(adminRoles);
                        User adminUser5 = new User("Manjeet_lcs29", "Manjeet Kumar", null, "lcs2022029@iiitl.ac.in",
                                        "Lucknow",
                                        "Male", "01/01/2004",
                                        "man.jpg");
                        adminUser5.setUserPassword(getEncodedPassword("Manjeet@1234"));
                        adminUser5.setRole(adminRoles);
                        User adminUser6 = new User("Sunil_lcs48", "Sunil Tirwal", null, "lcs2022048@iiitl.ac.in",
                                        "Lucknow",
                                        "Male", "01/01/2004",
                                        "sunil.jpg");
                        adminUser6.setUserPassword(getEncodedPassword("Sunil@1234"));
                        adminUser6.setRole(adminRoles);

                        userRepo.save(adminUser);
                        userRepo.save(adminUser1);
                        userRepo.save(adminUser2);
                        userRepo.save(adminUser3);
                        userRepo.save(adminUser4);
                        userRepo.save(adminUser5);
                        userRepo.save(adminUser6);
                        User user0 = new User("Manish_Rawat", "Manish Rawat", " ", "lcs2022028@iiitl.ac.in", "Dehradun",
                                        "Male",
                                        "05/02/2003", "user0.jpg");
                        user0.setUserPassword(getEncodedPassword("Manish@1234"));
                        user0.setRole(userRoles);
                        User user1 = new User("karthik_bannu", "Karthik Akshaj", " ", "lcs2022036@iiitl.ac.in",
                                        "Dehradun",
                                        "Male",
                                        "10/12/2004", "user1.jpg");
                        user1.setUserPassword(getEncodedPassword("Karthik@1234"));
                        user1.setRole(userRoles);
                        User user2 = new User("Slumber_420", "Praveen Kumar", " ", "lcs2022039@iiitl.ac.in", "Lucknow",
                                        "Male",
                                        "10/08/2003", "user2.jpg");
                        user2.setUserPassword(getEncodedPassword("Praveen@1234"));
                        user2.setRole(userRoles);
                        User user3 = new User("money_heist", "Professor", " ", "professor@gmail.com", "Berlin", "Male",
                                        "01/01/1979", "user3.jpg");
                        user3.setUserPassword(getEncodedPassword("Professor@1234"));
                        user3.setRole(userRoles);
                        User user4 = new User("asur_official", "Mukti Giver", " ", "asur@gmail.com", "Paataallok",
                                        "Male",
                                        null, "user4.jpeg");
                        user4.setUserPassword(getEncodedPassword("Asur@1234"));
                        user4.setRole(userRoles);
                        User user5 = new User("marvel_official", "Marvels", " ", "marvels@gmail.com", null, "Multiple",
                                        null, "user5.jpg");
                        user5.setUserPassword(getEncodedPassword("Marvels@1234"));
                        user5.setRole(userRoles);
                        User user6 = new User("iiitl_official", "IIIT LUCKNOW", " ", "iiitl@iiitl.ac.in", null, null,
                                        null, "iiitlProfile.png");
                        user6.setUserPassword(getEncodedPassword("Iiitl@1234"));
                        user6.setRole(userRoles);
                        User user7 = new User("axios_iiitl", "Axios Technical Society", " ", "axios@iiitl.ac.in", null,
                                        null,
                                        null, "Axios.jpg");
                        user7.setUserPassword(getEncodedPassword("Axios@1234"));
                        user7.setRole(userRoles);

                        userRepo.save(user0);
                        userRepo.save(user1);
                        userRepo.save(user2);
                        userRepo.save(user3);
                        userRepo.save(user4);
                        userRepo.save(user5);
                        userRepo.save(user6);
                        userRepo.save(user7);
                }
        }

        @Override
        public List<User> getAllUsers(Authentication authentication) {
                User LoggedInUser = userRepo.findByUserName(authentication.getName());
                List<User> users = userRepo.findAll(Sort.by(Direction.ASC, "email"));
                for (User user : users) {
                        if (user.getUserName().equals(LoggedInUser.getUserName())) {
                                users.remove(user);
                        }
                }

                return users;
        }

        @Override
        public User createUser(User user) throws Exception {

                if (user_exists(user))
                        throw new Exception("User already exists!");

                String UserEmail = user.getEmail();
                String Username = user.getUserName();
                String UserFullname = user.getUserFullName();

                String UserPassword = user.getUserPassword();
                String securityq = user.getSecurityq();

                boolean UserNameIsNotValid = (Username == null) ||
                                !Username.matches("[A-Za-z0-9_]+");
                boolean UserFullNameIsNotValid = (Username == null) ||
                                !Username.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
                /*
                 * Email Restriction
                 * ---------------------
                 * This expression matches email addresses, and checks that they are of the
                 * proper form.
                 * It checks to ensure the top level domain is between 2 and 4 characters long,
                 * but does not check the specific domain against a list (especially since
                 * there are so many of them now).
                 */
                boolean EmailIsNotValid = (UserEmail == null) ||
                                !UserEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

                /*
                 * Password Restriction
                 * ------------------------
                 * At least 8 chars
                 * Contains at least one digit
                 * Contains at least one lower alpha char and one upper alpha char
                 * Contains at least one char within a set of special chars (@#%$^ etc.)
                 * Does not contain space, tab, etc.
                 */
                boolean PasswordIsNotValid = (UserPassword == null)
                                ||
                                !UserPassword.matches(
                                                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}");
                if (UserFullNameIsNotValid)
                        throw new Exception("UserFullName not set or not valid!");
                if (UserNameIsNotValid)
                        throw new Exception("Username not set or not valid!");
                if (PasswordIsNotValid)
                        throw new Exception("Password not set or not valid");
                if (EmailIsNotValid)
                        throw new Exception("Email not set or not valid!");

                Role role = roleRepo.findByRoleName("User");
                Set<Role> userRoles = new HashSet<>();
                userRoles.add(role);
                user.setRole(userRoles);
                user.setUserName(Username);
                user.setUserFullName(UserFullname);
                user.setEmail(UserEmail);
                user.setUserPassword(getEncodedPassword(UserPassword));
                user.setSecurityq(securityq);
                return userRepo.save(user);

        }

        // !! FILE PATH INCLUDED

        @Override
        public User updateUser(User user, MultipartFile file) throws Exception {
                // ! UserEmail & Username can't be changed.
                String UserEmail = user.getEmail();
                String UserName = user.getUserName();
                String UserFullName = user.getUserFullName();
                String UserPassword = user.getUserPassword();
                String DOB = user.getDOB();
                String city = user.getCity();
                String gender = user.getGender();
                String image = file.getOriginalFilename();
                boolean UserFullNameIsNotValid = (UserFullName == null) ||
                                !UserFullName.matches("^[a-zA-Z]+(([' ][a-zA-Z ])?[a-zA-Z]*)*$");

                boolean PasswordIsNotValid = (UserPassword == null)
                                ||
                                !UserPassword.matches(
                                                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}");

                if (UserFullNameIsNotValid)
                        throw new Exception("UserFullName not set or not valid!");

                if (PasswordIsNotValid)
                        throw new Exception("Password not set or not valid");

                if (DOB == null)
                        user.setDOB(null);
                else {
                        boolean DOBIsNotValid = !DOB
                                        .matches("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$");
                        if (DOBIsNotValid)
                                throw new Exception("DOB not set as not valid");
                        else
                                user.setDOB(DOB);
                }
                if (city == null)
                        user.setCity(null);
                else {
                        boolean cityIsNotValid = !city.matches("^[a-zA-Z]+((['][a-zA-Z ])?[a-zA-Z]*)*$");
                        if (cityIsNotValid)
                                throw new Exception("City not set as not valid");
                        else
                                user.setCity(city);
                }
                if (gender == null)
                        user.setGender(null);
                else {
                        boolean genderIsNotValid = !gender.matches("^[a-zA-Z]+(([' ][a-zA-Z ])?[a-zA-Z]*)*$");
                        if (genderIsNotValid)
                                throw new Exception("Gender not set as not valid");
                        else
                                user.setGender(gender);
                }
                if (image == null)
                        user.setProfileImage("defaultProfile.jpg");
                else {
                        boolean profileImageIsNotValid = !image.matches("([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)");
                        if (profileImageIsNotValid)
                                throw new Exception("ProfileImage not set as not valid");
                        else {
                                String fileNameWithoutExtension = image.substring(0, image.lastIndexOf('.'));
                                String fileExtension = image.substring(image.lastIndexOf("."));
                                String profileImage = fileNameWithoutExtension + "___"
                                                + PostServiceImpl.getAlphaNumericString(5)
                                                + fileExtension;
                                // * save file to folder
                                file.transferTo(new File(PostServiceImpl.FOLDER_PATH + profileImage));
                                user.setProfileImage(profileImage);
                        }
                }

                Role role = roleRepo.findByRoleName("User");
                Set<Role> userRoles = new HashSet<>();
                userRoles.add(role);

                user.setRole(userRoles);
                user.setUserName(UserName);
                user.setUserFullName(UserFullName);
                user.setEmail(UserEmail);
                user.setUserPassword(getEncodedPassword(UserPassword));
                return userRepo.save(user);
        }

        @Override
        public User changePassword(User user) throws Exception {
                String UserEmail = user.getEmail();
                String UserPassword = user.getUserPassword();
                String securityq = user.getSecurityq();
                User existedUser = userRepo.findByUserDetails(UserEmail,securityq);
                if (existedUser == null)
                        throw new Exception("No User with Such Details");
                else {
                        boolean PasswordIsNotValid = (UserPassword == null)
                                        ||
                                        !UserPassword.matches(
                                                        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}");
                        if (PasswordIsNotValid)
                                throw new Exception("Password not set or not valid");
                        else {
                                existedUser.setUserPassword(getEncodedPassword(UserPassword));
                                userRepo.save(existedUser);
                                return existedUser;
                        }
                }
        }

        private Boolean user_exists(User user) {
                if (userRepo.findByUserName(user.getUserName()) != null)
                        return true;
                if (userRepo.findByEmail(user.getEmail()) != null)
                        return true;
                return false;
        }

        public String getEncodedPassword(String password) {
                return passwordEncoder.encode(password);
        }
}
