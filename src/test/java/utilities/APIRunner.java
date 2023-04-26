package utilities;

import entities.CustomResponse;

public class APIRunner {

    private static CustomResponse customerResponse;
    private static CustomResponse[] responseList;

    public static void runGet(String path){
        String token = Config.getValue("cashwiseToken");
        String url = Config.getValue("cashwiseBackend") + path;

    }
}
