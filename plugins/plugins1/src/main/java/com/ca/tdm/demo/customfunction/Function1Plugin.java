package main.java.com.ca.tdm.demo.customfunction;

import com.ca.tdm.customfunction.CustomFunctionRetriever;
import org.apache.commons.lang3.StringUtils;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;

public class Function1Plugin extends Plugin {

    public Function1Plugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("WelcomePlugin.start()");
        // for testing the development mode
        if (RuntimeMode.DEVELOPMENT.equals(wrapper.getRuntimeMode())) {
            System.out.println(StringUtils.upperCase("WelcomePlugin"));
        }
    }

    @Override
    public void stop() {
        System.out.println("WelcomePlugin.stop()");
    }

    @Extension
    public static class CustomFunction2 implements CustomFunctionRetriever {

        // the custom function used in TDMWeb will be called function1
        @Override
        public String getName() {
            return "function1";
        }

        // allows only one parameter
        // once successful input is converted to uppercase padded with **** on both sides
        @Override
        public String getCustomFunctionResult(String... parameters) throws Exception {
            if (parameters.length == 1) {
                return "****" + parameters[0].toUpperCase() + "****";
            }
            throw new Exception("sorry you cannot have more than one parameter from plugin");
        }
    }
}
