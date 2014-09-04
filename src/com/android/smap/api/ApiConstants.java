package com.android.smap.api;

/**
 * Interface that contains all constants associated with the API
 * 
 * @author matt witherow
 */
public interface ApiConstants {

	// URLs
	/** Production Endpoint URL */
	public static final String PRODUCTION_URL = "http://rmit.smap.com.au";
	public static final String DEV_URL = "stub-loggr.herokuapp.com";

	// REQUEST SCHEME
	public static final String SCHEME_HTTP = "http";
	public static final String SCHEME_HTTPS = "https";

	// HEADERS
	/** Header value for Content Type */
	public static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
	public static final String HEADER_AUTH_TOKEN = "smap-token";
	

	// API CALLs
	public static final String API_TOKEN = "token";
	public static final String DEFINITION_LIST = "formList";

	// DEFAULTS
	public static final int DEFAULT_TIMEOUT = 20000;
	public static final int DEFAULT_PAGE_SIZE = 10;

}
