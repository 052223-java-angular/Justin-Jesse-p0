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

/**
 * layer between the screen and DAO
 */
public class HistoryService {

    private final HistoryDAO historyDao;
    private final History history;


    public HistoryService(HistoryDAO historyDao, History history) {
        this.historyDao = historyDao;
        this.history = history;
    }


    /**
     * Used to create history items once a user checks out their cart
     * @param items
     * @param historyId
     */
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

    /**
     * Gets all the history items based on the history id
     * @param historyId
     * @return
     */
    public List<HistoryItem> getAllHistoryById(String historyId){
        List<HistoryItem> History = historyDao.getAllHistoryById(historyId);
        return History;
    }

    /**
     * Checks if user has a history id using their history id
     * @param userID - user id
     * @return true/false
     */
    public boolean doesUserHaveHistory(String userID)
    {
        Optional<History> historyOptional = historyDao.findByUserId(userID);
        return historyOptional.isPresent();
    }

    /**
     * creates a history id for the user
     * @param userId
     * @param historyId
     * @param total_Amount
     */
    public void createHistory(String userId, String historyId, int total_Amount)
    {
        historyDao.setHistory(historyId, userId, total_Amount);
    }

    /**
     * Returns
     * @param userId - userid
     * @return History order for the user
     */
    public Optional<History> findByUserId(String userId){
        return historyDao.findByUserId(userId);
    }
}
