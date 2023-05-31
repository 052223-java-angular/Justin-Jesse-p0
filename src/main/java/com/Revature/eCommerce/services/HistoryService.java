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
    private final History history;

    public HistoryService(HistoryDAO historyDao, History history) {
        this.historyDao = historyDao;
        this.history = history;
    }


    public void createOrder(ArrayList<CartItem> items, String historyId) {
        for (CartItem item : items)
        {
            String historyItemId = UUID.randomUUID().toString();
            historyDao.save(historyItemId, item, historyId);
        }

    }
/**
    public List<HistoryItem> getAllHistory(){
        List<HistoryItem> history = historyDao.getAllHistory();
        return history;
    }*/

    public List<HistoryItem> getAllHistoryById(String historyId){
        List<HistoryItem> History = historyDao.getAllHistoryById(historyId);
        return History;
    }
    public boolean doesUserHaveHistory(String userID)
    {
        Optional<History> historyOptional = historyDao.findByUserId(userID);
        return historyOptional.isPresent();
    }
    public void createHistory(String userId, String historyId, int total_Amount)
    {
        historyDao.setHistory(historyId, userId, total_Amount);
    }

    public Optional<History> findByUserId(String userId){
        return historyDao.findByUserId(userId);
    }
}
