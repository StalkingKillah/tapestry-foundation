/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zurb.foundation.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.TypeCoercer;
import org.apache.tapestry5.services.Heartbeat;

/**
 *
 * @author nighthawk
 */
@Import(library="classpath:com/zurb/foundation/assets/javascripts/jquery.foundation.reveal.js")
public class Modal {

    private final String baseStyle = "reveal-modal [expand, xlarge, large, medium, small]";
    
    @Property
    @Parameter(required = true, autoconnect = true, defaultPrefix = BindingConstants.LITERAL)
    private String id;
    
    @Property
    @Parameter(required = false, name = "modal-class", defaultPrefix = BindingConstants.LITERAL)
    private String modalContainerClass;
    
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.BLOCK)
    private Block link;
    
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.BLOCK)
    private Block modal;
    
    private Element pageBody;
    private Element modalElement;
    
    @Environmental
    private Heartbeat heartbeat;
    
    @Inject
    private TypeCoercer typeCoercer;
        
    public String getModalStyle() {
        return (modalContainerClass == null || modalContainerClass.isEmpty()) ? baseStyle : baseStyle + " " + modalContainerClass;
    }
    
    @AfterRender
    private void afterRender(MarkupWriter writer) {
        pageBody = writer.getDocument().find("html/body");
        modalElement = writer.getElement().getElementByAttributeValue("class", getModalStyle());
        
        final String modalMarkup = modalElement.toString();
        modalElement.remove();
        
        Runnable appendToBody = new Runnable() {
            public void run() {
                pageBody.raw(modalMarkup);
            }
        };
        heartbeat.defer(appendToBody);
    }

}
