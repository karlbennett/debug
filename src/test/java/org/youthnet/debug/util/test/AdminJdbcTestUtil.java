package org.youthnet.debug.util.test;

import org.springframework.stereotype.Component;
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.dao.jdbc.sql.SqlSyntax;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;

/**
 * User: Karl
 * Date: 23-May-2010
 */
@Component("adminJdbcTestUtil")
public class AdminJdbcTestUtil {

    @Resource(name = "adminJdbcDaoImpl")
    private JdbcDao adminJdbcDao;

    @Resource(name = "sqlSyntax")
    private SqlSyntax sqlSyntax;

    private final Random RANDOM = new Random();
    private final int RANDNUM = RANDOM.nextInt(99999);

    private final String COLLECTIVEID = "99999999-9999-9999-9999-999999999999";
    private final String COLNAME = "TEST COLLECTIVE " + RANDNUM;
    private final String COLSHORTNAME = "tc_99999999";

    private final String VUOID = "88888888-8888-8888-8888-888888888888";
    private final String VUONAME = "TEST VUO " + RANDNUM;
    private final String VUOCODE = "TV";

    private final String USERID = "77777777-7777-7777-7777-777777777777";
    private final String USERNAME = "testuser" + RANDNUM;
    private final String USRPASSWORD = "password" + RANDNUM;
    private final String USRPASSWORDHINT = "Test password hint.";
    private final String USRFIRSTNAME = "Test";
    private final String USRLASTNAME = "User";
    private final String USREMAL = "testuser@test.com";
    private final String USRPHONENUMBER = "0123456789";
    private final String USRWEBSITE = "test.com";
    private String USRENABLE;
    private String USRACCOUNTEXPIRED;
    private String USRACCOUNTLOCKED;
    private String USRCREDENTIALSEXPIRED;

    private final long ROLEID = 999999999;
    private final String ROLNAME = "TEST ROLE " + RANDNUM;
    private final String ROLDESCRIPTION = "A role created for testing.";

    @PostConstruct
    private void init() {
        USRENABLE = sqlSyntax.getSqlTrue();
        USRACCOUNTEXPIRED = sqlSyntax.getSqlFalse();
        USRACCOUNTLOCKED = sqlSyntax.getSqlFalse();
        USRCREDENTIALSEXPIRED = sqlSyntax.getSqlFalse();
    }

    public void createCollective() {
        adminJdbcDao.executeQuery("INSERT INTO " + sqlSyntax.getAdminSchema()
                + "collective(id, name, shortname) VALUES (" + sqlSyntax.getBinTypeStart()
                + COLLECTIVEID.replace("-", "") + sqlSyntax.getBinTypeEnd() + ", '" + COLNAME + "', '"
                + COLSHORTNAME + "')");
    }

    public void createVuo() {
        adminJdbcDao.executeQuery("INSERT INTO " + sqlSyntax.getAdminSchema()
                + "vuo(id, name, vuoCode, col_id) VALUES (" + sqlSyntax.getBinTypeStart() + VUOID.replace("-", "")
                + sqlSyntax.getBinTypeEnd() + ", '" + VUONAME + "', '" + VUOCODE + "', "
                + sqlSyntax.getBinTypeStart() + COLLECTIVEID.replace("-", "") + sqlSyntax.getBinTypeEnd() + ")");
    }

    public void createUser() {
        adminJdbcDao.executeQuery("INSERT INTO " + sqlSyntax.getAdminSchema()
                + "app_user(id, username, password, password_hint, first_name, "
                + "last_name, email, phone_number, website, account_enabled, account_expired, account_locked, "
                + "credentials_expired, vuo_id) VALUES (" + sqlSyntax.getBinTypeStart() + USERID.replace("-", "")
                + sqlSyntax.getBinTypeEnd() + ", '" + USERNAME + "', '" + USRPASSWORD + "', '" + USRPASSWORDHINT
                + "', '" + USRFIRSTNAME + "', '" + USRLASTNAME + "', '" + USREMAL + "', '" + USRPHONENUMBER + "', '"
                + USRWEBSITE + "', " + USRENABLE + ", " + USRACCOUNTEXPIRED + ", " + USRACCOUNTLOCKED + ", "
                + USRCREDENTIALSEXPIRED + ", " + sqlSyntax.getBinTypeStart() + VUOID.replace("-", "")
                + sqlSyntax.getBinTypeEnd() + ")");
    }

    public void createRole() {
        adminJdbcDao.executeQuery("INSERT INTO " + sqlSyntax.getAdminSchema()
                + "role(id, name, description) VALUES (" + ROLEID + ", '" + ROLNAME + "', '"
                + ROLDESCRIPTION + "')");
    }

    public void addRole() {
        adminJdbcDao.executeQuery("INSERT INTO " + sqlSyntax.getAdminSchema()
                + "user_role(user_id, role_id) VALUES (" + sqlSyntax.getBinTypeStart() + USERID.replace("-", "")
                + sqlSyntax.getBinTypeEnd() + ", " + ROLEID + ")");
    }

    public void deleteCollective() {
        adminJdbcDao.executeQuery("DELETE FROM " + sqlSyntax.getAdminSchema() + "collective WHERE id = "
                + sqlSyntax.getBinTypeStart() + COLLECTIVEID.replace("-", "") + sqlSyntax.getBinTypeEnd());
    }

    public void deleteVuo() {
        adminJdbcDao.executeQuery("DELETE FROM " + sqlSyntax.getAdminSchema() + "vuo WHERE id = "
                + sqlSyntax.getBinTypeStart() + VUOID.replace("-", "") + sqlSyntax.getBinTypeEnd());
    }

    public void deleteUser() {
        adminJdbcDao.executeQuery("DELETE FROM " + sqlSyntax.getAdminSchema() + "app_user WHERE id = "
                + sqlSyntax.getBinTypeStart() + USERID.replace("-", "") + sqlSyntax.getBinTypeEnd());
    }

    public void deleteRole() {
        adminJdbcDao.executeQuery("DELETE FROM " + sqlSyntax.getAdminSchema() + "role WHERE id = " + ROLEID);
    }

    public void removeRole() {
        adminJdbcDao.executeQuery("DELETE FROM " + sqlSyntax.getAdminSchema() + "user_role WHERE user_id = "
                + sqlSyntax.getBinTypeStart() + USERID.replace("-", "") + sqlSyntax.getBinTypeEnd());
    }

    public Random getRANDOM() {
        return RANDOM;
    }

    public int getRANDNUM() {
        return RANDNUM;
    }

    public String getCOLLECTIVEID() {
        return COLLECTIVEID;
    }

    public String getCOLNAME() {
        return COLNAME;
    }

    public String getCOLSHORTNAME() {
        return COLSHORTNAME;
    }

    public String getVUOID() {
        return VUOID;
    }

    public String getVUONAME() {
        return VUONAME;
    }

    public String getVUOCODE() {
        return VUOCODE;
    }

    public String getUSERID() {
        return USERID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getUSRPASSWORD() {
        return USRPASSWORD;
    }

    public String getUSRPASSWORDHINT() {
        return USRPASSWORDHINT;
    }

    public String getUSRFIRSTNAME() {
        return USRFIRSTNAME;
    }

    public String getUSRLASTNAME() {
        return USRLASTNAME;
    }

    public String getUSREMAL() {
        return USREMAL;
    }

    public String getUSRPHONENUMBER() {
        return USRPHONENUMBER;
    }

    public String getUSRWEBSITE() {
        return USRWEBSITE;
    }

    public String getUSRENABLE() {
        return USRENABLE;
    }

    public String getUSRACCOUNTEXPIRED() {
        return USRACCOUNTEXPIRED;
    }

    public String getUSRACCOUNTLOCKED() {
        return USRACCOUNTLOCKED;
    }

    public String getUSRCREDENTIALSEXPIRED() {
        return USRCREDENTIALSEXPIRED;
    }

    public long getROLEID() {
        return ROLEID;
    }

    public String getROLNAME() {
        return ROLNAME;
    }

    public String getROLDESCRIPTION() {
        return ROLDESCRIPTION;
    }
}
