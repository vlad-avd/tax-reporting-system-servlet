package ua.kpi.util;

import java.util.Map;

public class RequestParametersSetter {
    public String setParameters(Map<String, String> parameters) {
        StringBuilder requestParameters = new StringBuilder("?");

        for(Map.Entry<String, String> parameter : parameters.entrySet()) {
            requestParameters
                    .append("&")
                    .append(parameter.getKey())
                    .append("=")
                    .append(parameter.getValue());
        }

        requestParameters.deleteCharAt(1); // delete first &

        return requestParameters.toString();
    }
}
