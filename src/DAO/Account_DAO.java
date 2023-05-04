package DAO;

import DTO.Account_DTO;

import java.sql.*;
import java.util.ArrayList;

public class Account_DAO {
    private JDBC conn = new JDBC();

    public ArrayList<Account_DTO> getAllAccounts() {
        ArrayList<Account_DTO> Accountlist = new ArrayList<>();
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Account Where Status=1;";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    Account_DTO account = new Account_DTO();
                    account.setAccountID(rs.getString("AccountID"));
                    account.setAccessID(rs.getString("AccessID"));
                    account.setUsername(rs.getString("Username"));
                    account.setPassword(rs.getString("Password"));
//                    System.out.println(rs.getString("Username"));
//                    System.out.println(rs.getString("Password"));

                    Accountlist.add(account);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return Accountlist;
    }

        public Account_DTO getAccountByID(String AccountID) {
        Account_DTO account = new  Account_DTO();
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Account Where Status=1 and AccountID='" + AccountID + "';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                rs.next();
                account.setAccountID(AccountID);
                account.setAccessID(rs.getString("AccessID"));
                account.setUsername(rs.getString("Username"));
                account.setPassword(rs.getString("Password"));

            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return account;
    }

    public Account_DTO getAccountByUsername(String Username) {
        Account_DTO account = new  Account_DTO();
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Account Where Status=1 and Username='" + Username + "';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                rs.next();
                account.setAccountID(rs.getString("AccountID"));
                account.setAccessID(rs.getString("AccessID"));
                account.setUsername(Username);
                account.setPassword(rs.getString("Password"));

            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return account;
    }

    public boolean addAccount(Account_DTO account) {
        boolean result = false;
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Insert into Account values(?, ?, ?, ?, 1);";
                PreparedStatement stmt = conn.getCon().prepareStatement(sqlQuery);
                stmt.setString(1, account.getAccountID());
                stmt.setString(2, account.getAccessID());
                stmt.setString(3, account.getUsername());
                stmt.setString(4, account.getPassword());
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return result;
    }

    public boolean hasAccount(String AccountID) {
        boolean result = false;
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Account Where Status=1 and AccountID='" + AccountID + "';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                result = rs.next();
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return result;
    }

    public boolean hasAccountUsername(String Username) {
        boolean result = false;
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Account Where Status=1 and Username='" + Username + "';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                result = rs.next();
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return result;
    }

    public boolean hasAccountUsernameForUpdate(String AccountID, String Username) {
        boolean result = false;
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Account Where Status=1 and Username='" + Username + "' and AccountID!='" + AccountID + "';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                result = rs.next();
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return result;
    }

    public boolean updateAccount(Account_DTO account) {
        boolean result = false;
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Update Account Set AccessID=?, Username=?, Password=? Where Status=1 and AccountID=?;";
                PreparedStatement stmt = conn.getCon().prepareStatement(sqlQuery);
                stmt.setString(1, account.getAccessID());
                stmt.setString(2, account.getUsername());
                stmt.setString(3, account.getPassword());
                stmt.setString(4, account.getAccountID());

                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return result;
    }

    public boolean deleteAccount(String AccountID) {
        boolean result = false;
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Update Account Set Status=0 Where AccountID=?;";
                PreparedStatement stmt = conn.getCon().prepareStatement(sqlQuery);
                stmt.setString(1, AccountID);
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return result;
    }

    public ArrayList<Account_DTO> SearchAccountsByID(String AccountID) {
        ArrayList<Account_DTO> ResultList = new ArrayList<Account_DTO>();

        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * From Account Where Status=1 and AccountID Like '%" + AccountID + "%';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    Account_DTO account = new Account_DTO();
                    account.setAccountID(rs.getString("AccountID"));
                    account.setAccessID(rs.getString("AccessID"));
                    account.setUsername(rs.getString("Username"));
                    account.setPassword(rs.getString("Password"));
//                    System.out.println(rs.getString("AccountID"));
                    ResultList.add(account);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return ResultList;
    }

    public ArrayList<Account_DTO> SearchAccountsByUsername(String Username) {
        ArrayList<Account_DTO> ResultList = new ArrayList<>();

        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * From Account Where Status=1 and Username Like '%" + Username + "%';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    Account_DTO account = new Account_DTO();
                    account.setAccountID(rs.getString("AccountID"));
                    account.setAccessID(rs.getString("AccessID"));
                    account.setUsername(rs.getString("Username"));
                    account.setPassword(rs.getString("Password"));
//                    System.out.println(rs.getString("Username"));
                    ResultList.add(account);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return ResultList;
    }

    public ArrayList<Account_DTO> SearchAccountsByAuthority(String Authority) {
        ArrayList<Account_DTO> ResultList = new ArrayList<>();

        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Account, Access Where Status=1 and Access.AccessID = Account.AccessID and Authority Like '%" + Authority + "%';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    Account_DTO account = new Account_DTO();
                    account.setAccountID(rs.getString("AccountID"));
                    account.setAccessID(rs.getString("AccessID"));
                    account.setUsername(rs.getString("Username"));
                    account.setPassword(rs.getString("Password"));
//                    System.out.println(rs.getString("Username"));
                    ResultList.add(account);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return ResultList;
    }

    public Account_DTO checkLogin(String user, String pass) {
        Account_DTO account = new Account_DTO();
        boolean found = false;
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Account Where Status=1 and Username='" + user + "' and Password='" + pass + "';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                if (rs.next()) {
                    account.setAccountID(rs.getString("AccountID"));
                    account.setAccessID(rs.getString("AccessID"));
                    account.setUsername(rs.getString("Username"));
                    account.setPassword(rs.getString("Password"));
                    found = true;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        if (found) {
            return account;
        }

        return null;
    }

//    public static void main(String[] args) {
//        Account_DAO adao = new Account_DAO();
//        ArrayList<Account_DTO> Accountlist = adao.getAllAccounts();
//        for (Account_DTO account: Accountlist) {
//            System.out.println(account.getAccountID());
//        }
//    }
}

