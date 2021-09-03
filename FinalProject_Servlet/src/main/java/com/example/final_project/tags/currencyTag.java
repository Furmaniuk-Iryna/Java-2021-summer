package com.example.final_project.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class currencyTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("₴");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

}