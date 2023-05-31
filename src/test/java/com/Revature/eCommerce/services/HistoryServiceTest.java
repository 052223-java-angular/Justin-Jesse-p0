package com.Revature.eCommerce.services;

import com.Revature.eCommerce.dao.HistoryDAO;
import com.Revature.eCommerce.models.CartItem;
import com.Revature.eCommerce.models.History;
import com.Revature.eCommerce.models.HistoryItem;
import com.Revature.eCommerce.models.User;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;

public class HistoryServiceTest extends TestCase {
    HistoryService historyService;
    @Mock
    HistoryDAO historyDAO;
    @Mock
    History history;

    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.historyService = new HistoryService(historyDAO, history);
    }

    public void testCreateOrder()
    {
        ArrayList<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem("CI1", "P1", "C1", 1, 100));
        cartItems.add(new CartItem("CI2", "P1", "C1", 1, 100));

        String id = "ID";
        String historyId = "historyId";

        doNothing().when(historyDAO).save(id,cartItems.get(0),historyId);
         historyService.createOrder(cartItems,historyId);
         List< HistoryItem> result = historyDAO.getAllHistoryById(historyId);

        assertEquals(2,cartItems.size());
    }

    //public void testGetAllHistory()
    //{

    //}

    public void testGetAllHistoryById() {
        String historyId = "historyId";
        List<HistoryItem> historyItems = new ArrayList<>();
        historyItems.add(new HistoryItem("H1","P1",historyId,1,100));
        historyItems.add(new HistoryItem("H1","P1","HI2",1,100));
        when(historyDAO.getAllHistoryById(historyId)).thenReturn(historyItems);
        List<HistoryItem> result = historyService.getAllHistoryById(historyId);
        verify(historyDAO, times(1)).getAllHistoryById(historyId);
        assertEquals(historyId,result.get(0).getHistoryId());

    }

    public void testDoesUserHaveHistory() {
        String userId = "userId";
        History history = new History("H1",userId,0);
        when(historyDAO.findByUserId(userId)).thenReturn(Optional.of(history));
        boolean result = historyService.doesUserHaveHistory(userId);
        verify(historyDAO, times(1)).findByUserId(userId);
        assertTrue(result);

    }

    public void testCreateHistory() {
        String userId = "userId";
        String historyId = "historyId";
        int totalAmount = 100;
        historyService.createHistory(userId, historyId, totalAmount);
        verify(historyDAO, times(1)).setHistory(historyId, userId, totalAmount);

    }

    public void testFindByUserId() {
        String valid = "H1";
        String userId = "userId";
        History history = new History(valid, userId, 0);
        when(historyDAO.findByUserId(userId)).thenReturn(Optional.of(history));
        Optional<History> result = historyService.findByUserId(userId);

        verify(historyDAO, times(1)).findByUserId(userId);
        assertEquals(valid, result.get().getId());

    }
}