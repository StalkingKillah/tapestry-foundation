/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zurb.foundation.components;

import java.util.List;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 *
 * @author nighthawk
 */
@Import(library="classpath:com/zurb/foundation/assets/javascripts/jquery.foundation.orbit.js")
public class Orbit {
    
    @Inject
    private JavaScriptSupport javaScriptSupport;
    
    @Parameter(required = true, name = "images", defaultPrefix = BindingConstants.PROP)
    @Property
    private List<String> images;
    
    @Property
    private String image;
    
    /* Adds script to end of page
     * $(window).load(function() {
     *      $('#featured').orbit({ fluid: '2x1' });
     * });
     */
    @BeginRender
    private void beginRender(MarkupWriter writer) {
        String script = ""
                +"$(window).load(function() {"
                +"$('#featured').orbit({ fluid: '2x1' });"
                +"});";
        javaScriptSupport.addScript(InitializationPriority.IMMEDIATE, script, new JSONObject());
    }
    
}
