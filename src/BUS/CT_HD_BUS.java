package BUS;

import DAO.CT_HD_DAO;
import DTO.CT_HD_Product_DTO;
import DTO.CT_HD_ShowTTChung_DTO;
import DTO.CT_HD_ShowTable_DTO;

import java.util.ArrayList;

public class CT_HD_BUS {
    private static CT_HD_DAO daoCTHD = new CT_HD_DAO();
    public ArrayList<CT_HD_ShowTable_DTO> getCT_HD_ByOrderID(String orderID) {
        return daoCTHD.getCT_HD_ByOrderID(orderID);
    }
    public ArrayList<CT_HD_Product_DTO> getProductInfo() {
        return daoCTHD.getProductInfo();
    }
    public CT_HD_ShowTTChung_DTO getCusEmDetailByOrderId(String orderID) {
        return daoCTHD.getCusEmDetailByOrderId(orderID);
    }
    public int updateSLByProOrderId(String proId, String OrderId, int sl) {
        return daoCTHD.updateSLByProOrderId(proId, OrderId, sl);
    }
    public int addNewCT_HD(String proId, String OrderId, int sl) {
        return daoCTHD.addNewCT_HD(proId, OrderId, sl);
    }
    public int updateProductStock(String proId, int stock) {
        return daoCTHD.updateProductStock(proId, stock);
    }
    public int deleteProduct(String OrderId, String proId) {
        return daoCTHD.deleteProduct(OrderId, proId);
    }
}
