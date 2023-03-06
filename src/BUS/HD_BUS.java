package BUS;

import DAO.HD_DAO;
import DTO.HD_DTO;

import java.util.ArrayList;

public class HD_BUS {
    private static HD_DAO hdDao = new HD_DAO();
    public ArrayList<HD_DTO> getAllOrders() {
        return hdDao.getAllOrders();
    }
}
