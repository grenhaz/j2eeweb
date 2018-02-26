package org.obarcia.demo.components.bootstrap;

import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 *
 * @author obarcia
 */
public class NavBarItem extends AbstractHtmlElementTag
{
    private TagWriter tagWriter;
    private String url = "#";
    private boolean dropdown = false;
    
    public String getUrl() { return url; }
    public void setUrl(String value) { url = value; }
    public boolean getDropdown() { return dropdown; }
    public void setDropdown(boolean value) { dropdown = value; }
    
    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        this.tagWriter = tagWriter;
        
        if (dropdown) {
            tagWriter.startTag("li");
            tagWriter.writeAttribute("class", "dropdown");
            tagWriter.forceBlock();
            
            tagWriter.startTag("a");
            tagWriter.writeAttribute("href", "#");
            tagWriter.writeAttribute("class", "dropdown-toggle");
            tagWriter.writeAttribute("data-toggle", "dropdown");
            tagWriter.writeAttribute("role", "button");
            tagWriter.writeAttribute("aria-haspopup", "true");
            tagWriter.writeAttribute("aria-expanded", "false");
            tagWriter.appendValue(getTitle());
            tagWriter.forceBlock();
            tagWriter.startTag("span");
            tagWriter.writeAttribute("class", "caret");
            tagWriter.endTag(true);
            
            tagWriter.startTag("ul");
            tagWriter.writeAttribute("class", "dropdown-menu");
            tagWriter.forceBlock();
            
            return EVAL_BODY_INCLUDE;
        } else {
            tagWriter.startTag("li");
            tagWriter.forceBlock();
            tagWriter.startTag("a");
            tagWriter.writeAttribute("href", url);
            tagWriter.appendValue(getTitle());
            tagWriter.endTag(true);

            return SKIP_BODY;
        }
    }
    @Override
    public int doEndTag() throws JspException {
        if (dropdown) {
            tagWriter.endTag(true);
            tagWriter.endTag(true);
        }
        return EVAL_PAGE;
    }
}
