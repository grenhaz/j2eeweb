package org.obarcia.demo.components.datatables;

/**
 *
 * @author obarcia
 */
public class DataTablesOrder
{
    public static int ORDER_ASC = 0;
    public static int ORDER_DESC = 1;
    
    private String data;
    private Integer dir;
    
    public String getData()
    {
        return data;
    }
    public void setData(String value)
    {
        data = value;
    }
    public Integer getDir()
    {
        return dir;
    }
    public void setDir(Integer value)
    {
        dir = value;
    }
}
