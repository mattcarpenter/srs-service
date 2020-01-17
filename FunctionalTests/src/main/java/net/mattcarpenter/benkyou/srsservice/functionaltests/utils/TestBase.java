package net.mattcarpenter.benkyou.srsservice.functionaltests.utils;

import net.mattcarpenter.benkyou.srsservice.functionaltests.constants.TestConstants;
import net.mattcarpenter.benkyou.srsservice.functionaltests.helpers.CardHelper;
import net.mattcarpenter.benkyou.srsservice.functionaltests.helpers.LayoutHelper;
import org.testng.annotations.BeforeClass;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class TestBase {
    private Properties properties;
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String DEFAULT_ENV = "dev";
    private static final String SYSTEM_PROPERTY_ENV = "env";

    public LayoutHelper layoutHelper;
    public CardHelper cardHelper;

    @BeforeClass
    public void before() throws Exception {

        if (properties != null) {
            return;
        }

        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + PROPERTIES_FILE + "' not found in the classpath");
        }

        layoutHelper = new LayoutHelper(getProperty(TestConstants.BASE_URL));
        cardHelper = new CardHelper(getProperty(TestConstants.BASE_URL));
    }

    protected String getProperty(String propertyName) {
        String key = String.format("%s.%s", Optional.ofNullable(
                System.getProperty(SYSTEM_PROPERTY_ENV)).orElse(DEFAULT_ENV), propertyName);
        return properties.getProperty(key);
    }

}
