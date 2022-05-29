package com.example.twitterclone;

import com.example.twitterclone.dao.UserDao;
import com.example.twitterclone.entitites.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class )
@SpringBootTest
class UserDaoTest {

    @Autowired
    UserDao userDao;
    long initialSize;
    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        initialSize = userDao.count();
        //TODO Add counter to the StateDao to get the number of ids
        user1 = new User("TestFN", "TestLN", "TestEmail", "TestUsername");
        user2 = new User("TestFN2", "TestLN2", "TestEmail2", "TestUsername2");
        userDao.save(user1);
        userDao.save(user2);
    }

    @AfterEach
    void cleanUp() {
        userDao.delete(user1.getId());
        userDao.delete(user2.getId());
    }

    @Test
    @DisplayName("Test the save, getAll and count function")
    void testSaveGetAllAndCount() {
        assertEquals(initialSize + 2, userDao.count());
    }

    @Test
    @DisplayName("Test the get function")
    void testGet() {
        assertEquals(user1.getUsername(), userDao.get(user1.getId()).getUsername());
        assertEquals(user2.getUsername(), userDao.get(user2.getId()).getUsername());
    }

    @Test
    @DisplayName("Test the update function")
    void testUpdate() {
        String dummyName = "TestingName";
        User user = userDao.get(user1.getId());
        user.setUsername(dummyName);
        userDao.update(user);
        assertEquals(dummyName, userDao.get(user1.getId()).getUsername());
    }

    @Test
    @DisplayName("Test the delete function")
    void testDelete() {
        User dummyUser = new User("TestFN3", "TestLN3", "TestEmail3", "TestUsername3");
        userDao.save(dummyUser);
        long sizeAfterSave = userDao.count();
        userDao.delete(dummyUser.getId());
        assertEquals(sizeAfterSave - 1, userDao.count());
        assertNull(userDao.get(dummyUser.getId()));
    }

}
