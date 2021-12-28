# CustomFunctionsPlugin
Portal Custom Functions Plugin Demo

1- Download https://github.com/dahman/CustomFunctionsPlugin/tree/main/plugins/custom-functions-api-4.9.100.0.jar

2- Copy the jar file to your local Maven repository:
mvn install:install-file -DgroupId=com.ca.tdm.customfunctions -DartifactId=custom-functions-api -Dversion=4.9.100.0 -Dpackaging=jar -Dfile=custom-functions-api-4.9.100.0.jar

3- Do `mvn clean install` in your project folder and verify the newly-installed dependency is available and two plugin jar files created.

4- customfunction-plugin1-4.9.100.0.jar includes functionality implemented in class CustomFunction2

`    @Extension`  
`    public static class CustomFunction2 implements CustomFunctionRetriever {`  
  
`        // the custom function used in TDMWeb will be called function1`  
`        @Override`  
`        public String getName() {`  
`            return "function1";`  
`        }`  
  
`        // allows only one parameter`  
`        // once successful input is converted to uppercase padded with **** on both sides`  
`        @Override`  
`        public String getCustomFunctionResult(String... parameters) throws Exception {`  
`            if (parameters.length == 1) {`  
`                return "****" + parameters[0].toUpperCase() + "****";`  
`            }`  
`            throw new Exception("sorry you cannot have more than one parameter from plugin");`  
`        }`  
`    }`  
  
the method `getName()` provides the name of custom meta function. Same name should be added to the included json file (`DataGeneratorCustomFunctions.json`)  
The custom meta function is implemented in method getCustomFunctionResult(String... parameters)  
In this example implementation, if the method includes number of parameters other than one parameter, we throw an exception that will be visible to the user on the UI  
If one parameter is provided then the input string is converted to upper case and extra characters are appended at the start and end;   
for example input `hello` is converted to `****HELLO****`  
  
5- Make sure that the json file DataGeneratorCustomFunctions.json is updated accordingly
If one custom function called function1 is added then DataGeneratorCustomFunctions.json can be formatted as  
`[`  
`  {`  
`    "DataType": "Custom",`  
`    "Functions": [`  
`      {`  
`        "name": "function1(value)",`  
`        "type": "Numeric"`  
`      }`  
`    ]`  
`  }`  
`]`  

6- the json file and plugin jars should be copied to TDM install under `\CA\CA Test Data Manager Portal\tomcat\customfunctions`