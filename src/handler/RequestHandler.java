package handler;

import java.io.File;

import exception.NotFoundException;
import exception.MethodNotAllowedException;
import parameter.HttpRequest;
import parameter.MethodList;

public class RequestHandler {

    public int STATUS_CODE;
    public String pathName;

    public RequestHandler(HttpRequest httpRequest) throws MethodNotAllowedException, NotFoundException {

        // メソッド検証
        if (!MethodList.validateMethod(httpRequest.method)) {
            throw new MethodNotAllowedException();
        }

        // パス検証
        String path = "./bin/content" + httpRequest.contentPath;
        File requestContent = new File(path);
        if (!requestContent.exists()) {
            throw new NotFoundException();
        }
    }
}
