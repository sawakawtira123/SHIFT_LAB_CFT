package ru.kashtyra.alexandr.demo.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UriPath {

    public static final String API_VERSION = "1";

    public static final String API_PATH_PREFIX = "/api/" + API_VERSION;

    public static final String PRODUCT = "/product";

    public static final String PRODUCT_ID = "/{productId}";

    public static final String PRODUCT_TYPE = "/product_type";

}
