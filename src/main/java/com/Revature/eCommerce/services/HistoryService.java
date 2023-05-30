package com.Revature.eCommerce.services;
import java.util.List;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.dao.HistoryDAO;
import com.Revature.eCommerce.models.HistoryItem;
import java.util.ArrayList;
import java.util.UUID;

public class HistoryService {

    private final HistoryDAO historyDao;

    public HistoryService(HistoryDAO historyDao) {
        this.historyDao = historyDao;
    }


    public void createOrder(ArrayList<CartItem> items) {
        for (CartItem item : items)
        {
            String historyItemId = UUID.randomUUID().toString();
            historyDao.save(historyItemId, item);
        }

    }
    public List<HistoryItem> getAllHistory(){
        List<HistoryItem> history = historyDao.getAllHistory();
        return history;
    }
}
