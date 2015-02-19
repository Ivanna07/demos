package org.tuxdevelop.spring_boot_demo.service.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@Slf4j
public class AbstractRestClient implements InitializingBean {

    private static final String ENCODING_UTF8 = "UTF-8";

    protected RestTemplate restTemplate;
    protected String hostURL;
    protected HttpHeaders httpHeaders;
    private final String encodedCredentials;
    private final MediaType mediaType;


    protected AbstractRestClient(final RestTemplate restTemplate, final String userName, final String password,
                                 final String hostURL, final MediaType mediaType) {
        this.restTemplate = restTemplate;
        this.encodedCredentials = encodeCredentials(userName, password);
        this.hostURL = hostURL;
        this.mediaType = mediaType;
        createHttpHeaders();

    }

    protected void createHttpHeaders() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + encodedCredentials);
        httpHeaders.setContentType(mediaType);
        log.info("created headers: " + httpHeaders.toString());
    }

    protected void evaluateResponse(final ResponseEntity<?> responseEntity, final HttpMethod httpMethod) {
        final HttpStatus httpStatus = responseEntity.getStatusCode();
        if (HttpMethod.GET.equals(httpMethod)) {
            if (!HttpStatus.OK.equals(httpStatus)) {
                throwResponseException(httpStatus);
            }
        }
        if (HttpMethod.POST.equals(httpMethod)) {
            if (!HttpStatus.CREATED.equals(httpStatus)) {
                throwResponseException(httpStatus);
            }
        }
        if (HttpMethod.PUT.equals(httpMethod)) {
            if (!HttpStatus.OK.equals(httpStatus)) {
                throwResponseException(httpStatus);
            }
        }
        if (HttpMethod.DELETE.equals(httpMethod)) {
            if (!HttpStatus.OK.equals(httpStatus)) {
                throwResponseException(httpStatus);
            }
        }
    }

    private void throwResponseException(final HttpStatus httpStatus) {
        throw new RuntimeException(httpStatus + "received! Request was not successful!");
    }

    private String encodeCredentials(final String username, final String password) {

        if (username == null) {
            throw new IllegalArgumentException("username must not be null !");
        }

        if (password == null) {
            throw new IllegalArgumentException("Password must not be null !");
        }

        final String plainCredentials = username + ":" + password;
        try {
            final byte[] plainCredentialsBytes = plainCredentials.getBytes(ENCODING_UTF8);
            final byte[] base64CredentialsBytes = Base64.encode(plainCredentialsBytes);
            return new String(base64CredentialsBytes, ENCODING_UTF8);
        } catch (final UnsupportedEncodingException e) {
            throw new IllegalStateException("Problem during encoding of Password: " + e.getCause());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        assert restTemplate != null;
    }
}
