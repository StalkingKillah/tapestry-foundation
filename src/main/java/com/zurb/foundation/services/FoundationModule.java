/**
 * 
 */
package com.zurb.foundation.services;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.services.Environment;
//import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
//import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;

import com.zurb.foundation.FoundationValidationDecorator;

/**
 * @author nighthawk
 *
 */
public class FoundationModule {
	
	public static void contributeFactoryDefaults(MappedConfiguration<String, Object> configuration) {
		configuration.override(JQuerySymbolConstants.JQUERY_VERSION, "1.8.2");
        configuration.override(JQuerySymbolConstants.JQUERY_CORE_PATH, "classpath:com/zurb/foundation/javascripts/");
    }
		
	public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("foundation", "com.zurb.foundation"));
	}
	
    public static void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration, final Environment environment) {
    	MarkupRendererFilter validationDecorator = new MarkupRendererFilter() {
			public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
				ValidationDecorator decorator = new FoundationValidationDecorator(environment, writer);
				environment.push(ValidationDecorator.class, decorator);
				renderer.renderMarkup(writer);
				environment.pop(ValidationDecorator.class);
			}
		};
		configuration.override("ValidationDecorator", validationDecorator);
    }
	
//	public static void contributeClasspathAssetAliasManager(MappedConfiguration<String, String> configuration) {
//		configuration.add("foundation", "com/zurb/foundation/");
//	}
	
//	public static void contributeJavaScriptStackSource(MappedConfiguration<String, JavaScriptStack> configuration,
//			@Symbol(JQuerySymbolConstants.SUPPRESS_PROTOTYPE) boolean suppressPrototype) {
//		if (suppressPrototype) {
//			configuration.addInstance(FoundationStack.STACK_ID, FoundationStack.class);
//		}
//
//	}
}
