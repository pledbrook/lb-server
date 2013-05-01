package uk.co.cacoethes.lazybones

import wslite.http.auth.HTTPBasicAuthorization
import wslite.rest.RESTClient

class BintrayClient {
    def client

    BintrayClient() {
        def env = System.getenv()
        def bintrayUser = env['BINTRAY_USER']
        def bintrayKey = env['BINTRAY_API_KEY']

        assert bintrayUser, "Bintray user name must be specified in \$BINTRAY_USER"
        assert bintrayKey, "Bintray API key must be specified in \$BINTRAY_API_KEY"

        client = new RESTClient('https://api.bintray.com')
        client.authorization = new HTTPBasicAuthorization(bintrayUser, bintrayKey)
    }

    public String list() {
        def resp = client.get(path: '/v1/repos/pledbrook/lazybones-templates/packages')
        resp.json
    }

    public String search(def params) {
        def queryParams = params.collect { it }.join('&')
        def resp = client.get(path: "/v1/search/packages/?repo=lazybones-templates&${queryParams}")
        resp.json
    }
}
