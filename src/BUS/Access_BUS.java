package BUS;

import DAO.Access_DAO;
import DTO.Access_DTO;

import java.util.ArrayList;

public class Access_BUS {
    private Access_DAO accessDAO = new Access_DAO();

    public ArrayList<Access_DTO> getAllAccesses() {
        return accessDAO.getAllAccesses();
    }

    public Access_DTO getAccessFromID(String AccessID) {
        return accessDAO.getAccessFromID(AccessID);
    }

    public String getIDFromAuthority(String Authority) {
        return accessDAO.getIDFromAuthority(Authority);
    }
}
