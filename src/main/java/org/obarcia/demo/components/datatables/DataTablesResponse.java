package org.obarcia.demo.components.datatables;

import java.util.List;

/**
 *
 * @author Heck
 */
public class DataTablesResponse<T>
{
    private int draw = 1;
    private int recordsTotal = 0;
    private int recordsFiltered = 0;
    private List<T> data;
    
    public int getDraw()
    {
        return draw;
    }
    public void setDraw(int value)
    {
        draw = value;
    }
    public int getRecordsTotal()
    {
        return recordsTotal;
    }
    public void setRecordsTotal(int value)
    {
        recordsTotal = value;
    }
    public int getRecordsFiltered()
    {
        return recordsFiltered;
    }
    public void setRecordsFiltered(int value)
    {
        recordsFiltered = value;
    }
    public List<T> getData()
    {
        return data;
    }
    public void setData(List<T> value)
    {
        data = value;
    }
}