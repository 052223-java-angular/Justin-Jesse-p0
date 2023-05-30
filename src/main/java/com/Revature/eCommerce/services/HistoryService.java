package com.Revature.eCommerce.services;
import java.util.List;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.dao.HistoryDAO;
import com.Revature.eCommerce.models.HistoryItem;
import com.Revature.eCommerce.models.History;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Optional;

public class HistoryService {

    private final HistoryDAO historyDao;
    private final Session session; 

    public HistoryService(HistoryDAO historyDao, Session session) {
        this.historyDao = historyDao;
        this.session = session;
    }


    public void createOrder(ArrayList<CartItem> items, String userId) {
        Optional<History> history = historyDao.findByUserId(session.getId());

        if (history.isEmpty()) {
            String historyId= UUID.randomUUID().toString();
        historyDao.setHistory(historyId, userId, 0f);

        }
        for (CartItem item : items)
        {
            String historyItemId = UUID.randomUUID().toString();
            historyDao.save(historyItemId, item, userId);
        }

    }
    public List<HistoryItem> getAllHistory(){
        List<HistoryItem> history = historyDao.getAllHistory();
        return history;
    }

    public List<HistoryItem> getAllHistoryById(String userId){
        Optional<History> history = historyDao.findByUserId(userId);
        if (history.isEmpty()) {
            String historyId= UUID.randomUUID().toString();
        historyDao.setHistory(historyId, userId, 0f);

        }
        List<HistoryItem> History = historyDao.getAllHistoryById(userId);
        return History;
    }
}
