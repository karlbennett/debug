package org.youthnet.debug.dao.util;

import org.youthnet.debug.dao.jdbc.JdbcDao;

import java.util.Random;

/**
 * User: Karl
 * Date: 23-May-2010
 */
public class JdbcTestUtil {

    public static final Random RANDOM = new Random();
    public static final int RANDNUM = RANDOM.nextInt();

    public static final String COLLECTIVEID = "99999999-9999-9999-9999-999999999999";
    public static final String COLNAME = "TEST COLLECTIVE " + RANDNUM;
    public static final String COLSHORTNAME = "tc_99999999";

    public static final String VUOID = "88888888-8888-8888-8888-888888888888";
    public static final String VUONAME = "TEST VUO " + RANDNUM;
    public static final String VUOCODE = "TV";

    public static final String USERID = "77777777-7777-7777-7777-777777777777";
    public static final String USERNAME = "testuser" + RANDNUM;
    public static final String USRPASSWORD = "password" + RANDNUM;
    public static final String USRPASSWORDHINT = "Test password hint.";
    public static final String USRFIRSTNAME = "Test";
    public static final String USRLASTNAME = "User";
    public static final String USREMAL = "testuser@test.com";
    public static final String USRPHONENUMBER = "0123456789";
    public static final String USRWEBSITE = "test.com";
    public static final int USRENABLE = 1;
    public static final int USRACCOUNTEXPIRED = 0;
    public static final int USRACCOUNTLOCKED = 0;
    public static final int USRCREDENTIALSEXPIRED = 0;

    public static final long ROLEID = 999999999;
    public static final String ROLNAME = "TEST ROLE " + RANDNUM;
    public static final String ROLDESCRIPTION = "A role created for testing.";

    private JdbcTestUtil() {}

    public static void createCollective(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("INSERT INTO collective(id, name, shortname) VALUES (X'"
                        + COLLECTIVEID.replace("-", "") + "', '" + COLNAME + "', '" + COLSHORTNAME + "')");
    }

    public static void createVuo(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("INSERT INTO vuo(id, name, vuoCode, col_id) VALUES (X'"
                + VUOID.replace("-", "") + "', '" + VUONAME + "', '" + VUOCODE + "', X'"
                + COLLECTIVEID.replace("-", "") + "')");
    }

    public static void createUser(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("INSERT INTO app_user(id, username, password, password_hint, first_name, " +
                "last_name, email, phone_number, website, account_enabled, account_expired, account_locked, " +
                "credentials_expired, vuo_id) VALUES (X'" + USERID.replace("-", "") + "', '" + USERNAME + "', '"
                + USRPASSWORD + "', '" + USRPASSWORDHINT + "', '" + USRFIRSTNAME + "', '" + USRLASTNAME
                + "', '" + USREMAL + "', '" + USRPHONENUMBER + "', '" + USRWEBSITE + "', " + USRENABLE
                + ", " + USRACCOUNTEXPIRED + ", " + USRACCOUNTLOCKED + ", " + USRCREDENTIALSEXPIRED
                + ", X'" + VUOID.replace("-", "") + "')");
    }

    public static void createRole(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("INSERT INTO role(id, name, description) VALUES ("
                + ROLEID + ", '" + ROLNAME.substring(0, 19) + "', '" + ROLDESCRIPTION + "')");
    }

    public static void addRole(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("INSERT INTO user_role(user_id, role_id) VALUES (X'" + USERID.replace("-", "")
                + "', " + ROLEID + ")");    
    }

    public static void deleteCollective(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("DELETE FROM collective WHERE id = X'" + COLLECTIVEID.replace("-", "") + "'");
    }

    public static void deleteVuo(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("DELETE FROM vuo WHERE id = X'" + VUOID.replace("-", "") + "'");
    }

    public static void deleteUser(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("DELETE FROM app_user WHERE id = X'" + USERID.replace("-", "") + "'");
    }

    public static void deleteRole(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("DELETE FROM role WHERE id = " + ROLEID);
    }

    public static void removeRole(JdbcDao jdbcDao) {
        jdbcDao.executeQuery("DELETE FROM user_role WHERE user_id = X'" + USERID.replace("-", "") + "'");
    }
}
