package bikini.potato.email.service.impl;

import bikini.potato.email.service.PropertiesService;

public class PropertiesServiceImpl implements PropertiesService {

    @Override
    public String getPropertyAsString(String key) {
        if("email.disclaimer".equals(key)) {
            return "DISCLAIMER!";
        } else {
            return null;
        }
    }

    @Override
    public Integer getPropertyAsInteger(String key) {
        if("email.retries".equals(key)) {
            return 3;
        } else {
            return null;
        }
    }
}
