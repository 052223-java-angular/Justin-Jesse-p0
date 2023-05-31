package com.Revature.eCommerce.services;

import com.Revature.eCommerce.dao.HistoryDAO;
import com.Revature.eCommerce.models.CartItem;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class HistoryServiceTest extends TestCase {

    private HistoryService historyService;
    @Mock
    HistoryDAO historyDao;
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        historyService = new HistoryService(historyDao);
    }

    public void tearDown() throws Exception {
    }

    public void testCreateOrder() {

        ArrayList<CartItem> items = new ArrayList<>();
        items.add(new CartItem("C1", "P1", "CI1", 1, 5));
        items.add(new CartItem("C2", "P2", "CI2", 5, 10));

        for (CartItem item : items) {
            String historyItemId = UUID.randomUUID().toString();
           // doNothing().when(historyDao).save(historyItemId, item);
           // historyService.createOrder(items);
        }

        verify(historyDao, times(items.size()));
    }


    }