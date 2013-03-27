/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zurb.foundation.components;

import javax.inject.Inject;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.services.PageRenderLinkSource;

/**
 *
 * @author nighthawk
 */
public class Pagination {
    
    @Parameter(required = true, defaultPrefix = BindingConstants.PROP)
    private Integer count;
    
    @Parameter(required = true, defaultPrefix = BindingConstants.PROP)
    private Integer current;
    
    private Integer _count;
    
    private Integer _current;
    
    @Property
    private Integer _i;
        
    @Inject
    private PageRenderLinkSource linkSource;
    
    @Inject
    private ComponentResources resources;
    
    @SetupRender
    private void setupRender() {
        this._count = (count != 0) ? count : 1;
        this._current = (current <= _count) ? current : _count;
    }
    
    public String getStartLink() {
        return (!_current.equals(1)) ? linkSource.createPageRenderLinkWithContext(resources.getPage().getClass(), _current-1).toURI() : "";
    }
    
    public String getLink() {
        return linkSource.createPageRenderLinkWithContext(resources.getPage().getClass(), _i).toURI();
    }
    
    public String getEndLink() {
        return (!_current.equals(_count)) ? linkSource.createPageRenderLinkWithContext(resources.getPage().getClass(), current+1).toURI() : "";
    }
    
    public Integer getStart() {
        return 1;
    }
    
    public Integer getEnd() {
        return _count;
    }
    
    public boolean isVisible() {
        if (_count <= 8) {
            return true;
        } else {
            boolean left = (current >= _count - 1 && (_i >= 3 && _i <= _count - 4)) ^ ((current > 4 && current <= _count - 4) && (_i >= 3 && _i <= current - 1));
            boolean right = (current + 4 < _count - 4 && current < _count - 4) && (_i >= current + 4 && _i <= _count - 2);
            
            return !(right || left);
        }
    }

    public boolean getDisplayTrippleDot() {
        boolean left = (_i == 3);
        boolean right = (_i == _count - 2);
        
        return (right || left);
    }
    
    public String getCurrent() {
        return (_i.equals(_current)) ? "current" : "";
    }
    
}
