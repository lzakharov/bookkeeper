package ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.service;

import org.junit.Test;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.dao.CategoryDao;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.dao.ClientDao;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.dao.RecordDao;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.model.Category;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.model.Client;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.model.Coordinate;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.model.Record;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.model.enums.Type;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.service.impl.RecordServiceImpl;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecordServiceImplTest {
    @Test
    public void testCreate() {
        Record record = new Record(0L, 0L, 0L, Type.INCOME, 100, "description", Date.valueOf("2016-01-01"));
        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.add(record)).thenReturn(0L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);

        assertEquals(0L, recordService.create(record));
    }

    @Test
    public void testGetByClientLogin() {
        List<Record> records = new LinkedList<>();
        records.add(new Record(0L, 0L, 0L, Type.INCOME, 100, "description", Date.valueOf("2016-01-01")));
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        CategoryDao categoryDao = mock(CategoryDao.class);
        List<Category> categories = new LinkedList<>();
        categories.add(new Category(0L, "category0"));
        categories.add(new Category(1L, "category1"));
        when(categoryDao.get(0L)).thenReturn(categories.get(0));
        when(categoryDao.get(1L)).thenReturn(categories.get(1));

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getAll(0L)).thenReturn(records);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setCategoryDao(categoryDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(records, recordService.get(anyString()));
    }

    @Test
    public void testGetBalance() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getCurrentBalance(0L)).thenReturn(0L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(0L, recordService.getBalance(anyString()).longValue());
    }

    @Test
    public void testGetMonthlyIncome() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getMonthlyIncome(0L, 1)).thenReturn(0L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(0L, recordService.getMonthlyIncome(anyString(), 1).longValue());
    }

    @Test
    public void testGetIncomeList() {
        List<Record> records = new LinkedList<>();
        records.add(new Record(0L, 0L, 0L, Type.INCOME, 100, "description", Date.valueOf("2016-01-01")));
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        CategoryDao categoryDao = mock(CategoryDao.class);
        List<Category> categories = new LinkedList<>();
        categories.add(new Category(0L, "category0"));
        categories.add(new Category(1L, "category1"));
        when(categoryDao.get(0L)).thenReturn(categories.get(0));
        when(categoryDao.get(1L)).thenReturn(categories.get(1));

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getIncomeList(0L)).thenReturn(records);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setCategoryDao(categoryDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(records, recordService.getIncomeList(anyString()));
    }

    @Test
    public void testGetExpenditureList() {
        List<Record> records = new LinkedList<>();
        records.add(new Record(0L, 1L, 0L, Type.EXPENDITURE, 100, "description", Date.valueOf("2016-01-01")));
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(1L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        CategoryDao categoryDao = mock(CategoryDao.class);
        List<Category> categories = new LinkedList<>();
        categories.add(new Category(0L, "category0"));
        categories.add(new Category(1L, "category1"));
        when(categoryDao.get(0L)).thenReturn(categories.get(0));
        when(categoryDao.get(1L)).thenReturn(categories.get(1));

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getExpenditureList(1L)).thenReturn(records);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setCategoryDao(categoryDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(records, recordService.getExpenditureList(anyString()));
    }

    @Test
    public void testGetMonthlyExpenditure() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getMonthlyExpenditure(0L, 1)).thenReturn(0L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(0L, recordService.getMonthlyExpenditure(anyString(), 1).longValue());
    }

    @Test
    public void testRemoveTrue() {
        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.remove(0L)).thenReturn(true);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);

        assertTrue(recordService.remove(0L));
    }

    @Test
    public void testRemoveFalse() {
        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.remove(0L)).thenReturn(false);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);

        assertFalse(recordService.remove(0L));
    }

    @Test
    public void testGetMonthlyIncomeData() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        CategoryDao categoryDao = mock(CategoryDao.class);
        List<Category> categories = new LinkedList<>();
        categories.add(new Category(0L, "category0"));
        categories.add(new Category(1L, "category1"));
        when(categoryDao.getAll()).thenReturn(categories);

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getMonthlyIncomeForCategory(0L, 0L, 1)).thenReturn(100L);
        when(recordDao.getMonthlyIncomeForCategory(0L, 1L, 1)).thenReturn(200L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setCategoryDao(categoryDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        Map<String, Long> data = new HashMap<>();
        data.put("category0", 100L);
        data.put("category1", 200L);

        assertEquals(data,recordService.getMonthlyIncomeData(anyString(), 1));
    }

    @Test
    public void testGetMonthlyExpenditureData() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        CategoryDao categoryDao = mock(CategoryDao.class);
        List<Category> categories = new LinkedList<>();
        categories.add(new Category(0L, "category0"));
        categories.add(new Category(1L, "category1"));
        when(categoryDao.getAll()).thenReturn(categories);

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getMonthlyExpenditureForCategory(0L, 0L, 1)).thenReturn(100L);
        when(recordDao.getMonthlyExpenditureForCategory(0L, 1L, 1)).thenReturn(200L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setCategoryDao(categoryDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        Map<String, Long> data = new HashMap<>();
        data.put("category0", 100L);
        data.put("category1", 200L);

        assertEquals(data,recordService.getMonthlyExpenditureData(anyString(), 1));
    }

    @Test
    public void testGetTotalIncome() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getTotalIncome(0L)).thenReturn(200L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(200L,recordService.getTotalIncome(anyString()).longValue());
    }

    @Test
    public void testGetTotalExpenditure() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getTotalExpenditure(0L)).thenReturn(200L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(200L,recordService.getTotalExpenditure(anyString()).longValue());
    }

    @Test
    public void testGetTotalAverageIncome() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getTotalAverageIncome(0L)).thenReturn(200L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(200L,recordService.getTotalAverageIncome(anyString()).longValue());
    }

    @Test
    public void testGetTotalAverageExpenditure() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getTotalAverageExpenditure(0L)).thenReturn(200L);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(200L,recordService.getTotalAverageExpenditure(anyString()).longValue());
    }

    @Test
    public void testGetTotalMonthlyBalanceData() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(0L);

        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.get(anyString())).thenReturn(client);

        List<Coordinate> data = new LinkedList<>();
        data.add(new Coordinate("", 0L));

        RecordDao recordDao = mock(RecordDao.class);
        when(recordDao.getTotalMonthlyBalanceData(0L)).thenReturn(data);

        RecordService recordService = new RecordServiceImpl();
        ((RecordServiceImpl) recordService).setRecordDao(recordDao);
        ((RecordServiceImpl) recordService).setClientDao(clientDao);

        assertEquals(data,recordService.getTotalMonthlyBalanceData(anyString()));
    }
}
