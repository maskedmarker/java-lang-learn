package org.example.learn.java.io.file.uri;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * At the highest level a URI reference (hereinafter simply "URI") in string form has the syntax
 * [scheme:]scheme-specific-part[#fragment]
 *
 * The URI generic syntax consists of a hierarchical sequence of five components:
 * URI = scheme ":" ["//" authority] path ["?" query] ["#" fragment]
 * authority = [userinfo "@"] host [":" port]
 */
public class UriDemo {

    private URI uri;

    public UriDemo(String uri) throws URISyntaxException {
        this.uri = new URI(uri);
    }

    public void demoScheme() {
        String scheme = uri.getScheme();
        System.out.println("scheme = " + scheme);
    }

    public void demoSchemeSpecificPart() {
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        System.out.println("schemeSpecificPart = " + schemeSpecificPart);
        String rawSchemeSpecificPart = uri.getRawSchemeSpecificPart();
        System.out.println("rawSchemeSpecificPart = " + rawSchemeSpecificPart);
    }

    public void demoPath() {
        String path = this.uri.getPath();
        System.out.println("path = " + path);
    }
}
