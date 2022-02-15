package main.java.com.ca.tdm.demo.customfunction;

import com.ca.tdm.customfunction.CustomFunctionRetriever;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;

public class Function2Plugin extends Plugin {

    public Function2Plugin(PluginWrapper wrapper) {
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

        // the custom function used in TDMWeb will be called function2
        @Override
        public String getName() {
            return "add10";
        }

        // allows only one parameter
        // that one parameter should not start with mess
        // once successful input is converted to lowercase padded with **** on both sides
        @Override
        public String getCustomFunctionResult(String... parameters) throws Exception {
            if (parameters.length == 1) {
                int number;
                try{
                    number = Integer.parseInt(parameters[0]);
                }
                catch (NumberFormatException ex){
                    throw new Exception("Make sure the input parameter is a numeric number ");
                }

                return String.valueOf(number + 10) ;
            }
            throw new Exception("sorry you cannot have more than one parameter from plugin");
        }
    }
}
