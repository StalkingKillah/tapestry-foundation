/**
 * 
 */
package com.zurb.foundation.services.javascript;

import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Mapper;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.got5.tapestry5.jquery.utils.JQueryUtils;

/**
 * @author nighthawk
 * 
 */
public class FoundationStack implements JavaScriptStack {

	public static final String STACK_ID = "FoundationStack";
	
	public static final String[] javaScriptStackContribute = {"classpath:com/zurb/foundation/javascripts/foundation.min.js","classpath:com/zurb/foundation/javascripts/modernizr.foundation.js"};
	
	public static final String[] stylesheetStackContribute = {"classpath:com/zurb/foundation/stylesheets/foundation.min.css"};

	private final List<Asset> javaScriptStack;

	private final List<StylesheetLink> stylesheetStack;

	/**
	 * 
	 */
	public FoundationStack(final AssetSource assetSource,	@Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode) {
		final Mapper<String, Asset> pathToAsset = new Mapper<String, Asset>() {
			public Asset map(String path) {
				return assetSource.getExpandedAsset(path);
			}
		};
		
		final Mapper<String, StylesheetLink> pathToStylesheetLink = F.combine(pathToAsset, JQueryUtils.assetToStylesheetLink);

		if (productionMode) {
			javaScriptStack = F.flow("classpath:com/zurb/foundation/javascripts/foundation.min.js", "classpath:com/zurb/foundation/javascripts/modernizr.foundation.js").map(pathToAsset).toList();
			stylesheetStack = F.flow("classpath:com/zurb/foundation/stylesheets/foundation.min.css").map(pathToStylesheetLink).toList();

		} else {
			javaScriptStack = F.flow("classpath:com/zurb/foundation/javascripts/foundation.min.js","classpath:com/zurb/foundation/javascripts/modernizr.foundation.js").map(pathToAsset).toList();
			stylesheetStack = F.flow("classpath:com/zurb/foundation/stylesheets/foundation.css").map(pathToStylesheetLink).toList();
		}
	}

	public List<String> getStacks() {
        return Collections.emptyList();
    }

    public List<Asset> getJavaScriptLibraries() {
        return javaScriptStack;
    }

    public List<StylesheetLink> getStylesheets() {
        return stylesheetStack;
    }

    public String getInitialization() {
        return null;
    }

}
