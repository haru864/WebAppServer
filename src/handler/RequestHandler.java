package handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import exception.NotFoundException;
import exception.MethodNotAllowedException;
import parameter.ExtensionList;
import parameter.HttpRequest;
import parameter.MethodList;

public class RequestHandler {

    public int STATUS_CODE;
    public String pathName;
    public String extension;
    public File requestContent;

    public RequestHandler(HttpRequest httpRequest) throws MethodNotAllowedException, NotFoundException {

        // メソッド検証
        if (!MethodList.validateMethod(httpRequest.method)) {
            throw new MethodNotAllowedException();
        }

        // 拡張子検証
        String temp = "";
        this.extension = "";
        int index = httpRequest.contentPath.lastIndexOf(".");
        if (index != -1) {
            this.extension = httpRequest.contentPath.substring(index + 1);
        } else if ((temp = httpRequest.headerMap.get("Accept")) != null
                && ExtensionList.mimeToExtension.get(temp) != null) {
            this.extension = ExtensionList.mimeToExtension.get(temp);
        } else {
            throw new NotFoundException();
        }

        // パス検証
        StringBuffer path = new StringBuffer("./bin/content" + httpRequest.contentPath);
        if (index == -1) {
            path.append("." + this.extension);
        }
        System.out.println(path.toString());
        requestContent = new File(path.toString());
        if (!requestContent.exists()) {
            throw new NotFoundException();
        }
    }
}
