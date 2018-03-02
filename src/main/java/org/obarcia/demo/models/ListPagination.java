package org.obarcia.demo.models;

import java.util.List;

/**
 *
 * @author obarcia
 */
public class ListPagination<T>
{
    private Integer offset;
    private Integer limit;
    private Integer total;
    private List records;
    private String type;
    private String tag;
    
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
    public List<T> getRecords()
    {
        return records;
    }
    public void setRecords(List<T> value)
    {
        records = value;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String value)
    {
        type = value;
    }
    public String getTag()
    {
        return tag;
    }
    public void setTag(String value)
    {
        tag = value;
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