package org.obarcia.demo.components.datatables;

/**
 *
 * @author obarcia
 */
public class DataTablesColumn
{
    private String data;
    private String name;
    private Boolean searchable;
    private Boolean orderable;
    private String search;
    
    public String getData()
    {
        return data;
    }
    public void setData(String value)
    {
        data = value;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String value)
    {
        name = value;
    }
    public Boolean getSearchable()
    {
        return searchable;
    }
    public void setSearchable(Boolean value)
    {
        searchable = value;
    }
    public Boolean getOrderable()
    {
        return orderable;
    }
    public void setOrderable(Boolean value)
    {
        orderable = value;
    }
    public String getSearch()
    {
        return search;
    }
    public void setSearch(String value)
    {
        search = value;
    }
}
