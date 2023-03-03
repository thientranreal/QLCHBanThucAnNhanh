package BUS;

import DAO.CT_HD_DAO;
import DTO.CT_HD_DTO;

import java.util.ArrayList;
import java.util.Map;

public class CT_HD_BUS {
    public ArrayList<CT_HD_DTO> getCT_HD_ByOrderID(String orderID) {
        return new CT_HD_DAO().getCT_HD_ByOrderID(orderID);
    }

    public ArrayList<CT_HD_DAO.Product> getProductIdAndName() {
        return new CT_HD_DAO().getProductIdAndName();
    }
}
