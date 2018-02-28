package org.obarcia.demo.models;

import java.util.List;

/**
 *
 * @author obarcia
 */
public class ListPagination
{
    private Integer offset;
    private Integer limit;
    private Integer total;
    private List records;
    private String urlBase;
    private String type;
    
    public Integer getOffset()
    {
        return offset;
    }
    public void setOffset(Integer value)
    {
        offset = value;
    }
    public Integer getLimit()
    {
        return limit;
    }
    public void setLimit(Integer value)
    {
        limit = value;
    }
    public Integer getTotal()
    {
        return total;
    }
    public void setTotal(Integer value)
    {
        total = value;
    }
    public List getRecords()
    {
        return records;
    }
    public void setRecords(List value)
    {
        records = value;
    }
    public String getUrlBase()
    {
        return urlBase;
    }
    public void setUrlBase(String value)
    {
        urlBase = value;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String value)
    {
        type = value;
    }
    public Integer getCurrent()
    {
        if (limit > 0) {
            return (offset / limit) + 1;
        }
        
        return 1;
    }
    public Integer getPages()
    {
        if (limit > 0) {
            return (total / limit) + 1;
        }
        
        return 1;
    }
}