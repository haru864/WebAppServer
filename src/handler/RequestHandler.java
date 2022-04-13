package handler;

import java.io.File;

import exception.ContentNotFoundException;
import exception.MethodNotAllowedException;

public class RequestHandler {

    public int STATUS_CODE;
    public String pathName;

    public RequestHandler(HttpRequest httpRequest) throws MethodNotAllowedException, ContentNotFoundException {

        // メソッド検証
        if (MethodList.validateMethod(httpRequest.method) != true) {
            throw new MethodNotAllowedException();
        }

        // パス検証
        File requestContent = new File("./bin/content" + httpRequest.contentPath);
        if (requestContent.exists() != true) {
            throw new ContentNotFoundException();
        }
    }
}
