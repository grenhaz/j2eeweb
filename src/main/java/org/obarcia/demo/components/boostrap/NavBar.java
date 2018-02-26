package org.obarcia.demo.components.bootstrap;

import java.util.UUID;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 *
 * @author obarcia
 */
public class NavBar extends AbstractHtmlElementTag
{
    private TagWriter tagWriter;
    private String classCss = null;
    private String brandtitle = null;
    private String brandurl = "#";
    
    public String getClassCss() { return classCss; }
    public void setClassCss(String value) { classCss = value; }
    public String getBrandtitle() { return brandtitle; }
    public void setBrandtitle(String value) { brandtitle = value; }
    public String getBrandurl() { return brandurl; }
    public void setBrandurl(String value) { brandurl = value; }
    
    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        this.tagWriter = tagWriter;
        
        String collapseId = UUID.randomUUID().toString();
        
        // NAVBAR
        tagWriter.startTag("nav");
        tagWriter.writeAttribute("class", "navbar navbar-default " + (classCss != null ? classCss : "navbar-default"));
        tagWriter.forceBlock();
        tagWriter.startTag("div");
        tagWriter.writeAttribute("class", "container");
        tagWriter.forceBlock();
        
        // BRAND AND TOGGLE
        tagWriter.startTag("div");
        tagWriter.writeAttribute("class", "navbar-header");
        tagWriter.forceBlock();
        tagWriter.startTag("button");
        tagWriter.writeAttribute("type", "button");
        tagWriter.writeAttribute("class", "navbar-toggle collapsed");
        tagWriter.writeAttribute("data-toggle", "collapse");
        tagWriter.writeAttribute("data-target", "#" + collapseId);
        tagWriter.writeAttribute("aria-expanded", "false");
        tagWriter.forceBlock();
        tagWriter.startTag("span");
        tagWriter.writeAttribute("class", "sr-only");
        tagWriter.appendValue("Toggle navigation");
        tagWriter.endTag(true);
        tagWriter.startTag("span");
        tagWriter.writeAttribute("class", "icon-bar");
        tagWriter.endTag(true);
        tagWriter.startTag("span");
        tagWriter.writeAttribute("class", "icon-bar");
        tagWriter.endTag(true);
        tagWriter.startTag("span");
        tagWriter.writeAttribute("class", "icon-bar");
        tagWriter.endTag(true);
        tagWriter.endTag(true);
        if (brandtitle != null) {
            tagWriter.startTag("a");
            tagWriter.writeAttribute("class", "navbar-brand");
            tagWriter.writeAttribute("href", (brandurl != null ? brandurl : "#"));
            tagWriter.appendValue(brandtitle);
            tagWriter.endTag(true);
        }
        tagWriter.endTag(true);
        
        // ITEMS
        tagWriter.startTag("div");
        tagWriter.writeAttribute("class", "collapse navbar-collapse");
        tagWriter.writeAttribute("id", collapseId);
        tagWriter.forceBlock();
        tagWriter.startTag("ul");
        tagWriter.writeAttribute("class", "nav navbar-nav");
        tagWriter.forceBlock();
        
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        this.tagWriter.endTag(true);
        this.tagWriter.endTag(true);
        this.tagWriter.endTag(true);
        this.tagWriter.endTag(true);
        return EVAL_PAGE;
    }
}
