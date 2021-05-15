package bikini.potato.email.service.impl;

import bikini.potato.email.service.PropertiesService;

public class PropertiesServiceImpl implements PropertiesService {

    @Override
    public String getProperty(String key) {
        if("email.disclaimer".equals(key)) {
            return "DISCLAIMER!";
        } else {
            return null;
        }
    }
}
