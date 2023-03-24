package BUS;

import DAO.Account_DAO;
import DTO.Account_DTO;

import java.util.ArrayList;

public class Account_BUS {
    private Account_DAO accountDAO = new Account_DAO();

    public ArrayList<Account_DTO> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public boolean hasAccount(String AccountID) {
        return accountDAO.hasAccount(AccountID);
    }

    public boolean hasAccountUsername(String Username) {
        return accountDAO.hasAccountUsername(Username);
    }

    public boolean hasAccountUsernameForUpdate(String AccountID, String Username) {
        return accountDAO.hasAccountUsernameForUpdate(AccountID, Username);
    }

    public Account_DTO getAccountByID(String AccountID) {
        return accountDAO.getAccountByID(AccountID);
    }

    public Account_DTO getAccountByUsername(String Username) {
        return accountDAO.getAccountByUsername(Username);
    }

    public boolean addAccount(Account_DTO account) {
        return accountDAO.addAccount(account);
    }

    public boolean updateAccount(Account_DTO account) {
        return accountDAO.updateAccount(account);
    }

    public boolean deleteAccount(String AccountID) {
        return accountDAO.deleteAccount(AccountID);
    }

    public ArrayList<Account_DTO> SearchAccountsByID(String AccountID) {
        return accountDAO.SearchAccountsByID(AccountID);
    }

    public ArrayList<Account_DTO> SearchAccountsByUsername(String Username) {
        return accountDAO.SearchAccountsByUsername(Username);
    }

    public ArrayList<Account_DTO> SearchAccountsByAuthority(String AccessID) {
        return accountDAO.SearchAccountsByAuthority(AccessID);
    }

    public Account_DTO checkLogin(String user, String pass) {
        return accountDAO.checkLogin(user, pass);
    }
}
