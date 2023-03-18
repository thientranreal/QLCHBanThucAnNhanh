package BUS;

import DAO.ThongKe_DAO;
import DTO.ThongKe_DTO;

import java.util.ArrayList;

public class ThongKe_BUS {
    private static ThongKe_DAO tkDao = new ThongKe_DAO();
    public ArrayList<String> getAllProduct() {
        return tkDao.getAllProduct();
    }
    public ArrayList<ThongKe_DTO> thongKe(String fromDate, String toDate) {
        return tkDao.thongKe(fromDate, toDate);
    }
    public ArrayList<ThongKe_DTO> thongKeDieuKien(String fromDate, String toDate, String proId) {
        return tkDao.thongKeDieuKien(fromDate, toDate, proId);
    }
}
