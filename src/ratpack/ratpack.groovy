import org.ratpackframework.app.*
import org.ratpackframework.groovy.app.Routing

import uk.co.cacoethes.lazybones.BintrayClient

(this as Routing).with {

    def client = new BintrayClient()

    get('/list') { Request request, Response response ->
        def json = client.list()
        response.text("application/json", json)
        response.end()
    }

    get('/search') { Request request, Response response ->
        def json = client.search(request.queryParams)
        response.text("application/json", json)
        response.end()
    }
}
