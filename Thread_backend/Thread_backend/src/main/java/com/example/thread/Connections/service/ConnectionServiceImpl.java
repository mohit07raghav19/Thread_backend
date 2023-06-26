package com.example.thread.Connections.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.thread.Connections.model.Connections;
import com.example.thread.Connections.repo.ConnectionRepo;
import com.example.thread.User.model.User;
import com.example.thread.User.model.UserResponse;
import com.example.thread.User.repo.UserRepo;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ConnectionRepo connectionRepo;

    @Override
    public void initConnections() {

        if (connectionRepo.findAll().size() == 0) {
            User user0 = userRepo.findByUserName("Manish_Rawat");
            User user1 = userRepo.findByUserName("karthik_bannu");
            User user2 = userRepo.findByUserName("Slumber_420");
            User user3 = userRepo.findByUserName("money_heist");
            User user4 = userRepo.findByUserName("asur_official");
            User user5 = userRepo.findByUserName("marvel_official");
            User user6 = userRepo.findByUserName("admin");
            User user7 = userRepo.findByUserName("Yash_lcs57");
            User user8 = userRepo.findByUserName("Mohit_lcs32");
            User user9 = userRepo.findByUserName("Manjeet_lcs29");
            User user10 = userRepo.findByUserName("Harshit_lcs21");
            User user11 = userRepo.findByUserName("Sunil_lcs48");
            User user12 = userRepo.findByUserName("Umesh_lcs52");
            User user13 = userRepo.findByUserName("iiitl_official");
            User user14 = userRepo.findByUserName("axios_iiitl");
            connectionRepo.save(new Connections(user13, user14));
            connectionRepo.save(new Connections(user14, user13));

            //
            connectionRepo.save(new Connections(user7, user6));
            connectionRepo.save(new Connections(user8, user6));
            connectionRepo.save(new Connections(user9, user6));
            connectionRepo.save(new Connections(user10, user6));
            connectionRepo.save(new Connections(user11, user6));

            //
            connectionRepo.save(new Connections(user10, user8));
            connectionRepo.save(new Connections(user11, user12));
            connectionRepo.save(new Connections(user7, user10));

            //
            connectionRepo.save(new Connections(user1, user3));
            connectionRepo.save(new Connections(user1, user2));

            //
            connectionRepo.save(new Connections(user11, user6));
            //
            connectionRepo.save(new Connections(user5, user0));
            connectionRepo.save(new Connections(user5, user1));
            connectionRepo.save(new Connections(user5, user2));
            connectionRepo.save(new Connections(user5, user3));
            connectionRepo.save(new Connections(user5, user4));
            connectionRepo.save(new Connections(user5, user7));
            connectionRepo.save(new Connections(user5, user8));
        }

    }

    @Override
    public boolean connectWithUser(Authentication authentication, String userName) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        User ConnectionUser = userRepo.findByUserName(userName);
        if (ConnectionUser == null) {
            return false;
        }
        int flag = 1;
        List<Connections> connects = connectionRepo.findAll();
        for (Connections con : connects) {

            if ((con.getConnector().getUserName().equals(LoggedInUser.getUserName())
                    && con.getConnection().getUserName().equals(ConnectionUser.getUserName()))
                    || LoggedInUser.getUserName().equals(userName)) {

                flag = 0;
                break;
            }
        }
        if (flag == 0)
            return false;
        else {

            Connections c = new Connections();
            c.setConnector(LoggedInUser);
            c.setConnection(ConnectionUser);
            connectionRepo.save(c);
            return true;
        }

    }

    @Override
    public boolean UnconnectWithUser(Authentication authentication, String userName) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        User ConnectionUser = userRepo.findByUserName(userName);
        if (ConnectionUser == null) {

            return false;
        }

        if (LoggedInUser.getUserName().equals(ConnectionUser.getUserName())) {
            return false;
        }
        String user_name = LoggedInUser.getUserName();
        String user_name2 = ConnectionUser.getUserName();

        connectionRepo.deleteConnection(user_name, user_name2);
        return true;
    }

    @Override
    public List<UserResponse> getAllConnections(Authentication authentication) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        List<User> connections = this.userRepo.findConnections(LoggedInUser.getUserName());
        List<UserResponse> userResponse = new ArrayList<UserResponse>();

        for (User u : connections) {
            userResponse.add(new UserResponse(u));
        }
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllNonConnections(Authentication authentication) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        List<User> nonConnections = this.userRepo.findNonConnections(LoggedInUser.getUserName());
        List<UserResponse> userResponse = new ArrayList<UserResponse>();

        for (User u : nonConnections) {
            if (u.getUserName().equals(LoggedInUser.getUserName()))
                continue;
            userResponse.add(new UserResponse(u));
        }
        return userResponse;
    }
}
