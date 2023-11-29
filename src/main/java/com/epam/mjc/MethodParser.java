package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        String[] parts = signatureString.split("[\\(\\)]");

        if (parts.length == 0)
            return null;

        String[] members = parts[0].split("\\s");

        String accessModifier = null;
        String returnType = null;
        String methodName = null;

        List<MethodSignature.Argument> arguments = new ArrayList<>();

        if (members.length == 3){
            accessModifier = members[0];
            returnType = members[1];
            methodName = members[2];
        } else if (members.length == 2) {
            returnType = members[0];
            methodName = members[1];
        }

        if (parts.length > 1){
            arguments =  getArguments(parts[1]);
        }

        MethodSignature methodSignature = new MethodSignature(methodName,arguments);
        methodSignature.setReturnType(returnType);
        if (accessModifier != null)
            methodSignature.setAccessModifier(accessModifier);


        return methodSignature;
    }

    private List<MethodSignature.Argument> getArguments(String argumentsPart){

        List<MethodSignature.Argument> arguments = new ArrayList<>();

        String[] argumentsString = argumentsPart.split(",");

        for (String a: argumentsString) {

            String[] argumentParts = a.trim().split("\\s");

            if (argumentParts.length != 2)
                continue;

            MethodSignature.Argument argument = new MethodSignature.Argument(argumentParts[0],argumentParts[1]);

            arguments.add(argument);
        }
        return arguments;
    }
}
